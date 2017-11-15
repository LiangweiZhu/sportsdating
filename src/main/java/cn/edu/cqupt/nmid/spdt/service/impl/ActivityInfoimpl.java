package cn.edu.cqupt.nmid.spdt.service.impl;

import cn.edu.cqupt.nmid.spdt.constant.StatusCodeConstant;
import cn.edu.cqupt.nmid.spdt.dao.ActivityInfoDao;
import cn.edu.cqupt.nmid.spdt.model.Activity;
import cn.edu.cqupt.nmid.spdt.model.json.ResponseJson;
import cn.edu.cqupt.nmid.spdt.service.ActivityService;
import cn.edu.cqupt.nmid.spdt.service.FileService;
import cn.edu.cqupt.nmid.spdt.util.DaoResponseUtil;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by Lawrence on 2017/11/4.
 */
@Service
public class ActivityInfoimpl implements ActivityService {


    @Resource
    private ActivityInfoDao activityInfoDao;

    @Resource
    private FileService fileService;
    /**
     * 根据活动ID返回活动信息
     * @param activityId
     * @return
     */
    @Override
    public ResponseJson getActivityById(int activityId) {
        Activity activity = activityInfoDao.getActivityById(activityId);
        return DaoResponseUtil.isNull(activity);
    }

    /**
     * 返回所有活动
     * @return
     */
    @Override
    public ResponseJson getActivities() {
        List<Activity> activities = activityInfoDao.getActivities();
        return DaoResponseUtil.isNull(activities);
    }

    /**
     * 创建活动
     * @param activity
     * @return
     */
    @Override
    public ResponseJson estabActivity(HttpServletRequest request, Activity activity) throws IOException {
        Activity newActivity = activityInfoDao.saveActivity(activity);
        newActivity.setActivityPic(fileService.upLoadPic(request,"activities",activity.getActivityId()));
        return DaoResponseUtil.isNull(newActivity);
    }

    /**
     * 参加活动
     * @return
     */
    @Override
    public ResponseJson joinActivity(String userId,int activityId) {
        Activity activity = activityInfoDao.getActivityById(activityId);
        if (activity!=null) {
            return new ResponseJson(StatusCodeConstant.INVALID_REQUEST);
        }
        if (activity.getPeopleHave() < activity.getPeopleNeeds()) {
            if ("success".equals(activityInfoDao.updateActivityPeople(activity.getPeopleHave()+1,activityId))) {
                return new ResponseJson(StatusCodeConstant.OK);
            }
        }
        return new ResponseJson(StatusCodeConstant.FORBIDDEN);
    }
}
