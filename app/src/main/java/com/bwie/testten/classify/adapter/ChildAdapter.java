package com.bwie.testten.classify.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.testten.Goods.view.GoodActivity;
import com.bwie.testten.R;
import com.bwie.testten.classify.bean.MessageEvent;
import com.bwie.testten.classify.bean.TwoBean;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Zhang on 2017/11/16.
 */

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.MyViewHodler> {

    private List<TwoBean.DataBean.ListBean> list;
    private Context context;

    public ChildAdapter(List<TwoBean.DataBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHodler myViewHodler = new MyViewHodler(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gridtwoitem, null, false));

        return myViewHodler;
    }

    @Override
    public void onBindViewHolder(MyViewHodler holder, int position) {
        final TwoBean.DataBean.ListBean listBean = list.get(position);
        holder.tvTwogrid.setText(listBean.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toasts.showLong(context,listBean.getName());
                EventBus.getDefault().postSticky(new MessageEvent(listBean.getPscid()));
                Intent in = new Intent(context, GoodActivity.class);
                context.startActivity(in);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    static class MyViewHodler extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_twogrid)
        TextView tvTwogrid;

        public MyViewHodler(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
