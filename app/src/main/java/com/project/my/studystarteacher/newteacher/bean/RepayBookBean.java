package com.project.my.studystarteacher.newteacher.bean;

import java.io.Serializable;
import java.util.List;

public class RepayBookBean implements Serializable {

    /**
     * teacherid : null
     * btime : 2018-10-15
     * bags : [{"schoolbagno":"C5393318","backtime":"2018-10-15 10:10:06","back_user":575633,"xs_xming":"刘可可"}]
     */

    private Object teacherid;
    private String btime;
    private List<BagsBean> bags;

    public Object getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(Object teacherid) {
        this.teacherid = teacherid;
    }

    public String getBtime() {
        return btime;
    }

    public void setBtime(String btime) {
        this.btime = btime;
    }

    public List<BagsBean> getBags() {
        return bags;
    }

    public void setBags(List<BagsBean> bags) {
        this.bags = bags;
    }

    public static class BagsBean implements Serializable {
        /**
         * schoolbagno : C5393318
         * backtime : 2018-10-15 10:10:06
         * back_user : 575633
         * xs_xming : 刘可可
         */

        private String schoolbagno;
        private String backtime;
        private int back_user;
        private String xs_xming;

        public String getSchoolbagno() {
            return schoolbagno;
        }

        public void setSchoolbagno(String schoolbagno) {
            this.schoolbagno = schoolbagno;
        }

        public String getBacktime() {
            return backtime;
        }

        public void setBacktime(String backtime) {
            this.backtime = backtime;
        }

        public int getBack_user() {
            return back_user;
        }

        public void setBack_user(int back_user) {
            this.back_user = back_user;
        }

        public String getXs_xming() {
            return xs_xming;
        }

        public void setXs_xming(String xs_xming) {
            this.xs_xming = xs_xming;
        }
    }
}
