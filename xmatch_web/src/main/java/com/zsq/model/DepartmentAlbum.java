package com.zsq.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by coderqiang on 2017/11/14.
 */
@Entity
public class DepartmentAlbum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long departmentAlbumId;

    private long depId;
    private long activityId;
    private String imageUrl;
    private long creatTime;
    private String uploadUserName;
    private String imageSummary;
    private String activtiyName;

    public String getActivtiyName() {
        return activtiyName;
    }

    public void setActivtiyName(String activtiyName) {
        this.activtiyName = activtiyName;
    }

    public String getImageSummary() {
        return imageSummary;
    }

    public void setImageSummary(String imageSummary) {
        this.imageSummary = imageSummary;
    }

    public long getDepartmentAlbumId() {
        return departmentAlbumId;
    }

    public void setDepartmentAlbumId(long departmentAlbumId) {
        this.departmentAlbumId = departmentAlbumId;
    }

    public long getDepId() {
        return depId;
    }

    public void setDepId(long depId) {
        this.depId = depId;
    }

    public long getActivityId() {
        return activityId;
    }

    public void setActivityId(long activityId) {
        this.activityId = activityId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public long getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(long creatTime) {
        this.creatTime = creatTime;
    }

    public String getUploadUserName() {
        return uploadUserName;
    }

    public void setUploadUserName(String uploadUserName) {
        this.uploadUserName = uploadUserName;
    }
}
