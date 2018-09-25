package com.zhouqiang.framework.util;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Http访问工具类
 * GSON
 *
 * @author
 */
public class JsonUtility {
    /**
     * 将json转化为对象。
     *
     * @param json     Json字符串
     * @param classOfT 对象class
     * @return 对象
     */
    public static <T> T fromJson(String json, Class<T> classOfT) {
        try {
            return new Gson().fromJson(json, classOfT);
        } catch (JsonSyntaxException e) {
            Log.e("JsonUtility", "fromJson is null!");
            return null;
        }
    }

    /**
     * 将json转化为对象。
     *
     * @param element   json元素
     * @param typeToken 类型
     * @return 对象
     */
    public static <T> T fromJson(JsonElement element, TypeToken<T> typeToken) {

        T t = null;
        try {

            t = new Gson().fromJson(element, typeToken.getType());
        } catch (JsonSyntaxException e) {
            Log.e("JsonUtility", "fromJson<T> is null!");
        }
        return t;
    }

    /**
     * 将字符串解析为Json对象。
     *
     * @param json Json字符串
     * @return 对象
     */
    public static JsonObject parse(String json) {
        JsonObject object = null;
        try {
            object = new JsonParser().parse(json).getAsJsonObject();
        } catch (JsonSyntaxException e) {
            Log.e("JsonUtility", "parse is null!");
        }
        return object;
    }

    /**
     * 取得Json元素。
     *
     * @param json json字符串
     * @param key  元素key
     * @return Json元素
     */
    public static JsonElement getElement(String json, String key) {

        try {
            JsonElement element = getElement(parse(json), key);
            return element;
        } catch (Exception e) {
            Log.e("JsonUtility", "getElement is null!");
            return null;
        }
    }

    /**
     * 取得Json元素。
     *
     * @param object json对象
     * @param key    元素key
     * @return Json元素
     */
    public static JsonElement getElement(JsonObject object, String key) {

        JsonElement element = null;
        try {
            element = parseElement(object.get(key));
        } catch (Exception e) {
            Log.e("JsonUtility", "getElement is null!");
        }
        return element;
    }

    /**
     * Json元素转换。
     *
     * @param element 元素
     * @return 转换后Json元素
     */
    private static JsonElement parseElement(JsonElement element) {

        if (element instanceof JsonNull) {
            return null;
        }

        return element;
    }

    /**
     * 取得字符串。
     *
     * @param element json元素
     * @return 字符串
     */
    public static String getAsString(JsonElement element) {
        if (element != null) {
            String str = null;
            try {
                str = element.getAsString();
            } catch (Exception e) {
                Log.e("JsonUtility", "getAsString is null!");
            }
            return str;
        } else {
            return null;
        }
    }

    /**
     * 取得整数。
     *
     * @param element json元素
     * @return 整数
     */
    public static int getAsInt(JsonElement element) {
        if (element != null) {
            int i = -9999;
            try {
                i = element.getAsInt();
            } catch (Exception e) {
                Log.e("JsonUtility", "getAsInt is null!");
            }
            return i;
        } else {
            return -999;
        }
    }

    /**
     * 取得数值。
     *
     * @param element json元素
     * @return 数值
     */
    public static double getAsDouble(JsonElement element) {
        if (element != null) {
            double d = -999.9;
            try {
                d = element.getAsDouble();
            } catch (Exception e) {
                Log.e("JsonUtility", "getAsDouble is null!");
            }
            return d;
        } else {
            return -999.9;
        }
    }

    /**
     * 取得布尔值。
     *
     * @param element json元素
     * @return 布尔值
     */
    public static boolean getAsBoolean(JsonElement element) {
        if (element != null) {
            boolean b = false;
            try {
                b = element.getAsBoolean();
            } catch (Exception e) {
                Log.e("JsonUtility", "getAsBoolean is null!");
            }
            return b;
        } else {
            return false;
        }
    }

    /**
     * 将json转化为对象。
     *
     * @param element  json元素
     * @param classOfT 对象class
     * @return 对象
     */
    public static <T> T fromJson(JsonElement element, Class<T> classOfT) {

        try {
            return new Gson().fromJson(element, classOfT);
        } catch (JsonSyntaxException e) {
            Log.e("JsonUtility", "fromJson<T> is null!");
            return null;
        }
    }

    /**
     * 将json转化为对象。
     *
     * @param json      json字符串
     * @param typeToken 类型
     * @return 对象
     */
    public static <T> T fromJson(String json, TypeToken<T> typeToken) {
        T t = null;
        try {
            t = new Gson().fromJson(json, typeToken.getType());
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return t;
    }


    /***
     * 将json转为对象
     *
     * @param json 字符串
     * @param key  名称
     * @param t    类型
     * @return 对象
     */
    public static <T> T fromBean(String json, String key, Class<T> t) {
        if (json != null) {
            try {
                return fromJson(getElement(parse(json), key),
                        new TypeToken<T>() {
                        });
            } catch (Exception e) {
                Log.e("JsonUtility", "fromJson<T> is null!");
                return null;
            }
        } else {
            return null;
        }
    }

    /***
     * 将json转为对象
     *
     * @param json 字符串
     * @param key  名称
     * @param t    类型
     * @return 对象
     */
    public static <T> ArrayList<T> fromList(String json, String key, Class<T> t) {
        if (json != null) {
            try {
                return fromJson(getElement(parse(json), key),
                        new TypeToken<ArrayList<T>>() {
                        });
            } catch (Exception e) {
                Log.e("JsonUtility", "fromList<T> is null!");
                return null;
            }
        } else {
            return null;
        }
    }

    /***
     * 将json转为对象
     *
     * @param json 字符串
     * @return 对象
     */
    public static <T> ArrayList<T> fromList(String json, TypeToken<ArrayList<T>> typeToken) {
        if (json != null) {
            try {
                ArrayList<T> o = new Gson().fromJson(json, typeToken.getType());
                return o;
            } catch (Exception e) {
                Log.e("JsonUtility", "fromList<T> is null!");
                return null;
            }
        } else {
            return null;
        }
    }

    /***
     * 将json转为对象
     *
     * @param json 字符串
     * @param key  名称
     * @param t    类型
     * @return 对象
     */
    public static <T> T fromListToObject(String json, String key, Class<T> t) {
        if (json != null) {
            try {
                return new Gson().fromJson(getElement(parse(json), key),
                        t);

            } catch (Exception e) {
                Log.e("JsonUtility", "fromList<T> is null!");
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * 私有构造函数
     */
    private JsonUtility() {
        // 禁止实例化
    }

    /**
     * 将传入的json字符串按类模板解析成对象
     *
     * @param json 需要解析的json字符串
     * @param cls  类模板
     * @return 解析好的对象
     */
    public static <T> T getObj(String json, Class<T> cls) {
        Gson gson = new Gson();
        T bean = (T) gson.fromJson(json, cls);
        return bean;
    }

    /**
     * 将获取到的json字符串转换为对象集合进行返回
     *
     * @param jsonData 需要解析的json字符串
     * @param cls      类模板
     * @return
     */
    public static <T> ArrayList<T> getObjList(String jsonData, Class<T> cls) {
        try {
            List<T> list = new ArrayList<T>();
            if (jsonData.startsWith("[") && jsonData.endsWith("]")) {//当字符串以“[”开始，以“]”结束时，表示该字符串解析出来为集合
                //截取字符串，去除中括号
                jsonData = jsonData.substring(1, jsonData.length() - 1);
                //将字符串以"},"分解成数组
                String[] strArr = jsonData.split("\\},");
                //分解后的字符串数组的长度
                int strArrLength = strArr.length;
                //遍历数组，进行解析，将字符串解析成对象
                for (int i = 0; i < strArrLength; i++) {
                    String newJsonString = null;
                    if (i == strArrLength - 1) {
                        newJsonString = strArr[i];
                    } else {
                        newJsonString = strArr[i] + "}";
                    }
                    T bean = fromJson(newJsonString, cls);
                    list.add(bean);
                }
            }
            if (list == null || list.size() == 0) {
                return null;
            }
            return (ArrayList<T>) list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

/*	*//**
     * 字符串转JSON
     *
     * @param s
     *            需要转换的字符串
     * @return JSONObject
     * @throws DataParseException
     *//*
    public static JSONObject toJsonObject(String s) throws DataParseException {
		if (s != null && s.startsWith("\ufeff")) // 避免低版本utf-8bom头问题
			s = s.substring(1);
		try {
			return new JSONObject(s.trim());
		} catch (Exception e) {
			throw new DataParseException(e);
		}
	}*/
}
