package com.feidi.template.mvp.model;

import android.app.Application;

import com.feidi.template.di.api.ApiService;
import com.feidi.template.mvp.contract.LoginContract;
import com.feidi.template.mvp.model.entity.UserBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.miu30.common.async.Result;
import com.miu30.common.util.RxUtils;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;



/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/27/2020 10:11
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
public class LoginModel extends BaseModel implements LoginContract.Model {
    private static final String TAG = "LoginModel";
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public LoginModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<Result<UserBean>> toLogin(RequestBody params) {
        return mRepositoryManager.obtainRetrofitService(ApiService.class).toLogin(params)
                .compose(RxUtils.convertStringToEntity("content", new TypeToken<UserBean>() {
                }));
    }


}