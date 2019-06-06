package com.faruk.dragger2mvprxjava.di.module;

import com.faruk.dragger2mvprxjava.di.scopes.ActivityScope;
import com.faruk.dragger2mvprxjava.mvp.MainActivityContract;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityMvpModule {
    private final MainActivityContract.View mView;


    public MainActivityMvpModule(MainActivityContract.View mView) {
        this.mView = mView;
    }

    @Provides
    @ActivityScope
    MainActivityContract.View provideView() {
        return mView;
    }


}
