package cn.edu.cqupt.nmid.spdt.service.impl;

import cn.edu.cqupt.nmid.spdt.constant.StatusCodeConstant;
import cn.edu.cqupt.nmid.spdt.dao.ActivityInfoDao;
import cn.edu.cqupt.nmid.spdt.model.Activity;
import cn.edu.cqupt.nmid.spdt.model.json.ResponseJson;
import cn.edu.cqupt.nmid.spdt.service.ActivityService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Lawrence on 2017/11/4.
 */
@Service
public class ActivityInfoimpl implements ActivityService {

    @Resource
    private ActivityInfoDao activityInfoDao;

    /**
     * 根据活动ID返回活动信息
     * @param id
     * @return
     */
    @Override
    public ResponseJson getActivityById(int id) {
        Activity activity = activityInfoDao.getActivityById(id);
        if (activity != null) {
            ResponseJson responseJson = new ResponseJson(StatusCodeConstant.OK);
            responseJson.setBody(activity);
            return responseJson;
        }
        System.out.println("--NULL");
        return null;
    }

    @Override
    public ResponseJson getActivities() {
        List<Activity> activities = activityInfoDao.getActivities();
        if (activities!=null) {
            ResponseJson response = new ResponseJson(StatusCodeConstant.OK);
            response.setBody(activities);
            return response;
        } else {
            return new ResponseJson(StatusCodeConstant.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseJson estabActivity(Activity activity) {
        if ("success".equals(activityInfoDao.saveActivity(activity))) {
            Activity theActivity = activityInfoDao.getActivityByinitTime(activity.getInitTime());
            ResponseJson response = new ResponseJson(StatusCodeConstant.OK);
            response.setBody(theActivity);
            return response;
        } else {return new ResponseJson(StatusCodeConstant.INTERNAL_SERVER_ERROR);}
    }


}
