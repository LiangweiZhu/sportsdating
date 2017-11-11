package cn.edu.cqupt.nmid.spdt.service.impl;

import cn.edu.cqupt.nmid.spdt.constant.StatusCodeConstant;
import cn.edu.cqupt.nmid.spdt.dao.UserServiceDao;
import cn.edu.cqupt.nmid.spdt.model.User;
import cn.edu.cqupt.nmid.spdt.model.json.ResponseJson;
import cn.edu.cqupt.nmid.spdt.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Lawrence on 2017/11/4.
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserServiceDao userServiceDao;

    @Override
    public ResponseJson userLogin(User user) {
        if (userServiceDao.findUserByID(user.getId()) != null) {
            return new ResponseJson(StatusCodeConstant.OK);
        } else {
            if ("success".equals(userServiceDao.saveUser(user))) {
                return new ResponseJson(StatusCodeConstant.OK);
            } else {
                return new ResponseJson(StatusCodeConstant.USER_UNLOGIN);
            }
        }
    }
}
