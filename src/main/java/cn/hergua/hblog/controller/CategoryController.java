package cn.hergua.hblog.controller;

import cn.hergua.hblog.entity.vo.ArticleCustom;
import cn.hergua.hblog.entity.vo.Pager;
import cn.hergua.hblog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    /**
     * 获取某个标签的分页文章
     * @param model 对象
     * @param pager 分页对象
     * @param categoryId
     * @return
     */
    @RequestMapping("/load/{categoryId}")
    public String loadArticleByCategory(Model model, Pager pager, @PathVariable long categoryId){
        List<ArticleCustom> articleList = categoryService.loadArticleByCategory(pager,categoryId);
        if (!articleList.isEmpty()){
            model.addAttribute("articleList",articleList);
            model.addAttribute("pager",pager);
            model.addAttribute("categoryName",articleList.get(0).getCategoryName());
        }
        return "blog/part/categorySummary";
    }



}