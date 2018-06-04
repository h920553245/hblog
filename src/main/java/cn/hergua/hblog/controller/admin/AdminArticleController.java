package cn.hergua.hblog.controller.admin;


import cn.hergua.hblog.entity.Article;
import cn.hergua.hblog.entity.Category;
import cn.hergua.hblog.entity.Tag;
import cn.hergua.hblog.entity.User;
import cn.hergua.hblog.entity.vo.Pager;
import cn.hergua.hblog.service.ArticleService;
import cn.hergua.hblog.service.CategoryService;
import cn.hergua.hblog.service.TagService;
import cn.hergua.hblog.service.UserService;
import cn.hergua.hblog.util.ResultInfo;
import cn.hergua.hblog.util.ResultInfoFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/admin/article")
public class AdminArticleController {
    private Logger log = LoggerFactory.getLogger(AdminArticleController.class);
    //文章service
    @Autowired
    private ArticleService articleService;

    //标签service
    @Autowired
    private TagService tagService;

    //分类service
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;
    /**
     * 初始化文章分页信息
     * @param pager 分页对象
     * @return
     */
    @RequestMapping("/initPage")
    @ResponseBody
    public Pager initPage(Pager pager) {
        articleService.initPager(pager);
        return pager;
    }

    /**
     * 跳转到添加页面
     * @return
     */
    @RequestMapping("/addPage")
    public String addPage() {
        return "admin/article/articleAdd";
    }

    /**
     * 初始化文章列表
     * @param pager 分页对象 分页对象
     * @param categoryId 搜索条件 分类id
     * @param tagIds 搜索条件 tag集合
     * @param title 搜索条件 文章标题
     * @param model 对象
     * @return
     */
    @RequestMapping("/load")
    public String loadArticle(Pager pager, Integer categoryId, String tagIds, String title, Model model) {
        /**
         * 设置start位置
         */
        pager.setStart(pager.getStart());
        //封装查询条件
        Map<String, Object> param = new HashMap<>();
        if (tagIds != null && !"".equals(tagIds)) {
            param.put("tags", tagIds.split(","));
        }else {
            param.put("tags", null);
        }
        param.put("categoryId", categoryId);
        param.put("title",title);
        param.put("pager", pager);
        //获取文章列表
        List<Article> articleList = articleService.loadArticle(param);
        model.addAttribute("articleList", articleList);
        return "admin/article/articleTable";
    }

    /**
     * 更新文章可用状态
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("/updateStatue")
    @ResponseBody
    public ResultInfo updateStatue(long id, int status) {
        try {

            articleService.updateStatue(id, status);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResultInfoFactory.getErrorResultInfo("更新状态失败,请稍后再尝试");
        }
        return ResultInfoFactory.getSuccessResultInfo();
    }

    /**
     * 获取条件,所有tag和category
     * @param model 对象
     * @return
     */
    @RequestMapping("/term")
    public String articleTerm(Model model) {
        List<Tag> tagList = tagService.getTagList();
        List<Category> categoryList = categoryService.getCategoryList();
        model.addAttribute("tagList", tagList);
        model.addAttribute("categoryList", categoryList);
        return "admin/article/articleInfo";
    }

    /**
     * 保存文章
     * @param article
     * @param tags
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public ResultInfo saveArticle(long categoryId,Article article, long[] tags){
        try {
            //解码文章内容防止出现部分特殊字符串被转义
            Category category = categoryService.getCategoryById(categoryId);
            article.setCategory(category);
            article.setDescription(URLDecoder.decode(article.getDescription(),"UTF-8"));
            article.setTitle(URLDecoder.decode(article.getTitle(),"UTF-8"));
            article.setContent(URLDecoder.decode(article.getContent(),"UTF-8"));
            article.setStatus(1);
            article.setAuthor(userService.getCurrentUser().getUsername());
            article.setCreateTime(new Date());
            article.setUpdateTime(new Date());
            article.setShowCount(0);
            articleService.saveArticle(article, tags);
        }catch (Exception e){
            log.error(e.getMessage());
            return ResultInfoFactory.getErrorResultInfo("添加失败,请稍后再尝试");
        }
        return ResultInfoFactory.getSuccessResultInfo();

    }

    /**
     * 跳转到编辑页面
     * @param id
     * @param model 对象
     * @return
     */
    @RequestMapping("/editJump")
    public String updatePage(Long id,Model model){
        Article article = articleService.getArticleById(id);
        model.addAttribute("article",article);
        return"admin/article/articleEdit";
    }

    /**
     * 获取更新文章信息
     * @param articleId 文章标题 用于获取文章信息
     * @param model 对象
     * @return
     */
    @RequestMapping("/updateInfo")
    public String updateInfo(Long articleId,Model model){
        Article article = articleService.getArticleById(articleId);
        List<Category> categoryList = categoryService.getCategoryList();
        List<Tag> tagList = tagService.getTagList();
        model.addAttribute("article",article);
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("tagList",tagList);
        return "admin/article/articleEditInfo";
    }

    /**
     * 更新文章
     * @param article
     * @param tags
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public ResultInfo updateArticle(long categoryId,Article article,long[] tags){
        try {
            //解码文章内容防止出现部分特殊字符串被转义

            Category category = categoryService.getCategoryById(categoryId);
            Article oldArticle = articleService.findOne(article.getId());
            article.setCategory(category);
            article.setDescription(URLDecoder.decode(article.getDescription(),"UTF-8"));
            article.setTitle(URLDecoder.decode(article.getTitle(),"UTF-8"));
            article.setContent(URLDecoder.decode(article.getContent(),"UTF-8"));
            article.setStatus(oldArticle.getStatus());
            article.setAuthor(oldArticle.getAuthor());
            article.setCreateTime(oldArticle.getCreateTime());
            article.setUpdateTime(new Date());
            article.setShowCount(oldArticle.getShowCount());
            articleService.updateArticle(article,tags);
        }catch (Exception e){
            log.error(e.getMessage());
            ResultInfoFactory.getErrorResultInfo("修改失败,请稍后再试!");
        }
        return ResultInfoFactory.getSuccessResultInfo();
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public ResultInfo deleteArticle(@PathVariable Long id){
        try {

            articleService.deleteArticle(id);
        }catch (Exception e){
            log.error(e.getMessage());
            return ResultInfoFactory.getErrorResultInfo("删除失败!");
        }
        return ResultInfoFactory.getSuccessResultInfo();
    }


}