package cn.hergua.hblog.repository;

import cn.hergua.hblog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Mr.Hergua | HuangYuanQin
 * DATE: 2018/5/7
 * @version : 1.0
 */

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User getUserByUsername(String username);

}