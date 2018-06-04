package cn.hergua.hblog.service.impl;

import cn.hergua.hblog.entity.Article;
import cn.hergua.hblog.entity.Category;
import cn.hergua.hblog.entity.vo.ArticleCustom;
import cn.hergua.hblog.entity.vo.CategoryCustom;
import cn.hergua.hblog.entity.vo.Pager;
import cn.hergua.hblog.repository.ArticleRepository;
import cn.hergua.hblog.repository.CategoryRepository;
import cn.hergua.hblog.service.CategoryService;
import cn.hergua.hblog.util.PagerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Mr.Hergua | HuangYuanQin
 * DATE: 2018/5/12
 * @version : 1.0
 */

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public List<ArticleCustom> loadArticleByCategory(Pager pager, Long categoryId) {
        pager.getStart();
        List<ArticleCustom> customs = new LinkedList<>();
        articleRepository.loadArticleByCategory(categoryRepository.findOne(categoryId)).forEach(article ->
            customs.add(new ArticleCustom(article.getId(),article.getCategory().getCategoryId(),article.getCategory().getCategoryName(),
                    article.getCategory().getAliasName(),article.getTitle(),article.getContent(),article.getDescription(),
                    article.getStatus(),article.getAuthor(),article.getCreateTime(),article.getUpdateTime(),article.getShowCount(),
                    article.getArticleTag()))
        );
        PagerUtil.pagerList(pager,customs);
        return customs;
    }

    @Override
    public List<CategoryCustom> initCategoryList() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryCustom> categoryCustoms = new LinkedList<>();
        categories.forEach(category ->
            categoryCustoms.add(new CategoryCustom(category.getCategoryId(),
                    category.getCategoryName(),category.getAliasName(),
                    category.getSort(),articleRepository.countByCategory(category)))
        );
        return categoryCustoms;
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findOne(id);
    }

    @Override
    public List<Category> loadCategory(Pager pager, String categoryName) {
        pager.setStart(pager.getStart());
        List<Category> categories;
        if (categoryName==null || categoryName.equals("")){
            categories = categoryRepository.findAll();
        }else {
            categories = categoryRepository.loadCategory(categoryName);
        }
        PagerUtil.pagerList(pager,categories);
        return categories;
    }

    @Override
    public boolean checkExist(Category category) {
        return categoryRepository.exists(Example.of(category));
    }

    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void updateCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void initPage(Pager pager) {
        pager.setTotalCount((int) categoryRepository.count());
    }

    @Override
    public List<Category> getCategoryList() {
        return categoryRepository.findAll();
    }

    @Override
    public List<ArticleCustom> loadArticleByArchive(String createTime, Pager pager) {
        pager.getStart();
        List<Article> articles = articleRepository.findAll();
        List<ArticleCustom> customs = new LinkedList<>();
        SimpleDateFormat format = new SimpleDateFormat("Y年MM月");
        articles.forEach(article -> {
            if (createTime.equals(format.format(article.getCreateTime())))
                customs.add(new ArticleCustom(article.getId(),article.getCategory().getCategoryId(),
                        article.getCategory().getCategoryName(),article.getCategory().getAliasName(),
                        article.getTitle(),article.getContent(),article.getDescription(),article.getStatus(),
                        article.getAuthor(),article.getCreateTime(),article.getUpdateTime(),
                        article.getShowCount(), article.getArticleTag()));
        });
        PagerUtil.pagerList(pager,customs);
        return customs;

    }

    @Override
    public long getArticleCountByCategoryId(Long categoryId) {
        return articleRepository.countByCategory(categoryRepository.findOne(categoryId));
    }

    @Override
    public boolean deleteCategoryById(Long categoryId) {
        categoryRepository.delete(categoryId);
        return true;
    }

}