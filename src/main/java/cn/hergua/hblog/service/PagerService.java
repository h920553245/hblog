package cn.hergua.hblog.service;


import cn.hergua.hblog.entity.vo.Pager;

/**
 * @author Do
 * @package com.eumji.zblog.service
 * @name PagerService
 * @date 2017/4/11
 * @time 21:46
 */
public interface PagerService {
    /**
     * 初始化分页信息
     * @return
     * @param pager 分页对象
     */
    void initPage(Pager pager);

    /**
     * 初始化某个category的分页信息
     * @param pager 分页对象
     * @param categoryId
     */
    void loadCategoryPager(Pager pager, Long categoryId);

    /**
     * 初始化某个tag的分页信息
     * @param pager 分页对象
     * @param tagId
     */
    void loadTagPager(Pager pager, Long tagId);

    /**
     * 初始化时间归档的分页信息
     * @param pager 分页对象
     * @param createTime 创建时间
     */
    void loadArchivePager(Pager pager, String createTime);
}
