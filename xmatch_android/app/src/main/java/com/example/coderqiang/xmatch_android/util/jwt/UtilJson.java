package com.example.coderqiang.xmatch_android.util.jwt;

import com.google.gson.GsonBuilder;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UtilJson {
    private static final String TAG = UtilJson.class.getSimpleName();

    public static <T> T parseToObject(String json, Class<T> x) {
        return new GsonBuilder().serializeNulls().create().fromJson(json, x);
    }

    public static <T> String parseToJson(Object o, Class<T> x) {
        return new GsonBuilder().serializeNulls().create().toJson(o, (Type) x);
    }

    public static <T> String parseToJsonList(List<T> list, Class<T> x) {
        String retJson = "[";
        int i = 0;
        for (T t : list) {
            if (i > 0) {
                retJson = retJson + ",";
            }
            retJson = retJson + parseToJson(t, x);
            i++;
        }
        return retJson + "]";
    }

    public static <T> List<T> parseToObjectList(String json, Class<T> x) {
        List<T> list = new ArrayList();
        try {
            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                list.add(parseToObject(array.getJSONObject(i).toString(), x));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
