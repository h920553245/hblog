package cn.hergua.hblog.service.impl;

import cn.hergua.hblog.entity.Article;
import cn.hergua.hblog.entity.Tag;
import cn.hergua.hblog.entity.vo.ArticleCustom;
import cn.hergua.hblog.entity.vo.Pager;
import cn.hergua.hblog.repository.ArticleRepository;
import cn.hergua.hblog.repository.TagRepository;
import cn.hergua.hblog.service.TagService;
import cn.hergua.hblog.util.PagerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Mr.Hergua | HuangYuanQin
 * DATE: 2018/5/13
 * @version : 1.0
 */

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public List<ArticleCustom> loadArticleByTag(Pager pager, long tagId) {
        List<Article> articles = articleRepository.findByArticleTagOrderByCreateTimeDesc(tagRepository.findOne(tagId));
        List<ArticleCustom> customs = new LinkedList<>();
        articles.forEach(article ->
                customs.add(new ArticleCustom(article.getId(),article.getCategory().getCategoryId(),
                        article.getCategory().getCategoryName(),article.getCategory().getAliasName(),
                        article.getTitle(),article.getContent(),article.getDescription(),article.getStatus(),
                        article.getAuthor(),article.getCreateTime(),article.getUpdateTime(),article.getShowCount(),
                        article.getArticleTag()))
        );
        PagerUtil.pagerList(pager,customs);
        return customs;
    }

    @Override
    public int getTagCount() {
        return (int) tagRepository.count();
    }

    @Override
    public Tag getTagById(long id) {
        return tagRepository.findOne(id);
    }

    @Override
    public List<Tag> loadTagList(Pager pager, String tagName) {
        List<Tag> tagList = tagRepository.loadTagList(tagName);
        PagerUtil.pagerList(pager,tagList);
        return tagList;
    }

    @Override
    public void saveTag(Tag tag) {
        tagRepository.save(tag);
    }

    @Override
    public boolean checkExist(Tag tag) {
        return !tagRepository.checkExist(tag.getTagName(),tag.getAliasName(),tag.getTagId()).isEmpty();
    }

    @Override
    public void updateTag(Tag tag) {
        tagRepository.save(tag);
    }

    @Override
    public void initPage(Pager pager) {
        pager.setTotalCount((int) tagRepository.count());
    }

    @Override
    public List<Tag> getTagList() {
        return tagRepository.findAll();
    }

    @Override
    public void articleTagPage(Pager pager, long tagId) {
        pager.setTotalCount(articleRepository.findByArticleTagOrderByCreateTimeDesc(
                tagRepository.findOne(tagId)).size());
    }

    @Override
    public void deleteTagById(long id) {
        tagRepository.delete(id);
    }

    @Override
    public long tagByName(String tagName, String aliasName){
        return tagRepository.findByTagNameAndAliasName(tagName,aliasName).getTagId();
    }
}