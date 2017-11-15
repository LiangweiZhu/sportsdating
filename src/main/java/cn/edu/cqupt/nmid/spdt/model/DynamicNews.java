package cn.edu.cqupt.nmid.spdt.model;

import com.sdicons.json.validator.impl.predicates.Str;

/**
 * Created by Lawrence on 2017/11/11.
 */
public class DynamicNews {
    private int dynamicId;
    private String userId;
    private Long initTime;
    private String content;
    private String dynamicPic;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getInitTime() {
        return initTime;
    }

    public void setInitTime(Long initTime) {
        this.initTime = initTime;
    }


    public String getDynamicPic() {
        return dynamicPic;
    }

    public void setDynamicPic(String dynamicPic) {
        this.dynamicPic = dynamicPic;
    }

    @Override
    public String toString() {
        return "["+getUserId()+","+getInitTime()+","+getContent()+","+getDynamicPic()+"]";
    }

    public int getDynamicId() {
        return dynamicId;
    }

    public void setDynamicId(int dynamicId) {
        this.dynamicId = dynamicId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
