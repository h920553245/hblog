package cn.hergua.hblog.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @version : 1.0
 * @author Hergua | Mr.hergua
 * DATE : 2018/5/5
 */
@Entity
@Table(name = "tab_article")
public class Article implements Serializable ,Comparable<Article>{

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoryId")
    private Category category;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Tag> articleTag;

    private String title;   //标题

    @Lob
    private String content;  //内容

    private String description; //描述

    private Integer status;  //状态

    private String author; //作者

    private Date createTime;    //创建时间

    private Date updateTime;    //更新时间

    private Integer showCount;  //浏览数

    @Override
    public int compareTo(Article o) {
        return this.getCreateTime().before(o.getCreateTime())?1:-1;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", category=" + category +
                ", articleTag=" + articleTag +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", author='" + author + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", showCount=" + showCount +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Tag> getArticleTag() {
        return articleTag;
    }

    public void setArticleTag(List<Tag> articleTag) {
        this.articleTag = articleTag;
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
}