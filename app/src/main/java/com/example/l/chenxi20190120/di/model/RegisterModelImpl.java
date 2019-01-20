package com.example.l.chenxi20190120.di.model;

import com.example.l.chenxi20190120.data.constant.MyConstant;
import com.example.l.chenxi20190120.di.contract.RegContract;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class RegisterModelImpl implements RegContract.RegistModel {
    @Override
    public void containRegistData(String name, String password, final CallBack callBack) {
        OkGo.<String>post(MyConstant.reg_url+"?phone="+name+"&pwd"+password).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String s = response.body().toString();
                callBack.onRegistCall(s);
            }
        });
    }
}
