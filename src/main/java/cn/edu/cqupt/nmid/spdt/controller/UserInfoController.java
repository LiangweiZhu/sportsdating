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
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lawrence on 2017/11/4.
 */
@Controller
@RequestMapping(value = "/user")
public class UserInfoController {

    @Resource
    private UserServiceImpl userServiceImpl;

    @ResponseBody
    @RequestMapping(value = "/userLogin",method = RequestMethod.POST)
    public ResponseJson uerLogin(User user) {
        return userServiceImpl.userLogin(user);
    }
}
