package cn.edu.cqupt.nmid.spdt.service.impl;

import cn.edu.cqupt.nmid.spdt.constant.StatusCodeConstant;
import cn.edu.cqupt.nmid.spdt.dao.ActivityInfoDao;
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

    /**
     * 上传活动图片
     * 暂未完成：若已有图片则删除原来图片，即一个活动一张图片
     * @param request
     * @param activeId
     * @return
     * @throws IOException
     */
    @Override
    public ResponseJson upLoadPic(HttpServletRequest request,int activeId) throws IOException {
        //将当前上下文初始化给CommonsMultipartResolver（多部分解析器）
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //判断request是否有文件上传
        if (multipartResolver.isMultipart(request)) {
            //将request变成多部分request
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            //获取multipartRequest中所有文件名
            Iterator iter = multipartHttpServletRequest.getFileNames();
            java.lang.String newFilePath="";
            System.out.println("one::"+iter.hasNext());
            while(iter.hasNext()){
                MultipartFile file = multipartHttpServletRequest.getFile(iter.next().toString());
                System.out.println("one::"+file.isEmpty());
                if (!file.isEmpty()){
                    //判断文件夹是否存在，不存在则创建
                    String basePath = request.getSession().getServletContext().getRealPath("/")+"WEB-INF"+
                            File.separator+"resources"+File.separator+"activities"+File.separator+activeId+File.separator;
                    System.out.println(basePath);
                    File filePath = new File(basePath);
                    if (!filePath.exists()) {
                        filePath.mkdirs();
                    }
                    newFilePath = System.currentTimeMillis()+ file.getOriginalFilename();
                    String path = basePath+newFilePath;
                    java.io.File file1 = new java.io.File(path);
                    file.transferTo(file1);
                    activityInfoDao.savePicture(newFilePath,activeId);
                }else {
                    return new ResponseJson(StatusCodeConstant.INTERNAL_SERVER_ERROR);
                }
            }
            ResponseJson response = new ResponseJson(StatusCodeConstant.OK);
            response.setBody(newFilePath);
            return response;
        } else {
            return new ResponseJson(StatusCodeConstant.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 以流的形式接收客户端上传的活动图片，并返回存储在数据库中的图片地址
     * @param file
     * @param activeId
     * @param request
     * @return
     * @throws IOException
     */
    public ResponseJson uploadActPicByStream (CommonsMultipartFile file, int activeId,HttpServletRequest request) throws IOException {
        String basePath = request.getSession().getServletContext().getRealPath("/")+"WEB-INF"+File.separator
                +"resources"+File.separator+"activities"+File.separator+activeId+File.separator;
        File fileBasePath = new File(basePath);
        if (!fileBasePath.exists()) {
            fileBasePath.mkdirs();
        }
        String newPath = System.currentTimeMillis()+file.getOriginalFilename();
        try {
            OutputStream outputStream = new FileOutputStream(basePath+newPath);
            InputStream inputStream = file.getInputStream();
            byte[] buf = new byte[1024];
            while (inputStream.read(buf)!=-1) {
                outputStream.write(buf);
                outputStream.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        activityInfoDao.savePicture(newPath,activeId);
        ResponseJson responseJson = new ResponseJson(StatusCodeConstant.OK);
        responseJson.setBody(newPath);
        return responseJson;
    }

    /**
     * 废弃，原因：上传到Linux之后无法完整回传，原因未明。
     * 以流的形式向客户端传送图片 26ms
     * @param request
     * @param response
     * @param activePic
     * @param activeId
     * @throws IOException
     */
    public void getActivePic(HttpServletRequest request, HttpServletResponse response,
                       String activePic,int activeId) throws IOException {
        if ("".equals(activePic)) {
            return;
        }
//        Long startTime = System.currentTimeMillis();
        String filePath = request.getSession().getServletContext().getRealPath("/")+"WEB-INF"+File.separator+
                "resources"+File.separator+"actives"+File.separator+activeId+activePic;
        File targetFile = new File(filePath);
        if (targetFile.exists() && targetFile.canRead()) {
            FileInputStream inputStream = new FileInputStream(targetFile);
            byte[] data = new byte[(int)targetFile.length()];
            int length = inputStream.read(data);
            response.setContentType("image/png");

            OutputStream stream = response.getOutputStream();
            stream.write(data);
            stream.flush();
            stream.close();
//            Long endTime = System.currentTimeMillis();
//            System.out.println(endTime-startTime);
        }
    }
}
