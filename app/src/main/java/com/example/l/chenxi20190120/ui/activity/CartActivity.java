package com.example.l.chenxi20190120.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.l.chenxi20190120.R;
import com.example.l.chenxi20190120.ui.fragment.CartFragment;
import com.example.l.chenxi20190120.ui.fragment.MineFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartActivity extends AppCompatActivity {

    @BindView(R.id.main_frame_layout)
    FrameLayout mainFrameLayout;
    @BindView(R.id.rb_cart)
    RadioButton rbCart;
    @BindView(R.id.rb_mine)
    RadioButton rbMine;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    private CartFragment cartFragment;
    private MineFragment mineFragment;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ButterKnife.bind(this);
        //创建Fragment对象
        cartFragment = new CartFragment();
        mineFragment = new MineFragment();

        //开启事务
        manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();

        //默认添加到事务
        fragmentTransaction.add(R.id.main_frame_layout,cartFragment);
        //提交到事务
        fragmentTransaction.commit();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                FragmentTransaction fragmentTransaction1 = manager.beginTransaction();
                 switch (i){
                     case R.id.rb_cart:
                         fragmentTransaction1.replace(R.id.main_frame_layout,cartFragment);
                         break;
                     case R.id.rb_mine:
                         fragmentTransaction1.replace(R.id.main_frame_layout,mineFragment);
                         break;
                 }
                fragmentTransaction1.commit();
            }
        });
    }
}
