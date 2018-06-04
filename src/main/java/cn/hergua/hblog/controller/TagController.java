package cn.hergua.hblog.controller;

import cn.hergua.hblog.entity.vo.ArticleCustom;
import cn.hergua.hblog.entity.vo.Pager;
import cn.hergua.hblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;



@Controller
@RequestMapping("/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    /**
     * 通过tag获取文章列表
     * @param pager 分页对象
     * @param tagId 标签id
     * @param model 对象 数据视图
     * @return
     */
    @RequestMapping("/load/{tagId}")
    public String loadArticleByTag(Pager pager, @PathVariable Integer tagId, Model model){
        List<ArticleCustom> articleList = tagService.loadArticleByTag(pager,tagId);
        if (!articleList.isEmpty()){
            model.addAttribute("articleList",articleList);
            model.addAttribute("pager",pager);
            //2017-05-07修复获取tag名称错误的问题,不应该从articlelist中取,因为每篇文章可能有多个tag
            model.addAttribute("tagName",tagService.getTagById(tagId).getTagName());
        }

        return "blog/part/tagSummary";
    }

}