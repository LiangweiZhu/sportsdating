package cn.edu.cqupt.nmid.spdt.controller;

import cn.edu.cqupt.nmid.spdt.model.User;
import cn.edu.cqupt.nmid.spdt.model.json.ResponseJson;
import cn.edu.cqupt.nmid.spdt.service.UserService;
import cn.edu.cqupt.nmid.spdt.service.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lawrence on 2017/11/4.
 */
@Controller
@RequestMapping(value = "/user")
public class UserInfoController {

    @Resource
    private UserService userService;

    /**
     * 登陆
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/userLogin",method = RequestMethod.POST)
    public ResponseJson uerLogin(User user) {
        return userService.userLogin(user);
    }

    /**
     * 获取改用户所有参加的活动
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getJoinedActivites",method = RequestMethod.GET)
    public ResponseJson getJoinedActivities(@RequestParam("userId") String userId) {
        return userService.getJoinedActivities(userId);
    }

    /**
     * 上传照片
     * @param request
     * @param userId
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/uploadPic",method = RequestMethod.POST)
    public ResponseJson uploadPic(HttpServletRequest request,
                                  @RequestParam("userId") String userId) throws IOException{
        return userService.uploadUserPic(request,userId);
    }
}
