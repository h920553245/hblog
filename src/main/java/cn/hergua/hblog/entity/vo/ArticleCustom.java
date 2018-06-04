package cn.hergua.hblog.entity.vo;

import cn.hergua.hblog.entity.Tag;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Mr.Hergua | HuangYuanQin
 * DATE: 2018/5/6
 * @version : 1.0
 */
public class ArticleCustom implements Serializable {


    public Long id;
    public Long categoryId; //分类id
    public String categoryName; //分类名称
    public String categoryAliasName; //分类别名
    public String title;   //标题
    public String content;  //内容
    public String description; //描述
    public Integer status;  //状态
    public String author; //作者
    public Date createTime;    //创建时间
    public Date updateTime;    //更新时间
    public Integer showCount;  //浏览数
    public List<Tag> tagList;   //标签列表

    public ArticleCustom(Long id, Long categoryId, String categoryName, String categoryAliasName, String title, String content, String description, Integer status, String author, Date createTime, Date updateTime, Integer showCount, List<Tag> tagList) {
        this.id = id;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryAliasName = categoryAliasName;
        this.title = title;
        this.content = content;
        this.description = description;
        this.status = status;
        this.author = author;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.showCount = showCount;
        this.tagList = tagList;
    }

    public ArticleCustom() {
    }

    @Override
    public String toString() {
        return "ArticleCustom{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", categoryAliasName='" + categoryAliasName + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", author='" + author + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", showCount=" + showCount +
                ", tagList=" + tagList +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryAliasName() {
        return categoryAliasName;
    }

    public void setCategoryAliasName(String categoryAliasName) {
        this.categoryAliasName = categoryAliasName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getShowCount() {
        return showCount;
    }

    public void setShowCount(Integer showCount) {
        this.showCount = showCount;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }
}