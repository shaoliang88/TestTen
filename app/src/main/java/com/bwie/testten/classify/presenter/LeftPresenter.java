package com.bwie.testten.classify.presenter;

import com.bwie.testten.classify.ClassifyConstract;
import com.bwie.testten.classify.bean.OneBean;
import com.bwie.testten.classify.model.LeftModel;

import java.util.List;

/**
 * Created by Zhang on 2017/11/14.
 */

public class LeftPresenter implements ClassifyConstract.IClassifyPresenter {
    ClassifyConstract.IClassifyView iClassifyView;
    ClassifyConstract.IClassifyModel iClassifyModel;

    public LeftPresenter(ClassifyConstract.IClassifyView iClassifyView) {
        this.iClassifyView = iClassifyView;
        iClassifyModel = new LeftModel();
    }

    @Override
    public void LoadList(String url) {
        iClassifyModel.OnRequestListener(url, new ClassifyConstract.OnRequestListener() {
            @Override
            public void OnSuccess(List<OneBean.DatasBean.ClassListBean> list) {
                iClassifyView.ShowList(list);
            }

            @Override
            public void OnError(String e) {
                iClassifyView.ShowError(e);
            }
        });
    }
}
