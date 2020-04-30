package com.feidi.template.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.feidi.template.di.module.LiveRoomFragmentModule;
import com.feidi.template.mvp.contract.LiveRoomFragmentContract;

import com.jess.arms.di.scope.FragmentScope;
import com.feidi.template.mvp.ui.fragment.LiveRoomFragment;


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
@FragmentScope
@Component(modules = LiveRoomFragmentModule.class, dependencies = AppComponent.class)
public interface LiveRoomFragmentComponent {
    void inject(LiveRoomFragment fragment);

    @Component.Builder
    interface Builder {
        @BindsInstance
        LiveRoomFragmentComponent.Builder view(LiveRoomFragmentContract.View view);

        LiveRoomFragmentComponent.Builder appComponent(AppComponent appComponent);

        LiveRoomFragmentComponent build();
    }
}