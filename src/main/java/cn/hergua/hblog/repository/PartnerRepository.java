package cn.hergua.hblog.repository;

import cn.hergua.hblog.entity.Partner;
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
public interface PartnerRepository extends JpaRepository<Partner,Long> {


    @Query("from Partner p where p.siteName like %:name%")
    List<Partner> loadPartner(@Param("name") String name);

}