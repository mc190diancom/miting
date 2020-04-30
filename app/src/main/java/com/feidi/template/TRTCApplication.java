package com.feidi.template;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;

import com.blankj.utilcode.util.ServiceUtils;
import com.feidi.template.mvp.ui.service.CallService;
import com.feidi.tim.helper.ConfigHelper;
import com.miu30.common.MiuBaseApp;
import com.tencent.imsdk.session.SessionWrapper;
import com.tencent.liteav.debug.GenerateTestUserSig;
import com.tencent.qcloud.tim.uikit.TUIKit;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TRTCApplication extends MiuBaseApp {
    @Override
    public void onCreate() {
        super.onCreate();
        closeAndroidPDialog();
        initShare();
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
                if (!ServiceUtils.isServiceRunning(CallService.class)) {//ProfileManager.getInstance().isLogin()
                    startCallService();
                }
            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
        if (SessionWrapper.isMainProcess(getApplicationContext())) {
            /**
             * TUIKit的初始化函数
             *
             * @param context  应用的上下文，一般为对应应用的ApplicationContext
             * @param sdkAppID 您在腾讯云注册应用时分配的sdkAppID
             * @param configs  TUIKit的相关配置项，一般使用默认即可，需特殊配置参考API文档
             */
            TUIKit.init(this, GenerateTestUserSig.SDKAPPID, new ConfigHelper().getConfigs(getApplicationContext()));


        }
    }

    private void startCallService() {
        Intent intent = new Intent(this, CallService.class);
        startService(intent);
    }

    private void initShare() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
            builder.detectFileUriExposure();
        }
    }

    private void closeAndroidPDialog() {
        try {
            Class       aClass              = Class.forName("android.content.pm.PackageParser$Package");
            Constructor declaredConstructor = aClass.getDeclaredConstructor(String.class);
            declaredConstructor.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Class  cls            = Class.forName("android.app.ActivityThread");
            Method declaredMethod = cls.getDeclaredMethod("currentActivityThread");
            declaredMethod.setAccessible(true);
            Object activityThread         = declaredMethod.invoke(null);
            Field  mHiddenApiWarningShown = cls.getDeclaredField("mHiddenApiWarningShown");
            mHiddenApiWarningShown.setAccessible(true);
            mHiddenApiWarningShown.setBoolean(activityThread, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
