package com.miu30.common.config;

import android.os.Environment;


/**
 * Created by Murphy on 2018/10/8.
 */
public class Config {
    public final static String DIR_PATH = Environment.getExternalStorageDirectory().getPath() + "/jicha/";
    public final static String PATH = Environment.getExternalStorageDirectory().getPath() + "/qh_inspect/";
    public final static String PATHROOT = Environment.getExternalStorageDirectory().getAbsolutePath();

    public static final String SYSTEMLOGOUT = "system_logout";//TCP发出退出通知
    public static final String IDCARD = "idCard";//身份证背夹通知

    public static final String SP_USERINFO = "SP_userInfo";


    public static final String ACCOUNT = "account";
    public static final String USER_NAME = "user_name";
    public static final String USER_ID = "user_id";

    public static final String USER_TOKEN = "user_token";

    // EventBus
    public static final String CHANGE = "chat_change";
}
