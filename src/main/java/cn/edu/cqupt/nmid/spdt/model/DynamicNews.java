package cn.edu.cqupt.nmid.spdt.model;

import com.sdicons.json.validator.impl.predicates.Str;

/**
 * Created by Lawrence on 2017/11/11.
 */
public class DynamicNews {
    private String userId;
    private Long initTime;
    private String text;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDynamicPic() {
        return dynamicPic;
    }

    public void setDynamicPic(String dynamicPic) {
        this.dynamicPic = dynamicPic;
    }

    @Override
    public String toString() {
        return "["+getUserId()+","+getInitTime()+","+getText()+","+getDynamicPic()+"]";
    }
}
