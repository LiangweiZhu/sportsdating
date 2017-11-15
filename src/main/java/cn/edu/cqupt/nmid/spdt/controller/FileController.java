package cn.edu.cqupt.nmid.spdt.controller;

import cn.edu.cqupt.nmid.spdt.constant.StatusCodeConstant;
import cn.edu.cqupt.nmid.spdt.model.json.ResponseJson;
import cn.edu.cqupt.nmid.spdt.service.FileService;
import cn.edu.cqupt.nmid.spdt.service.impl.FileServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by Lawrence on 2017/11/7.
 */
@Controller
@RequestMapping(value = "/pic")
public class FileController {


    @Resource
    private FileService fileService;

    /**
     * 上传活动图片
     * @param request
     * @param activityId 活动ID
     * @return
     * @throws IOException
     */
/*    @ResponseBody
    @RequestMapping(value = "/uploadActivityPic",method = RequestMethod.POST)
    public ResponseJson doUpload(HttpServletRequest request,@RequestParam("activityId") int activityId) throws IOException {
        return fileService.upLoadPic(request,"activities",activityId);
    }

    @ResponseBody
    @RequestMapping(value = "/uploadDynamicsPic",method = RequestMethod.POST)
    public ResponseJson uploadDynamicsPic(HttpServletRequest request, int dynamicId) throws IOException {
        return fileService.upLoadPic(request,"DynamicNews",dynamicId);
    }*/

}
