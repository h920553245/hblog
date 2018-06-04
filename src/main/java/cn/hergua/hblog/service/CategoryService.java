package cn.hergua.hblog.service;



import cn.hergua.hblog.entity.Category;
import cn.hergua.hblog.entity.vo.ArticleCustom;
import cn.hergua.hblog.entity.vo.CategoryCustom;
import cn.hergua.hblog.entity.vo.Pager;

import java.util.List;

/**
* Created by GeneratorFx on 2017-04-11.
*/
public interface CategoryService {



    List<ArticleCustom> loadArticleByCategory(Pager pager, Long categoryId);

    /**
     * 初始化分类信息
     * @return
     */
    List<CategoryCustom> initCategoryList();

    Category getCategoryById(Long id);

    List<Category> loadCategory(Pager pager, String categoryName);

    boolean checkExist(Category category);

    void saveCategory(Category category);

    void updateCategory(Category category);

    void initPage(Pager pager);

    List<Category> getCategoryList();


    List<ArticleCustom> loadArticleByArchive(String createTime, Pager pager);

    long getArticleCountByCategoryId(Long categoryId);

    boolean deleteCategoryById(Long categoryId);
}
