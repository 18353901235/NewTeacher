package com.project.my.studystarteacher.newteacher.utils;

import android.os.Handler;

import com.project.my.studystarteacher.newteacher.MiceApplication;
import com.zhouqiang.framework.util.Logger;
import com.zhouqiang.framework.util.ToastUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * Created by hasee on 2018/6/5.
 */

public class lubanUtils {
    public lubanUtils() {
    }

    /**
     * 压缩多图
     *
     * @param pathList 传入的为图片原始路径
     */
    public static void compressMore(final List<String> pathList, final LubanCallBack callBack) {
        final LinkedList<Runnable> taskList = new LinkedList<>();
        final ArrayList<String> newList = new ArrayList<>();//压缩后的图片路径
        final Handler handler = new Handler();
        class Task implements Runnable {
            String path;

            Task(String path) {
                this.path = path;
            }

            @Override
            public void run() {//可以多线程处理，但是我这里因为项目急，暂时未修改 2018/6/5
                Luban.get(MiceApplication.getInstance())
                        .load(new File(path))//传人要压缩的图片
                        .putGear(Luban.THIRD_GEAR)
                        .setCompressListener(new OnCompressListener() { //设置回调
                            @Override
                            public void onStart() {
                            }

                            @Override
                            public void onSuccess(File file) {
                                newList.add(file.getPath());
                                if (!taskList.isEmpty()) {
                                    Runnable runnable = taskList.pop();
                                    handler.post(runnable);
                                    Logger.d("压缩后图片：" + file.getPath());
                                } else {
                                    //完成之后的个人操作
                                    callBack.getImgs(newList);
                                }
                            }
                            @Override
                            public void onError(Throwable e) {
                                ToastUtil.showLongToast(MiceApplication.getInstance(), "图片压缩失败");
                            }
                        }).launch();    //启动压缩
            }
        }
        //循环遍历原始路径 添加至linklist中
        for (String path : pathList) {
            taskList.add(new Task(path));
        }
        if (taskList.size() > 0) {
            handler.post(taskList.pop());
        }

    }
}
