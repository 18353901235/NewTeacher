package com.project.my.studystarteacher.newteacher.net;

import com.zhouqiang.framework.net.SanmiHttpInfomation;

/**
 * 网络请求信息枚举类
 */
public enum DemoHttpInformation implements SanmiHttpInfomation {
    /**
     * 后台服务接口根路径
     */
    SYS_ROOT(0, DemoConfig.SYS_ROOT, "后台服务接口根路径", true),
    /**
     * 登录
     */
    SELECT_VERSION(1, "teacher/login", "检测版本", false),
    LOGIN(1, "teacher/login", "登录", false),

    VALIDATEPHONE(1, "teacher/validatePhone", "注册时验证手机号", false),
    SEACHSCHOOLANDCLASSCTRL(1, "teacher/seachSchoolAndClassCtrl", "查询学校及班级", false),
    REGISTER(1, "teacher/register", "注册", false),

    //-----------首页start-------------------------------
    GETBANNER(1, "homepage/getBanner", "获取主页的banner数据", false),
    GETREADDYNAMICS(1, "homepage/getReadDynamics", "获取主页的悦读动态数据", false),
    POPULARITY(1, " anchorREC/popularity", "人气主播", false),

    GETACTIVITYRANKING(1, "homepage/getActivityRanking", "阅读排行榜", false),
    GETACTIVITYDATA(1, "homepage/getActivityData", "21阅读活动排行", false),
    GETEXPERTLECTURE(1, "homepage/getExpertLecture", "获取专家讲堂数据", false),
    GETEXPERTLECTUREBYID(1, "homepage/getExpertLectureById ", "专家讲堂详细数据", false),
    INTERACT(1, "live/interact", "互动列表", false),
    COUNT(1, "red/count", "红包 ", false),
    STATISTICS(1, "survey/statistics", " 调研统计", false),


    PUBLISH(1, "live/publish", "发表评论", false),


    TIMES(1, "live/times", "访问(4395)、播放(4396)次数增加", false),
    //-----------首页end-------------------------------
    //------------------有声绘本
    GETVOICEDBOOKLIST(1, "voicedBookCtrl/getVoicedBookList", "有声绘本列表", false),
    GETSEARCHCONDITIONSLIST(1, "voicedBookCtrl/getSearchConditionsList", "获取有声绘本的筛选条件", false),
    //------------------有声绘本
    //------------------借阅管理
    GETCLASSLIST(1, "bookBorrowReadCtrl/getClassList", "管理员获取班级列表", false),
    GETBORROWBOOKRECORD(1, "bookBorrowReadCtrl/getBorrowBookRecord", "借阅记录", false),
    GETBORROWBLACKLIST(1, "bookBorrowReadCtrl/getBorrowBlackList", "管理对象（黑名单）", false),
    REMOVEBLACKLIST(1, "bookBorrowReadCtrl/removeBlackList", "移除管理对象（黑名单）", false),
    GETSTUDENTBORROWLIST(1, "bookBorrowReadCtrl/getStudentBorrowList", "获取学生借阅详情", false),


    //---------------破损管理
    BOOKDAMAGEUPLOAD(1, "bookDamage/bookDamageUpload", "破损登记管理", false),
    GETBOOKDAMAGELIST(1, "bookDamage/getbookDamageList", "破损记录列表", false),
    CANCELDAMAGE(1, "bookDamage/cancelDamage", "破损取消", false),


    //------还书管理
    GETBACKRECORDS(1, "returnBook/getBackRecords", "还书记录列表", false),
    GIVEBACKBAGSLIST(1, "returnBook/giveBackBagsList", "批量还书列表", false),

    //------------------有声绘本

    //-------------主播
    BOOKLIST(1, "anchorREC/bookList", "绘本列表", false),
    UPLOADAUDIO(1, "anchorREC/uploadAudio ", "音频上传", false),
    MYAUDIO(1, "anchorREC/myAudio", "我的录制", false),
    DELAUDIO(1, "anchorREC/delAudio", "删除我的录制", false),
    //----------
    //------------------我的
    GETINFO(1, "teacher/getInfo", "获取个人信息", false),
    TOANSWER(1, "http://app.xuezhixing.net:8080/xmanager/appAnswer/toAnswer.do", "调研分享", true),
    SHARED(1, "http://app.xuezhixing.net:8081/TearcherService/pay/shared", "调研分享", true),
    PERFECTINFO(1, "teacher/perfectInfo", "完善个人设置（不包含上传头像）", false),


    //------------------我的

    //-----------------------------
    DYNAMICFUNCTION(1, "dynamic/dynamicFunction", "动态的功能", false),
    DYNAMICUPLOAD(1, "dynamic/dynamicUpload", "动态上传", false),


    //--------------------


    SELECTINFOBYPIC(64, "goods/selectInfoByPic.do", "通过图片特征码获取详情信息", false),;

    private int id;// 对应NetTask的id
    private String urlPath;// 请求地址
    private String description;// 请求描述
    private boolean isRootPath;// 是否是根路径

    private DemoHttpInformation(int id, String urlPath, String description,
                                boolean isRootPath) {
        this.id = id;
        this.urlPath = urlPath;
        this.description = description;
        this.isRootPath = isRootPath;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getUrlPath() {
        if (isRootPath)
            return urlPath;
        String path = SYS_ROOT.urlPath + urlPath;
        return path;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean isRootPath() {
        return isRootPath;
    }

}
