package com.example.coderqiang.xmatch_android.model;


/**
 * @author CoderQiang
 * Created by coderqiang on 2017/11/5.
 */
public class Activity {

    private long activityId;
    private long depId;
    //活动名称
    private String activityName;
    //活动内容介绍
    private String content;
    //活动地点
    private String address;
    /**
     * 负责人手机号
     */
    private String managerPhone;
    /**
     * 创建时间
     */
    private long createTime;
    //开始时间
    private long startTime;
    //结束时间
    private long endTime;
    //活动图片
    private String imageUrl;
    //申请人数
    private int applyNum;
    //签到人数
    private int signIn;

    //综测
    private float measure;

    private String depName;

    public float getMeasure() {
        return measure;
    }

    public void setMeasure(float measure) {
        this.measure = measure;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public long getDepId() {
        return depId;
    }

    public void setDepId(long depId) {
        this.depId = depId;
    }

    public int getApplyNum() {
        return applyNum;
    }

    public void setApplyNum(int applyNum) {
        this.applyNum = applyNum;
    }

    public int getSignIn() {
        return signIn;
    }

    public void setSignIn(int signIn) {
        this.signIn = signIn;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getActivityId() {
        return activityId;
    }

    public void setActivityId(long activityId) {
        this.activityId = activityId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
