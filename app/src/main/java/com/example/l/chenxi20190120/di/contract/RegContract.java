package com.example.l.chenxi20190120.di.contract;

public interface RegContract {
    //创建V层接口
    public interface RegistView{
        //展示数据
        public void showRegistData(String s);
    }
    //创建P层接口
    public interface RegistPresenter<T>{
        //绑定
        public void attachView(T t);
        //解绑
        public void dettchView(T t);
        //请求数据
        public void requestRegistData(String name, String password);
    }
    //创建M层接口
    public interface RegistModel{
        //登陆
        public void containRegistData(String name, String password, CallBack callBack);
        //接口回调
        public interface CallBack{
            //回显数据
            public void onRegistCall(String s);
        }
    }
}
