package com.faruk.dragger2mvprxjava;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.faruk.dragger2mvprxjava.di.component.ApplicationComponent;
import com.faruk.dragger2mvprxjava.di.component.DaggerMainActivityComponent;
import com.faruk.dragger2mvprxjava.di.component.MainActivityComponent;
import com.faruk.dragger2mvprxjava.di.module.MainActivityContextModule;
import com.faruk.dragger2mvprxjava.di.module.MainActivityMvpModule;
import com.faruk.dragger2mvprxjava.di.qualifier.ActivityContext;
import com.faruk.dragger2mvprxjava.di.qualifier.ApplicationContext;
import com.faruk.dragger2mvprxjava.mvp.MainActivityContract;
import com.faruk.dragger2mvprxjava.mvp.PresenterImpl;
import com.faruk.dragger2mvprxjava.data.AppData;


import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View, RecyclerViewAdapter.ClickListener {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    MainActivityComponent mainActivityComponent;

    @Inject
    public RecyclerViewAdapter recyclerViewAdapter;


    @Inject
    @ApplicationContext
    public Context mContext;

    @Inject
    @ActivityContext
    public Context activityContext;

    @Inject
    PresenterImpl presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ApplicationComponent applicationComponent = MyApplication.get(this).getApplicationComponent();
        mainActivityComponent = DaggerMainActivityComponent.builder()
                .mainActivityContextModule(new MainActivityContextModule(this))
                .mainActivityMvpModule(new MainActivityMvpModule(this))
                .applicationComponent(applicationComponent)
                .build();

        mainActivityComponent.injectMainActivity(this);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(activityContext));
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration itemDecorator = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider));
        recyclerView.addItemDecoration(itemDecorator);

        progressBar = findViewById(R.id.progressBar);

        presenter.loadData();


    }

    @Override
    public void launchIntent(String name) {
        Toast.makeText(mContext, name, Toast.LENGTH_SHORT).show();
        // startActivity(new Intent(activityContext, DetailActivity.class).putExtra("name", name));
    }

    @Override
    public void showData(List<AppData> data) {
        recyclerViewAdapter.setData(data);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showComplete() {

    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }
}
