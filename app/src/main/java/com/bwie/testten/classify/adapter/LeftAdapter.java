package com.bwie.testten.classify.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.testten.R;
import com.bwie.testten.classify.bean.OneBean;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.http.Url;

/**
 * Created by Zhang on 2017/11/14.
 */

public class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.MyViewHolder> {

    private List<OneBean.DatasBean.ClassListBean> list;
    private Context  context;
    private OnLeftClickListener  onLeftClickListener;
    public interface OnLeftClickListener{
        void OnItemClickListener(View v,int position);
    }

    public OnLeftClickListener getOnLeftClickListener() {
        return onLeftClickListener;
    }

    public void setOnLeftClickListener(OnLeftClickListener onLeftClickListener) {
        this.onLeftClickListener = onLeftClickListener;
    }

    public LeftAdapter(List<OneBean.DatasBean.ClassListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.leftitem, parent, false));

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        OneBean.DatasBean.ClassListBean cb = list.get(position);
        holder.leftTv.setText(cb.getGc_name());
        if(cb.getImage()!=null){
            Uri uri = Uri.parse(cb.getImage());
            holder.leftImg.setImageURI(uri);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLeftClickListener.OnItemClickListener(v,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

     class MyViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.left_img)
        SimpleDraweeView leftImg;
        @BindView(R.id.left_tv)
        TextView leftTv;

         public MyViewHolder(View itemView) {
             super(itemView);
             ButterKnife.bind(this, itemView);
         }
    }
}
