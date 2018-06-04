package cn.hergua.hblog.controller.admin;


import cn.hergua.hblog.constant.ProjectConstant;
import cn.hergua.hblog.entity.Partner;
import cn.hergua.hblog.entity.vo.Pager;
import cn.hergua.hblog.service.PartnerService;
import cn.hergua.hblog.util.ResultInfo;
import cn.hergua.hblog.util.ResultInfoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;


@RestController
@RequestMapping("/admin/partner")
public class AdminPartnerController {
    @Autowired
    private PartnerService partnerService;

    @RequestMapping("/initPage")
    public Pager initPage(Pager pager){
        partnerService.initPage(pager);
        return pager;
    }

    /**
     * 保存友链
     * @param partner
     * @return
     */
    @RequestMapping("/save")
    public ResultInfo savePartner(Partner partner){
        try {
            partner.setSiteName(URLDecoder.decode(partner.getSiteName(), ProjectConstant.CHARSET_UTF8));
            partner.setSiteDesc(URLDecoder.decode(partner.getSiteDesc(), ProjectConstant.CHARSET_UTF8));
            partner.setSiteUrl(URLDecoder.decode(partner.getSiteUrl(), ProjectConstant.CHARSET_UTF8));
            partnerService.savePartner(partner);
        }catch (Exception e){

            return ResultInfoFactory.getErrorResultInfo();
        }
       return ResultInfoFactory.getSuccessResultInfo();

    }

    /**
     * 更新友链
     * @param partner
     * @return
     */
    @RequestMapping("/update")
    public ResultInfo updatePartner(Partner partner) {
        try {
            partner.setSiteName(URLDecoder.decode(partner.getSiteName(), ProjectConstant.CHARSET_UTF8));
            partner.setSiteDesc(URLDecoder.decode(partner.getSiteDesc(), ProjectConstant.CHARSET_UTF8));
            partner.setSiteUrl(URLDecoder.decode(partner.getSiteUrl(), ProjectConstant.CHARSET_UTF8));
            if (partner.getSort()==null){
                partner.setSort(0);
            }
            partnerService.updatePartner(partner);
        } catch (Exception e) {
            return ResultInfoFactory.getErrorResultInfo();
        }
       return ResultInfoFactory.getSuccessResultInfo();

    }

    /**
     * 删除友链
     * @param id
     * @return
     */
    @RequestMapping("/delete/{id}")
    public ResultInfo deletePartner(@PathVariable Integer id){
        try {
            partnerService.deletePartner(id);
        }catch (Exception e){
            return ResultInfoFactory.getErrorResultInfo();
        }
        return ResultInfoFactory.getSuccessResultInfo();
    }
}
