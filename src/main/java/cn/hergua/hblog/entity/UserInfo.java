package cn.hergua.hblog.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Mr.Hergua | HuangYuanQin
 * DATE: 2018/5/6
 * @version : 1.0
 */

@Entity
@Table(name = "tab_userInfo")
public class UserInfo implements Serializable {

    @Id
    @GeneratedValue
    private Long userInfoId;

    @OneToOne(targetEntity = User.class,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "userId")
    private User user;

    private String avatarPath; //图像src

    private String nickname; //昵称

    private String phone; //电话号码

    private String email; //邮箱

    private String signature; //个性签名

    private String address; //地址

    private String announcement; //公告

    private String telegram; //tg

    private String weChat;  //微信

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(Long userInfoId) {
        this.userInfoId = userInfoId;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    public String getTelegram() {
        return telegram;
    }

    public void setTelegram(String telegram) {
        this.telegram = telegram;
    }

    public String getWeChat() {
        return weChat;
    }

    public void setWeChat(String weChat) {
        this.weChat = weChat;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userInfoId=" + userInfoId +
                ", user=" + user +
                ", avatarPath='" + avatarPath + '\'' +
                ", nickname='" + nickname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", signature='" + signature + '\'' +
                ", address='" + address + '\'' +
                ", announcement='" + announcement + '\'' +
                ", telegram='" + telegram + '\'' +
                ", weChat='" + weChat + '\'' +
                '}';
    }
}