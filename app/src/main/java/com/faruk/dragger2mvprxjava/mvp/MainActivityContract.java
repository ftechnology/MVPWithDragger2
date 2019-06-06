package com.faruk.dragger2mvprxjava.mvp;


import com.faruk.dragger2mvprxjava.data.AppData;

import java.util.List;

public interface MainActivityContract {
    interface View {
        void showData(List<AppData> data);

        void showError(String message);

        void showComplete();

        void showProgress();

        void hideProgress();
    }

    interface Presenter {
        void loadData();
    }
}
