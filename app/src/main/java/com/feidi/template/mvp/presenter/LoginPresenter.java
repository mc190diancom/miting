package com.feidi.template.mvp.presenter;

import android.app.Application;

import com.blankj.utilcode.util.SPUtils;
import com.feidi.template.mvp.contract.LoginContract;
import com.feidi.template.mvp.model.entity.UserBean;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.miu30.common.app.MyErrorHandleSubscriber;
import com.miu30.common.async.Result;
import com.miu30.common.config.Config;
import com.miu30.common.util.MapUtil;
import com.miu30.common.util.RxUtils;
import com.tencent.qcloud.tim.uikit.utils.MD5Utils;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;


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
public class LoginPresenter extends BasePresenter<LoginContract.Model, LoginContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public LoginPresenter(LoginContract.Model model, LoginContract.View rootView) {
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

    public void toLogin(String account, String pwd) {
        SPUtils userInfo = SPUtils.getInstance(Config.SP_USERINFO);
        Map<String, String> params = new HashMap<>();
        params.put("account", account);
        long l = System.currentTimeMillis();
        params.put("password", MD5Utils.getMD5String(l + MD5Utils.getMD5String(pwd)));
        params.put("clientType", "android");
        params.put("salt", l + "");
        mModel.toLogin(MapUtil.getRequestBody(params))
                .compose(RxUtils.applySchedulers(mRootView, true))
                .subscribe(new MyErrorHandleSubscriber<Result<UserBean>>() {
                    @Override
                    public void onNextResult(Result<UserBean> userBeanResult) throws Exception {
                        if (userBeanResult.ok()) {
                            UserBean data = userBeanResult.getData();
                            mRootView.LoginSuccess(data);
                            userInfo.put(Config.ACCOUNT, account);//用户名正确了才保存
                            userInfo.put(Config.USER_NAME, data.getUsername());
                            userInfo.put(Config.USER_ID, data.getId());
                        } else {
                            mRootView.showMessage(userBeanResult.getMsg());
                        }
                    }
                });
    }
}
