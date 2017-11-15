package cn.edu.cqupt.nmid.spdt.controller;

import cn.edu.cqupt.nmid.spdt.model.Activity;
import cn.edu.cqupt.nmid.spdt.model.json.ResponseJson;
import cn.edu.cqupt.nmid.spdt.service.FileService;
import cn.edu.cqupt.nmid.spdt.service.impl.ActivityInfoimpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Lawrence on 2017/11/4.
 */
@Controller
@RequestMapping(value = "/activity")
public class ActivitiesInfoController {

    @Resource
    private ActivityInfoimpl activityInfoimpl;

    @Resource
    private FileService fileService;

    @ResponseBody
    @RequestMapping(value = "/getActivities",method = RequestMethod.GET)
    public ResponseJson getActivities() {
        return activityInfoimpl.getActivities();
    }

    @ResponseBody
    @RequestMapping(value = "/joinActivity",method = RequestMethod.POST)
    public ResponseJson joinActivity(
            @RequestParam("userId") String id,
            @RequestParam("activeId") String activeId
    ) {

        return  null;
    }

    /**
     * 创建活动
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/estabActivity",method = RequestMethod.POST)
    public ResponseJson estabActivity(HttpServletRequest request,
                                      Activity activity) {
        return activityInfoimpl.estabActivity(request,activity);
    }
}
