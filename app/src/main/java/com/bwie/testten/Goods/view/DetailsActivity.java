package com.bwie.testten.Goods.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.bwie.testten.Goods.GoodConstract;
import com.bwie.testten.Goods.bean.DetailsBean;
import com.bwie.testten.Goods.bean.DetailsEvent;
import com.bwie.testten.Goods.presenter.DetailPresenter;
import com.bwie.testten.R;
import com.bwie.testten.utils.Api;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity implements GoodConstract.IDetailsView{

    @BindView(R.id.tv_eee)
    TextView tvEee;
    private DetailPresenter detailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        detailPresenter = new DetailPresenter(this);

    }
    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED,sticky = true)
    public void getDetails(DetailsEvent detailsEvent){
        //tvEee.setText(detailsEvent.getCid()+"");
        detailPresenter.LoadDetails(Api.BANNERURL,detailsEvent.getCid());
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void ShowList(DetailsBean.DataBean list) {

    }

    @Override
    public void ShowError(String e) {

    }
}
