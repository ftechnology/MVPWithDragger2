package com.faruk.dragger2mvprxjava.di.module;

import android.content.Context;
import com.faruk.dragger2mvprxjava.di.qualifier.ApplicationContext;
import com.faruk.dragger2mvprxjava.di.scopes.ApplicationScope;
import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    private Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @ApplicationScope
    @ApplicationContext
    public Context provideContext() {
        return context;
    }
}
