package com.zhouqiang.framework.util;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;

/**
 * 文件工具类
 *
 * @author Administrator
 */

public class FileUtil {
    private static final String TAG = "XtomFileUtil";

    /**
     * 复制文件到指定目录
     *
     * @param filePath 被复制文件地址
     * @param savePath 保存文件地址
     * @return 复制是否成功
     */
    public static boolean copy(String filePath, String savePath) {
        File file = new File(filePath);
        if (!file.exists())
            return false;
        BufferedInputStream inBuff = null;
        BufferedOutputStream outBuff = null;
        try {
            // 新建文件输入流并对它进行缓冲
            inBuff = new BufferedInputStream(new FileInputStream(file));
            File temp = new File(savePath);
            if (!temp.exists()) {
                File dirFile = temp.getParentFile();
                if (!dirFile.exists())
                    dirFile.mkdirs();
                temp.createNewFile();
            }

            // 新建文件输出流并对它进行缓冲
            outBuff = new BufferedOutputStream(new FileOutputStream(temp));
            // 缓冲数组
            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = inBuff.read(b)) != -1) {
                outBuff.write(b, 0, len);
            }
            // 刷新此缓冲的输出流
            outBuff.flush();
        } catch (Exception e) {
            return false;
        } finally {
            // 关闭流
            try {
                if (inBuff != null)
                    inBuff.close();
                if (outBuff != null)
                    outBuff.close();
            } catch (IOException e) {
                // ignore
            }
        }
        return true;
    }

    /**
     * 调用此方法自动计算指定文件或指定文件夹的大小
     *
     * @param filePath 文件路径
     * @return 计算好的带B、KB、MB、GB的字符串
     */
    public static String getAutoFileOrFilesSize(String filePath) {
        File file = new File(filePath);
        long blockSize = 0;
        try {
            if (file.isDirectory()) {
                blockSize = getFileSizes(file);
            } else {
                blockSize = getFileSize(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return FormetFileSize(blockSize);
    }

    /**
     * 获取指定文件大小
     *
     * @return
     * @throws Exception
     */
    private static long getFileSize(File file) throws Exception {
        long size = 0;
        if (file.exists()) {
            FileInputStream fis = null;
            fis = new FileInputStream(file);
            size = fis.available();
        } else {
            file.createNewFile();
        }
        return size;
    }

    /**
     * 转换文件大小
     *
     * @param fileS
     * @return
     */
    private static String FormetFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        String wrongSize = "0k";
        if (fileS == 0) {
            return wrongSize;
        }
//        if (fileS < 1024) {
//            fileSizeString = df.format((double) fileS) + "B";
//        } else
        if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "GB";
        }
        return fileSizeString;
    }

    /**
     * 获取指定文件夹
     *
     * @param f
     * @return
     * @throws Exception
     */
    private static long getFileSizes(File f) throws Exception {
        long size = 0;
        File flist[] = f.listFiles();
        for (int i = 0; i < flist.length; i++) {
            if (flist[i].isDirectory()) {
                size = size + getFileSizes(flist[i]);
            } else {
                size = size + getFileSize(flist[i]);
            }
        }
        return size;
    }

    /**
     * 获取外部存储根目录
     *
     * @return 如果有返回String，否则null
     */
    public static final String getExternalMemoryPath() {
        return (isExternalMemoryAvailable()) ? Environment
                .getExternalStorageDirectory().getPath() : null;
    }

    /**
     * 判断是否有外部存储
     *
     * @return 如果有返回true，否则false
     */
    public static final boolean isExternalMemoryAvailable() {
        return Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState());
    }

    /**
     * 删除临时文件
     *
     * @param context
     * @return
     */
    public static final void deleteTempFile(Context context) {
        File cacheDir = new File(getTempFileDir(context));
        if (!cacheDir.exists())
            return;
        final File[] files = cacheDir.listFiles();
        for (int i = 0; i < files.length; i++) {
            files[i].delete();
        }
    }

    /**
     * 获取临时文件存放目录
     *
     * @param context
     * @return
     */
    public static final String getTempFileDir(Context context) {
        String path = getFileDir(context);
        File file = new File(path);
        if (!file.exists())
            file.mkdirs();
        return path;
    }

    /**
     * 获取临时文件存放目录
     *
     * @param context
     * @return
     */
    public static final String getFileDir(Context context) {

        String path = null;
        try {
            path = isExternalMemoryAvailable() ? context
                    .getExternalFilesDir(null).getPath() + "/" : context
                    .getFilesDir().getPath() + "/";
        } catch (Exception e) {
            e.printStackTrace();
        }
        File file = new File(path);
        if (!file.exists())
            file.mkdirs();
        return path;
    }

    /**
     * 获取缓存目录
     *
     * @param context
     * @return
     */
    public static final String getCacheDir(Context context) {
        return isExternalMemoryAvailable() ? context.getExternalCacheDir()
                .getPath() + "/" : context.getCacheDir().getPath() + "/";
    }

    /**
     * 删除文件
     *
     * @param filepath
     */
    public static boolean deleteFile(String filepath) {
        File file = new File(filepath);
        return file.delete();
    }

    /**
     * 获取缓存名
     *
     * @param key
     * @return
     */
    public static String getKeyForCache(String key) {
        String cacheKey;
        try {
            MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(key.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }

    private static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    /**
     * lff 写数据
     *
     * @param context
     * @param fileName
     * @param bytes
     */
    public static void writeFileData(Context context, String fileName, byte[] bytes) {
        try {
            FileOutputStream fout = context.openFileOutput(fileName,
                    Context.MODE_PRIVATE);
            fout.write(bytes);
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * lff 读数据
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String readFileData(Context context, String fileName) {
        String res = "";
        try {
            FileInputStream fin = context.openFileInput(fileName);
            int length = fin.available();
            byte[] buffer = new byte[length];
            fin.read(buffer);
            res = new String(buffer, 0, buffer.length, "UTF-8");
            fin.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;

    }

    /**
     * lff
     * 从assets 读取
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String readFileFromAssets(Context context, String fileName) {
        String Result = "";
        try {
            InputStreamReader inputReader = new InputStreamReader(context
                    .getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            while ((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result;
    }

    /**
     * lff
     * 写入文件到 assets下
     *
     * @param context
     * @param fileName
     * @param message
     */
    public static void writeFileData(Context context, String fileName, String message) {
        try {
            FileOutputStream fout = context.openFileOutput(fileName,
                    Context.MODE_PRIVATE);
            byte[] bytes = message.getBytes();
            fout.write(bytes);
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除单个文件
     *
     * @param fileName 被删除文件的文件名
     * @return 单个文件删除成功返回true, 否则返回false
     */
    public static boolean deleteFiles(String fileName) {
        File file = new File(fileName);
        if (file.isFile() && file.exists()) {
            file.delete();
            return true;
        } else {
            System.out.println("删除单个文件" + fileName + "失败！");
            return false;
        }
    }

    /**
     * 删除目录（文件夹）以及目录下的文件
     *
     * @param dir 被删除目录的文件路径
     * @return 目录删除成功返回true, 否则返回false
     */
    public static boolean deleteDirectory(String dir) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator)) {
            dir = dir + File.separator;
        }
        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            System.out.println("删除目录失败" + dir + "目录不存在！");
            return false;
        }
        boolean flag = true;
        // 删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = deleteFiles(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
            // 删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
        }

        if (!flag) {
            System.out.println("删除目录失败");
            return false;
        }

        // 删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            System.out.println("删除目录" + dir + "失败！");
            return false;
        }
    }
}
