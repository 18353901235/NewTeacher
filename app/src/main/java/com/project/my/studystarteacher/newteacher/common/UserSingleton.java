package com.project.my.studystarteacher.newteacher.common;

import android.content.Context;
import android.text.TextUtils;

import com.project.my.studystarteacher.newteacher.MiceApplication;
import com.project.my.studystarteacher.newteacher.bean.User;
import com.zhouqiang.framework.util.SharedPreferencesUtil;


/**
 * @author ZhouQiang
 * @desc 描述
 * @date 2016/2/26
 */
public class UserSingleton {
    private User sysUser;
    private String token;

    private static UserSingleton ourInstance = new UserSingleton();

    public static UserSingleton getInstance() {
        return ourInstance;
    }

    private UserSingleton() {

    }

    public static boolean isLogin() {
        if (!TextUtils.isEmpty(SharedPreferencesUtil.get(MiceApplication.getInstance(), ProjectConstant.USERID)))
            return true;
        return false;
    }

    public User getSysUser() {
        if (sysUser == null) {
            sysUser = new User();
            sysUser.setId(SharedPreferencesUtil.get(MiceApplication.getInstance(), ProjectConstant.USERID) == null ? "" : SharedPreferencesUtil.get(MiceApplication.getInstance(), ProjectConstant.USERID));
            sysUser.setPhone(SharedPreferencesUtil.get(MiceApplication.getInstance(), ProjectConstant.USER_PHONE));
            sysUser.setHeadPic(SharedPreferencesUtil.get(MiceApplication.getInstance(), ProjectConstant.ICON));
            token = SharedPreferencesUtil.get(MiceApplication.getInstance(), ProjectConstant.TOKEN);
        }
        return sysUser;
    }

    public void setSysUser(User sysUser) {
        this.sysUser = sysUser;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 登录时保存用户数据
     *
     * @param mContext
     * @param mUser
     * @param token
     */
    public void saveUser(Context mContext, User mUser, String pass, String token, String phone) {
        //保存用户数据
        SharedPreferencesUtil.save(mContext, ProjectConstant.USERID, mUser.getId() + "");
        SharedPreferencesUtil.save(mContext, ProjectConstant.USER_PHONE, phone);
        SharedPreferencesUtil.save(mContext, ProjectConstant.ICON, mUser.getHeadPic());
        SharedPreferencesUtil.save(mContext, ProjectConstant.USER_PWD, pass);
        //  SharedPreferencesUtil.save(mContext, ProjectConstant.CATEGORY, category);
        //  SharedPreferencesUtil.save(mContext, ProjectConstant.UID, uid);
        setSysUser(mUser);
        //保存token信息
        SharedPreferencesUtil.save(mContext, ProjectConstant.TOKEN, token);
        setToken(token);
        //聊天登录
//        if (!EMChat.getInstance().isLoggedIn()) {
//            UserLoginUtility.loginToHxChat(mUser.getClientId());
//        }
        //推送
//        if(JPushInterface.isPushStopped(mContext)){
//            JPushInterface.resumePush(mContext);
//        }
//        JpushSinglon.getInstance().setAlias(mContext,mUser.getClientId());
    }

    public void clearUser(Context mContext) {
        SharedPreferencesUtil.save(mContext, ProjectConstant.USERID, "");
        SharedPreferencesUtil.save(mContext, ProjectConstant.USER_PHONE, "");
        SharedPreferencesUtil.save(mContext, ProjectConstant.USER_PWD, "");
        setSysUser(null);
        SharedPreferencesUtil.save(mContext, ProjectConstant.TOKEN, "");
        setToken("");
//        UserLoginUtility.logOutToHxChat(true, null);
//        JPushInterface.stopPush(mContext);
    }

}
