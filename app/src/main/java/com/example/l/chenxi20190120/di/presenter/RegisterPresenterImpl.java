package com.example.l.chenxi20190120.di.presenter;

import com.example.l.chenxi20190120.di.contract.RegContract;
import com.example.l.chenxi20190120.di.model.RegisterModelImpl;

import java.lang.ref.SoftReference;

public class RegisterPresenterImpl implements RegContract.RegistPresenter<RegContract.RegistView>{
    RegContract.RegistView registView;
    private SoftReference<RegContract.RegistView> reference;
    private RegContract.RegistModel model;

    @Override
    public void attachView(RegContract.RegistView registView) {
           this.registView = registView;
        reference = new SoftReference<>(registView);
        model = new RegisterModelImpl();
    }

    @Override
    public void dettchView(RegContract.RegistView registView) {
        reference.clear();
    }

    @Override
    public void requestRegistData(String name, String password) {
        model.containRegistData(name,password,new RegContract.RegistModel.CallBack() {
            @Override
            public void onRegistCall(String s) {
                registView.showRegistData(s);
            }
        });
    }
}
