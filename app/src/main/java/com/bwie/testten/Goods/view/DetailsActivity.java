package com.bwie.testten.Goods.view;

import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.testten.Goods.GoodConstract;
import com.bwie.testten.Goods.bean.AddBean;
import com.bwie.testten.Goods.bean.DetailsBean;
import com.bwie.testten.Goods.bean.DetailsEvent;
import com.bwie.testten.Goods.presenter.DetailPresenter;
import com.bwie.testten.R;
import com.bwie.testten.utils.Api;
import com.bwie.testten.utils.Toasts;
import com.stx.xhb.xbanner.XBanner;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailsActivity extends AppCompatActivity implements GoodConstract.IDetailsView {


    @BindView(R.id.detail_banner)
    XBanner detailBanner;
    @BindView(R.id.details_title)
    TextView detailsTitle;
    @BindView(R.id.details_text)
    TextView detailsText;
    @BindView(R.id.details_price)
    TextView detailsPrice;
    @BindView(R.id.buy_now)
    Button buyNow;
    @BindView(R.id.add_cart)
    Button addCart;
    private DetailPresenter detailPresenter;
    private int sellerid;
    private int pid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        detailPresenter = new DetailPresenter(this);
        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
        int hei = (point.y) / 2;
        ViewGroup.LayoutParams params = detailBanner.getLayoutParams();
        params.height = hei;
        detailBanner.setLayoutParams(params);
    }

    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED, sticky = true)
    public void getDetails(DetailsEvent detailsEvent) {
        //tvEee.setText(detailsEvent.getCid()+"");
        detailPresenter.LoadDetails(Api.BANNERURL, detailsEvent.getCid());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void ShowList(DetailsBean lists) {
        DetailsBean.SellerBean seller = lists.getSeller();
        sellerid = seller.getSellerid();
        DetailsBean.DataBean list = lists.getData();
        pid = list.getPid();
        final List<String> img_list = new ArrayList<>();
        String images = list.getImages();
        String[] split = images.split("\\|");
        for (int i = 0; i < split.length; i++) {
            img_list.add(split[i]);
        }
        detailBanner.setData(img_list, null);
        detailBanner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(DetailsActivity.this).load(img_list.get(position)).into((ImageView) view);
            }
        });
        detailsTitle.setText(list.getTitle());
        detailsPrice.setText("￥ " + list.getPrice() + "");
        detailsText.setText(list.getSubhead());

    }

    @Override
    public void AddShop(AddBean addBean) {
        Toasts.showLong(this,addBean.getMsg());
    }

    @Override
    public void AddError(String e) {
        Toasts.showLong(this,e);
    }

    @Override
    public void ShowError(String e) {
        Toasts.showLong(this, e);
    }

    @OnClick({R.id.buy_now, R.id.add_cart})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.buy_now:

                break;
            case R.id.add_cart:
                SharedPreferences user = getSharedPreferences("USER", MODE_PRIVATE);
                String name = user.getString("name", "000");
                int uid = user.getInt("uid", 0);
                if(name.equals("000")){
                    Toasts.showLong(DetailsActivity.this,"请先登录");
                }else{
                    detailPresenter.LoadAdd(Api.BANNERURL,uid,pid,sellerid);
                }
                break;
        }
    }
}
