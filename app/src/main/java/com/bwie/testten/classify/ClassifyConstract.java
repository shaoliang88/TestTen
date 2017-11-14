package com.bwie.testten.classify;

import com.bwie.testten.classify.bean.OneBean;

import java.util.List;

/**
 * Created by Zhang on 2017/11/13.
 */

public interface ClassifyConstract {
    interface IClassifyView{
        void ShowList(List<OneBean.DatasBean.ClassListBean> list);
        void ShowError(String e);
    }
    interface IClassifyModel{
        void OnRequestListener(String url,OnRequestListener onRequestListener);
    }
    interface OnRequestListener{
        void OnSuccess(List<OneBean.DatasBean.ClassListBean> list);
        void OnError(String e);
    }
    interface IClassifyPresenter{
        void LoadList(String url);
    }
}
