package com.bwie.testten.home.view;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bwie.testten.R;
import com.bwie.testten.home.BannerConstract;
import com.bwie.testten.home.Bean.BannerBean;
import com.bwie.testten.home.presenter.BannerPresenter;
import com.bwie.testten.utils.Api;
import com.bwie.testten.utils.Toasts;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Zhang on 2017/11/9.
 */

public class HomeFragment extends Fragment implements BannerConstract.IBannerView{

    @BindView(R.id.banner)
    XBanner banner;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home, container, false);
        unbinder = ButterKnife.bind(this, v);
        // 设置XBanner的页面切换特效
        banner.setPageTransformer(Transformer.Default);
        // 设置XBanner页面切换的时间，即动画时长
        banner.setPageChangeDuration(1000);
        BannerPresenter bannerPresenter = new BannerPresenter(this);
        bannerPresenter.LoadBan(Api.BANNERURL);
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void ShowBanner(BannerBean bb) {
        List<String> bantitle = new ArrayList<>();
        final List<String> banimg = new ArrayList<>();
        List<BannerBean.DataBean> data = bb.getData();
        for (int i = 0; i < data.size(); i++) {
            bantitle.add(data.get(i).getTitle());
            banimg.add(data.get(i).getIcon());
        }
        banner.setData(banimg,bantitle);
        banner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, View view, int position) {
                Glide.with(getActivity()).load(banimg.get(position)).into((ImageView) view);
            }
        });

    }

    @Override
    public void ShowError(String e) {
        Toasts.showLong(getActivity(),""+e);
    }

    @Override
    public void onResume() {
        super.onResume();
        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        banner.stopAutoPlay();
    }
}
