package cn.edu.cqupt.nmid.spdt.dao;

import cn.edu.cqupt.nmid.spdt.model.DynamicNews;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by Lawrence on 2017/11/11.
 */
@Repository
public class DynamicsServiceDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    public DynamicNews estabDynamicNews(DynamicNews dynamicNews) {
        System.out.println(dynamicNews);
        String url = "INSERT INTO dynamics (user_id,init_time,words) VALUES (?,?,?)";
        int results = jdbcTemplate.update(url,dynamicNews.getUserId(),dynamicNews.getInitTime(),dynamicNews.getText());
        if (1 == results) {
            return dynamicNews;
        }
        return null;
    }

}
