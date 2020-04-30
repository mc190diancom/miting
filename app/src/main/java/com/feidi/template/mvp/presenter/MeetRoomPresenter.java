package com.feidi.template.mvp.presenter;

import android.app.Application;

import com.feidi.template.mvp.model.LiveChatRoomDataBean;
import com.feidi.template.mvp.model.entity.LiveChatRoomBean;
import com.feidi.template.mvp.model.entity.bannerBean;
import com.google.gson.reflect.TypeToken;
import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import com.feidi.template.mvp.contract.MeetRoomContract;
import com.miu30.common.base.BaseData;

import java.util.ArrayList;
import java.util.List;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/23/2020 18:47
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@FragmentScope
public class MeetRoomPresenter extends BasePresenter<MeetRoomContract.Model, MeetRoomContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;
    @Inject
    ArrayList<LiveChatRoomBean.NegotiateBean> mDatas;

    @Inject
    public MeetRoomPresenter(MeetRoomContract.Model model, MeetRoomContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

    public void getRoomChatData(LiveChatRoomBean bean) {
        if(bean == null || bean.getNegotiate() == null || bean.getNegotiate().isEmpty()){
            return;
        }
        mDatas.addAll(bean.getNegotiate());
        mRootView.Notify();
    }

    public List<String> getBanner(String banner) {
        List<String> banners = new ArrayList<>();
        try {
            List<bannerBean> beans = BaseData.gson.fromJson(banner, new TypeToken<List<bannerBean>>() {
            }.getType());
            for (int i = 0; i < beans.size(); i++) {
                banners.add(beans.get(i).getUrl());
            }
        }catch (Exception e){

        }

        return banners;
    }
}
