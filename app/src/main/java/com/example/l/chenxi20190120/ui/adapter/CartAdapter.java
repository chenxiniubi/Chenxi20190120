package com.example.l.chenxi20190120.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.l.chenxi20190120.R;
import com.example.l.chenxi20190120.data.bean.CartBean;

import java.util.List;

public class CartAdapter extends BaseQuickAdapter<CartBean.DataBean,BaseViewHolder> {

    OnBusinessItemClickLisenter onBusinessItemClickLisenter;
    public interface OnBusinessItemClickLisenter {
        public void onCallBack();
    }

    public void setOnBusinessItemClickLisenter(OnBusinessItemClickLisenter onBusinessItemClickLisenter) {
        this.onBusinessItemClickLisenter = onBusinessItemClickLisenter;
    }

    public CartAdapter(int layoutResId, @Nullable List<CartBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final CartBean.DataBean item) {
        //设置商品名称
        helper.setText(R.id.tv_business_name,item.getSellerName());
        //设商品子条目
        RecyclerView rv_goods = helper.getView(R.id.rv_goods);

        //避免焦点抢占
        final CheckBox cb_business = helper.getView(R.id.cb_business);
        cb_business.setOnCheckedChangeListener(null);

        //获取商家条目是否选中状态
        cb_business.setChecked(item.getBussinessChecked());

       //设置商品子条目数据源
        List<CartBean.DataBean.ListBean> beanList = item.getList();
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rv_goods.setLayoutManager(manager);
        //设置适配器
        final GoodsAdapter adapter = new GoodsAdapter(R.layout.recycler_goods_item, beanList);
        rv_goods.setAdapter(adapter);
        //首先处理内部子条目控制外部条目
        adapter.setOnGoodsItemClickLisenter(new GoodsAdapter.OnGoodsItemClickLisenter() {
            @Override
            public void onCallBack() {
                boolean result = true;
                for (int i = 0; i < item.getList().size(); i++) {
                    result = result & item.getList().get(i).isGoodsChecked();
                }
                cb_business.setChecked(result);
                //刷新适配器
                adapter.notifyDataSetChanged();
                //把最后的状态进行回传
                onBusinessItemClickLisenter.onCallBack();
            }
        });
    }
}
