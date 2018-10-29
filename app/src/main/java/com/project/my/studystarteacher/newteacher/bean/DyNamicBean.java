package com.project.my.studystarteacher.newteacher.bean;

import com.project.my.studystarteacher.newteacher.adapter.IvAdapter;

import java.io.Serializable;
import java.util.List;

public class DyNamicBean implements Serializable {

    /**
     * headImg : http://app.xuezhixing.net:8080/image/7878/01/photo/1518052723761_Thead.jpg
     * canbedelete : 0
     * Comments : [{"id":8432,"name":"殷小强","contents":"输入个人心得测试数据","insertTime":1539073350000,"insertTimeStr":"2018-10-09 08:22:30","dtid":58553},{"id":8433,"name":"殷小强","contents":"隔热你俄方认为发热潍坊人违反烦人烦人分为非沟通沟通沟通沟通给她过","insertTime":1539073553000,"insertTimeStr":"2018-10-09 08:25:53","dtid":58553}]
     * candelete : false
     * Pics : http://app.xuezhixing.net:8080/image/7878/01/dynamic/1078-2/1538102096693_T2.jpg,http://app.xuezhixing.net:8080/image/7878/01/dynamic/1078-2/1538102097666_T3.jpg
     * wetherZan : 0
     * InsertTime : 2018-09-28 10:34:57
     * Name : 赵小林
     * isHide : N
     * PraiseCount : 2
     * contents : 啦啦啦KKK了
     * ID : 58553
     * whodid : 1078-2
     */

    private String headImg;
    private int canbedelete;
    private boolean candelete;
    private String Pics;
    private String wetherZan;
    private String InsertTime;
    private String Name;
    private String isHide;
    private String PraiseCount;
    private String contents;
    private String ID;
    private String whodid;
    private List<CommentsBean> Comments;
    private IvAdapter adapter;

    public IvAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(IvAdapter adapter) {
        this.adapter = adapter;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public int getCanbedelete() {
        return canbedelete;
    }

    public void setCanbedelete(int canbedelete) {
        this.canbedelete = canbedelete;
    }

    public boolean isCandelete() {
        return candelete;
    }

    public void setCandelete(boolean candelete) {
        this.candelete = candelete;
    }

    public String getPics() {
        return Pics;
    }

    public void setPics(String Pics) {
        this.Pics = Pics;
    }

    public String getWetherZan() {
        return wetherZan;
    }

    public void setWetherZan(String wetherZan) {
        this.wetherZan = wetherZan;
    }

    public String getInsertTime() {
        return InsertTime;
    }

    public void setInsertTime(String InsertTime) {
        this.InsertTime = InsertTime;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getIsHide() {
        return isHide;
    }

    public void setIsHide(String isHide) {
        this.isHide = isHide;
    }

    public String getPraiseCount() {
        return PraiseCount;
    }

    public void setPraiseCount(String PraiseCount) {
        this.PraiseCount = PraiseCount;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getWhodid() {
        return whodid;
    }

    public void setWhodid(String whodid) {
        this.whodid = whodid;
    }

    public List<CommentsBean> getComments() {
        return Comments;
    }

    public void setComments(List<CommentsBean> Comments) {
        this.Comments = Comments;
    }

    public static class CommentsBean implements Serializable {
        /**
         * id : 8432
         * name : 殷小强
         * contents : 输入个人心得测试数据
         * insertTime : 1539073350000
         * insertTimeStr : 2018-10-09 08:22:30
         * dtid : 58553
         */

        private int id;
        private String name;
        private String contents;
        private long insertTime;
        private String insertTimeStr;
        private int dtid;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getContents() {
            return contents;
        }

        public void setContents(String contents) {
            this.contents = contents;
        }

        public long getInsertTime() {
            return insertTime;
        }

        public void setInsertTime(long insertTime) {
            this.insertTime = insertTime;
        }

        public String getInsertTimeStr() {
            return insertTimeStr;
        }

        public void setInsertTimeStr(String insertTimeStr) {
            this.insertTimeStr = insertTimeStr;
        }

        public int getDtid() {
            return dtid;
        }

        public void setDtid(int dtid) {
            this.dtid = dtid;
        }
    }
}
