package com.feidi.template.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.feidi.template.di.module.TRTCVedioRoomActivityModule;
import com.feidi.template.mvp.contract.TRTCVedioRoomActivityContract;

import com.jess.arms.di.scope.ActivityScope;
import com.feidi.template.mvp.ui.activity.TRTCVedioRoomActivity;


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
@ActivityScope
@Component(modules = TRTCVedioRoomActivityModule.class, dependencies = AppComponent.class)
public interface TRTCVedioRoomActivityComponent {
    void inject(TRTCVedioRoomActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        TRTCVedioRoomActivityComponent.Builder view(TRTCVedioRoomActivityContract.View view);

        TRTCVedioRoomActivityComponent.Builder appComponent(AppComponent appComponent);

        TRTCVedioRoomActivityComponent build();
    }
}