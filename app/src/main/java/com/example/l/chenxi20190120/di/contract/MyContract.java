package com.example.l.chenxi20190120.di.contract;

public interface MyContract {
    //创建V层接口
    public interface LoginView{
       //展示数据
       public void showLoginData(String name, String password);
    }
    //创建P层接口
    public interface LoginPresenter<T>{
        //绑定
        public void attachView(T t);
        //解绑
        public void dettchView(T t);
        //请求数据
        public void requestLoginData(String name, String password);
    }
    //创建M层接口
    public interface LoginModel{
        //登陆
        public void containLoginData(String name, String password, CallBack callBack);
        //接口回调
        public interface CallBack{
             //回显数据
            public void onLoginCall(String s);
        }
    }
    
}
