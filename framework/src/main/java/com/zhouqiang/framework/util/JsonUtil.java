package com.zhouqiang.framework.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Json工具类
 */
public class JsonUtil {

    /***
     * 解析为字符串
     *
     * @param jsonString
     * @param key
     * @return
     */
    public static String fromString(String jsonString, String key) {
        try {
            if (jsonString != null && jsonString.length() > 0) {
                JSONObject jsonObject = JSONObject.parseObject(jsonString);
                return jsonObject.getString(key);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /***
     * 解析为字符串
     *
     * @param jsonString
     * @param key
     * @return
     */
    public static int fromInt(String jsonString, String key) {
        try {
            if (jsonString != null && jsonString.length() > 0) {
                JSONObject jsonObject = JSONObject.parseObject(jsonString);
                return jsonObject.getInteger(key);
            } else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /***
     * 解析为布尔
     *
     * @param jsonString
     * @param key
     * @return
     */
    public static Boolean formBoolean(String jsonString, String key) {
        try {
            if (jsonString != null && jsonString.length() > 0) {
                JSONObject jsonObject = JSONObject.parseObject(jsonString);
                return jsonObject.getBoolean(key);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /***
     * 解析为数字
     *
     * @param jsonString
     * @param key
     * @return
     */
    public static Integer formInteger(String jsonString, String key) {
        try {
            if (jsonString != null && jsonString.length() > 0) {
                JSONObject jsonObject = JSONObject.parseObject(jsonString);
                return jsonObject.getInteger(key);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /***
     * 解析为长位十进制数
     *
     * @param jsonString
     * @param key
     * @return
     */
    public static BigDecimal formBigDecimal(String jsonString, String key) {
        try {
            if (jsonString != null && jsonString.length() > 0) {
                JSONObject jsonObject = JSONObject.parseObject(jsonString);
                return jsonObject.getBigDecimal(key);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /***
     * 解析为双精度
     *
     * @param jsonString
     * @param key
     * @return
     */
    public static Double formDouble(String jsonString, String key) {
        try {
            if (jsonString != null && jsonString.length() > 0) {
                JSONObject jsonObject = JSONObject.parseObject(jsonString);
                return jsonObject.getDouble(key);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /***
     * 解析为浮点数
     *
     * @param jsonString
     * @param key
     * @return
     */
    public static Long formlong(String jsonString, String key) {
        try {
            if (jsonString != null && jsonString.length() > 0) {
                JSONObject jsonObject = JSONObject.parseObject(jsonString);
                return jsonObject.getLong(key);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /***
     * 解析为浮点数
     *
     * @param jsonString
     * @param key
     * @return
     */
    public static Float formFloat(String jsonString, String key) {
        try {
            if (jsonString != null && jsonString.length() > 0) {
                JSONObject jsonObject = JSONObject.parseObject(jsonString);
                return jsonObject.getFloat(key);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /***
     * 解析为对象
     *
     * @param jsonString
     * @param key
     * @param t
     * @param <T>
     * @return
     */
    public static <T> T fromBean(String jsonString, String key, Class<T> t) {
        if (jsonString != null && jsonString.length() > 0) {
            try {
                JSONObject jsonObj = JSONObject.parseObject(jsonString);
                return JSONObject.toJavaObject(jsonObj.getJSONObject(key), t);
            } catch (Exception e) {
                return null;
            }
        } else {
            return null;
        }
    }

    /***
     * 解析为列表
     *
     * @param jsonString
     * @param key
     * @param t
     * @param <T>
     * @return
     */
    public static <T> ArrayList<T> fromList(String jsonString, String key, Class<T> t) {
        ArrayList<T> list = new ArrayList<T>();
        if (jsonString != null && jsonString.length() > 0) {
            try {
                JSONObject jsonObj = JSONObject.parseObject(jsonString);
                JSONArray inforArray = jsonObj.getJSONArray(key);
                for (int index = 0; index < inforArray.size(); index++) {
                    list.add(JSONObject.toJavaObject(
                            inforArray.getJSONObject(index), t));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    /***
     * 直接解析为bean
     *
     * @param jsonString
     * @param t
     * @param <T>
     * @return
     */
    public static <T> T fromBean(String jsonString, Class<T> t) throws Exception {
        return JSON.parseObject(jsonString, t);
    }

    /***
     * 直接解析为list
     *
     * @param jsonString
     * @param t
     * @param <T>
     * @return
     */
    public static <T> ArrayList<T> fromList(String jsonString, Class<T> t) throws Exception {
        ArrayList<T> list = new ArrayList<>();
        if (jsonString != null && jsonString.length() > 0) {
            list.addAll(JSON.parseArray(jsonString, t));
        }
        return list;
    }


    /***
     * 将列表转换为json
     *
     * @param listBean
     * @return
     */
    public static <T> String ArrayToJson(ArrayList<T> listBean) {
        String jsonString = JSON.toJSONString(listBean);
        return jsonString;
    }

    /***
     * 将类转为json
     *
     * @param <T>
     * @return
     */
    public static <T> String BeanToJson(Object obj) {
        String jsonsString = JSON.toJSONString(obj);
        return jsonsString;
    }

    /**
     * 用fastjson 将json字符串解析为一个 JavaBean
     *
     * @param jsonString
     * @param cls
     * @return
     */
    public static <T> T getJson(String jsonString, Class<T> cls) {
        T t = null;
        try {
            t = JSON.parseObject(jsonString, cls);
        } catch (Exception e) {
// TODO: handle exception
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 用fastjson 将json字符串 解析成为一个 List<JavaBean> 及 List<String>
     *
     * @param jsonString
     * @param cls
     * @return
     */
    public static <T> ArrayList<T> getArrayJson(String jsonString, Class<T> cls) {
        List<T> list = new ArrayList<T>();
        try {
            list = JSON.parseArray(jsonString, cls);
        } catch (Exception e) {
// TODO: handle exception
        }
        return (ArrayList<T>) list;
    }

    /**
     * 用fastjson 将json字符串 解析成为一个 List<JavaBean> 及 List<String>
     *
     * @param jsonString
     * @param cls
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> getArrayJson(String jsonString) {
        List<T> list = new ArrayList<T>();
        try {
            list = (List<T>) JSON.parseArray(jsonString);
        } catch (Exception e) {
// TODO: handle exception
        }
        return list;
    }


}
