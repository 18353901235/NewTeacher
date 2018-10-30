package com.project.my.studystarteacher.newteacher.net;

import android.content.Context;

import com.dmcbig.mediapicker.entity.Media;
import com.project.my.studystarteacher.newteacher.common.ProjectConstant;
import com.project.my.studystarteacher.newteacher.utils.LubanCallBack;
import com.project.my.studystarteacher.newteacher.utils.NetUtils;
import com.project.my.studystarteacher.newteacher.utils.lubanUtils;
import com.zhouqiang.framework.net.SanmiNetWorker;
import com.zhouqiang.framework.util.SharedPreferencesUtil;
import com.zhouqiang.framework.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * 网络请求工具类
 */
public class MiceNetWorker extends SanmiNetWorker {
    /**
     * 实例化网络请求工具类
     *
     * @param mContext
     */
    private Context mContext;

    public MiceNetWorker(Context mContext) {
        super(mContext);
        this.mContext = mContext;

    }

    private void addParams(DemoHttpInformation information, HashMap<String, String> params) {
//        boolean add = true;
//        if (information == DemoHttpInformation.LOGIN) {
//            add = false;
//        } else {
//        }
//        if (add == true) {
//            if (!params.containsKey(Config.KeyProvinceId)) {
//                params.put(Config.KeyProvinceId, Config.ValueProvinceId);
//            }
//        }
    }

    public void executeTask(DemoHttpInformation information, HashMap<String, String> params, boolean isShow) {
        if (!NetUtils.hasDataConnection(mContext)) {
            ToastUtil.showLongToast(mContext, "网络连接异常，请检查网络");
            return;
        }
        //  addParams(information,params);
        DemoNetTask task = new DemoNetTask(information, params, null, null);
        task.setShow(isShow);
        executeTask(task);
    }

    public void executeTask(DemoHttpInformation information, HashMap<String, String> params, HashMap<String, String> files, boolean isShow) {
        if (!NetUtils.hasDataConnection(mContext)) {
            ToastUtil.showLongToast(mContext, "网络连接异常，请检查网络");
            return;
        }
        addParams(information, params);
        DemoNetTask task = new DemoNetTask(information, params, files, null);
        task.setShow(isShow);
        executeTask(task);
    }

    public void executeTaskHide(DemoHttpInformation information, HashMap<String, String> params) {
        executeTask(information, params, false);
    }

    public void executeTask(DemoHttpInformation information, HashMap<String, String> params) {
        executeTask(information, params, true);
    }

    public void Login(String username, String password) {
        DemoHttpInformation information = DemoHttpInformation.LOGIN;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", username);
        params.put("password", password);
        executeTask(information, params);
    }

    public void validatephone(String phone) {
        DemoHttpInformation information = DemoHttpInformation.VALIDATEPHONE;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("phone", phone);
        executeTask(information, params);
    }

    public void phoneBook() {
        DemoHttpInformation information = DemoHttpInformation.PHONEBOOK;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("token", SharedPreferencesUtil.get(mContext, ProjectConstant.TOKEN));
        executeTask(information, params);
    }

    public void giveBarcodeBackBags(String schoolbagbhao) {
        DemoHttpInformation information = DemoHttpInformation.GIVEBARCODEBACKBAGS;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("token", SharedPreferencesUtil.get(mContext, ProjectConstant.TOKEN));
        params.put("schoolbagbhao", schoolbagbhao + "");

        executeTask(information, params);
    }

    public void getNewHistoryRecommended(int studentId) {
        DemoHttpInformation information = DemoHttpInformation.GETNEWHISTORYRECOMMENDED;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("token", SharedPreferencesUtil.get(mContext, ProjectConstant.TOKEN));
        params.put("studentId", studentId + "");
        executeTask(information, params);
    }

    public void count() {
        DemoHttpInformation information = DemoHttpInformation.COUNT;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("token", SharedPreferencesUtil.get(mContext, ProjectConstant.TOKEN));
        executeTask(information, params);
    }

    public void statistics() {
        DemoHttpInformation information = DemoHttpInformation.STATISTICS;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("token", SharedPreferencesUtil.get(mContext, ProjectConstant.TOKEN));
        executeTask(information, params);
    }


    public void getBanner() {
        DemoHttpInformation information = DemoHttpInformation.GETBANNER;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("token", SharedPreferencesUtil.get(mContext, ProjectConstant.TOKEN));
        executeTask(information, params);
    }

    public void getbookDamageList(String searchValue) {
        DemoHttpInformation information = DemoHttpInformation.GETBOOKDAMAGELIST;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("token", SharedPreferencesUtil.get(mContext, ProjectConstant.TOKEN));
        params.put("searchValue", searchValue);

        executeTask(information, params);
    }

    public void getInfo() {
        DemoHttpInformation information = DemoHttpInformation.GETINFO;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("token", SharedPreferencesUtil.get(mContext, ProjectConstant.TOKEN));

        executeTask(information, params);
    }

    public void cancelDamage(String bookDamageId) {
        DemoHttpInformation information = DemoHttpInformation.CANCELDAMAGE;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("token", SharedPreferencesUtil.get(mContext, ProjectConstant.TOKEN));
        params.put("bookDamageId", bookDamageId);
        executeTask(information, params);
    }

    public void popularity(String code) {
        DemoHttpInformation information = DemoHttpInformation.POPULARITY;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("token", SharedPreferencesUtil.get(mContext, ProjectConstant.TOKEN));
        params.put("code", code);


        executeTask(information, params);
    }

    public void getReadDynamics(int pageNum, int pageSize) {
        DemoHttpInformation information = DemoHttpInformation.GETREADDYNAMICS;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("token", SharedPreferencesUtil.get(mContext, ProjectConstant.TOKEN));
        params.put("pageNum", pageNum + "");
        params.put("pageSize", pageSize + "");
        executeTask(information, params);
    }

    public void getActivityData() {
        DemoHttpInformation information = DemoHttpInformation.GETACTIVITYDATA;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("token", SharedPreferencesUtil.get(mContext, ProjectConstant.TOKEN));
        executeTask(information, params);
    }

    public void getActivityRanking() {
        DemoHttpInformation information = DemoHttpInformation.GETACTIVITYRANKING;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("token", SharedPreferencesUtil.get(mContext, ProjectConstant.TOKEN));
        executeTask(information, params);
    }

    public void listLive(String type) {
        DemoHttpInformation information = DemoHttpInformation.LISTLIVE;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("token", SharedPreferencesUtil.get(mContext, ProjectConstant.TOKEN));

        params.put("type", type + "");
        executeTask(information, params);
    }

    public void getRecommendBookList(String studentId) {
        DemoHttpInformation information = DemoHttpInformation.GETRECOMMENDBOOKLIST;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("token", SharedPreferencesUtil.get(mContext, ProjectConstant.TOKEN));

        params.put("studentId", studentId);
        executeTask(information, params);
    }

    public void makeRecommendedBagToOneStudent(String studentId, String bagNo) {
        DemoHttpInformation information = DemoHttpInformation.MAKERECOMMENDEDBAGTOONESTUDENT;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("token", SharedPreferencesUtil.get(mContext, ProjectConstant.TOKEN));

        params.put("studentId", studentId);
        params.put("bagNo", bagNo);
        executeTask(information, params);
    }

    public void typeLive() {
        DemoHttpInformation information = DemoHttpInformation.TYPELIVE;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("token", SharedPreferencesUtil.get(mContext, ProjectConstant.TOKEN));
        executeTask(information, params);
    }

    public void getExpertLecture(int pageNum, int pageSize) {
        DemoHttpInformation information = DemoHttpInformation.GETEXPERTLECTURE;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("token", SharedPreferencesUtil.get(mContext, ProjectConstant.TOKEN));
        params.put("pageNum", pageNum + "");
        params.put("pageSize", pageSize + "");
        executeTask(information, params);
    }

    public void seachSchoolAndClassCtrl(String schoolUniqueNo) {
        DemoHttpInformation information = DemoHttpInformation.SEACHSCHOOLANDCLASSCTRL;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("sign", "7002");
        params.put("schoolUniqueNo", schoolUniqueNo);

        executeTask(information, params);
    }

    public void getExpertLectureById(String id) {
        DemoHttpInformation information = DemoHttpInformation.GETEXPERTLECTUREBYID;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("token", SharedPreferencesUtil.get(mContext, ProjectConstant.TOKEN));
        params.put("id", id);

        executeTask(information, params);
    }

    public void interact(String id) {
        DemoHttpInformation information = DemoHttpInformation.INTERACT;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("token", SharedPreferencesUtil.get(mContext, ProjectConstant.TOKEN));
        params.put("id", id);

        executeTask(information, params);
    }

    public void publish(String id, String content, String role) {
        DemoHttpInformation information = DemoHttpInformation.PUBLISH;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("token", SharedPreferencesUtil.get(mContext, ProjectConstant.TOKEN));
        params.put("id", id);
        params.put("content", content);
        params.put("role", role);
        executeTask(information, params);
    }

    public void times(String id, String code) {
        DemoHttpInformation information = DemoHttpInformation.TIMES;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("token", SharedPreferencesUtil.get(mContext, ProjectConstant.TOKEN));
        params.put("id", id);
        params.put("code", code);
        executeTaskHide(information, params);
    }

    public void getSearchConditionsList() {
        DemoHttpInformation information = DemoHttpInformation.GETSEARCHCONDITIONSLIST;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("token", SharedPreferencesUtil.get(mContext, ProjectConstant.TOKEN));
        executeTaskHide(information, params);
    }

    public void getClassList() {
        DemoHttpInformation information = DemoHttpInformation.GETCLASSLIST;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("token", SharedPreferencesUtil.get(mContext, ProjectConstant.TOKEN));
        executeTask(information, params);
    }

    public void getBorrowBookRecord(String searchValue) {
        DemoHttpInformation information = DemoHttpInformation.GETBORROWBOOKRECORD;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("token", SharedPreferencesUtil.get(mContext, ProjectConstant.TOKEN));
        params.put("searchValue", searchValue);

        executeTask(information, params);
    }

    public void getStudentBorrowList(String classId) {
        DemoHttpInformation information = DemoHttpInformation.GETSTUDENTBORROWLIST;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("token", SharedPreferencesUtil.get(mContext, ProjectConstant.TOKEN));
        params.put("classId", classId);
        executeTask(information, params);
    }

    public void getBorrowBlackList(String classId) {
        DemoHttpInformation information = DemoHttpInformation.GETBORROWBLACKLIST;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("token", SharedPreferencesUtil.get(mContext, ProjectConstant.TOKEN));
        params.put("classId", classId);
        executeTask(information, params);
    }

    public void bookDamageUpload(String studentId, String bagNo, String bookNo, String damageDesc, String damageDegree, String bookName, ArrayList<Media> arrayList) {
        final DemoHttpInformation information = DemoHttpInformation.BOOKDAMAGEUPLOAD;
        final HashMap<String, String> params = new HashMap<String, String>();
        params.put("token", SharedPreferencesUtil.get(mContext, ProjectConstant.TOKEN));
        params.put("studentId", studentId);
        params.put("bagNo", bagNo);
        params.put("bookNo", bookNo);
        params.put("damageDesc", damageDesc);
        params.put("damageDegree", damageDegree);
        params.put("bookName", bookName);
        final HashMap<String, String> files = new HashMap<String, String>();
        //-------------------压缩
        ArrayList<String> arrayList1 = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            if (!arrayList.get(i).getPath().contains("drawable://")) {
                arrayList1.add(arrayList.get(i).getPath());
            }
        }
        if (arrayList1.size() > 0) {
            final StringBuffer buffer = new StringBuffer();
            lubanUtils.compressMore(arrayList1, new LubanCallBack() {
                @Override
                public void getImgs(ArrayList<String> imgs) {
                    for (int i = 0; i < imgs.size(); i++) {
                        if (i == 0) {
                            buffer.append(imgs.get(i));
                        } else {
                            buffer.append("," + imgs.get(i));
                        }
                    }
                    files.put("files", buffer.toString());
                    executeTask(information, params, files, true);

                }
            });
        } else {
            executeTask(information, params, null, true);
        }
    }

    public void dynamicUpload(String content, ArrayList<Media> arrayList, String videoPath) {
        DemoHttpInformation information = DemoHttpInformation.DYNAMICUPLOAD;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("token", SharedPreferencesUtil.get(mContext, ProjectConstant.TOKEN));
        params.put("content", content);
        HashMap<String, String> files = new HashMap<>();
        if (isNull(videoPath)) {
            if (arrayList.size() > 0) {
                final StringBuffer buffer = new StringBuffer();
                for (int i = 0; i < arrayList.size(); i++) {
                    if (i == 0) {
                        buffer.append(arrayList.get(i).getPath());
                    } else {
                        buffer.append("," + arrayList.get(i).getPath());
                    }
                }
                files.put("files", buffer.toString());
            } else {
                if (!isNull(videoPath)) {
                    files.put("files", videoPath);
                }
            }
            if (files.size() > 0) {
                executeTask(information, params, files, true);
            } else {
                executeTask(information, params);
            }
        } else {
            files.put("files", videoPath);
            executeTask(information, params, files, true);
        }
    }

    public void removeBlackList(String parentId) {
        DemoHttpInformation information = DemoHttpInformation.REMOVEBLACKLIST;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("token", SharedPreferencesUtil.get(mContext, ProjectConstant.TOKEN));
        params.put("parentId", parentId);
        executeTask(information, params);
    }

    public void getBackRecords(String searchValue) {
        DemoHttpInformation information = DemoHttpInformation.GETBACKRECORDS;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("token", SharedPreferencesUtil.get(mContext, ProjectConstant.TOKEN));
        params.put("searchValue", searchValue);
        executeTask(information, params);
    }

    public void giveBackBagsList() {
        DemoHttpInformation information = DemoHttpInformation.GIVEBACKBAGSLIST;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("token", SharedPreferencesUtil.get(mContext, ProjectConstant.TOKEN));
        executeTask(information, params);
    }

    //userNo,sex,classId,className,mainSchoolNo,partSchoolNo,userName
    public void perfectInfo(String classId, String className, String mainSchoolNo, String partSchoolNo, String userName) {
        DemoHttpInformation information = DemoHttpInformation.PERFECTINFO;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("token", SharedPreferencesUtil.get(mContext, ProjectConstant.TOKEN));
        //  params.put("userNo", age);
        params.put("classId", classId);
        params.put("className", className);
        params.put("mainSchoolNo", mainSchoolNo);
        params.put("partSchoolNo", partSchoolNo);
        params.put("userName", userName);
        executeTask(information, params);
    }

    public void bookList() {
        DemoHttpInformation information = DemoHttpInformation.BOOKLIST;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("token", SharedPreferencesUtil.get(mContext, ProjectConstant.TOKEN));
        executeTask(information, params);
    }

    public void myAudio() {
        DemoHttpInformation information = DemoHttpInformation.MYAUDIO;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("token", SharedPreferencesUtil.get(mContext, ProjectConstant.TOKEN));
        executeTask(information, params);
    }

    public void Share() {
        DemoHttpInformation information = DemoHttpInformation.SHARED;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("token", SharedPreferencesUtil.get(mContext, ProjectConstant.TOKEN));
        executeTask(information, params);
    }

    //type的类型,1是返回动态列表2是点赞,3是取消点赞,4添加评论,7删除
    public void dynamicFunction(String type, String comments, String id, String praiscount, String requestPagenum) {
        DemoHttpInformation information = DemoHttpInformation.DYNAMICFUNCTION;
        HashMap<String, String> params = new HashMap<String, String>();

        params.put("type", type);
        if (!isNull(comments))
            params.put("comments", comments);
        if (!isNull(id))
            params.put("id", id);
        if (!isNull(praiscount))
            params.put("praiscount", praiscount);
        if (!isNull(requestPagenum))
            params.put("requestPagenum", requestPagenum);
        params.put("token", SharedPreferencesUtil.get(mContext, ProjectConstant.TOKEN));
        executeTask(information, params);
    }

    public void delAudio(String id) {
        DemoHttpInformation information = DemoHttpInformation.DELAUDIO;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("token", SharedPreferencesUtil.get(mContext, ProjectConstant.TOKEN));
        params.put("id", id);
        executeTask(information, params);
    }

    public void bookcaseStatus() {
        DemoHttpInformation information = DemoHttpInformation.STATUSBOOKCASE;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("token", SharedPreferencesUtil.get(mContext, ProjectConstant.TOKEN));
        executeTask(information, params);
    }

    public void switchannel() {
        DemoHttpInformation information = DemoHttpInformation.SWITCHANNEL;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("token", SharedPreferencesUtil.get(mContext, ProjectConstant.TOKEN));
        executeTask(information, params);
    }

    public void giveBackBags(String schoolbagbhao) {
        DemoHttpInformation information = DemoHttpInformation.GIVEBACKBAGS;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("token", SharedPreferencesUtil.get(mContext, ProjectConstant.TOKEN));
        params.put("schoolbagbhao", schoolbagbhao);
        executeTask(information, params);
    }

    public void uploadAudio(String bookbhao, String path) {
        DemoHttpInformation information = DemoHttpInformation.UPLOADAUDIO;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("token", SharedPreferencesUtil.get(mContext, ProjectConstant.TOKEN));

        HashMap<String, String> files = new HashMap<>();
        files.put("files", path);
        params.put("bookbhao", bookbhao);
        executeTask(information, params, files, true);
    }

    public void getVoicedBookList(String age, String theme, String country, String pageNum, String bookName) {
        DemoHttpInformation information = DemoHttpInformation.GETVOICEDBOOKLIST;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("token", SharedPreferencesUtil.get(mContext, ProjectConstant.TOKEN));
        params.put("age", age);
        params.put("theme", theme);
        params.put("country", country);
        params.put("pageNum", pageNum);
        params.put("bookName", bookName);
        if (!isNull(pageNum)) {
            params.put("pageSize", "10");
        } else {
            params.put("pageNum", "1");
            params.put("pageSize", "100");
        }
        executeTask(information, params);
    }


    public void register(String userNo, String userPwd, String sex, String classId,
                         String className, String mainSchoolNo, String partSchoolNo, String userName) {
        DemoHttpInformation information = DemoHttpInformation.REGISTER;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("userNo", userNo);
        params.put("userPwd", userPwd);
        params.put("sex", sex);
        params.put("classId", classId);
        params.put("className", className);
        params.put("mainSchoolNo", mainSchoolNo);
        params.put("partSchoolNo", partSchoolNo);
        params.put("userName", userName);
        executeTask(information, params);
    }

}
