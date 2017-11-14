package com.bwie.testten.mine.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bwie.testten.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Zhang on 2017/11/9.
 */

public class MeFragment extends Fragment {
    @BindView(R.id.tv_name_back)
    TextView tvNameBack;
    @BindView(R.id.rbspsc)
    RadioButton rbspsc;
    @BindView(R.id.rbdpsc)
    RadioButton rbdpsc;
    @BindView(R.id.rbwdzj)
    RadioButton rbwdzj;
    @BindView(R.id.rbwfk)
    RadioButton rbwfk;
    @BindView(R.id.rbdfh)
    RadioButton rbdfh;
    @BindView(R.id.rbdsh)
    RadioButton rbdsh;
    @BindView(R.id.rbdpj)
    RadioButton rbdpj;
    @BindView(R.id.rbtk)
    RadioButton rbtk;
    @BindView(R.id.rbyfk)
    RadioButton rbyfk;
    @BindView(R.id.rbczk)
    RadioButton rbczk;
    @BindView(R.id.rbdjq)
    RadioButton rbdjq;
    @BindView(R.id.rbhb)
    RadioButton rbhb;
    @BindView(R.id.rbjf)
    RadioButton rbjf;
    @BindView(R.id.shouhuodizhi)
    RadioButton shouhuodizhi;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.me, container, false);

        unbinder = ButterKnife.bind(this, v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), LoginActivity.class);
                startActivity(in);
            }
        });
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
