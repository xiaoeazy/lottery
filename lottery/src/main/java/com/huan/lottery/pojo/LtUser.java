package com.huan.lottery.pojo;

import java.util.Date;

public class LtUser {
    private Integer id;

    private String username;

    private String idcard;

    private String salespersonid;

    private String phone;

    private Boolean flag;

    private Integer pid;

    private Date awarddate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getSalespersonid() {
        return salespersonid;
    }

    public void setSalespersonid(String salespersonid) {
        this.salespersonid = salespersonid == null ? null : salespersonid.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Date getAwarddate() {
        return awarddate;
    }

    public void setAwarddate(Date awarddate) {
        this.awarddate = awarddate;
    }
}