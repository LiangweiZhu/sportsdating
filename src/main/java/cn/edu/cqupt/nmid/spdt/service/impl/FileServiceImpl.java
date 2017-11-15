package cn.edu.cqupt.nmid.spdt.service.impl;

import cn.edu.cqupt.nmid.spdt.constant.StatusCodeConstant;
import cn.edu.cqupt.nmid.spdt.dao.ActivityInfoDao;
import cn.edu.cqupt.nmid.spdt.dao.DynamicsServiceDao;
import cn.edu.cqupt.nmid.spdt.model.json.ResponseJson;
import cn.edu.cqupt.nmid.spdt.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Iterator;

/**
 * Created by Lawrence on 2017/11/7.
 */
@Service
public class FileServiceImpl implements FileService {

    @Resource
    private ActivityInfoDao activityInfoDao;
    @Resource
    private DynamicsServiceDao dynamicsServiceDao;

    /**
     * 上传活动图片
     * 暂未完成：若已有图片则删除原来图片，即一个活动一张图片
     * @param request
     * @param id
     * @return
     * @throws IOException
     */
    @Override
    public String upLoadPic(HttpServletRequest request,String property,int id) throws IOException {
        //将当前上下文初始化给CommonsMultipartResolver（多部分解析器）
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //判断request是否有文件上传
        if (multipartResolver.isMultipart(request)) {
            //将request变成多部分request
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            //获取multipartRequest中所有文件名
            Iterator iter = multipartHttpServletRequest.getFileNames();
            java.lang.String newFilePath="";
            while(iter.hasNext()){
                MultipartFile file = multipartHttpServletRequest.getFile(iter.next().toString());
                if (!file.isEmpty()){
                    //判断文件夹是否存在，不存在则创建
                    String basePath = request.getSession().getServletContext().getRealPath("/")+"WEB-INF"+
                            File.separator+"resources"+File.separator+property+File.separator+id+File.separator;
                    System.out.println(basePath);
                    File filePath = new File(basePath);
                    if (!filePath.exists()) {
                        filePath.mkdirs();
                    }
                    newFilePath = System.currentTimeMillis()+ file.getOriginalFilename();
                    String path = basePath+newFilePath;
                    java.io.File file1 = new java.io.File(path);
                    file.transferTo(file1);
                    if (!"success".equals(toSavePic(property,id,newFilePath))){
                        return "";
                    }
                }else {
                    return "";
                }
            }
            return newFilePath;
        } else {
            return "";
        }
    }

    private String toSavePic(String property,int id,String filePath) {
        if ("activities".equals(property)) {
            return activityInfoDao.savePicture(filePath,id);
        } else if ("DynamicNews".equals(property)) {
            return dynamicsServiceDao.saveDynamicPic(filePath,id);
        } else {
            return "fail";
        }
    }
}
