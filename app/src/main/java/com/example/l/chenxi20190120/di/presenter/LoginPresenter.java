package com.example.l.chenxi20190120.di.presenter;

import com.example.l.chenxi20190120.di.contract.MyContract;
import com.example.l.chenxi20190120.di.model.LoginModelImpl;

import java.lang.ref.SoftReference;

public class LoginPresenter implements MyContract.LoginPresenter<MyContract.LoginView> {
    MyContract.LoginView loginView;
    private SoftReference<MyContract.LoginView> reference;
    private MyContract.LoginModel model;

    @Override
    public void attachView(MyContract.LoginView loginView) {
         this.loginView = loginView;
         //软引用包裹
        reference = new SoftReference<>(loginView);
        //创建M层
        model = new LoginModelImpl();
    }

    @Override
    public void dettchView(MyContract.LoginView loginView) {
         //qingkong
        reference.clear();
    }

    @Override
    public void requestLoginData(final String name, final String password) {
          //接口回调
        model.containLoginData(name,password,new MyContract.LoginModel.CallBack() {
            @Override
            public void onLoginCall(String s) {
                loginView.showLoginData(name,password);
            }
        });
    }
}
