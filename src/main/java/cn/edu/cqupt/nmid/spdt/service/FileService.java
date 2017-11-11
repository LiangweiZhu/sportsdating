package cn.edu.cqupt.nmid.spdt.service;

import cn.edu.cqupt.nmid.spdt.model.json.ResponseJson;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * Created by Lawrence on 2017/11/7.
 */
public interface FileService {

//    public final String fileBasePath = "F:"+ File.separator+"new";

    public ResponseJson upLoadPic(HttpServletRequest request, int activeId) throws IOException;
}
