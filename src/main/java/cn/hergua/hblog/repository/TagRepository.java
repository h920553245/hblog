package cn.hergua.hblog.repository;

import cn.hergua.hblog.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Mr.Hergua | HuangYuanQin
 * DATE: 2018/5/7
 * @version : 1.0
 */

@Repository
@Transactional
public interface TagRepository extends JpaRepository<Tag,Long>{

    @Query("from Tag t where t.aliasName like %:name% or t.tagName like %:name%")
    List<Tag> loadTagList(@Param("name") String name);

    @Query("from Tag t where t.tagId<>:tagId and t.tagName=:tagName or t.aliasName=:aliasName ")
    List<Tag> checkExist(@Param("tagName") String tagName,
                   @Param("aliasName") String aliasName,
                   @Param("tagId") long tagId);

    Tag findByTagNameAndAliasName(String tagName,String aliasName);

}