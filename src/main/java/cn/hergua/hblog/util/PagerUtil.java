package cn.hergua.hblog.util;

import cn.hergua.hblog.entity.vo.Pager;

import java.util.List;

/**
 * @author Mr.Hergua | HuangYuanQin
 * DATE: 2018/5/12
 * @version : 1.0
 */
public class PagerUtil {

    public static void pagerList(Pager pager, List list){

        if (list.size() > pager.getStart()){
            if (list.size() > pager.getStart()+pager.getLimit()){
                for (int i = pager.getStart()+pager.getLimit(); i <list.size() ; i++) {
                    list.remove(i);
                }
            }
            for (int i = 0; i < pager.getStart(); i++) {
                list.remove(i);
            }
        }
    }

}