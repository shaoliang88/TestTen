package com.bwie.testten.classify.model;

import java.util.List;

import com.bwie.testten.classify.ClassifyConstract;
import com.bwie.testten.classify.bean.OneBean;
import com.bwie.testten.utils.ApiServer;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Zhang on 2017/11/14.
 */

public class LeftModel implements ClassifyConstract.IClassifyModel {

    @Override
    public void OnRequestListener(String url, final ClassifyConstract.OnRequestListener onRequestListener) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ApiServer apiServer = retrofit.create(ApiServer.class);
        Observable<OneBean> getonedata = apiServer.getonedata();
        getonedata.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<OneBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onRequestListener.OnError(e.getMessage().toString());
                    }

                    @Override
                    public void onNext(OneBean oneBean) {
                        List<OneBean.DatasBean.ClassListBean> class_list = oneBean.getDatas().getClass_list();
                        onRequestListener.OnSuccess(class_list);
                    }
                });
    }
}
