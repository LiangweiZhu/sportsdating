package cn.edu.cqupt.nmid.spdt.model;

/**
 * Created by Lawrence on 2017/11/16.
 */
public class DynamicNewsLike {
    private int serialNum;
    private int dynamicId;
    private String userId;
    private String userName;
    private String userLike;
    private Long likeTime;

    public int getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(int serialNum) {
        this.serialNum = serialNum;
    }

    public int getDynamicId() {
        return dynamicId;
    }

    public void setDynamicId(int dynamicId) {
        this.dynamicId = dynamicId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getLikeTime() {
        return likeTime;
    }

    public void setLikeTime(Long likeTime) {
        this.likeTime = likeTime;
    }

    public String getUserLike() {
        return userLike;
    }

    public void setUserLike(String userLike) {
        this.userLike = userLike;
    }
}
