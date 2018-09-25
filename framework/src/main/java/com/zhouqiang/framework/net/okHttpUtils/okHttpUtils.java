package com.zhouqiang.framework.net.okHttpUtils;

import com.zhouqiang.framework.exception.HttpException;
import com.zhouqiang.framework.util.Logger;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by hasee on 2018/5/11.
 */

public class okHttpUtils {
    OkHttpClient client;

    private okHttpUtils() {
        client = new OkHttpClient
                .Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
    }

    private static okHttpUtils single;

    //静态工厂方法
    public static okHttpUtils getInstance() {
        if (single == null) {
            single = new okHttpUtils();
        }
        return single;
    }

    /**
     * 同步请求  方法里面的第二个参数是一个接口，
     * 可以自行写一个接口目的是实现接口直接获取请求后的数据
     */
    public void getOkHttp(String url, final TaskLoadCallback callback) {
        callback.pre();
        //调用ok的get请求
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        final Call call = client.newCall(request);
        //同步execute
        //同步请求
        //同步是耗时的
        //同步execute需要开启子线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = call.execute();
                    if (response.isSuccessful()) {
                        String string = response.body().string();
                        //调用者只需要实现provide方法就能拿到这个String了
                        callback.getLoad(string);
                        callback.onEnd();
                    }
                } catch (IOException e) {
                    try {
                        callback.onFailure(e);
                    } catch (HttpException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    callback.onEnd();
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * get拼接传值方法
     *
     * @param url ?username=xxxx&password=xxx
     */
    public void getOkHttp(String url, Map<String, String> map, final TaskLoadCallback callback) {
        callback.pre();
        StringBuffer sb = new StringBuffer();
        String string = "";
        String result = "";
        //当用户传入null或者传了一个空的map
        if (map != null && !map.isEmpty()) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (sb == null) {
//                    sb = new StringBuffer ();
                    sb.append("?");
                } else {
                    //拼接好的网站去掉最后一个“&”符号
                    sb.append("&");
                }
                sb.append(entry.getKey() + "=" + entry.getValue());
            }
        }
        if (sb.toString() != null) {
            string = sb.toString();
            Logger.e("", string);
            result = url + string;
            Logger.e("", result);
        }
        Request request = new Request.Builder()
                .get() //声明我是get请求,如果不写默认就是get
                .url(string == "" ? url : result)//声明网站访问的网址
                .build();//创建Request
        Call call = client.newCall(request);
        //同步execute,异步enqueue
        //这里的同步是耗时的
        //而且OK 也没有为我们开启子线程‘
        // 如果你用同步方法的话，需要开启子线程

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                try {
                    callback.onFailure(e);
                } catch (HttpException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                callback.onEnd();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callback.onSucceed(response);
                callback.onEnd();
            }
        });

    }

    public void postOkhttp(String url, Map<String, String> map, final TaskLoadCallback callBack) {
        //上传文字格式 数据的传输，区别于多媒体输出
        callBack.pre();
        FormBody.Builder formbody = new FormBody.Builder();
        if (map != null && !map.isEmpty()) {
            //上传参数
            for (String key : map.keySet()) {
                formbody.add(key, map.get(key));
            }
            //创建请求体
            FormBody body = formbody.build();
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)//请求体
                    .build();
            Call call = client.newCall(request);
            //异步请求方式
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    try {
                        callBack.onFailure(e);
                    } catch (HttpException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    callBack.onEnd();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    callBack.onSucceed(response);
                    callBack.onEnd();
                }
            });

        } else {
            //创建请求体
            FormBody body = formbody.build();
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            Call call = client.newCall(request);
            //异步请求方式
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    try {
                        callBack.onFailure(e);
                    } catch (HttpException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    callBack.onEnd();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    callBack.onSucceed(response);
                    callBack.onEnd();
                }
            });
        }
    }

    /**
     * MultipartBody：用来提交包涵文件的参数
     *
     * @param path    :路径
     * @param map     ：普通参数
     * @param img     ：提交文件的关键字
     * @param imgPath ：提交文件的路径
     */
    public void postFileOkhttp(String path, HashMap<String, String> map, String img, String imgPath, final TaskLoadCallback callBack) {
        callBack.pre();
        MultipartBody.Builder requestBody = new MultipartBody.Builder();
        if (map != null && !map.isEmpty()) {
            //上传参数
            for (String key : map.keySet()) {
                requestBody.addFormDataPart(key, map.get(key));
            }
            File file = new File(imgPath);
            requestBody.addFormDataPart(img, file.getPath()
                    , RequestBody.create(MediaType.parse("File/*"), file));
            Request request = new Request.Builder()
                    .post(requestBody.build())
                    .url(path)
                    .build();
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    try {
                        callBack.onFailure(e);
                    } catch (HttpException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    callBack.onEnd();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    callBack.onSucceed(response);
                    callBack.onEnd();
                }
            });
        } else {
            File file = new File(imgPath);
            requestBody.addFormDataPart(img, file.getPath()
                    , RequestBody.create(MediaType.parse("File/*"), file));
            Request request = new Request.Builder()
                    .post(requestBody.build())
                    .url(path)
                    .build();
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    try {
                        callBack.onFailure(e);
                    } catch (HttpException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    callBack.onEnd();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    callBack.onSucceed(response);
                    callBack.onEnd();
                }
            });
        }
    }

    /**
     * MultipartBody：用来提交包涵文件的参数
     * 多文件上传
     */
    public void postFileOkhttp2(String path, HashMap<String, String> map, String img, List<String> imgPths, final TaskLoadCallback callBack) {
        callBack.pre();
        MultipartBody.Builder requestBody = new MultipartBody.Builder();
        if (map != null && !map.isEmpty()) {

            //上传参数
            for (String key : map.keySet()) {
                requestBody.addFormDataPart(key, map.get(key));
            }
            //遍历paths中所有图片绝对路径到builder，并约定key如“upload”作为后台接受多张图片的key
            if (imgPths != null) {
                for (String string : imgPths) {
                    File file = new File(string);
                    requestBody.addFormDataPart(img, file.getName(), RequestBody.create(MediaType.parse("File/*"), file));
                }
            }
            Request request = new Request.Builder()
                    .post(requestBody.build())
                    .url(path)
                    .build();
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    try {
                        callBack.onFailure(e);
                    } catch (HttpException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    callBack.onEnd();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    callBack.onSucceed(response);
                    callBack.onEnd();
                }
            });
        } else {
            //遍历paths中所有图片绝对路径到builder，并约定key如“upload”作为后台接受多张图片的key
            if (imgPths != null) {
                for (String string : imgPths) {
                    File file = new File(string);
                    requestBody.addFormDataPart(img, file.getName(), RequestBody.create(MediaType.parse("File/*"), file));
                }
            }
            Request request = new Request.Builder()
                    .post(requestBody.build())
                    .url(path)
                    .build();
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    try {
                        callBack.onFailure(e);
                    } catch (HttpException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    callBack.onEnd();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    callBack.onSucceed(response);
                    callBack.onEnd();
                }
            });
        }
    }
}
