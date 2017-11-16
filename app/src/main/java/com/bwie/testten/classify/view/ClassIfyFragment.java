package com.bwie.testten.classify.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.testten.R;
import com.bwie.testten.classify.ClassifyConstract;
import com.bwie.testten.classify.adapter.LeftAdapter;
import com.bwie.testten.classify.bean.OneBean;
import com.bwie.testten.classify.presenter.LeftPresenter;
import com.bwie.testten.utils.Api;
import com.bwie.testten.utils.Toasts;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Zhang on 2017/11/9.
 */

public class ClassIfyFragment extends Fragment implements ClassifyConstract.IClassifyView{
    @BindView(R.id.left_rcv)
    RecyclerView leftRcv;
    Unbinder unbinder;
    private LeftPresenter leftPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.classify, container, false);
        unbinder = ButterKnife.bind(this, v);
        leftPresenter = new LeftPresenter(this);
        leftPresenter.LoadList(Api.FENLIEURL);
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void ShowList(final List<OneBean.DatasBean.ClassListBean> list) {
        LeftAdapter leftAdapter = new LeftAdapter(list,getActivity());
        leftRcv.setAdapter(leftAdapter);
        leftRcv.setLayoutManager(new LinearLayoutManager(getActivity()));
        leftAdapter.setOnLeftClickListener(new LeftAdapter.OnLeftClickListener() {
            @Override
            public void OnItemClickListener(View v, int position) {
                //Toasts.showLong(getActivity(),list.get(position).getGc_name());
            }
        });
    }

    @Override
    public void ShowError(String e) {
        Toasts.showLong(getActivity(),e);
        Log.e("hehehehehehehheheheheh",e);
    }
}
