package com.feidi.template.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.feidi.template.di.module.LiveModule;
import com.feidi.template.mvp.contract.LiveContract;

import com.jess.arms.di.scope.FragmentScope;
import com.feidi.template.mvp.ui.fragment.LiveFragment;


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
@Component(modules = LiveModule.class, dependencies = AppComponent.class)
public interface LiveComponent {
    void inject(LiveFragment fragment);

    @Component.Builder
    interface Builder {
        @BindsInstance
        LiveComponent.Builder view(LiveContract.View view);

        LiveComponent.Builder appComponent(AppComponent appComponent);

        LiveComponent build();
    }
}