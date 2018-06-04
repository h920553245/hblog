package cn.hergua.hblog.repository;

import cn.hergua.hblog.entity.Article;
import cn.hergua.hblog.entity.Category;
import cn.hergua.hblog.entity.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mr.Hergua | HuangYuanQin
 * DATE: 2018/5/6
 * @version : 1.0
 */

@Repository
public interface ArticleRepository extends JpaRepository<Article,Long> {


    @Override
    @Query("select art from Article art order by art.createTime desc")
    List<Article> findAll();

    @Override
    @Query(value = "select count(*) from tab_article where status = 1",nativeQuery = true)
    long count();


    List<Article> findByArticleTagOrderByCreateTimeDesc(Tag tag);

    @Query("from Article art where art.status=1 and " +
            "art.category=:category order by art.createTime ")
    List<Article> loadArticleByCategory(@Param("category") Category category);


    @Query("from Article art where art.createTime < (select createTime from Article a  where a.id = :articleId ) order by art.createTime desc")
    List<Article> getLastArticle(@Param("articleId") Long articleId);

    @Query("from Article art where art.createTime > (select createTime from Article a where a.id = :articleId ) order by art.createTime ")
    List<Article> getNextArticle(@Param("articleId") Long articleId);

    @Query("from Article art order by showCount desc ")
    List<Article> getPopularArticle(Pageable pageable);

    @Query("from Article art where art.title like %:keyWord% or art.title like %:keyWord% or art.description like %:keyWord% ")
    List<Article> getListByKeyWord(@Param("keyWord") String keyWord,Pageable pageable);

    long countByCategory(Category category);

    long countByCategoryAndStatus(Category category,int status);

    long countByArticleTagAndStatus(Tag tag,int status);

    @Query("from Article a where a.status=1 order by a.createTime desc")
    List<Article> getArticles();

    @Query(value = "select DATE_FORMAT(create_time,'%Y年%m月') createTime,count(id) count from tab_article  group by DATE_FORMAT(create_time,'%Y年%m月') ORDER BY createTime desc",nativeQuery = true)
    List<Object[]> articleArchiveList();


}