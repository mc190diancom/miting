package com.feidi.template.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import com.feidi.template.mvp.contract.TRTCVedioRoomActivityContract;
import com.feidi.template.mvp.model.TRTCVedioRoomActivityModel;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/27/2020 17:12
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
public abstract class TRTCVedioRoomActivityModule {

    @Binds
    abstract TRTCVedioRoomActivityContract.Model bindTRTCVedioRoomActivityModel(TRTCVedioRoomActivityModel model);
}