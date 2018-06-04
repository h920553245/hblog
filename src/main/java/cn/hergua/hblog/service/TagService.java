package cn.hergua.hblog.service;


import cn.hergua.hblog.entity.Tag;
import cn.hergua.hblog.entity.vo.ArticleCustom;
import cn.hergua.hblog.entity.vo.Pager;

import java.util.List;


public interface TagService {
    /**
     * 获取当前tag下的文章列表
     * @param pager 分页对象
     * @param tagId
     * @return
     */
    List<ArticleCustom> loadArticleByTag(Pager pager, long tagId);

    int getTagCount();

    Tag getTagById(long id);

    List<Tag> loadTagList(Pager pager, String tagName);

    void saveTag(Tag tag);

    boolean checkExist(Tag tag);

    void updateTag(Tag tag);

    void initPage(Pager pager);

    List<Tag> getTagList();

    /**
     * 初始化分页
     * @param pager 分页对象
     * @param tagId
     */
    void articleTagPage(Pager pager, long tagId);

    /**
     * 根据id删除标签
     * @param id
     */
    void deleteTagById(long id);

    long tagByName(String tagName,String aliasName);
}
