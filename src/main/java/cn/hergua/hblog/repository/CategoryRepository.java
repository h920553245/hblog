package cn.hergua.hblog.repository;

import cn.hergua.hblog.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mr.Hergua | HuangYuanQin
 * DATE: 2018/5/7
 * @version : 1.0
 */

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    @Query(" from Category c where c.aliasName like %:categoryName% or c.categoryName like %:categoryName%")
    List<Category> loadCategory(@Param("categoryName") String categoryName);



}