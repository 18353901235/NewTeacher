package com.project.my.studystarteacher.newteacher.bean;

import java.io.Serializable;

public class ZhuBoBean implements Serializable {
    /**
     * play_Count : 6
     * PGardernCode : 01
     * booklogourl : a1481265315249_a2g.jpg
     * headImg : http://app.xuezhixing.net:8080/image/sbook/a1481265315249_a2g.jpg
     * ZGardernCode : 7878
     * id : 2
     * bookname : 巴巴爸爸的诞生
     * tid : 5463
     * url : http://app.xuezhixing.net:8080/image/7878/01/audio/1539329563504_T.mp3
     * bookbhao : 9787544810807
     */

    private int play_Count;
    private String PGardernCode;
    private String booklogourl;
    private String headImg;
    private String ZGardernCode;
    private int id;
    private String bookname;
    private int tid;
    private String url;
    private String bookbhao;
    private String author;
    private String bookdesc;

    public String getBookdesc() {
        return bookdesc;
    }

    public void setBookdesc(String bookdesc) {
        this.bookdesc = bookdesc;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPlay_Count() {
        return play_Count;
    }

    public void setPlay_Count(int play_Count) {
        this.play_Count = play_Count;
    }

    public String getPGardernCode() {
        return PGardernCode;
    }

    public void setPGardernCode(String PGardernCode) {
        this.PGardernCode = PGardernCode;
    }

    public String getBooklogourl() {
        return booklogourl;
    }

    public void setBooklogourl(String booklogourl) {
        this.booklogourl = booklogourl;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getZGardernCode() {
        return ZGardernCode;
    }

    public void setZGardernCode(String ZGardernCode) {
        this.ZGardernCode = ZGardernCode;
    }

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

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBookbhao() {
        return bookbhao;
    }

    public void setBookbhao(String bookbhao) {
        this.bookbhao = bookbhao;
    }
}
