package com.project.my.studystarteacher.newteacher.common;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/30.
 */

public class TempSourceSupply {

    public static ArrayList<String> getCZSData() {
        ArrayList<String> data = new ArrayList<>();
        data.add(0, "好吃");
        data.add(1, "好玩");
        data.add(2, "好购");
        data.add(3, "好住");
        data.add(4, "攻略");
        return data;
    }

    public static ArrayList<String> getEmpeyData() {
        ArrayList<String> imageList = new ArrayList<>();
        return imageList;
    }

    public static ArrayList<String> getImgData() {
        ArrayList<String> imageList = new ArrayList<>();
        imageList.add("http://mvimg10.meitudata.com/569b9090af0526344.jpg");
        imageList.add("http://img.mp.sohu.com/upload/20170703/c8c1818222a547f78585f9b357c93613_th.png");
        imageList.add("http://img5.duitang.com/uploads/item/201405/12/20140512000053_axANX.thumb.700_0.jpeg");
        imageList.add("http://img5.duitang.com/uploads/item/201405/12/20140512000053_axANX.thumb.700_0.jpeg");
        imageList.add("http://img.mp.sohu.com/upload/20170711/3f177d2be18143a48a9af1217e669855_th.png");
        imageList.add("http://img4.duitang.com/uploads/item/201509/26/20150926014223_BW8EG.jpeg");
        imageList.add("http://mvimg10.meitudata.com/569b9090af0526344.jpg");
        imageList.add("http://img.mp.sohu.com/upload/20170703/c8c1818222a547f78585f9b357c93613_th.png");
        return imageList;
    }

    public static ArrayList<String> getVrData() {
        ArrayList<String> data = new ArrayList<>();
        data.add(0, "好吃");
        data.add(1, "好玩");
        data.add(2, "好购");
        data.add(3, "农户直销");
        data.add(4, "共享农庄");
        data.add(5, "美丽乡村");
        return data;
    }

    public static ArrayList<String> getHData() {
        ArrayList<String> data = new ArrayList<>();
        data.add(0, "农户直销");
        data.add(1, "共享农庄");
        data.add(2, "美丽乡村");
        data.add(3, "特色民俗");
        data.add(4, "深度人文");
        data.add(5, "果蔬采摘");
        data.add(6, "扶贫项目");
        return data;
    }

    public static ArrayList<String> getHomeClazz() {
        ArrayList<String> data = new ArrayList<>();
        data.add(0, "有声绘本");
        data.add(1, "借阅管理");
        data.add(2, "主播录制");
        data.add(3, "悦读活动");
        return data;
    }

    public static ArrayList<String> getTemp() {
        ArrayList<String> data = new ArrayList<>();
        data.add(0, "农户直销");
        data.add(1, "共享农庄");
        data.add(2, "美丽乡村");
        data.add(3, "美丽乡村");
        return data;
    }

    public static ArrayList<String> getMyData() {
        ArrayList<String> data = new ArrayList<>();
        data.add(0, "我的收藏");
        data.add(1, "我的投诉");
        data.add(2, "我的订单");
        data.add(3, "我的游记");
        data.add(4, "我的问答");
        data.add(5, "我的点评");
        data.add(6, "优惠券");
        data.add(7, "我的钱包");
        data.add(8, "我的活动");
        data.add(9, "店铺管理");
        data.add(10, "购物车");
//        data.add(11, "我的VR");
        return data;
    }
}
