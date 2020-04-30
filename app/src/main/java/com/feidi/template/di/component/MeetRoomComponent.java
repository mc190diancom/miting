package com.feidi.template.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.feidi.template.di.module.MeetRoomModule;
import com.feidi.template.mvp.contract.MeetRoomContract;

import com.jess.arms.di.scope.FragmentScope;
import com.feidi.template.mvp.ui.fragment.MeetRoomFragment;


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
@FragmentScope
@Component(modules = MeetRoomModule.class, dependencies = AppComponent.class)
public interface MeetRoomComponent {
    void inject(MeetRoomFragment fragment);

    @Component.Builder
    interface Builder {
        @BindsInstance
        MeetRoomComponent.Builder view(MeetRoomContract.View view);

        MeetRoomComponent.Builder appComponent(AppComponent appComponent);

        MeetRoomComponent build();
    }
}