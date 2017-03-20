package com.yxk.tjm.tianjiumeng.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.custom.CircleImageView;


public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar mToolbar;
    private CircleImageView mImageHead;
    private EditText mEtPhone;
    private EditText mEtPwd;
    private Button mBtnLogin;
    private Button mBtnRegist;
    private TextView mTvForgetPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mImageHead = (CircleImageView) findViewById(R.id.image_head);
        mEtPhone = (EditText) findViewById(R.id.et_phone);
        mEtPwd = (EditText) findViewById(R.id.et_pwd);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mBtnRegist = (Button) findViewById(R.id.btn_regist);
        mTvForgetPwd = (TextView) findViewById(R.id.tv_forget_pwd);

        mBtnLogin.setOnClickListener(this);
        mBtnRegist.setOnClickListener(this);
        mTvForgetPwd.setOnClickListener(this);
        setToolbarNavigationClick();
    }

    private void setToolbarNavigationClick() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void submit() {
        // validate
        String phone = mEtPwd.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        String pwd = mEtPwd.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(this, "至少六位密码", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:

                break;
            case R.id.btn_regist:
                startActivity(new Intent(LoginActivity.this, RegistActivity.class));
                break;

            case R.id.tv_forget_pwd:
                startActivity(new Intent(LoginActivity.this, ForgetPwdActivity.class));
                break;
        }
    }

}
