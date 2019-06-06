package com.faruk.dragger2mvprxjava.retrofit;

import com.faruk.dragger2mvprxjava.data.AppData;
import java.util.List;
import retrofit2.http.GET;
import rx.Observable;

public interface APIInterface {

    String REQUEST_PARAM = "datalist.json";

    @GET(REQUEST_PARAM)
    Observable<List<AppData>> getData();
}
