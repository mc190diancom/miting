package com.miu30.common.app;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.TypeAdapter;
import com.miu30.common.async.Result;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by Murphy on 2018/2/2.
 * 参照GsonResponseBodyConverter，重写主要为了解析各种类型的返回数据
 */

public class JsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    public static final int CASTERROR = 110;
    private static final String TAG = "StringResponseBodyConver";
    private Type type;
    private Gson gson;

    JsonResponseBodyConverter(Gson gson, Type type) {
        this.type = type;
        this.gson = gson;
    }

    @Override
    public T convert(ResponseBody value) {
        Result result = new Result();
        try {
            String string = value.string();//只能调用一次

            result.setData(string);
        } catch (Exception e) {
            result.setError(-10);
            e.printStackTrace();
            result.setThrowable(e);
        }
        return (T) result;
    }

}
