package cn.edu.cqupt.nmid.spdt.service;

import cn.edu.cqupt.nmid.spdt.model.Activity;
import cn.edu.cqupt.nmid.spdt.model.json.ResponseJson;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Lawrence on 2017/11/5.
 */
public interface ActivityService {
    public ResponseJson getActivityById(int id);

    public ResponseJson getActivities();

    public ResponseJson estabActivity(HttpServletRequest request/*, CommonsMultipartFile file*/, Activity activity) throws IOException;

    public ResponseJson joinActivity(String userId,int activeId);
}
