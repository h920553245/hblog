package cn.hergua.hblog.entity;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Mr.Hergua | HuangYuanQin
 * DATE: 2018/5/6
 * @version : 1.0
 */

@Entity
@Table(name = "tab_partner")
public class Partner implements Serializable {

    @Id
    @GeneratedValue
    private Long partnerId;

    private String siteName;    //友链名称

    private String siteUrl; //友链URL

    private String siteDesc; //友链描述

    private Integer sort;  //排序

    @Override
    public String toString() {
        return "Partner{" +
                "partnerId=" + partnerId +
                ", siteName='" + siteName + '\'' +
                ", siteUrl='" + siteUrl + '\'' +
                ", siteDesc='" + siteDesc + '\'' +
                ", sort=" + sort +
                '}';
    }

    public Long getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Long partnerId) {
        this.partnerId = partnerId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public String getSiteDesc() {
        return siteDesc;
    }

    public void setSiteDesc(String siteDesc) {
        this.siteDesc = siteDesc;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}