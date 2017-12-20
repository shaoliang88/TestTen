package com.bwie.testten.Goods.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.testten.Goods.bean.DetailsEvent;
import com.bwie.testten.Goods.bean.GoodBean;
import com.bwie.testten.Goods.view.DetailsActivity;
import com.bwie.testten.R;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoodAdapter extends RecyclerView.Adapter<GoodAdapter.MyViewHolder> {


    private List<GoodBean.DataBean> list;
    private Context context;

    public GoodAdapter(List<GoodBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.goodlistitem, parent, false));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final GoodBean.DataBean dataBean = list.get(position);
        holder.goodlistTvtitle.setText(dataBean.getTitle());
        String images = dataBean.getImages();
        if(images!=null){


        String[] split = images.split("\\|");
        Uri uri = Uri.parse(split[0]);
        holder.goodlistImg.setImageURI(uri);
        holder.goodlistTvprice.setText("￥ "+dataBean.getPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(new DetailsEvent(dataBean.getPid()));
                Intent in = new Intent(context, DetailsActivity.class);
                context.startActivity(in);
            }
        });
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.goodlist_img)
        SimpleDraweeView goodlistImg;
        @BindView(R.id.goodlist_tvtitle)
        TextView goodlistTvtitle;
        @BindView(R.id.goodlist_tvprice)
        TextView goodlistTvprice;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
