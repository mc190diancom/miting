package com.miu30.common.base;

import android.app.KeyguardManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v4.app.FragmentActivity;

import com.miu30.common.ui.widget.MyProgressDialog;
import com.miu30.common.util.StatusBarUtils;
import com.miu360.library.R;

import java.lang.ref.SoftReference;

public class BaseActivity extends FragmentActivity {
    protected BaseActivity self;
    private MyProgressDialog waitDialog;

    private SoftReference<KeyguardManager.KeyguardLock> kl;
    private SoftReference<PowerManager.WakeLock> unLock;
    protected StatusBarUtils statusBarUtils;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        self = this;
        if (useDefaultBg()) {
            initBar();
        }
    }

    protected boolean useDefaultBg() {
        return true;
    }

    protected void initBar() {
        if (statusBarUtils==null){
            statusBarUtils = StatusBarUtils.with(this);
        }
        int statusBarHeight = StatusBarUtils.getStatusBarHeight(this);
        getWindow().getDecorView().setPadding(0, statusBarHeight, 0, 0);
        getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.white));
        statusBarUtils.init();
    }

    protected void setStatusBar() {
        //这里做了两件事情，1.使状态栏透明并使contentView填充到状态栏 2.预留出状态栏的位置，防止界面上的控件离顶部靠的太近。这样就可以实现开头说的第二种情况的沉浸式状态栏了
        StatusBarUtils.init(this, 0);
    }

    protected void onDestroy() {
        super.onDestroy();
        if (waitDialog != null && waitDialog.isShowing()) {
            waitDialog.dismiss();
        }
        if (statusBarUtils != null) {
            statusBarUtils.onDestory();
            statusBarUtils = null;
        }
    }

    public void setWaitDialog(MyProgressDialog waitDialog) {
        this.waitDialog = waitDialog;
    }

}
