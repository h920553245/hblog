package cn.hergua.hblog.entity.vo;

import java.io.Serializable;

/**
 * @author Mr.Hergua | HuangYuanQin
 * DATE: 2018/5/6
 * @version : 1.0
 */
public class CategoryCustom implements Serializable {

    public long id;

    public String categoryName; //分类名

    public String aliasName;   //分类别名

    public Integer sort;

    public int articleCount; //此分类下文章的数量

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CategoryCustom{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", aliasName='" + aliasName + '\'' +
                ", sort=" + sort +
                ", articleCount=" + articleCount +
                '}';
    }

    public CategoryCustom(long id, String categoryName, String aliasName, Integer sort, long articleCount) {
        this.id = id;
        this.categoryName = categoryName;
        this.aliasName = aliasName;
        this.sort = sort;
        this.articleCount = (int)articleCount;
    }

}