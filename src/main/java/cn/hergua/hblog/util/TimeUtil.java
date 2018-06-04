package cn.hergua.hblog.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Mr.Hergua | HuangYuanQin
 * DATE: 2018/5/13
 * @version : 1.0
 */
public class TimeUtil {

    public static String format(Date date){
        SimpleDateFormat format = new SimpleDateFormat("Y年M月");
        return format.format(date);
    }

}