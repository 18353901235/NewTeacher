package com.zbar.lib;

/**
 * 作者: 周强(932070319@qq.com)
 * 描述: zbar调用类
 */
public class ZbarManager {

    static {
        System.loadLibrary("zbar");
    }

    public native String decode(byte[] data, int width, int height, boolean isCrop, int x, int y, int cwidth, int cheight);
}
