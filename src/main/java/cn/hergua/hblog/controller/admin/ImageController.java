package cn.hergua.hblog.controller.admin;

import cn.hergua.hblog.entity.User;
import cn.hergua.hblog.entity.vo.PhotoResult;
import cn.hergua.hblog.service.UserService;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 上传图片的controller
 * Created by eumji on 17-5-31.
 */
@RestController
public class ImageController {

    private Logger logger = LoggerFactory.getLogger(AdminArticleController.class);


    @Autowired
    private UserService userService;

    @RequestMapping("/admin/article/imageUpload")
    public PhotoResult imageUpload(@RequestParam(value = "editormd-image-file",required = true) MultipartFile file){
        PhotoResult result = new PhotoResult();
        try {
            if (file.getSize()>=10048576)
                throw new IOException("文件不能超过10M");

            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            if(!path.exists()) path = new File("");
            File upload = new File(path.getAbsolutePath(),"static/uploadImages/");
            if(!upload.exists()) upload.mkdirs();


            File localFile = new File(upload.getAbsolutePath()+File.separator+file.getOriginalFilename());
            if (!localFile.exists())
                if (!localFile.createNewFile())
                    throw new IOException("文件创建失败");
            file.transferTo(localFile);
            result.setSuccess(1);
            result.setUrl("http://www.hergua.club/uploadImages/"+file.getOriginalFilename());
            return result;
        } catch (IOException e) {
           logger.error("方法上传文件出错,错误信息:{}"+e.getMessage());
           return new PhotoResult(0,"","IO异常上传失败！！！");
        }
    }

    /**
     * 头像修改
     * @param request 获取session的request
     * @param avatarSrc 图片路径
     * @param avatarData 图片裁剪的内容
     * @param file 图片
     * @return
     */
    @RequestMapping("/admin/avatar/update")
    public PhotoResult updateAvatar(HttpServletRequest request, String avatarSrc, @RequestParam(value = "avatar_data") String avatarData, @RequestParam(value = "avatar_file",required = true) MultipartFile file){
        PhotoResult result = new PhotoResult();
        String type = file.getContentType();
        if(type==null || !type.toLowerCase().startsWith("image/")) {
            return new PhotoResult(0,"","不支持的文件类型，仅支持图片！");
        }
        try {
            File localFile = new File(ResourceUtils.getURL("classpath:static/image/").getPath()+file.getOriginalFilename());
            if (!localFile.exists())
                if (!localFile.createNewFile())
                    throw new IOException("文件上传失败");
            file.transferTo(localFile);
            JSONObject object = (JSONObject) JSONObject.parse(avatarData);
            //上传图片
            file.transferTo(localFile);
            result.setSuccess(1);
            result.setUrl("/image/"+file.getOriginalFilename());
            User user = (User) request.getSession().getAttribute("user");
            userService.updateAvatar(result.getUrl(),user.getUserId());
            result.setMessage("修改图像成功！！！");

        }catch (IOException e){
           logger.error(e.getMessage());
            return new PhotoResult(0,"","上传图片发生异常！");
        }
        return result;
    }
}
