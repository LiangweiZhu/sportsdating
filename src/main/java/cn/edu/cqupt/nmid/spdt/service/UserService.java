package cn.edu.cqupt.nmid.spdt.service;

import cn.edu.cqupt.nmid.spdt.model.User;
import cn.edu.cqupt.nmid.spdt.model.json.ResponseJson;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Lawrence on 2017/11/4.
 */
public interface UserService {
    public ResponseJson userLogin(User user);
}
