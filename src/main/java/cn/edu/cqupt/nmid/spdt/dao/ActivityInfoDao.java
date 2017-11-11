package cn.edu.cqupt.nmid.spdt.dao;

import cn.edu.cqupt.nmid.spdt.constant.DaoConstant;
import cn.edu.cqupt.nmid.spdt.model.Activity;
import com.sdicons.json.validator.impl.predicates.Str;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Lawrence on 2017/11/4.
 */
@Repository(value = "activityInfo")
public class ActivityInfoDao implements DaoConstant{

    @Resource
    private JdbcTemplate jdbcTemplate;

    public Activity getActivityByinitTime(Long initTime) {
        String sql = "SELECT * FROM activities WHERE init_Time=?";
        return jdbcTemplate.queryForObject(sql,new ActivityRowMapper(),initTime);
    }

    public Activity getActivityById(int activeId) {
        String sql="SELECT * FROM activities WHERE active_id=?";
        return jdbcTemplate.queryForObject(sql,new ActivityRowMapper(),activeId);
    }

    /**
     * 获取全部活动信息按开始时间降序排列排列
     * @return
     */
    public List<Activity> getActivities() {
        String sql = "SELECT * FROM activities ORDER BY start_time DESC";
        return jdbcTemplate.query(sql,new ActivityRowMapper());
    }

    /**
     * 创建活动，保存活动信息
     * @param activity
     * @return
     */
    public String saveActivity(Activity activity) {
        String sql="INSERT INTO activities (active_name,initiator,content,remarks,init_time,start_time,end_time," +
                "location,people_needs,ac_ra) VALUES (?,?,?,?,?,?,?,?,?,?)";
        int resluts = jdbcTemplate.update(sql,activity.getActiveName(),activity.getInitiator(),
                activity.getActiveIntroduction(),activity.getRemarks(),activity.getInitTime(),activity.getStartTime()
                ,activity.getEndTime(),activity.getLocation(),activity.getPeopleNeeds(),activity.getActiveOrRace());
        if (1 == resluts) {return DaoConstant.SUCCESS;}
        else {return DaoConstant.FAIL;}
    }

    public String savePicture(String activePic,int activeId) {
        String sql="UPDATE activities SET active_pic=? WHERE active_id=?";
        int num = jdbcTemplate.update(sql,activePic,activeId);
        if (1==num) {
            return DaoConstant.SUCCESS;
        } else {
            return DaoConstant.FAIL;
        }
    }

    /**
     * 更新参加活动的运动人数
     * @param number
     * @param activeId
     * @return
     */
    public String updateActivityPeople(int number,String activeId) {
        String sql = "UPDATE activities SET people_have=? WHERE active_id=?";
        if (1==jdbcTemplate.update(sql,number,activeId)) {
            return SUCCESS;
        } else {return FAIL;}
    }


    private class ActivityRowMapper implements RowMapper<Activity> {

        @Override
        public Activity mapRow(ResultSet resultSet, int i) throws SQLException {
            Activity activity = new Activity();
            activity.setActiveId(resultSet.getInt("active_id"));
            activity.setActiveName(resultSet.getString("active_name"));
            activity.setInitiator(resultSet.getInt("initiator"));
            activity.setActiveIntroduction(resultSet.getString("content"));
            activity.setRemarks(resultSet.getString("remarks"));
            activity.setInitTime(resultSet.getLong("init_time"));
            activity.setStartTime(resultSet.getLong("start_time"));
            activity.setEndTime(resultSet.getLong("end_time"));
            activity.setLocation(resultSet.getString("location"));
            activity.setPeopleNeeds(resultSet.getInt("people_needs"));
            activity.setPeopleHave(resultSet.getInt("people_have"));
            activity.setActivePic(resultSet.getString("active_pic"));
            activity.setActiveOrRace(resultSet.getString("ac_ra"));
            activity.setClickNumber(resultSet.getInt("click_num"));
            return activity;
        }
    }
}
