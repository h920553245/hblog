package cn.hergua.hblog.service;

import cn.hergua.hblog.entity.User;
import cn.hergua.hblog.entity.UserInfo;

/**
 * @author Mr.Hergua | HuangYuanQin
 * DATE: 2018/5/7
 * @version : 1.0
 */
public interface UserService {

    User loadUserByUsername(String username);

    UserInfo getUserInfo(Long userId);

    void updateAvatar(String url, Long userId);

    void updatePassword(User user);

    void updateUserInfo(UserInfo userInfo);

    User getCurrentUser();
}
