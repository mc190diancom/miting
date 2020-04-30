package com.feidi.template.di.module;

import com.feidi.template.mvp.model.LiveChatRoomDataBean;
import com.feidi.template.mvp.model.entity.LiveChatRoomBean;
import com.feidi.template.mvp.ui.adapter.LiveRoomChatAdaper;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.di.scope.FragmentScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import com.feidi.template.mvp.contract.MeetRoomContract;
import com.feidi.template.mvp.model.MeetRoomModel;
import com.miu30.common.ui.entity.DriverInfo;

import java.util.ArrayList;


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
@Module
public abstract class MeetRoomModule {

    @Binds
    abstract MeetRoomContract.Model bindMeetRoomModel(MeetRoomModel model);


    @FragmentScope
    @Provides
    static ArrayList<LiveChatRoomBean.NegotiateBean> provideLiveChatList() {
        return new ArrayList<>();
    }

    @FragmentScope
    @Provides
    static LiveRoomChatAdaper providesLiveChatAdapter(ArrayList<LiveChatRoomBean.NegotiateBean> data) {
        return new LiveRoomChatAdaper(data);
    }
}