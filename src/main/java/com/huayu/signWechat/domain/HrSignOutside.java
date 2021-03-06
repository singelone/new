package com.huayu.signWechat.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.ly.dao.base.BaseDomain;
import com.ly.dao.base.Table;
import com.ly.dao.base.TableField;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
* Created by DengPeng on 2017-9-25 16:11:06.
*/
@Table(name = "hr_sign_outside")
public class HrSignOutside extends BaseDomain  implements Serializable{

 //private String updateFiledKey = id,userid,username,telephone,groupname,checkin_type,exception_type,checkin_time,location_title,location_detail,wifiname,notes,wifimac,mediaids,status,createTime,updateTime,updateUser;
 //private String updateFiledValue = id=:id,userid=:userid,username=:username,telephone=:telephone,groupname=:groupname,checkin_type=:checkin_type,exception_type=:exception_type,checkin_time=:checkin_time,location_title=:location_title,location_detail=:location_detail,wifiname=:wifiname,notes=:notes,wifimac=:wifimac,mediaids=:mediaids,status=:status,createTime=:createTime,updateTime=:updateTime,updateUser=:updateUser;
    private Integer id;
    private String userid;
    private String username;
    private String telephone;
    private String groupname;
    private String checkin_type;
    private String exception_type;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date checkin_time;
    private String location_title;
    private String location_detail;
    private String wifiname;
    private String notes;
    private String wifimac;
    private String mediaids;
    private Integer status;
    private Date createTime;
    private Date updateTime;
    private Integer updateUser;

    public HrSignOutside() {
        this.dt = "desc";
        this.dtn = "id";
        this.pageSize = 10;
    }

    @TableField
    private String userNum;
    @TableField
    private String department;
    @TableField
    private String company;
    @TableField
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date checkDate;
    @TableField
    private String checkInTimeStr;
    @TableField
    private String week;

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public String getCheckInTimeStr() {
        return checkInTimeStr;
    }

    public void setCheckInTimeStr(String checkInTimeStr) {
        this.checkInTimeStr = checkInTimeStr;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getUserid() {
      return userid;
   }

   public void setUserid(String userid) {
      this.userid = userid;
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getTelephone() {
      return telephone;
   }

   public void setTelephone(String telephone) {
      this.telephone = telephone;
   }

   public String getGroupname() {
      return groupname;
   }

   public void setGroupname(String groupname) {
      this.groupname = groupname;
   }

   public String getCheckin_type() {
      return checkin_type;
   }

   public void setCheckin_type(String checkin_type) {
      this.checkin_type = checkin_type;
   }

   public String getException_type() {
      return exception_type;
   }

   public void setException_type(String exception_type) {
      this.exception_type = exception_type;
   }

   public Date getCheckin_time() {
      return checkin_time;
   }

   public void setCheckin_time(Date checkin_time) {
      this.checkin_time = checkin_time;
   }

   public String getLocation_title() {
      return location_title;
   }

   public void setLocation_title(String location_title) {
      this.location_title = location_title;
   }

   public String getLocation_detail() {
      return location_detail;
   }

   public void setLocation_detail(String location_detail) {
      this.location_detail = location_detail;
   }

   public String getWifiname() {
      return wifiname;
   }

   public void setWifiname(String wifiname) {
      this.wifiname = wifiname;
   }

   public String getNotes() {
      return notes;
   }

   public void setNotes(String notes) {
      this.notes = notes;
   }

   public String getWifimac() {
      return wifimac;
   }

   public void setWifimac(String wifimac) {
      this.wifimac = wifimac;
   }

   public String getMediaids() {
      return mediaids;
   }

   public void setMediaids(String mediaids) {
      this.mediaids = mediaids;
   }

   public Integer getStatus() {
      return status;
   }

   public void setStatus(Integer status) {
      this.status = status;
   }

   public Date getCreateTime() {
      return createTime;
   }

   public void setCreateTime(Date createTime) {
      this.createTime = createTime;
   }

   public Date getUpdateTime() {
      return updateTime;
   }

   public void setUpdateTime(Date updateTime) {
      this.updateTime = updateTime;
   }

   public Integer getUpdateUser() {
      return updateUser;
   }

   public void setUpdateUser(Integer updateUser) {
      this.updateUser = updateUser;
   }
}
