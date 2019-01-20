package com.example.l.chenxi20190120.di.presenter;

import com.example.l.chenxi20190120.di.contract.CartContract;
import com.example.l.chenxi20190120.di.model.CartModelImpl;

import java.lang.ref.SoftReference;

public class CartPresenterImpl implements CartContract.CartPresenter<CartContract.CartView> {
    CartContract.CartView cartView;
    private SoftReference<CartContract.CartView> reference;
    private CartContract.CartModel model;

    @Override
    public void attachView(CartContract.CartView cartView) {
          this.cartView = cartView;
        reference = new SoftReference<>(cartView);
        model = new CartModelImpl();
    }

    @Override
    public void dettchView(CartContract.CartView cartView) {
        reference.clear();
    }

    @Override
    public void requestCartData() {
        model.containCartData(new CartContract.CartModel.CallBack() {
            @Override
            public void onCartCall(String s) {
                cartView.showCartData(s);
            }
        });
    }
}
