package com.project.my.studystarteacher.newteacher.bean;

import java.io.Serializable;

public class LoginSchollBean implements Serializable {

    /**
     * partSchoolNo : 01
     * mainSchoolNo : 7878
     * schoolName : 研发专属分院一院
     */

    private String partSchoolNo;
    private String mainSchoolNo;
    private String schoolName;

    public String getPartSchoolNo() {
        return partSchoolNo;
    }

    public void setPartSchoolNo(String partSchoolNo) {
        this.partSchoolNo = partSchoolNo;
    }

    public String getMainSchoolNo() {
        return mainSchoolNo;
    }

    public void setMainSchoolNo(String mainSchoolNo) {
        this.mainSchoolNo = mainSchoolNo;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
