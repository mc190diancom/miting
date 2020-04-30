package com.feidi.template.mvp.model;

import android.app.Application;

import com.feidi.template.di.api.ApiService;
import com.feidi.template.mvp.model.entity.LiveChatRoomBean;
import com.feidi.template.mvp.model.entity.UserBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.FragmentScope;

import javax.inject.Inject;

import com.feidi.template.mvp.contract.LiveContract;
import com.miu30.common.async.Result;
import com.miu30.common.util.RxUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/22/2020 10:26
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@FragmentScope
public class LiveModel extends BaseModel implements LiveContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public LiveModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<Result<LiveChatRoomBean>> invate(String requestBody, String token) {
        return mRepositoryManager.obtainRetrofitService(ApiService.class).invate(requestBody,token)
                .compose(RxUtils.convertStringToEntity("content", new TypeToken<LiveChatRoomBean>() {
                }));
    }
}