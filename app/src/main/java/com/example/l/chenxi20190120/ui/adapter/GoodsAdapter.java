package com.example.l.chenxi20190120.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.l.chenxi20190120.R;
import com.example.l.chenxi20190120.data.bean.CartBean;

import java.util.List;

public class GoodsAdapter extends BaseQuickAdapter<CartBean.DataBean.ListBean,BaseViewHolder>{
    OnGoodsItemClickLisenter onGoodsItemClickLisenter;

    public void setOnGoodsItemClickLisenter(OnGoodsItemClickLisenter onGoodsItemClickLisenter) {
        this.onGoodsItemClickLisenter = onGoodsItemClickLisenter;
    }

    //接口回调
    public interface OnGoodsItemClickLisenter {
        public void onCallBack();
    }

    public GoodsAdapter(int layoutResId, @Nullable List<CartBean.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final CartBean.DataBean.ListBean item) {
         helper.setText(R.id.tv_goodsPrice,"￥:"+item.getPrice());
         helper.setText(R.id.tv_goodsTitle,item.getTitle());
        ImageView iv_goodsIcon = helper.getView(R.id.iv_goodsIcon);
        String imageString = item.getImages();
        String[] imagesStr = imageString.split("\\|");
        Glide.with(mContext).load(imagesStr[0]).into(iv_goodsIcon);
        //避免焦点抢占
       final CheckBox cb_goods = helper.getView(R.id.cb_goods);
        cb_goods.setOnCheckedChangeListener(null);
        //获取商家条目是否选中状态
        cb_goods.setChecked(item.isGoodsChecked());
        
        //接口回调的方式传给外部
        cb_goods.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //Bean对象状态进行更新完毕
                item.setGoodsChecked(isChecked);
                onGoodsItemClickLisenter.onCallBack();
            }
        });
    }
}
