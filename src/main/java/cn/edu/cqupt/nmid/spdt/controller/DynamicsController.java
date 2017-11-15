package cn.edu.cqupt.nmid.spdt.controller;

import cn.edu.cqupt.nmid.spdt.model.DynamicNews;
import cn.edu.cqupt.nmid.spdt.model.json.ResponseJson;
import cn.edu.cqupt.nmid.spdt.service.impl.DynamicsServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Lawrence on 2017/11/11.
 */
@Controller
@RequestMapping("/dynamics")
public class DynamicsController {

    @Resource
    private DynamicsServiceImpl dynamicsService;

    @ResponseBody
    @RequestMapping(value = "/estab",method = RequestMethod.POST)
    public ResponseJson estabDynamic(HttpServletRequest request, DynamicNews dynamicNews) throws IOException {
        return dynamicsService.estabDynamic(request,dynamicNews);
    }

    @ResponseBody
    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    public ResponseJson getAllDynamicNews() {
        return dynamicsService.getAllDynamicNews();
    }
}
