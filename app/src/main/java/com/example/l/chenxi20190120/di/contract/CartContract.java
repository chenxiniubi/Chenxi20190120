package com.example.l.chenxi20190120.di.contract;

public interface CartContract {
    //创建V层接口
    public interface CartView{
        //展示数据
        public void showCartData(String s);
    }
    //创建P层接口
    public interface CartPresenter<T>{
        //绑定
        public void attachView(T t);
        //解绑
        public void dettchView(T t);
        //请求数据
        public void requestCartData();
    }
    //创建M层接口
    public interface CartModel{
        //登陆
        public void containCartData(CallBack callBack);
        //接口回调
        public interface CallBack{
            //回显数据
            public void onCartCall(String s);
        }
    }
}
