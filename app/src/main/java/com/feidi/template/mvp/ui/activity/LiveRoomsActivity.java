package com.feidi.template.mvp.ui.activity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.feidi.template.R;
import com.feidi.template.di.component.DaggerLiveRoomsComponent;
import com.feidi.template.mvp.contract.LiveRoomsContract;
import com.feidi.template.mvp.presenter.LiveRoomsPresenter;
import com.feidi.template.mvp.ui.adapter.LiveRoomAdapter;
import com.feidi.template.mvp.ui.fragment.LiveRoomFragment;
import com.feidi.template.mvp.ui.fragment.MeetRoomFragment;
import com.feidi.template.mvp.ui.view.NoScrollViewPager;
import com.jess.arms.di.component.AppComponent;
import com.miu30.common.base.BaseMvpActivity;
import com.miu30.common.util.StatusBarUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/26/2020 09:50
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class LiveRoomsActivity extends BaseMvpActivity<LiveRoomsPresenter> implements LiveRoomsContract.View {
    @BindView(R.id.mTabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.mViewPager)
    NoScrollViewPager mViewPager;
    @BindView(R.id.ivClose)
    ImageView ivClose;
    @BindView(R.id.fl_Title)
    FrameLayout flTitle;
    private List<String> mTitle = new ArrayList<>();
    private List<Fragment> liveRoomFragment = new ArrayList<>();

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerLiveRoomsComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_live_rooms; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initViewPager();
        initTableLayout();
    }

    private void initTableLayout() {
        mTitle.clear();
        mTitle.add(getResources().getString(R.string.live_top_title_live));
        mTitle.add(getResources().getString(R.string.live_top_title_chatroom));
        for (String s : mTitle) {
            View textView = getLayoutInflater().inflate(R.layout.live_title_textview, (ViewGroup) getWindow().getDecorView(), false);
            TabLayout.Tab tab = mTabLayout.newTab();
            tab.setCustomView(textView);
            TextView tvTitle = textView.findViewById(R.id.tvTitle);
            tvTitle.setText(s);
            mTabLayout.addTab(tab);
        }
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                TextView tvTitle = tab.getCustomView().findViewById(R.id.tvTitle);
                setCheck(true, tvTitle);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                TextView tvTitle = tab.getCustomView().findViewById(R.id.tvTitle);
                setCheck(false, tvTitle);

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setCheck(Boolean isCheck, TextView textView) {
        if (isCheck) {
            textView.setTextColor(getResources().getColor(R.color.txt_color_blue));
        } else {
            textView.setTextColor(getResources().getColor(R.color.txt_color_gray));
        }

    }

    @Override
    public void onBackPressed() {
        if (flTitle.getVisibility() == View.GONE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            super.onBackPressed();
        }
    }

    private void initViewPager() {
        LiveRoomFragment liveRoomFragment = LiveRoomFragment.newInstance();
        MeetRoomFragment meetRoomFragment = MeetRoomFragment.newInstance();
        this.liveRoomFragment.add(liveRoomFragment);
        this.liveRoomFragment.add(meetRoomFragment);
        mViewPager.setAdapter(new LiveRoomAdapter(getSupportFragmentManager(), this.liveRoomFragment));
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.removeAllTabs();
    }

    @OnClick(R.id.ivClose)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void showTitle(int visibility) {
        flTitle.setVisibility(visibility);
        if (visibility == View.GONE) {
            mViewPager.setScroll(false);
            getWindow().getDecorView().setPadding(0, 0, 0, 0);
        } else {
            mViewPager.setScroll(true);
            initBar();
        }
    }

}
