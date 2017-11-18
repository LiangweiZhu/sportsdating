package cn.edu.cqupt.nmid.spdt.dao;

import cn.edu.cqupt.nmid.spdt.constant.DaoConstant;
import cn.edu.cqupt.nmid.spdt.model.DynamicNews;
import cn.edu.cqupt.nmid.spdt.model.DynamicNewsLike;
import cn.edu.cqupt.nmid.spdt.model.User;
import com.sdicons.json.validator.impl.predicates.Str;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Lawrence on 2017/11/11.
 */
@Repository
public class DynamicsServiceDao implements DaoConstant {

    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * 创建圈子的消息
     * @param dynamicNews
     * @return
     */
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

    /**
     * 获取热门消息列表
     * @return
     */
    public List<DynamicNews> getAllDynamicNews() {
        String sql = "SELECT * FROM dynamics ORDER BY like_num DESC, init_time DESC";
        return jdbcTemplate.query(sql,new DynamicsRowMapper());
    }

    /**
     * 根据ID查询消息详情
     * @param dynamicId
     * @return
     */
    public DynamicNews getDynamicNewsById(int dynamicId) {
        String sql="SELECT * FROM dynamics WHERE dynamic_id=?";
        return jdbcTemplate.queryForObject(sql,new DynamicsRowMapper(),dynamicId);
    }

    public List<String> getLikedPeole(int dynamicId) {
        String sql = "SELECT * FROM dynamics_like WHERE dynamic_id=?";
        List<DynamicNewsLike> list = jdbcTemplate.query(sql,new DynamicsLikeRowMapper(),dynamicId);
        List<String> userNames = new LinkedList<>();
        Iterator<DynamicNewsLike> iterator = list.iterator();
        while (iterator.hasNext()) {
            DynamicNewsLike temp = iterator.next();
            userNames.add(temp.getUserName());
        }
        return userNames;
    }
    /**
     * 更新消息的点赞数
     * @param dynamicId
     * @param likeNum
     * @return
     */
    public String updateLikeNum(int dynamicId,int likeNum) {
        String sql="UPDATE dynamics SET like_num=? WHERE dynamic_id=?";
        int results = jdbcTemplate.update(sql,likeNum,dynamicId);
        if (results==1) {
            return SUCCESS;
        }
        return FAIL;
    }





    /**
     * 新建点赞记录
     * @param dynamicNewsLike
     * @param dynamicNewsLike
     * @return
     */
    public DynamicNewsLike newLike(DynamicNewsLike dynamicNewsLike) {
        String sql="insert into dynamics_like (dynamic_id,user_id,user_name,user_like,like_time) values (?,?,?,?,?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int results = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement pS = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                pS.setInt(1,dynamicNewsLike.getDynamicId());
                pS.setString(2,dynamicNewsLike.getUserId());
                pS.setString(3,dynamicNewsLike.getUserName());
                pS.setString(4,DaoConstant.LIKE);
                pS.setLong(5,dynamicNewsLike.getLikeTime());
                return pS;
            }
        },keyHolder);
        if (results==1) {
            dynamicNewsLike.setSerialNum(keyHolder.getKey().intValue());
            return dynamicNewsLike;
        }
        return null;
    }

    /**
     * 查找已有的点赞记录
     * @param dynamicNewsLike
     * @return
     */
    public DynamicNewsLike findHistory (DynamicNewsLike dynamicNewsLike) {
        String sql = "SELECT * FROM dynamics_like WHERE dynamic_id=? AND user_id=?";
        DynamicNewsLike oldHistory;
        try {
            oldHistory = jdbcTemplate.queryForObject(sql,new DynamicsLikeRowMapper(),dynamicNewsLike.getDynamicId(),
                    dynamicNewsLike.getUserId());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        return oldHistory;
    }

    /**
     * 变换点赞状态
     * @param dynamicNewsLike
     * @return
     */
    public DynamicNewsLike changeLikeStatus(DynamicNewsLike dynamicNewsLike) {
        String sql ="UPDATE dynamics_like SET user_like=? WHERE dynamic_id=? AND user_id=?";
        int results = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement pS = connection.prepareStatement(sql);
                if (DaoConstant.LIKE.equals(dynamicNewsLike.getUserLike())) {
                    pS.setString(1,DaoConstant.UNLIKE);
                } else {
                    pS.setString(1,DaoConstant.LIKE);
                }
                pS.setInt(2,dynamicNewsLike.getDynamicId());
                pS.setString(3,dynamicNewsLike.getUserId());
                return pS;
            }
        });
        if (results==1) {
            if (DaoConstant.LIKE.equals(dynamicNewsLike.getUserLike())) {
                dynamicNewsLike.setUserLike(DaoConstant.UNLIKE);
            } else {
                dynamicNewsLike.setUserLike(DaoConstant.LIKE);
            }
        }
        return dynamicNewsLike;
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
            dynamicNews.setLikeNumber(resultSet.getInt("like_num"));
            return dynamicNews;
        }
    }

    private class DynamicsLikeRowMapper implements RowMapper<DynamicNewsLike> {

        @Override
        public DynamicNewsLike mapRow(ResultSet resultSet, int i) throws SQLException {
            DynamicNewsLike dynamicNewsLike = new DynamicNewsLike();
            dynamicNewsLike.setSerialNum(resultSet.getInt("serial_num"));
            dynamicNewsLike.setDynamicId(resultSet.getInt("dynamic_id"));
            dynamicNewsLike.setUserId(resultSet.getString("user_id"));
            dynamicNewsLike.setUserName(resultSet.getString("user_name"));
            dynamicNewsLike.setUserLike(resultSet.getString("user_like"));
            dynamicNewsLike.setLikeTime(resultSet.getLong("like_time"));
            return dynamicNewsLike;
        }
    }
}
