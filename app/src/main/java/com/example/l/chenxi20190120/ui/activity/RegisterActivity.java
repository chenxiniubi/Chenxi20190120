package com.example.l.chenxi20190120.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.l.chenxi20190120.R;
import com.example.l.chenxi20190120.di.contract.RegContract;
import com.example.l.chenxi20190120.di.presenter.RegisterPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements RegContract.RegistView {

    @BindView(R.id.reg_name)
    EditText regName;
    @BindView(R.id.reg_password)
    EditText regPassword;
    @BindView(R.id.reg_btn)
    Button regBtn;
    private RegContract.RegistPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        //创建P
        presenter = new RegisterPresenterImpl();
        //绑定
        presenter.attachView(this);

    }

    @Override
    public void showRegistData(String s) {
       Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(intent);
    }
    //防止内存泄露

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.dettchView(this);
    }

    @OnClick(R.id.reg_btn)
    public void onViewClicked() {
        String name = regName.getText().toString();
        String password = regPassword.getText().toString();
        if (name.equals("")||password.equals("")){
            Toast.makeText(RegisterActivity.this,"注册账号和密码不能为空",Toast.LENGTH_LONG).show();
            return;
        }
        presenter.requestRegistData(name,password);
    }
}
