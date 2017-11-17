package com.bwie.testten.shopcar.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.testten.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Zhang on 2017/11/9.
 */

public class ShopCarFragment extends Fragment {

    @BindView(R.id.tv_keepout)
    TextView tvKeepout;

    Unbinder unbinder;
    private String name;
    private int uid;
    private String pwd;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.shopcar, container, false);
        unbinder = ButterKnife.bind(this, v);


        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        SharedPreferences user = getActivity().getSharedPreferences("USER", Context.MODE_PRIVATE);
        name = user.getString("name","000");
        uid = user.getInt("uid", 0);
        pwd = user.getString("pwd", "000");
        if (name.equals("000")) {
            tvKeepout.setVisibility(View.VISIBLE);
        }else{
            tvKeepout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
