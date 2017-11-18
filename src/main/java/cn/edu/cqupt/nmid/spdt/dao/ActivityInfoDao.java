package cn.edu.cqupt.nmid.spdt.dao;

import cn.edu.cqupt.nmid.spdt.constant.DaoConstant;
import cn.edu.cqupt.nmid.spdt.model.Activity;
import com.sdicons.json.validator.impl.predicates.Str;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.*;
import java.util.List;

/**
 * Created by Lawrence on 2017/11/4.
 */
@Repository(value = "activityInfo")
public class ActivityInfoDao implements DaoConstant{

    @Resource
    private JdbcTemplate jdbcTemplate;

    public Activity getActivityById(int activityId) {
        String sql="SELECT * FROM activities WHERE activity_id=?";
        return jdbcTemplate.queryForObject(sql,new ActivityRowMapper(),activityId);
    }

    /**
     * 获取全部活动信息按开始时间降序排列排列
     * @return
     */
    public List<Activity> getActivities() {
        String sql = "SELECT * FROM activities WHERE end_time > ? ORDER BY people_have DESC, people_needs DESC ";
        return jdbcTemplate.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement pS = connection.prepareStatement(sql);
                pS.setLong(1,System.currentTimeMillis());
                return pS;
            }
        },new ActivityRowMapper());
    }

    /**
     * 创建活动，保存活动信息
     * @param activity
     * @return
     */
    public Activity saveActivity(Activity activity) {
        String sql="INSERT INTO activities (activity_name,initiator,content,remarks,init_time,start_time,end_time," +
                "location,people_needs,ac_ra) VALUES (?,?,?,?,?,?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int results = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement pS = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pS.setString(1,activity.getActivityName());
                pS.setString(2,activity.getInitiator());
                pS.setString(3,activity.getContent());
                pS.setString(4,activity.getRemarks());
                pS.setLong(5,activity.getInitTime());
                pS.setLong(6,activity.getStartTime());
                pS.setLong(7,activity.getEndTime());
                pS.setString(8,activity.getLocation());
                pS.setInt(9,activity.getPeopleNeeds());
                pS.setString(10,activity.getActivityOrRace());
                return pS;
            }
        },keyHolder);
        if (1 == results) {
            activity.setActivityId(keyHolder.getKey().intValue());
            return activity;
        } return null;
    }

    /**
     * 保存活动照片
     * @param activityPic
     * @param activityId
     * @return
     */
    public String savePicture(String activityPic,int activityId) {
        String sql="UPDATE activities SET activity_pic=? WHERE activity_id=?";
        int num = jdbcTemplate.update(sql,activityPic,activityId);
        if (1==num) {
            return DaoConstant.SUCCESS;
        } else {
            return DaoConstant.FAIL;
        }
    }

    /**
     * 更新参加活动的运动人数
     * @param number
     * @param activityId
     * @return
     */
    public String updateActivityPeople(int number,int activityId) {
        String sql = "UPDATE activities SET people_have=? WHERE activity_id=?";
        if (1==jdbcTemplate.update(sql,number,activityId)) {
            return SUCCESS;
        } else {return FAIL;}
    }

    /**
     * 加入活动 user_activity表
     * @param userId
     * @param activityId
     * @return
     */
    public String joinActivity(String userId,int activityId) {
        String sql="INSERT INTO user_activity (user_id,activity_id) VALUES (?,?)";
        int results = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement pS = connection.prepareStatement(sql);
                pS.setString(1,userId);
                pS.setInt(2,activityId);
                return pS;
            }
        });
        if (results==1) {
            return SUCCESS;
        }
        else {
            return FAIL;
        }
    }

    private class ActivityRowMapper implements RowMapper<Activity> {

        @Override
        public Activity mapRow(ResultSet resultSet, int i) throws SQLException {
            Activity activity = new Activity();
            activity.setActivityId(resultSet.getInt("activity_id"));
            activity.setActivityName(resultSet.getString("activity_name"));
            activity.setInitiator(resultSet.getString("initiator"));
            activity.setContent(resultSet.getString("content"));
            activity.setRemarks(resultSet.getString("remarks"));
            activity.setInitTime(resultSet.getLong("init_time"));
            activity.setStartTime(resultSet.getLong("start_time"));
            activity.setEndTime(resultSet.getLong("end_time"));
            activity.setLocation(resultSet.getString("location"));
            activity.setPeopleNeeds(resultSet.getInt("people_needs"));
            activity.setPeopleHave(resultSet.getInt("people_have"));
            activity.setActivityPic(resultSet.getString("activity_pic"));
            activity.setActivityOrRace(resultSet.getString("ac_ra"));
            activity.setClickNumber(resultSet.getInt("click_num"));
            return activity;
        }
    }
}
