package cn.hergua.hblog.service;


import cn.hergua.hblog.entity.Partner;
import cn.hergua.hblog.entity.vo.Pager;

import java.util.List;

/**
* Created by GeneratorFx on 2017-04-10.
*/
public interface PartnerService {

    List<Partner> findAll();

    void savePartner(Partner partner);

    /**
     * 分页查询好友列表
     * @param pager 分页对象
     * @param param
     * @return
     */
    List<Partner> loadPartner(Pager pager, String param);

    Partner getPartnerById(long id);

    void deletePartner(long id);

    void updatePartner(Partner partner);

    void initPage(Pager pager);
}
