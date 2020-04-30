package com.feidi.template.mvp.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.blankj.utilcode.util.SPUtils;
import com.feidi.template.R;
import com.miu30.common.config.Config;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme_Launcher);
        String string = SPUtils.getInstance(Config.SP_USERINFO).getString(Config.USER_TOKEN);

        String account = SPUtils.getInstance(Config.SP_USERINFO).getString(Config.ACCOUNT);

        if (TextUtils.isEmpty(string) || TextUtils.isEmpty(account)) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        finish();
    }
}
