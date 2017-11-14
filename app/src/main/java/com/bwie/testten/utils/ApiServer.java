package com.bwie.testten.utils;

import com.bwie.testten.classify.bean.OneBean;
import com.bwie.testten.home.Bean.BannerBean;
import com.bwie.testten.mine.bean.LoginBean;
import com.bwie.testten.mine.bean.SignUpBean;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by fan on 2017/11/8.
 */

public interface ApiServer {
    @POST
    Observable<SignUpBean> getsup(@Url String url, @QueryMap Map<String,String> map);
    @POST
    Observable<LoginBean> getlg(@Url String url, @QueryMap Map<String,String> map);

    @GET("mobile/index.php?act=goods_class")
    Observable<OneBean> getonedata();

    @GET("ad/getAd")
    Observable<BannerBean> getbanner();
}
