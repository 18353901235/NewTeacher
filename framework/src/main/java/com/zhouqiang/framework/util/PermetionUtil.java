package com.zhouqiang.framework.util;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

/**
 * 6.0权限控制相关工具类
 */
public class PermetionUtil {

    /**
     * 判断是否有此权限
     *
     * @return
     */
    public static boolean hasedPermetion(Object object, String[] permetion, int requstCode) {
        if (object instanceof Activity) {
            if (permissionSet((Activity) object, permetion)) {
                return true;
            } else {
                ActivityCompat.requestPermissions((Activity) object, permetion, requstCode);
            }
        } else if (object instanceof Fragment) {
            if (permissionSet(((Fragment) object).getActivity(), permetion)) {
                return true;
            } else {
                ((Fragment) object).requestPermissions(permetion, requstCode);
            }
        }


        return false;
    }

    //检查系统权限是，判断当前是否缺少权限(PERMISSION_DENIED:权限是否足够)
    private static boolean isLackPermission(Activity activity, String permission) {
        return ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED;
    }

    //

    /**
     * 检查权限时，判断系统的权限集合
     *
     * @return
     */
    public static boolean permissionSet(Activity activity, String... permissions) {
        for (String permission : permissions) {
            if (!isLackPermission(activity, permission)) {//是否添加完全部权限集合
                return false;
            }
        }
        return true;
    }
}
