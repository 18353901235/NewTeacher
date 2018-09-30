package com.project.my.studystarteacher.newteacher.utils;

import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import org.xutils.common.util.DensityUtil;

/**
 * Created by ZhouQiang on 2016/2/4.
 */
public class ImageUtility {
    /**
     * ImageLoader
     */
    private final ImageLoader imageLoader;

    /**
     * 默认显示设置lff
     */
    private final DisplayImageOptions defaultOptions;

    public ImageUtility(int Res) {
        imageLoader = ImageLoader.getInstance();
        defaultOptions = new DisplayImageOptions.Builder().showStubImage(Res).showImageForEmptyUri(Res)
                .showImageOnFail(Res).cacheInMemory(true).cacheOnDisc(true).build();
    }

    /**
     * 显示图片。
     *
     * @param url       图片路径
     * @param imageView ImageView
     */
    public void showImage(String url, ImageView imageView) {
        try {
            imageLoader.displayImage(url, imageView, defaultOptions);
        } catch (OutOfMemoryError e) {
            System.gc();
        } catch (Exception e) {
        }
    }

    /**
     * 显示图片。
     *
     * @param url       图片路径
     * @param imageView ImageView
     */
    public void showImage(String url, ImageView imageView, ImageLoadingListener l) {
        try {
            imageLoader.displayImage(url, imageView, defaultOptions, l);
        } catch (OutOfMemoryError e) {
            System.gc();
        } catch (Exception e) {
        }
    }

    /**
     * 显示图片。
     *
     * @param url       图片路径
     * @param imageView ImageView
     */
    public void showcornerImage(String url, ImageView imageView, int Res) {
        try {
            imageLoader.displayImage(url, imageView, new DisplayImageOptions.Builder().showStubImage(Res).showImageForEmptyUri(Res)
                    .showImageOnFail(Res).cacheInMemory(true).cacheOnDisc(true).displayer(new FlexibleRoundedBitmapDisplayer(DensityUtil.dip2px(5))).build());
        } catch (OutOfMemoryError e) {
            System.gc();
        } catch (Exception e) {
        }
    }

    /**
     * 显示图片。
     *
     * @param url       图片路径
     * @param imageView ImageView
     */
    public void showCircleImage(String url, ImageView imageView, int Res) {
        try {
            imageLoader.displayImage(url, imageView, new DisplayImageOptions.Builder().showStubImage(Res).showImageForEmptyUri(Res)
                    .showImageOnFail(Res).cacheInMemory(true).cacheOnDisc(true).displayer(new RoundedBitmapDisplayer(80)).build());
        } catch (OutOfMemoryError e) {
            System.gc();
        } catch (Exception e) {
        }
    }


    /***
     * 显示图片（带失败监听）
     *
     * @param imageView    图片试图
     * @param urlPath      图片路径
     * @param loadListener 失败监听
     */
    public void ShowHttpImage(ImageView imageView, String urlPath,
                              ImageLoadingListener loadListener) {
        imageLoader.displayImage(urlPath, imageView, defaultOptions, loadListener);
    }


    /**
     * 从内存卡中异步加载本地图片
     *
     * @param uri
     * @param imageView
     */
    public void displayFromSDCard(String uri, ImageView imageView) {
        // String imageUri = "file:///mnt/sdcard/image.png"; // from SD card
        imageLoader.displayImage("file://" + uri, imageView, defaultOptions);
    }

}
