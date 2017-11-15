package cn.edu.cqupt.nmid.spdt.dao;

import cn.edu.cqupt.nmid.spdt.constant.DaoConstant;
import cn.edu.cqupt.nmid.spdt.model.DynamicNews;
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
 * Created by Lawrence on 2017/11/11.
 */
@Repository
public class DynamicsServiceDao implements DaoConstant {

    @Resource
    private JdbcTemplate jdbcTemplate;

    public DynamicNews estabDynamicNews(DynamicNews dynamicNews) {
        String sql = "INSERT INTO dynamics (user_id,init_time,words) VALUES (?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int results = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement pS = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pS.setString(1,dynamicNews.getUserId());
                pS.setLong(2,dynamicNews.getInitTime());
                pS.setString(3,dynamicNews.getContent());
                return pS;

            }
        },keyHolder);
        if (1 == results) {
            dynamicNews.setDynamicId(keyHolder.getKey().intValue());
            return dynamicNews;
        }
        return null;
    }

    /**
     * 存储图片
     * @param filePath
     * @param dynamicId
     * @return
     */
    public String saveDynamicPic(String filePath,int dynamicId) {
        String sql = "UPDATE dynamics SET dynamic_pic=? WHERE dynamic_id = ?";
        int num = jdbcTemplate.update(sql,filePath,dynamicId);
        if (1 == num) {
            return SUCCESS;
        }
        return FAIL;
    }

    public List<DynamicNews> getAllDynamicNews() {
        String sql = "SELECT * FROM dynamics ORDER BY init_time DESC ";
        return jdbcTemplate.query(sql,new DynamicsRowMapper());
    }

    private class DynamicsRowMapper implements RowMapper<DynamicNews> {

        @Override
        public DynamicNews mapRow(ResultSet resultSet, int i) throws SQLException {
            DynamicNews dynamicNews = new DynamicNews();
            dynamicNews.setDynamicId(resultSet.getInt("dynamic_id"));
            dynamicNews.setUserId(resultSet.getString("user_id"));
            dynamicNews.setContent(resultSet.getString("words"));
            dynamicNews.setInitTime(resultSet.getLong("init_time"));
            dynamicNews.setDynamicPic(resultSet.getString("dynamic_pic"));
            return dynamicNews;
        }
    }
}
