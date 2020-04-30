package com.feidi.template.di.module;

import com.jess.arms.di.scope.FragmentScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import com.feidi.template.mvp.contract.LiveRoomFragmentContract;
import com.feidi.template.mvp.model.LiveRoomFragmentModel;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/23/2020 18:46
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
public abstract class LiveRoomFragmentModule {

    @Binds
    abstract LiveRoomFragmentContract.Model bindLiveRoomFragmentModel(LiveRoomFragmentModel model);
}