package com.example.l.chenxi20190120.di.model;

import com.example.l.chenxi20190120.data.constant.MyConstant;
import com.example.l.chenxi20190120.di.contract.CartContract;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class CartModelImpl implements CartContract.CartModel {
    @Override
    public void containCartData(final CallBack callBack) {
        OkGo.<String>get(MyConstant.cart_url).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String s = response.body().toString();
                callBack.onCartCall(s);
            }
        });
    }
}
