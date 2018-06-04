package cn.hergua.hblog.service.impl;

import cn.hergua.hblog.entity.User;
import cn.hergua.hblog.entity.UserInfo;
import cn.hergua.hblog.repository.UserInfoRepository;
import cn.hergua.hblog.repository.UserRepository;
import cn.hergua.hblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Mr.Hergua | HuangYuanQin
 * DATE: 2018/5/7
 * @version : 1.0
 */

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public User loadUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public UserInfo getUserInfo(Long userId) {
        return userInfoRepository.findByUser(userRepository.findOne(userId));
    }

    @Override
    public void updateAvatar(String url, Long userId) {
        UserInfo userInfo = userInfoRepository.findByUser(userRepository.findOne(userId));
        userInfo.setAvatarPath(url);
        userInfoRepository.save(userInfo);
    }

    @Override
    public void updatePassword(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateUserInfo(UserInfo userInfo) {
        userInfoRepository.save(userInfo);
    }

    @Override
    public User getCurrentUser() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        return (User) session.getAttribute("user");
    }
}