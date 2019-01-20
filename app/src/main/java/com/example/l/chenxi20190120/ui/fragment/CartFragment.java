package com.example.l.chenxi20190120.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.l.chenxi20190120.R;
import com.example.l.chenxi20190120.data.bean.CartBean;
import com.example.l.chenxi20190120.di.contract.CartContract;
import com.example.l.chenxi20190120.di.presenter.CartPresenterImpl;
import com.example.l.chenxi20190120.ui.adapter.CartAdapter;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CartFragment extends Fragment implements CartContract.CartView, View.OnClickListener {

    @BindView(R.id.rv_business)
    RecyclerView rvBusiness;
    @BindView(R.id.srl_container)
    SmartRefreshLayout srlContainer;
    @BindView(R.id.cb_all)
    CheckBox cbAll;
    @BindView(R.id.btn_price)
    Button btnPrice;
    @BindView(R.id.tv_count)
    TextView tvCount;
    Unbinder unbinder;
    private CartContract.CartPresenter presenter;
    private CartAdapter adapter;
    private List<CartBean.DataBean> beanList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cart_fragment, container, false);
        //chuangP
        presenter = new CartPresenterImpl();
        presenter.attachView(this);
        presenter.requestCartData();
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void showCartData(String s) {

        cbAll.setOnCheckedChangeListener(null);
        cbAll.setOnClickListener(this);

        Gson gson = new Gson();
        CartBean cartBean = gson.fromJson(s, CartBean.class);
        //添加外层商家数据源
        beanList = cartBean.getData();
         //设置布局管理器
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvBusiness.setLayoutManager(manager);
        //设置适配器
        adapter = new CartAdapter(R.layout.recycler_business_item, beanList);
        rvBusiness.setAdapter(adapter);

        adapter.setOnBusinessItemClickLisenter(new CartAdapter.OnBusinessItemClickLisenter() {
            @Override
            public void onCallBack() {
                boolean result = true;
                for (int i = 0; i < beanList.size(); i++) {
                    //外层选中状态
                    boolean businessChecked = beanList.get(i).getBussinessChecked();
                    result = result & businessChecked;
                    for (int j = 0; j < beanList.get(i).getList().size(); j++) {
                        //里层选中状态
                        boolean goodsChecked = beanList.get(i).getList().get(j).isGoodsChecked();
                        result = result & goodsChecked;
                    }
                }
                cbAll.setChecked(result);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.dettchView(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        //全选逻辑
        for (int i = 0; i < beanList.size(); i++) {
            //让外层商家全部选中
            beanList.get(i).setBussinessChecked(cbAll.isChecked());
            //让内层商品全部选中
            for (int j = 0; j < beanList.get(i).getList().size(); j++) {
                beanList.get(i).getList().get(j).setGoodsChecked(cbAll.isChecked());
            }
        }
        adapter.notifyDataSetChanged();
    }
}
