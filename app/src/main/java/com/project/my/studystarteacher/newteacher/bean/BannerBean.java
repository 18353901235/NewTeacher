package com.project.my.studystarteacher.newteacher.bean;

public class BannerBean {

    /**
     * id : 96
     * title : 亲子阅读要注意的问题
     * pic : http://app.xuezhixing.net:8080/image/advertImg/teacher/1532660012201tad.jpg
     * name : 亲子阅读要注意的问题
     * url : https://mp.weixin.qq.com/s/94JRqBJ9OBbGhYx2BOoezA
     * shortdescribe :
     * contentsimg : null
     * inserttime : 1532688812000
     * lastupdate : 1532688828000
     * status : 1
     */

    private int id;
    private String title;
    private String pic;
    private String name;
    private String url;
    private String shortdescribe;
    private Object contentsimg;
    private long inserttime;
    private long lastupdate;
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getShortdescribe() {
        return shortdescribe;
    }

    public void setShortdescribe(String shortdescribe) {
        this.shortdescribe = shortdescribe;
    }

    public Object getContentsimg() {
        return contentsimg;
    }

    public void setContentsimg(Object contentsimg) {
        this.contentsimg = contentsimg;
    }

    public long getInserttime() {
        return inserttime;
    }

    public void setInserttime(long inserttime) {
        this.inserttime = inserttime;
    }

    public long getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(long lastupdate) {
        this.lastupdate = lastupdate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
