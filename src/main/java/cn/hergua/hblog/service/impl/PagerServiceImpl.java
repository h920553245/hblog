package cn.hergua.hblog.service.impl;

import cn.hergua.hblog.entity.Article;
import cn.hergua.hblog.entity.vo.Pager;
import cn.hergua.hblog.repository.ArticleRepository;
import cn.hergua.hblog.repository.CategoryRepository;
import cn.hergua.hblog.repository.TagRepository;
import cn.hergua.hblog.service.PagerService;
import cn.hergua.hblog.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Mr.Hergua | HuangYuanQin
 * DATE: 2018/5/12
 * @version : 1.0
 */
@Service
public class PagerServiceImpl implements PagerService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TagRepository tagRepository;

    @Override
    public void initPage(Pager pager) {
        pager.setTotalCount((int) articleRepository.count());
    }

    @Override
    public void loadCategoryPager(Pager pager, Long categoryId) {
        long count = articleRepository.countByCategoryAndStatus(
                categoryRepository.findOne(categoryId),1);
        pager.setTotalCount((int)count);
    }

    @Override
    public void loadTagPager(Pager pager, Long tagId) {
        long count = articleRepository.countByArticleTagAndStatus(
                tagRepository.findOne(tagId),1);
        pager.setTotalCount((int) count);
    }

    @Override
    public void loadArchivePager(Pager pager, String createTime) {
        List<Article> articles = articleRepository.getArticles();
        AtomicInteger count = new AtomicInteger(articles.size());
        articles.forEach(article -> {
            if (!TimeUtil.format(article.getCreateTime()).equals(createTime))
                count.getAndDecrement();
        });
        pager.setTotalCount(count.get());
    }
}