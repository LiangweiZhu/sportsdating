package cn.edu.cqupt.nmid.spdt.controller;

import cn.edu.cqupt.nmid.spdt.constant.StatusCodeConstant;
import cn.edu.cqupt.nmid.spdt.model.json.ResponseJson;
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
    private FileServiceImpl fileServiceImpl;

    /**
     * 上传活动图片
     * @param request
     * @param activeId 活动ID
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/uploadActivityPic",method = RequestMethod.POST)
    public ResponseJson doUpload(HttpServletRequest request,@RequestParam("activeId") int activeId) throws IOException {
        return fileServiceImpl.upLoadPic(request,activeId);
    }

    //不用
    @ResponseBody
    @RequestMapping(value = "/uploadOneActivityPic",method = RequestMethod.POST)
    public ResponseJson Upload(@RequestParam("activityPic") CommonsMultipartFile file,
                                 @RequestParam("activeId") int activeId,
                                 HttpServletRequest request) throws IOException {
        return fileServiceImpl.uploadActPicByStream(file,activeId,request);
    }

    /**
     * 获取活动图片
     * @param request
     * @param response
     * @param activePic 数据库中的图片地址
     * @param activeId
     */
    @ResponseBody
    @RequestMapping(value = "/getActivePic",method = RequestMethod.POST)
    public ResponseJson getActivePic(HttpServletRequest request, HttpServletResponse response,
                       @RequestParam("activePic") String activePic,
                       @RequestParam("activeId")int activeId) {
        try {
            fileServiceImpl.getActivePic(request,response,activePic,activeId);
        } catch (IOException e) {
            return new ResponseJson(StatusCodeConstant.INTERNAL_SERVER_ERROR);
        }
        return null;
    }


    //不用
    @ResponseBody
    @RequestMapping(value = "/uploadByBufStream", method = RequestMethod.POST)
    public ResponseJson uploadByBufStream(@RequestParam("file") CommonsMultipartFile file){
        long startTime = System.currentTimeMillis();
        try {
            OutputStream os = new FileOutputStream("F:\\new\\"+ System.currentTimeMillis()+ file.getOriginalFilename());
            InputStream is = file.getInputStream();
            byte[] buf = new byte[1024];
            while(is.read(buf) != -1){
                os.write(buf);
                os.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
        return new ResponseJson(StatusCodeConstant.OK);
    }

}
