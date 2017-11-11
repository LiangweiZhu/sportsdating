package cn.edu.cqupt.nmid.spdt.dao;

import cn.edu.cqupt.nmid.spdt.constant.DaoConstant;
import cn.edu.cqupt.nmid.spdt.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Lawrence on 2017/11/4.
 */
@Repository
public class UserServiceDao implements DaoConstant{

    @Resource
    private JdbcTemplate jdbcTemplate;

    public String saveUser(User user) {
        String sql="INSERT INTO user (id,userName,gender,password,nick_name,qq_num,telephone_num,profile_pic," +
                "ability,class,control) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        int number = jdbcTemplate.update(sql,user.getId(),user.getUserName(),user.getGender(),user.getPassword(),user.getNickname()
        ,user.getQqNum(),user.getTelephoneNum(),user.getProfilePic(),user.getAbility(),user.getClassNum()
        ,user.getControl());
        if (number == 1) {return SUCCESS;}
        return FAIL;
    }

    public User findUserByID(String userId) {
        String sql = "SELECT * FROM user WHERE id=?";
        User user;
        try {
            user = jdbcTemplate.queryForObject(sql,new UserRowMapper(),userId);
        } catch (Exception exception) {
            return null;
        }
        return user;
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
