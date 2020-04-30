package com.feidi.template.mvp.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.feidi.template.R;
import com.feidi.template.di.component.DaggerHomeComponent;
import com.feidi.template.mvp.contract.HomeContract;
import com.feidi.template.mvp.presenter.HomePresenter;
import com.feidi.template.mvp.ui.adapter.HomeAdaper;
import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.di.component.AppComponent;
import com.miu30.common.api.Api;
import com.miu30.common.base.BaseMvpFragment;
import com.miu30.common.ui.WebActivity;
import com.miu30.common.util.SpacesItemDecoration;
import com.miu30.common.util.UIUtils;
import com.miu30.common.util.WebUtil;
import com.miu30.common.view.WebViewController;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/22/2020 10:25
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class HomeFragment extends BaseMvpFragment<HomePresenter> implements HomeContract.View {

    @BindView(R.id.rv_home_list)
    RecyclerView rvHomeList;
    @BindView(R.id.wv_home)
    WebView wvHome;
    @BindView(R.id.more_controller)
    WebViewController moreController;
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerHomeComponent
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initwebV();
    }

    private String url = Api.APP_DOMAIN + "odm/mihalls/h5/index.html";
    private void initwebV() {
        wvHome.loadUrl(url);
        List<WebUtil.HandleCallback> handlers = new ArrayList<>();
        WebUtil.wrapWebView(wvHome, moreController, handlers, null);
        moreController.setWebView(wvHome);
    }

    //////////////////////////////////////////////////////////////////////////////////
    private void initRv() {
        rvHomeList.setAdapter(mPresenter.getAdapter());
        rvHomeList.setLayoutManager(mPresenter.getLayoutManager());
    }

    @Override
    public Activity getFragmentActivity() {
        return self;
    }
}
