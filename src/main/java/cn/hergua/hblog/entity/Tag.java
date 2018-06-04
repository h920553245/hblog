package cn.hergua.hblog.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Mr.Hergua | HuangYuanQin
 * DATE: 2018/5/6
 * @version : 1.0
 */

@Entity
@Table(name = "tab_tag")
public class Tag implements Serializable {


    @Id
    @GeneratedValue
    private Long tagId;

    @Column(unique = true)
    private String tagName; //标签名

    @Column(unique = true)
    private String aliasName;  //别名

    @Override
    public String toString() {
        return "Tag{" +
                "tagId=" + tagId +
                ", tagName='" + tagName +
                ", aliasName='" + aliasName +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(tagId, tag.tagId) &&
                Objects.equals(tagName, tag.tagName) &&
                Objects.equals(aliasName, tag.aliasName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(tagId, tagName, aliasName);
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }
}