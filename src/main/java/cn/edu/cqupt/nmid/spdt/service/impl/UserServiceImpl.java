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
        User oldUser = userServiceDao.findUserByID(user.getId());
        if (oldUser != null && user.getPassword().equals(oldUser.getPassword())) {
            ResponseJson responseJson = new ResponseJson(StatusCodeConstant.OK);
            responseJson.setBody(oldUser);
            return responseJson;
        } else {
            //默认新注册用户昵称为实名，以后可开放修改接口
            if (user.getNickname()==null || user.getNickname().equals("")) {
                user.setNickname(user.getUserName());
            }
            if ("success".equals(userServiceDao.saveUser(user))) {
                ResponseJson responseJson = new ResponseJson(StatusCodeConstant.OK);
                responseJson.setBody(user);
                return responseJson;
            } else {
                return new ResponseJson(StatusCodeConstant.USER_UNLOGIN);
            }
        }
    }
}
