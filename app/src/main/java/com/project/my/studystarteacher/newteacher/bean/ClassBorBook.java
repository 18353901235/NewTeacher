package com.project.my.studystarteacher.newteacher.bean;

import java.io.Serializable;
import java.util.List;

public class ClassBorBook implements Serializable {

    /**
     * xs_id : 504647
     * xs_xming : 崔梦露
     * ID : 32307
     * currentdeposit : 0
     * isend : n
     * vipEndtime : 2019-04-10
     * recommentTime : 2018-10-24
     * damage_times : 4
     * xs_pic : http://app.xuezhixing.net:8080/image/7878/01/photo/1528273555520_head.jpg
     * isappear : 1
     * cardNum : 当前学生还没有分配卡号
     * isInstalled : 1
     * uniqueCode : 1539327273838
     * code : 9904
     * books : [{"bookcategory":"社交情感","bookbhao":"9787556031078","bookname":"谁厉害？ ","schoolbagbhao":"B5392494","schoolbagname":"B5392494","bookIsDamaged":1}]
     * damageCredit : 2
     * bag : {"borrowstatus":1,"schoolbagname":"B5392494","schoolbagbhao":"B5392494"}
     */

    private int xs_id;
    private String xs_xming;
    private int ID;
    private int currentdeposit;
    private String isend;
    private String vipEndtime;
    private String recommentTime;
    private int damage_times;
    private String xs_pic;
    private int isappear;
    private String cardNum;
    private int isInstalled;
    private String uniqueCode;
    private int code;
    private int damageCredit;
    private MangOBJ.BagBean bag;
    private List<MangOBJ.BooksBean> books;

    public int getXs_id() {
        return xs_id;
    }

    public void setXs_id(int xs_id) {
        this.xs_id = xs_id;
    }

    public String getXs_xming() {
        return xs_xming;
    }

    public void setXs_xming(String xs_xming) {
        this.xs_xming = xs_xming;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getCurrentdeposit() {
        return currentdeposit;
    }

    public void setCurrentdeposit(int currentdeposit) {
        this.currentdeposit = currentdeposit;
    }

    public String getIsend() {
        return isend;
    }

    public void setIsend(String isend) {
        this.isend = isend;
    }

    public String getVipEndtime() {
        return vipEndtime;
    }

    public void setVipEndtime(String vipEndtime) {
        this.vipEndtime = vipEndtime;
    }

    public String getRecommentTime() {
        return recommentTime;
    }

    public void setRecommentTime(String recommentTime) {
        this.recommentTime = recommentTime;
    }

    public int getDamage_times() {
        return damage_times;
    }

    public void setDamage_times(int damage_times) {
        this.damage_times = damage_times;
    }

    public String getXs_pic() {
        return xs_pic;
    }

    public void setXs_pic(String xs_pic) {
        this.xs_pic = xs_pic;
    }

    public int getIsappear() {
        return isappear;
    }

    public void setIsappear(int isappear) {
        this.isappear = isappear;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public int getIsInstalled() {
        return isInstalled;
    }

    public void setIsInstalled(int isInstalled) {
        this.isInstalled = isInstalled;
    }

    public String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getDamageCredit() {
        return damageCredit;
    }

    public void setDamageCredit(int damageCredit) {
        this.damageCredit = damageCredit;
    }

    public MangOBJ.BagBean getBag() {
        return bag;
    }

    public void setBag(MangOBJ.BagBean bag) {
        this.bag = bag;
    }

    public List<MangOBJ.BooksBean> getBooks() {
        return books;
    }

    public void setBooks(List<MangOBJ.BooksBean> books) {
        this.books = books;
    }

}
