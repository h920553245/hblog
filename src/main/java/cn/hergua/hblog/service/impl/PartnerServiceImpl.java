package cn.hergua.hblog.service.impl;

import cn.hergua.hblog.entity.Partner;
import cn.hergua.hblog.entity.vo.Pager;
import cn.hergua.hblog.repository.PartnerRepository;
import cn.hergua.hblog.service.PartnerService;
import cn.hergua.hblog.util.PagerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mr.Hergua | HuangYuanQin
 * DATE: 2018/5/13
 * @version : 1.0
 */

@Service
public class PartnerServiceImpl implements PartnerService {

    @Autowired
    private PartnerRepository partnerRepository;

    @Override
    public List<Partner> findAll() {
        return partnerRepository.findAll();
    }

    @Override
    public void savePartner(Partner partner) {
        partnerRepository.save(partner);
    }

    @Override
    public List<Partner> loadPartner(Pager pager, String name) {
        List<Partner> partners = partnerRepository.loadPartner(name);
        PagerUtil.pagerList(pager,partners);
        return partners;
    }

    @Override
    public Partner getPartnerById(long id) {
        return partnerRepository.findOne(id);
    }

    @Override
    public void deletePartner(long id) {
        partnerRepository.delete(id);
    }

    @Override
    public void updatePartner(Partner partner) {
        partnerRepository.save(partner);
    }

    @Override
    public void initPage(Pager pager) {
        pager.setTotalCount((int) partnerRepository.count());
    }
}