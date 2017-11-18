package cn.edu.cqupt.nmid.spdt.service.impl;

import cn.edu.cqupt.nmid.spdt.constant.StatusCodeConstant;
import cn.edu.cqupt.nmid.spdt.dao.UserServiceDao;
import cn.edu.cqupt.nmid.spdt.model.User;
import cn.edu.cqupt.nmid.spdt.model.json.ResponseJson;
import cn.edu.cqupt.nmid.spdt.service.FileService;
import cn.edu.cqupt.nmid.spdt.service.UserService;
import cn.edu.cqupt.nmid.spdt.util.DaoResponseUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Lawrence on 2017/11/4.
 */
@Service
//@Transactional(value = "transactionManager", isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Resource
    private UserServiceDao userServiceDao;
    @Resource
    private FileService fileService;

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

    @Override
    public ResponseJson getJoinedActivities(String userId) {
        Map<Integer,String> joinedActivities = userServiceDao.getJoinedActivities(userId);
        return DaoResponseUtil.isNull(joinedActivities);
    }

    @Override
    public ResponseJson uploadUserPic(HttpServletRequest request, String userId) throws IOException {
        String filePath = fileService.upLoadPic(request,"user",new Integer(userId));
        if (userServiceDao.updatePic(filePath,userId).equals("success")) {
            ResponseJson responseJson = new ResponseJson(StatusCodeConstant.OK);
            responseJson.setBody(filePath);
            return responseJson;
        }
        else {
            return new ResponseJson(StatusCodeConstant.INTERNAL_SERVER_ERROR);
        }
    }

}
