package cn.edu.cqupt.nmid.spdt.service;

import cn.edu.cqupt.nmid.spdt.model.json.ResponseJson;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * Created by Lawrence on 2017/11/7.
 */
public interface FileService {

//    public final String fileBasePath = "F:"+ File.separator+"new";

    public String upLoadPic(HttpServletRequest request,String property, int activityId) throws IOException;

    public String uploadByBufStream(HttpServletRequest request, String property, CommonsMultipartFile file, int id);
}
