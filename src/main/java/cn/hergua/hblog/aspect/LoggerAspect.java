package cn.hergua.hblog.aspect;

import cn.hergua.hblog.entity.LogInfo;
import cn.hergua.hblog.entity.User;
import cn.hergua.hblog.repository.LoggerRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;

/**
 * @author Mr.Hergua | HuangYuanQin
 * DATE: 2018/6/10
 * @version : 1.0
 */

@Aspect
@Component
public class LoggerAspect {

    @Autowired
    private LoggerRepository loggerRepository;

    private Logger logger = LoggerFactory.getLogger(LoggerAspect.class);

    @Pointcut("execution(* cn.hergua.hblog.controller.*.*(..))")
    public void exceptionLog(){}


    @AfterThrowing(pointcut = "exceptionLog()",throwing = "e")
    public void afterThrowing(JoinPoint joinPoint,Throwable e){
        LogInfo log = new LogInfo();
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request = requestAttributes.getRequest();
            User user = (User) request.getSession().getAttribute("user");
            if (user!=null) {
                log.setUserId(user.getUserId());
            }
        }
        HttpServletRequest request = requestAttributes.getRequest();
        log.setIp(request.getRemoteAddr());
        log.setMethod(request.getMethod());
        log.setUrl(request.getRequestURL().toString());
        log.setArgs(Arrays.toString(joinPoint.getArgs()));
        log.setClassMethod(joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
        log.setException(e.getMessage());
        log.setOperateTime(new Date());
        logger.info(log.toString());
        loggerRepository.save(log);
    }


}