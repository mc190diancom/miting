package com.feidi.template.mvp.presenter;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.SizeUtils;
import com.feidi.template.mvp.ui.adapter.HomeAdaper;
import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import com.feidi.template.mvp.contract.HomeContract;
import com.miu30.common.api.Api;
import com.miu30.common.ui.WebActivity;
import com.miu30.common.ui.widget.MultiVeriticalItemDecoration;
import com.miu30.common.util.SpacesItemDecoration;

import java.util.ArrayList;


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
@FragmentScope
public class HomePresenter extends BasePresenter<HomeContract.Model, HomeContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;

    @Inject
    HomeAdaper mAdapter;

    @Inject
    ArrayList<String> mData;

    @Inject
    public HomePresenter(HomeContract.Model model, HomeContract.View rootView) {
        super(model, rootView);
    }

    public void getListData() {
        mData.add("第一个");
        mData.add("第二个");
        mData.add("第三个");
        mData.add("第四个");
        mData.add("第五个");
        mData.add("第六个");
        mData.add("第七个");
        mData.add("第八个");
        mData.add("第九个");
        mAdapter.notifyDataSetChanged();
        mAdapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int viewType, Object data, int position) {
                if(position != 0){
                    Intent intent = WebActivity.getTargetIntent(mRootView.getFragmentActivity(),"热点资讯", Api.APP_DOMAIN + "odm/mihalls/h5/index.html",true,true);
                    mRootView.getFragmentActivity().startActivity(intent);
                }
            }
        });
    }

    public RecyclerView.Adapter getAdapter() {
        return mAdapter;
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        final GridLayoutManager layoutManager = new GridLayoutManager(mRootView.getFragmentActivity(), 2);
        //调用以下方法让RecyclerView的第一个条目仅为1列
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                //如果位置是0，那么这个条目将占用SpanCount()这么多的列数，再此也就是3
                //而如果不是0，则说明不是Header，就占用1列即可
                return mAdapter.isHeader(position) ? layoutManager.getSpanCount() : 1;
            }
        });
        return layoutManager;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
    }

}
