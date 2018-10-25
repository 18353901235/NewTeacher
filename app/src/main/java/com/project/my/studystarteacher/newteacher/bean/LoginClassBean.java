package com.project.my.studystarteacher.newteacher.bean;

import java.io.Serializable;

public class LoginClassBean implements Serializable {

    /**
     * bji : 一班
     * bjid : 3371
     */
    private String bji;
    private int bjid;
    private String njname;
    private String test;
    private LoginSchollBean bean;

    public LoginSchollBean getBean() {
        return bean;
    }

    public String getNjname() {
        return njname;
    }

    public void setNjname(String njname) {
        this.njname = njname;
    }

    public void setBean(LoginSchollBean bean) {
        this.bean = bean;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getBji() {
        return bji;
    }

    public void setBji(String bji) {
        this.bji = bji;
    }

    public int getBjid() {
        return bjid;
    }

    public void setBjid(int bjid) {
        this.bjid = bjid;
    }
}
