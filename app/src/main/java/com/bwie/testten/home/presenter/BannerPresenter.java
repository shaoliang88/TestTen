package com.bwie.testten.home.presenter;

import com.bwie.testten.home.BannerConstract;
import com.bwie.testten.home.Bean.BannerBean;
import com.bwie.testten.home.model.BannerModel;



public class BannerPresenter implements BannerConstract.IBannerPresenter {
    BannerConstract.IBannerView iBannerView;
    BannerConstract.IBannerModel iBannerModel;

    public BannerPresenter(BannerConstract.IBannerView iBannerView) {
        this.iBannerView = iBannerView;
        iBannerModel = new BannerModel();
    }

    @Override
    public void LoadBan(String url) {
        iBannerModel.RequestData(url, new BannerConstract.OnBannerRequest() {
            @Override
            public void OnSuccess(BannerBean bb) {
                iBannerView.ShowBanner(bb);
            }

            @Override
            public void OnError(String e) {
                iBannerView.ShowError(e);
            }
        });
    }
}
