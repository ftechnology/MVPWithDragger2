package com.faruk.dragger2mvprxjava.mvp;

import com.faruk.dragger2mvprxjava.data.AppData;
import com.faruk.dragger2mvprxjava.retrofit.APIInterface;
import java.util.List;
import javax.inject.Inject;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PresenterImpl implements MainActivityContract.Presenter {

    APIInterface apiInterface;
    MainActivityContract.View mView;

    @Inject
    public PresenterImpl(APIInterface apiInterface, MainActivityContract.View mView) {
        this.apiInterface = apiInterface;
        this.mView = mView;
    }

    @Override
    public void loadData() {

        mView.showProgress();

        apiInterface.getData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<AppData>>() {
                    @Override
                    public void onCompleted() {
                        mView.showComplete();
                        mView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError("Error occurred");
                        mView.hideProgress();
                    }

                    @Override
                    public void onNext(List<AppData> data) {
                        mView.showData(data);
                    }
                });
    }
}
