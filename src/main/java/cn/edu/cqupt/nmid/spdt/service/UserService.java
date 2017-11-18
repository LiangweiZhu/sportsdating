package cn.edu.cqupt.nmid.spdt.service;

import cn.edu.cqupt.nmid.spdt.model.User;
import cn.edu.cqupt.nmid.spdt.model.json.ResponseJson;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Lawrence on 2017/11/4.
 */
public interface UserService {
    public ResponseJson userLogin(User user);

    public ResponseJson getJoinedActivities(String userId);

    public ResponseJson uploadUserPic(HttpServletRequest request, String userId) throws IOException;
}
