package cn.edu.cqupt.nmid.spdt.service;

import cn.edu.cqupt.nmid.spdt.model.Activity;
import cn.edu.cqupt.nmid.spdt.model.json.ResponseJson;

/**
 * Created by Lawrence on 2017/11/5.
 */
public interface ActivityService {
    public ResponseJson getActivityById(int id);

    public ResponseJson getActivities();

    public ResponseJson estabActivity(Activity activity);
}
