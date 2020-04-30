package com.feidi.template.di.module;

import com.feidi.template.mvp.model.LiveChatRoomDataBean;
import com.feidi.template.mvp.ui.adapter.HomeAdaper;
import com.feidi.template.mvp.ui.adapter.LiveRoomChatAdaper;
import com.jess.arms.di.scope.FragmentScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import com.feidi.template.mvp.contract.HomeContract;
import com.feidi.template.mvp.model.HomeModel;

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
@Module
public abstract class HomeModule {

    @Binds
    abstract HomeContract.Model bindHomeModel(HomeModel model);


    @FragmentScope
    @Provides
    static ArrayList<String> provideLiveChatList() {
        return new ArrayList<>();
    }

    @FragmentScope
    @Provides
    static HomeAdaper providesLiveChatAdapter(ArrayList<String> data) {
        return new HomeAdaper(data);
    }
}