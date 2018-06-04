package cn.hergua.hblog.controller.admin;

import cn.hergua.hblog.constant.ProjectConstant;
import cn.hergua.hblog.entity.User;
import cn.hergua.hblog.entity.UserInfo;
import cn.hergua.hblog.repository.UserInfoRepository;
import cn.hergua.hblog.service.UserService;
import cn.hergua.hblog.util.Md5Util;
import cn.hergua.hblog.util.ResultInfo;
import cn.hergua.hblog.util.ResultInfoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserInfoRepository userInfoRepository;

    /**
     * 已经通过spring security去操作
     * @param user
     * @param session
     * @return
     */
    @RequestMapping(value = "/login/auth",method = RequestMethod.POST)
    public ResultInfo loginAuth(User user, HttpSession session){
        ResultInfo resultInfo = null;
        User userInfo = userService.loadUserByUsername(user.getUsername());
        if (userInfo==null){
            resultInfo =  ResultInfoFactory.getErrorResultInfo("账号不存在");
        }else{
            if (userInfo.getPassword().equals(Md5Util.pwdDigest(user.getPassword()))){
                resultInfo = ResultInfoFactory.getSuccessResultInfo();
            }else {
                resultInfo = ResultInfoFactory.getErrorResultInfo("账号或密码错误");
            }
        session.setAttribute("user",userInfo);
        }

        return resultInfo;

    }

    /**
     * 修改密码
     * @param oldPassword
     * @param newPassword
     * @param request
     * @return
     */
    @RequestMapping("/admin/password/update")
    @ResponseBody
    public ResultInfo updatePassword(String oldPassword, String newPassword, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        System.out.println(user);
        if (!Md5Util.pwdDigest(oldPassword).equals(user.getPassword())){
            return ResultInfoFactory.getErrorResultInfo("原密码错误！！！");
        }
        user.setPassword(Md5Util.pwdDigest(newPassword));
        userService.updatePassword(user);
        return ResultInfoFactory.getSuccessResultInfo("修改成功！！！");
    }

    /**
     * 获取用户信息
     * @param model 对象
     * @return
     */
    @RequestMapping("/admin/userinfo/get")
    public String getUserInfo(Model model){
        UserInfo userInfo = userService.getUserInfo(9999L);
        model.addAttribute(ProjectConstant.USERINFO,userInfo);
        return "admin/partial/userinfo";

    }

    @RequestMapping("/admin/userinfo/update")
    @ResponseBody
    public ResultInfo updateInfo(UserInfo userInfo){
        UserInfo old = userInfoRepository.findOne(userInfo.getUserInfoId());
        userInfo.setUser(old.getUser());
        userInfo.setAvatarPath(old.getAvatarPath());
        userInfo.setWeChat(old.getWeChat());
        userService.updateUserInfo(userInfo);
        return ResultInfoFactory.getSuccessResultInfo("更新个人信息成功!!");
    }

    @RequestMapping("/admin/loginOut")
    public String loginOut() {
        return "login";
    }

}


