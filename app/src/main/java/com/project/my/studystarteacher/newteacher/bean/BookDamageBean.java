package com.project.my.studystarteacher.newteacher.bean;

import java.io.Serializable;
import java.util.List;

public class BookDamageBean implements Serializable {

    /**
     * id : 1001
     * bookname : 谁厉害？
     * bookbhao : 9787556031078
     * schoolbagbhao : B5392494
     * studentid : 504647
     * damageimg :
     * inserttime : 1540390562000
     * teacherid : 5463
     * damagedesc : 11211212
     * damagedegree : 1
     * isrepair : 0
     * main_school_no : 7878
     * part_school_no : 01
     * studentName : 崔梦露
     * mGrade : 初一
     * mClass : 一班
     * recentBorrower : [{"mname":"崔梦露","mclass":"一班","mdate":1538685019000}]
     */

    private int id;
    private String bookname;
    private String bookbhao;
    private String schoolbagbhao;
    private int studentid;
    private String damageimg;
    private long inserttime;
    private int teacherid;
    private String damagedesc;
    private int damagedegree;
    private int isrepair;
    private String main_school_no;
    private String part_school_no;
    private String studentName;
    private String mGrade;
    private String mClass;
    private List<RecentBorrowerBean> recentBorrower;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getBookbhao() {
        return bookbhao;
    }

    public void setBookbhao(String bookbhao) {
        this.bookbhao = bookbhao;
    }

    public String getSchoolbagbhao() {
        return schoolbagbhao;
    }

    public void setSchoolbagbhao(String schoolbagbhao) {
        this.schoolbagbhao = schoolbagbhao;
    }

    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    public String getDamageimg() {
        return damageimg;
    }

    public void setDamageimg(String damageimg) {
        this.damageimg = damageimg;
    }

    public long getInserttime() {
        return inserttime;
    }

    public void setInserttime(long inserttime) {
        this.inserttime = inserttime;
    }

    public int getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(int teacherid) {
        this.teacherid = teacherid;
    }

    public String getDamagedesc() {
        return damagedesc;
    }

    public void setDamagedesc(String damagedesc) {
        this.damagedesc = damagedesc;
    }

    public int getDamagedegree() {
        return damagedegree;
    }

    public void setDamagedegree(int damagedegree) {
        this.damagedegree = damagedegree;
    }

    public int getIsrepair() {
        return isrepair;
    }

    public void setIsrepair(int isrepair) {
        this.isrepair = isrepair;
    }

    public String getMain_school_no() {
        return main_school_no;
    }

    public void setMain_school_no(String main_school_no) {
        this.main_school_no = main_school_no;
    }

    public String getPart_school_no() {
        return part_school_no;
    }

    public void setPart_school_no(String part_school_no) {
        this.part_school_no = part_school_no;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getMGrade() {
        return mGrade;
    }

    public void setMGrade(String mGrade) {
        this.mGrade = mGrade;
    }

    public String getMClass() {
        return mClass;
    }

    public void setMClass(String mClass) {
        this.mClass = mClass;
    }

    public List<RecentBorrowerBean> getRecentBorrower() {
        return recentBorrower;
    }

    public void setRecentBorrower(List<RecentBorrowerBean> recentBorrower) {
        this.recentBorrower = recentBorrower;
    }

    public static class RecentBorrowerBean implements Serializable {
        /**
         * mname : 崔梦露
         * mclass : 一班
         * mdate : 1538685019000
         */

        private String mname;
        private String mclass;
        private long mdate;

        public String getMname() {
            return mname;
        }

        public void setMname(String mname) {
            this.mname = mname;
        }

        public String getMclass() {
            return mclass;
        }

        public void setMclass(String mclass) {
            this.mclass = mclass;
        }

        public long getMdate() {
            return mdate;
        }

        public void setMdate(long mdate) {
            this.mdate = mdate;
        }
    }
}
