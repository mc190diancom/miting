package com.feidi.template.mvp.presenter;

import android.app.Application;

import com.blankj.utilcode.util.SPUtils;
import com.feidi.template.mvp.model.entity.LiveChatRoomBean;
import com.feidi.template.mvp.model.entity.UserBean;
import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import com.feidi.template.mvp.contract.LiveContract;
import com.miu30.common.app.MyErrorHandleSubscriber;
import com.miu30.common.async.Result;
import com.miu30.common.config.Config;
import com.miu30.common.util.MapUtil;
import com.miu30.common.util.RxUtils;
import com.miu30.common.util.UIUtils;
import com.tencent.qcloud.tim.uikit.utils.MD5Utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
public class LivePresenter extends BasePresenter<LiveContract.Model, LiveContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;

    @Inject
    public LivePresenter(LiveContract.Model model, LiveContract.View rootView) {
        super(model, rootView);
    }

    public void invate(String invateCode) {
        SPUtils userInfo = SPUtils.getInstance(Config.SP_USERINFO);
        Map<String, String> params = new HashMap<>();
        params.put("invitationCode", invateCode);
        mModel.invate(invateCode,userInfo.getString(Config.USER_TOKEN))
                .compose(RxUtils.applySchedulers(mRootView, true))
                .subscribe(new MyErrorHandleSubscriber<Result<LiveChatRoomBean>>() {
                    @Override
                    public void onNextResult(Result<LiveChatRoomBean> result) throws Exception {
                        if (result.ok()) {
                            if(result.getData() == null){
                                mRootView.showMessage("获取到数据为空");
                            }else{
                                mRootView.Success(result.getData());
                            }
                        } else {
                            mRootView.showMessage(result.getMsg());
                        }
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
    }
}
