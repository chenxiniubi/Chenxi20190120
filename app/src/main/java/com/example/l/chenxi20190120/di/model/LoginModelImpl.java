package com.example.l.chenxi20190120.di.model;

import android.util.Log;

import com.example.l.chenxi20190120.data.constant.MyConstant;
import com.example.l.chenxi20190120.di.contract.MyContract;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class LoginModelImpl implements MyContract.LoginModel{
    @Override
    public void containLoginData(String name, String password, final CallBack callBack) {
        OkGo.<String>post(MyConstant.log_url+"?phone="+name+"&pwd="+password).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String s = response.body().toString();
                Log.i("cx", "onSuccess: ===================="+s);
                callBack.onLoginCall(s);
            }
        });
    }
}
