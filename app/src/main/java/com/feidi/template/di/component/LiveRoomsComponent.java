package com.feidi.template.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.feidi.template.di.module.LiveRoomsModule;
import com.feidi.template.mvp.contract.LiveRoomsContract;

import com.jess.arms.di.scope.ActivityScope;
import com.feidi.template.mvp.ui.activity.LiveRoomsActivity;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/26/2020 09:50
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(modules = LiveRoomsModule.class, dependencies = AppComponent.class)
public interface LiveRoomsComponent {
    void inject(LiveRoomsActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        LiveRoomsComponent.Builder view(LiveRoomsContract.View view);

        LiveRoomsComponent.Builder appComponent(AppComponent appComponent);

        LiveRoomsComponent build();
    }
}