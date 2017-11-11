package cn.edu.cqupt.nmid.spdt.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;

/**
 * Created by Lawrence on 2017/11/4.
 */
public class Activity {

    private Integer activeId;
    private String activeName;
    private int initiator;
    private String activeIntroduction;
    private String remarks;
    private long initTime;
    private long startTime;
    private long endTime;
    private String location;
    private int peopleNeeds;
    private int peopleHave;
    private String activePic;
    private String activeOrRace;
    private Integer clickNumber;

    public Integer getActiveId() {
        return activeId;
    }

    public void setActiveId(Integer activeId) {
        this.activeId = activeId;
    }

    public String getActiveName() {
        return activeName;
    }

    public void setActiveName(String activeName) {
        this.activeName = activeName;
    }

    public String getActiveIntroduction() {
        return activeIntroduction;
    }

    public void setActiveIntroduction(String activeIntroduction) {
        this.activeIntroduction = activeIntroduction;
    }

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

    public String getActivePic() {
        return activePic;
    }

    public void setActivePic(String activePic) {
        this.activePic = activePic;
    }

    public String getActiveOrRace() {
        return activeOrRace;
    }

    public void setActiveOrRace(String activeOrRace) {
        this.activeOrRace = activeOrRace;
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

    public int getInitiator() {
        return initiator;
    }

    public void setInitiator(int initiator) {
        this.initiator = initiator;
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
}
