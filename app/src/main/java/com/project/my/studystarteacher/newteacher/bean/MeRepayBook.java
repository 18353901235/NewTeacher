package com.project.my.studystarteacher.newteacher.bean;

public class MeRepayBook {

    /**
     * readTime : 2018-10-04
     * xs_id : 504647
     * schoolbagNo : B5392494
     * xs_xming : 崔梦露
     */

    private String readTime;
    private String xs_id;
    private String schoolbagNo;
    private String xs_xming;
    private boolean isCheck;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getReadTime() {
        return readTime;
    }

    public void setReadTime(String readTime) {
        this.readTime = readTime;
    }

    public String getXs_id() {
        return xs_id;
    }

    public void setXs_id(String xs_id) {
        this.xs_id = xs_id;
    }

    public String getSchoolbagNo() {
        return schoolbagNo;
    }

    public void setSchoolbagNo(String schoolbagNo) {
        this.schoolbagNo = schoolbagNo;
    }

    public String getXs_xming() {
        return xs_xming;
    }

    public void setXs_xming(String xs_xming) {
        this.xs_xming = xs_xming;
    }
}
