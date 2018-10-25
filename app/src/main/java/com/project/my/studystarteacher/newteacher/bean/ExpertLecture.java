package com.project.my.studystarteacher.newteacher.bean;

import java.io.Serializable;

public class ExpertLecture implements Serializable {
    /**
     * id : 15
     * lecturer : 余治莹
     * title : 听余治莹谈绘本
     * cover : http://app.xuezhixing.net:8080/image/phone/teach/20180827172747.jpg
     * topic : 专家讲坛
     * play_Count : 123
     * visited_Count : 518
     * comment : null
     * video_Url : http://app.xuezhixing.net:8080/image/phone/teach/20180827172747.mp4
     * description : 绘本大师余治莹作客中育悦读直播间
     * modify_Time : 1539968050000
     * create_Time : 1535390867000
     */
    private int id;
    private String lecturer;
    private String title;
    private String cover;
    private String topic;
    private int play_Count;
    private int visited_Count;
    private Object comment;
    private String video_Url;
    private String description;
    private long modify_Time;
    private long create_Time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getPlay_Count() {
        return play_Count;
    }

    public void setPlay_Count(int play_Count) {
        this.play_Count = play_Count;
    }

    public int getVisited_Count() {
        return visited_Count;
    }

    public void setVisited_Count(int visited_Count) {
        this.visited_Count = visited_Count;
    }

    public Object getComment() {
        return comment;
    }

    public void setComment(Object comment) {
        this.comment = comment;
    }

    public String getVideo_Url() {
        return video_Url;
    }

    public void setVideo_Url(String video_Url) {
        this.video_Url = video_Url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getModify_Time() {
        return modify_Time;
    }

    public void setModify_Time(long modify_Time) {
        this.modify_Time = modify_Time;
    }

    public long getCreate_Time() {
        return create_Time;
    }

    public void setCreate_Time(long create_Time) {
        this.create_Time = create_Time;
    }
}
