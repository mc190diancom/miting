package com.feidi.template.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.SPUtils;
import com.feidi.template.R;
import com.feidi.template.di.component.DaggerMainComponent;
import com.feidi.template.mvp.contract.MainContract;
import com.feidi.template.mvp.presenter.MainPresenter;
import com.feidi.template.mvp.ui.fragment.HomeFragment;
import com.feidi.template.mvp.ui.fragment.LiveFragment;
import com.feidi.template.mvp.ui.fragment.MineFragment;
import com.feidi.tim.chat.Constants;
import com.heima.tabview.library.TabView;
import com.heima.tabview.library.TabViewChild;
import com.jess.arms.di.component.AppComponent;
import com.miu30.common.base.BaseMvpActivity;
import com.miu30.common.config.Config;
import com.tencent.liteav.debug.GenerateTestUserSig;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.base.IUIKitCallBack;
import com.tencent.qcloud.tim.uikit.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/22/2020 09:40
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainContract.View {

    @BindView(R.id.tabView)
    TabView tabView;

    List<TabViewChild> tabViewChildList;
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerMainComponent
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setStatusBar();
        initPermission();
        initTabViewList();
        initTabViewClick();
        initImLogin();
    }

    @Override
    protected boolean useDefaultBg() {
        return false;
    }

    private void initImLogin() {
        String account = SPUtils.getInstance(Config.SP_USERINFO).getString(Config.ACCOUNT);
        String userSig = GenerateTestUserSig.genTestUserSig(account);
        TUIKit.login(account, userSig, new IUIKitCallBack() {
            @Override
            public void onError(String module, final int code, final String desc) {
                runOnUiThread(new Runnable() {
                    public void run() {
                        ToastUtil.toastLongMessage("loginError, errCode = " + code + ", errInfo = " + desc);
                    }
                });
                Log.i(TAG, "imLogin errorCode = " + code + ", errorInfo = " + desc);
            }

            @Override
            public void onSuccess(Object data) {
                SharedPreferences shareInfo = getSharedPreferences(Constants.USERINFO, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = shareInfo.edit();
                editor.putBoolean(Constants.AUTO_LOGIN, true);
                editor.commit();
            }
        });
    }

    private void initPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PermissionUtils.permission(PermissionConstants.STORAGE, PermissionConstants.MICROPHONE, PermissionConstants.CAMERA)
                    .request();
        }
    }

    private void initTabViewList() {
        tabViewChildList = new ArrayList<>();
        TabViewChild tabViewChild01 = new TabViewChild(R.drawable.main_icon_select, R.drawable.main_icon_no_select, getResources().getString(R.string.main_home_title), HomeFragment.newInstance());
        TabViewChild tabViewChild02 = new TabViewChild(R.drawable.live_icon_select, R.drawable.live_icon_no_select, getResources().getString(R.string.main_live_title), LiveFragment.newInstance());
        TabViewChild tabViewChild03 = new TabViewChild(R.drawable.mine_icon_select, R.drawable.mine_icon_no_select, getResources().getString(R.string.main_mine_title), MineFragment.newInstance());
        tabViewChildList.add(tabViewChild01);
        tabViewChildList.add(tabViewChild02);
        tabViewChildList.add(tabViewChild03);
    }


    private void initTabViewClick() {
        tabView.setTabViewChild(tabViewChildList,getSupportFragmentManager());
        tabView.setOnTabChildClickListener(new TabView.OnTabChildClickListener() {
            @Override
            public void onTabChildClick(int  position, ImageView currentImageIcon, TextView currentTextView) {
                //Toast.makeText(getApplicationContext(),"position:"+position,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
