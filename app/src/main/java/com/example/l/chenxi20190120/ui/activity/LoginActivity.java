package com.example.l.chenxi20190120.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.l.chenxi20190120.R;
import com.example.l.chenxi20190120.di.contract.MyContract;
import com.example.l.chenxi20190120.di.presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements MyContract.LoginView {

    @BindView(R.id.edit_name)
    EditText editName;
    @BindView(R.id.edit_password)
    EditText editPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_rigist)
    Button btnRigist;
    @BindView(R.id.btn_qq)
    Button btnQq;
    private MyContract.LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //创建P层对象
        presenter = new LoginPresenter();
        //绑定
        presenter.attachView(this);

    }

    @Override
    public void showLoginData(String name, String password) {
         Toast.makeText(LoginActivity.this,"登陆成功",Toast.LENGTH_LONG).show();
         Intent intent = new Intent(LoginActivity.this,CartActivity.class);
         startActivity(intent);
    }
    //解绑

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.dettchView(this);
    }

    @OnClick({R.id.btn_login, R.id.btn_rigist, R.id.btn_qq})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                String name = editName.getText().toString();
                String password = editPassword.getText().toString();
                if (name.equals("")||password.equals("")){
                    Toast.makeText(LoginActivity.this,"用户名密码不能为空",Toast.LENGTH_LONG).show();
                    return;
                }
                presenter.requestLoginData(name,password);
                break;
            case R.id.btn_rigist:
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_qq:
                break;
        }
    }
}
