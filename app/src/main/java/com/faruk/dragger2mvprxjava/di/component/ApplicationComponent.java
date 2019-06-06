package com.faruk.dragger2mvprxjava.di.component;

import android.content.Context;


import com.faruk.dragger2mvprxjava.MyApplication;
import com.faruk.dragger2mvprxjava.di.module.ContextModule;
import com.faruk.dragger2mvprxjava.di.module.RetrofitModule;
import com.faruk.dragger2mvprxjava.di.qualifier.ApplicationContext;
import com.faruk.dragger2mvprxjava.di.scopes.ApplicationScope;
import com.faruk.dragger2mvprxjava.retrofit.APIInterface;

import dagger.Component;

@ApplicationScope
@Component(modules = {ContextModule.class, RetrofitModule.class})
public interface ApplicationComponent {

    APIInterface getApiInterface();

    @ApplicationContext
    Context getContext();

    void injectApplication(MyApplication myApplication);
}
