package cn.hergua.hblog.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Mr.Hergua | HuangYuanQin
 * DATE: 2018/5/6
 * @version : 1.0
 */

@Entity
@Table(name = "tab_log")
public class LogInfo implements Serializable {

    @Id
    @GeneratedValue
    private Long logId;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "userId")
    private Long userId;

    private String url;  //请求的url

    private String ip;  //请求的ip

    private String method;  //http请求的方法

    private String args;  //请求方法的参数

    private String classMethod;  //对应的类方法

    private String exception;  //异常信息

    private Date operateTime;  //操作时间

    @Override
    public String toString() {
        return "LogInfo{" +
                "logId=" + logId +
                ", userId='" + userId + '\'' +
                ", url='" + url + '\'' +
                ", ip='" + ip + '\'' +
                ", method='" + method + '\'' +
                ", args='" + args + '\'' +
                ", classMethod='" + classMethod + '\'' +
                ", exception='" + exception + '\'' +
                ", operateTime=" + operateTime +
                '}';
    }

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }

    public String getClassMethod() {
        return classMethod;
    }

    public void setClassMethod(String classMethod) {
        this.classMethod = classMethod;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }
}