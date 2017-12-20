package com.bwie.testten.app;

import android.app.Application;

import com.bwie.testten.utils.NetUtils;
import com.bwie.testten.utils.Toasts;
import com.facebook.drawee.backends.pipeline.Fresco;

import org.xutils.BuildConfig;
import org.xutils.x;



public class MyApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        Fresco.initialize(this);

        boolean connected = NetUtils.isConnected(this);
        if(connected){
            boolean available = NetUtils.isAvailable(this);
            if(available){
//                Toasts.showLong(this,"网络已连接");
            }else{
//                Toasts.showLong(this,"当前网络不可用");
            }
        }else{
//            Toasts.showLong(this,"当前网络无法连接");
        }
        initxUtils();
    }
    private void initxUtils() {
        //初始化
        x.Ext.init(this);
        // 设置是否输出debug
        x.Ext.setDebug(BuildConfig.DEBUG);
    }
}
