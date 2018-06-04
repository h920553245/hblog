package cn.hergua.hblog.service.impl;

import cn.hergua.hblog.entity.Article;
import cn.hergua.hblog.entity.Category;
import cn.hergua.hblog.entity.Tag;
import cn.hergua.hblog.entity.vo.ArticleCustom;
import cn.hergua.hblog.entity.vo.Pager;
import cn.hergua.hblog.repository.ArticleRepository;
import cn.hergua.hblog.repository.CategoryRepository;
import cn.hergua.hblog.repository.TagRepository;
import cn.hergua.hblog.service.ArticleService;
import cn.hergua.hblog.util.PagerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Mr.Hergua | HuangYuanQin
 * DATE: 2018/5/7
 * @version : 1.0
 */

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<ArticleCustom> articleList(Pager pager) {
        List<ArticleCustom> articleCustoms = new ArrayList<>();
        List<Article> articles = articleRepository.getArticles();
        PagerUtil.pagerList(pager,articles);
        articles.forEach(article ->
                articleCustoms.add(new ArticleCustom(article.getId(),article.getCategory().getCategoryId(),
                        article.getCategory().getCategoryName(),article.getCategory().getAliasName(),
                        article.getTitle(),article.getContent(),article.getDescription(),article.getStatus(),
                        article.getAuthor(),article.getCreateTime(),article.getUpdateTime(),article.getShowCount(),
                        article.getArticleTag())));
        return articleCustoms;

    }

    @Override
    public Long getArticleCount() {
        return articleRepository.count();
    }

    @Override
    public void initPager(Pager pager) {
        pager.setTotalPageNum((int)articleRepository.count());
    }

    @Override
    public List<Article> loadArticle(Map<String, Object> param) {

        List<Article> loadArticle = new LinkedList<>();

        Integer categoryId = (Integer) param.get("categoryId");
        String[] tags = (String[]) param.get("tags");
        String title = (String) param.get("title");
        Pager pager = (Pager) param.get("pager");
        List<Article> allArticles = articleRepository.findAll();
        if (categoryId==null && tags==null && title==null)
            return allArticles;
        for (Article article: allArticles) {

            boolean flag = true;
            if (tags!=null && tags.length>0){
                List<Tag> articleTags = article.getArticleTag();
                for (String tagId : tags) {
                    Tag tag = tagRepository.findOne(Long.parseLong(tagId));
                    if (!articleTags.contains(tag)) {
                        flag=false;
                        break;
                    }
                }

            }

            if (categoryId!=null){
                Category category =  categoryRepository.findOne((long)categoryId);
                if (!article.getCategory().equals(category)){
                    flag=false;
                }
            }

            if (title!=null && !title.equals("") && !article.getTitle().toLowerCase().contains(title.toLowerCase())){
                flag=false;
            }

            if (flag){
                loadArticle.add(article);
            }
        }

        PagerUtil.pagerList(pager,loadArticle);
        return loadArticle;
    }

    @Override
    public void updateStatue(Long id, int status) {
        Article article = articleRepository.findOne(id);
        article.setStatus(status);
        articleRepository.save(article);
    }

    @Override
    public void saveArticle(Article article, long[] tags){
        List<Tag> tagList = new LinkedList<>();
        for (int i = 0; i < tags.length; i++) {
            tagList.add(tagRepository.findOne(tags[i]));
        }
        article.setCreateTime(new Date());
        article.setArticleTag(tagList);
        articleRepository.save(article);
    }

    @Override
    public Article getArticleById(Long id) {
        return articleRepository.findOne(id);
    }

    @Override
    public void updateArticle(Article article, long[] tags) {
        List<Tag> tagList =new LinkedList<>();
        for (Long id:tags) {
            tagList.add(tagRepository.findOne(id));
        }
        article.setArticleTag(tagList);
        articleRepository.save(article);
    }

    @Override
    public void deleteArticle(Long id) {
        articleRepository.delete(id);
    }

    @Override
    public ArticleCustom getArticleCustomById(Long articleId) {
        Article article = articleRepository.findOne(articleId);
        return new ArticleCustom(article.getId(),article.getCategory().getCategoryId(),article.getCategory().getCategoryName(),
                article.getCategory().getAliasName(),article.getTitle(),article.getContent(),article.getDescription(),
                article.getStatus(),article.getAuthor(),article.getCreateTime(),article.getUpdateTime(),
                article.getShowCount(),article.getArticleTag());
    }

    @Override
    public Article getLastArticle(long articleId) {
        List<Article> articles = articleRepository.getLastArticle(articleId);
        articles.forEach(article -> System.out.println(article.getId()));
        return articles.isEmpty()?null:articles.get(0);
    }

    @Override
    public Article getNextArticle(long articleId) {
        List<Article> articles = articleRepository.getNextArticle(articleId);
        return articles.isEmpty()?null:articles.get(0);
    }

    @Override
    public void addArticleCount(long articleId) {
        Article article = articleRepository.findOne(articleId);
        article.setShowCount(article.getShowCount()+1);
        articleRepository.save(article);
    }

    @Override
    public List<ArticleCustom> popularArticle() {
        Pageable pageable = new PageRequest(0,10);
        List<Article> articles = articleRepository.getPopularArticle(pageable);
        List<ArticleCustom> customs = new LinkedList<>();
        articles.forEach(article ->
            customs.add(new ArticleCustom(article.getId(),article.getCategory().getCategoryId(),
                    article.getCategory().getCategoryName(),article.getCategory().getAliasName(),
                    article.getTitle(),article.getContent(),article.getDescription(),article.getStatus(),
                    article.getAuthor(),article.getCreateTime(),article.getUpdateTime(),article.getShowCount(),
                    article.getArticleTag()))
        );
        return customs;
    }

    @Override
    public String[] getArticleId() {
        List<Article> articles = articleRepository.findAll();
        List<String> ids = new LinkedList<>();
        articles.forEach(article -> {
            if (article.getStatus().equals(1))
                ids.add(article.getId().toString());
        });
        return ids.toArray(new String[0]);
    }

    @Override
    public List<Article> getArticleListByKeywords(String keyword) {
        if (keyword==null || keyword.equals(""))
            return articleRepository.findAll();
        else {
            Pageable pageable = new PageRequest(0,10);
            return articleRepository.getListByKeyWord(keyword,pageable);
        }

    }

    @Override
    public List<Map> articleArchiveList() {

        List<Map> mapList = new LinkedList<>();
        articleRepository.articleArchiveList().forEach(objects -> {
            Map map = new HashMap();
            map.put("createTime",objects[0]);
            map.put("count",objects[1]);
            mapList.add(map);
        });
        return mapList;
    }

    @Override
    public Article findOne(long id) {
        return articleRepository.findOne(id);
    }
}