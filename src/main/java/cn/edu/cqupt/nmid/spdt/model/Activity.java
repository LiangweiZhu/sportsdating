package cn.edu.cqupt.nmid.spdt.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;

/**
 * Created by Lawrence on 2017/11/4.
 */
public class Activity {

    private int activityId;
    private String activityName;
    private String initiator;
    private String content;
    private String remarks;
    private long initTime;
    private long startTime;
    private long endTime;
    private String location;
    private int peopleNeeds;
    private int peopleHave;
    private String activityPic;
    private String activityOrRace;
    private Integer clickNumber;


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPeopleNeeds() {
        return peopleNeeds;
    }

    public void setPeopleNeeds(int peopleNeeds) {
        this.peopleNeeds = peopleNeeds;
    }

    public int getPeopleHave() {
        return peopleHave;
    }

    public void setPeopleHave(int peopleHave) {
        this.peopleHave = peopleHave;
    }


    public Integer getClickNumber() {
        return clickNumber;
    }

    public void setClickNumber(Integer clickNumber) {
        this.clickNumber = clickNumber;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public long getInitTime() {
        return initTime;
    }

    public void setInitTime(long initTime) {
        this.initTime = initTime;
    }

    public String getInitiator() {
        return initiator;
    }

    public void setInitiator(String initiator) {
        this.initiator = initiator;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public String getActivityPic() {
        return activityPic;
    }

    public void setActivityPic(String activityPic) {
        this.activityPic = activityPic;
    }

    public String getActivityOrRace() {
        return activityOrRace;
    }

    public void setActivityOrRace(String activityOrRace) {
        this.activityOrRace = activityOrRace;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
