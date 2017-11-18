package cn.edu.cqupt.nmid.spdt.dao;

import cn.edu.cqupt.nmid.spdt.constant.DaoConstant;
import cn.edu.cqupt.nmid.spdt.model.User;
import com.sdicons.json.validator.impl.predicates.Str;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lawrence on 2017/11/4.
 */
@Repository
public class UserServiceDao implements DaoConstant{

    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * 存储用户信息
     * @param user
     * @return
     */
    public String saveUser(User user) {
        String sql="INSERT INTO user (id,userName,gender,password,nick_name,qq_num,telephone_num,profile_pic," +
                "ability,class,control) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        int number = jdbcTemplate.update(sql,user.getId(),user.getUserName(),user.getGender(),user.getPassword(),user.getNickname()
        ,user.getQqNum(),user.getTelephoneNum(),user.getProfilePic(),user.getAbility(),user.getClassNum()
        ,user.getControl());
        if (number == 1) {return SUCCESS;}
        return FAIL;
    }

    /**
     * 根据学号查找用户信息
     * @param userId
     * @return
     */
    public User findUserByID(String userId) {
        String sql = "SELECT * FROM user WHERE id=?";
        User user = new User();
        try {
            user = jdbcTemplate.queryForObject(sql,new UserRowMapper(),userId);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            System.out.println(exception.getCause());
        }
        return user;
    }

    public String updatePic(String filePath, String userId) {
        String sql="UPDATE user SET profile_pic=? WHERE id=?";
        int results = jdbcTemplate.update(sql,userId);
        if (results==1) {
            return SUCCESS;
        } else {
            return FAIL;
        }
    }

    /**
     * 获取所有参加的活动
     * @param userId
     * @return
     */
    public Map<Integer,String> getJoinedActivities(String userId) {
        String sql="SELECT activity_id,activity_name from activities WHERE activity_id " +
                "IN (SELECT activity_id FROM user_activity WHERE user_id=?)";
        SqlRowSet joinedActivities = jdbcTemplate.queryForRowSet(sql,userId);
        Map<Integer,String> map = new HashMap<>();
        while (joinedActivities.next()) {
            map.put(joinedActivities.getInt("activity_id"),joinedActivities.getString("activity_name"));
        }
        return map;
    }

    private class UserRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = new User();
            user.setId(resultSet.getString("id"));
            user.setUserName(resultSet.getString("userName"));
            user.setGender(resultSet.getString("gender"));
            user.setPassword(resultSet.getString("password"));
            user.setNickname(resultSet.getString("nick_name"));
            user.setQqNum(resultSet.getString("qq_num"));
            user.setTelephoneNum(resultSet.getString("telephone_num"));
            user.setProfilePic(resultSet.getString("profile_pic"));
            user.setAbility(resultSet.getInt("ability"));
            user.setClassNum(resultSet.getString("class"));
            user.setControl(resultSet.getString("control"));
            return user;
        }
    }
}
