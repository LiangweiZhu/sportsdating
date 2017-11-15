package cn.edu.cqupt.nmid.spdt.dao;

import cn.edu.cqupt.nmid.spdt.constant.DaoConstant;
import cn.edu.cqupt.nmid.spdt.model.DynamicNews;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Lawrence on 2017/11/11.
 */
@Repository
public class DynamicsServiceDao implements DaoConstant {

    @Resource
    private JdbcTemplate jdbcTemplate;

    public DynamicNews estabDynamicNews(DynamicNews dynamicNews) {
        System.out.println(dynamicNews);
        String url = "INSERT INTO dynamics (user_id,init_time,words) VALUES (?,?,?)";
        int results = jdbcTemplate.update(url,dynamicNews.getUserId(),dynamicNews.getInitTime(),dynamicNews.getContent());
        if (1 == results) {
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
