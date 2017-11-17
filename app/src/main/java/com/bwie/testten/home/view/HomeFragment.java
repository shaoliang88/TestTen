package com.bwie.testten.home.view;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.bumptech.glide.Glide;
import com.bwie.testten.R;
import com.bwie.testten.home.BannerConstract;
import com.bwie.testten.home.Bean.BannerBean;
import com.bwie.testten.home.RecommendRecyclerView;
import com.bwie.testten.home.adapter.MiaoShaAdapter;
import com.bwie.testten.home.adapter.RecommendAdapter;
import com.bwie.testten.home.presenter.BannerPresenter;
import com.bwie.testten.utils.Api;
import com.bwie.testten.utils.ObservableScrollView;
import com.bwie.testten.utils.ScrollViewListener;
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
    @BindView(R.id.msrcv)
    RecyclerView msrcv;
    @BindView(R.id.tjrcv)
    RecommendRecyclerView tjrcv;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.sllv)
    ObservableScrollView sllv;
    int mDistanceY ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home, container, false);
        unbinder = ButterKnife.bind(this, v);
        // 设置XBanner的页面切换特效
        banner.setPageTransformer(Transformer.Depth);
        // 设置XBanner页面切换的时间，即动画时长
        banner.setPageChangeDuration(1000);
        banner.stopAutoPlay();
        BannerPresenter bannerPresenter = new BannerPresenter(this);
        bannerPresenter.LoadBan(Api.BANNERURL);
        //sllv.setScrollViewListener(this);
        getbar();
        return v;
    }

    private void getbar() {

sllv.setScrollViewListener(new ScrollViewListener() {
    @Override
    public void onScrollChanged(ScrollView scrollView, int x, int y, int oldx, int oldy) {
        mDistanceY += y;
        //toolbar的高度
        int toolbarHeight = 10000;

        //当滑动的距离 <= toolbar高度的时候，改变Toolbar背景色的透明度，达到渐变的效果
        if (mDistanceY <= toolbarHeight) {
            float scale = (float) mDistanceY / toolbarHeight;
            float alpha = scale * 255;
            mToolbar.setBackgroundColor(Color.argb((int) alpha, 128, 0, 0));
        } else {
            //上述虽然判断了滑动距离与toolbar高度相等的情况，但是实际测试时发现，标题栏的背景色
            //很少能达到完全不透明的情况，所以这里又判断了滑动距离大于toolbar高度的情况，
            //将标题栏的颜色设置为完全不透明状态
            mToolbar.setBackgroundResource(R.color.colorAccent);
        }
        if(oldy>y){
            float scale = (float) mDistanceY / toolbarHeight;
            float alpha = scale / 255;
            mToolbar.setBackgroundColor(Color.argb((int) alpha, 128, 0, 0));
        }
    }
});

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
        banner.setData(banimg, bantitle);
        banner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model ,View view, int position) {
                Glide.with(getActivity()).load(banimg.get(position)).into((ImageView) view);
            }
        });
        List<BannerBean.MiaoshaBean.ListBeanX> listx = bb.getMiaosha().getList();
        MiaoShaAdapter miaoShaAdapter = new MiaoShaAdapter(listx, getActivity());
        msrcv.setAdapter(miaoShaAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        msrcv.setLayoutManager(linearLayoutManager);
        List<BannerBean.TuijianBean.ListBean> list = bb.getTuijian().getList();
        RecommendAdapter recommendAdapter = new RecommendAdapter(list, getActivity());
        tjrcv.setAdapter(recommendAdapter);
        //FullyLinearLayoutManager fullyLinearLayoutManager = new FullyLinearLayoutManager(getActivity(),2,true);
        tjrcv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    }

    @Override
    public void ShowError(String e) {
        Toasts.showLong(getActivity(), "" + e);
        Log.e("错错粗粗哦错错错从错错错", e);
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
