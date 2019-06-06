package com.faruk.dragger2mvprxjava.di.module;

import com.faruk.dragger2mvprxjava.MainActivity;
import com.faruk.dragger2mvprxjava.RecyclerViewAdapter;
import com.faruk.dragger2mvprxjava.di.scopes.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module(includes = {MainActivityContextModule.class})
public class AdapterModule {

    @Provides
    @ActivityScope
    public RecyclerViewAdapter getCoinList(RecyclerViewAdapter.ClickListener clickListener) {
        return new RecyclerViewAdapter(clickListener);
    }

    @Provides
    @ActivityScope
    public RecyclerViewAdapter.ClickListener getClickListener(MainActivity mainActivity) {
        return mainActivity;
    }
}
