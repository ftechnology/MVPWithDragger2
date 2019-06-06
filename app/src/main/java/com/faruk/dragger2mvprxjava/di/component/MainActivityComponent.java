package com.faruk.dragger2mvprxjava.di.component;

import android.content.Context;

import com.faruk.dragger2mvprxjava.MainActivity;
import com.faruk.dragger2mvprxjava.di.module.AdapterModule;
import com.faruk.dragger2mvprxjava.di.module.MainActivityMvpModule;
import com.faruk.dragger2mvprxjava.di.qualifier.ActivityContext;
import com.faruk.dragger2mvprxjava.di.scopes.ActivityScope;
import dagger.Component;


@ActivityScope
@Component(modules = {AdapterModule.class, MainActivityMvpModule.class}, dependencies = ApplicationComponent.class)
public interface MainActivityComponent {

    @ActivityContext
    Context getContext();
    void injectMainActivity(MainActivity mainActivity);
}
