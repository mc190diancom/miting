package com.miu30.common.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;
import com.miu30.common.MiuBaseApp;

public class ToastUtil {

    private static volatile ToastUtil toastUtil;
    private static View centerToastView;
    private static Handler uiHandler = new Handler(Looper.getMainLooper());
    private static Toast toast;

    public static ToastUtil getInstance(Context context) {
        if (toastUtil == null) {
            synchronized (ToastUtil.class) {
                if (toastUtil == null) {
                    toastUtil = new ToastUtil();
                    //initLayout(context);
                }
            }
        }

        return toastUtil;
    }

    /*private static void initLayout(Context context) {
        if (centerToastView == null) {
            //LayoutInflater的作用：对于一个没有被载入或者想要动态载入的界面，都需要LayoutInflater.inflate()来载入，LayoutInflater是用来找res/layout/下的xml布局文件，并且实例化
            LayoutInflater inflater = LayoutInflater.from(context);//调用Activity的getLayoutInflater()
            //加載layout下的布局
            centerToastView = inflater.inflate(R.layout.layout_toast, null);
        }

    }

    public static void clearToast() {
        if (uiHandler != null) {
            uiHandler.removeCallbacksAndMessages(null);
        }
        if (toast != null) {
            toast.cancel();
        }
        uiHandler = null;
        toast = null;
        centerToastView = null;
        toastUtil = null;
    }

    *//**
     * 将Toast封装在一个方法中，在其它地方使用时直接输入要弹出的内容即可
     *//*
    public void toastMessage(final Context context, String msg, final int duration) {
        try {
            if (TextUtils.isEmpty(msg) || centerToastView == null) {
                return;
            }

            TextView tv = centerToastView.findViewById(R.id.tv_context);
            tv.setText(msg);
            if (Looper.myLooper() == Looper.getMainLooper()) {
                showCenterToast(context, duration, centerToastView);
            } else {
                if (uiHandler != null) {
                    uiHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            showCenterToast(context, duration, centerToastView);
                        }
                    });
                }

            }

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }


    }*/

    public void showCenterToast(Context context, final String resId,final int duration) {
            try {
                if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                    //这里这样是为了避免多次执行这个方法,弹出框叠加
                    if (toast == null) {
                        toast = Toast.makeText(MiuBaseApp.self, resId, duration);
                    } else {
                        toast.setText(resId);
                    }
                    toast.show();
                } else {
                    uiHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (toast == null) {
                                toast = Toast.makeText(MiuBaseApp.self, resId, duration);
                            } else {
                                toast.setText(resId);
                            }
                            toast.show();
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

    }

}
