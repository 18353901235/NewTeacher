package com.project.my.studystarteacher.newteacher.bean;

import java.io.Serializable;

public class PinglunBean implements Serializable {

    /**
     * role : 2
     * create_time : 1540222942000
     * headImg : http://app.xuezhixing.net:8080/image/7878/01/photo/null
     * part : 01
     * modify_time : 1540222942000
     * main : 7878
     * id : 301
     * userid : 5895
     * content : 10
     * username : 1111
     * video_id : 15
     */

    private int role;
    private long create_time;
    private String headImg;
    private String part;
    private long modify_time;
    private String main;
    private int id;
    private int userid;
    private String content;
    private String username;
    private int video_id;

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public long getModify_time() {
        return modify_time;
    }

    public void setModify_time(long modify_time) {
        this.modify_time = modify_time;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getVideo_id() {
        return video_id;
    }

    public void setVideo_id(int video_id) {
        this.video_id = video_id;
    }
}
