package com.project.my.studystarteacher.newteacher.bean;

import java.io.Serializable;

public class AudioBook implements Serializable {

    /**
     * id : 3119
     * bookname : 妈妈要去上班啦！
     * bookcategory : 社交情感
     * owner : 0
     * totalamount : null
     * surplusamount : 0
     * agegroup : 4-6岁
     * difcountry : 其它
     * inserttime : 1519383633000
     * author : 珍妮弗·摩尔-迈丽斯
     * bookbhao : 9787535381071
     * bookconcernamount : 0
     * bookdesc : 《儿童逆商培养故事书:你有秘密吗?》通过12个故事，帮助孩子面对生活中常见的困难，让他们在遭遇逆境时也拥有阳光心态。旨在通过孩子的故事，培养3岁及以上孩子的逆商，让孩子在遭遇逆境时，也能拥有阳光心态，从而健康成长。如果一个秘密，能让你和其他人都感到快乐，这样的秘密是值得被保守的。保守这样的秘密肯定也是非常幸福的。
     * booktitle : null
     * booktype : null
     * bookhot : null
     * booklogourl : http://app.xuezhixing.net:8080/image/sbook/1519354833844_b7q.jpg
     * bookdescimgurl : http://app.xuezhixing.net:8080/image/sbook/1519354833861_r1l.jpg,http://app.xuezhixing.net:8080/image/sbook/1519354833862_p8x.jpg
     * playurl : http://app.xuezhixing.net:8080/image/schoolRoom/1519370192315_s4g.mp3
     */

    private int id;
    private String bookname;
    private String bookcategory;
    private int owner;
    private Object totalamount;
    private int surplusamount;
    private String agegroup;
    private String difcountry;
    private long inserttime;
    private String author;
    private String bookbhao;
    private int bookconcernamount;
    private String bookdesc;
    private Object booktitle;
    private Object booktype;
    private Object bookhot;
    private String booklogourl;
    private String bookdescimgurl;
    private String playurl;

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

    public String getBookcategory() {
        return bookcategory;
    }

    public void setBookcategory(String bookcategory) {
        this.bookcategory = bookcategory;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public Object getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(Object totalamount) {
        this.totalamount = totalamount;
    }

    public int getSurplusamount() {
        return surplusamount;
    }

    public void setSurplusamount(int surplusamount) {
        this.surplusamount = surplusamount;
    }

    public String getAgegroup() {
        return agegroup;
    }

    public void setAgegroup(String agegroup) {
        this.agegroup = agegroup;
    }

    public String getDifcountry() {
        return difcountry;
    }

    public void setDifcountry(String difcountry) {
        this.difcountry = difcountry;
    }

    public long getInserttime() {
        return inserttime;
    }

    public void setInserttime(long inserttime) {
        this.inserttime = inserttime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookbhao() {
        return bookbhao;
    }

    public void setBookbhao(String bookbhao) {
        this.bookbhao = bookbhao;
    }

    public int getBookconcernamount() {
        return bookconcernamount;
    }

    public void setBookconcernamount(int bookconcernamount) {
        this.bookconcernamount = bookconcernamount;
    }

    public String getBookdesc() {
        return bookdesc;
    }

    public void setBookdesc(String bookdesc) {
        this.bookdesc = bookdesc;
    }

    public Object getBooktitle() {
        return booktitle;
    }

    public void setBooktitle(Object booktitle) {
        this.booktitle = booktitle;
    }

    public Object getBooktype() {
        return booktype;
    }

    public void setBooktype(Object booktype) {
        this.booktype = booktype;
    }

    public Object getBookhot() {
        return bookhot;
    }

    public void setBookhot(Object bookhot) {
        this.bookhot = bookhot;
    }

    public String getBooklogourl() {
        return booklogourl;
    }

    public void setBooklogourl(String booklogourl) {
        this.booklogourl = booklogourl;
    }

    public String getBookdescimgurl() {
        return bookdescimgurl;
    }

    public void setBookdescimgurl(String bookdescimgurl) {
        this.bookdescimgurl = bookdescimgurl;
    }

    public String getPlayurl() {
        return playurl;
    }

    public void setPlayurl(String playurl) {
        this.playurl = playurl;
    }
}
