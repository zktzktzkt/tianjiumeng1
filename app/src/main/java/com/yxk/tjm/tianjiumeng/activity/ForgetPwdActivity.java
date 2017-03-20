package com.yxk.tjm.tianjiumeng.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.yxk.tjm.tianjiumeng.R;

import static com.yxk.tjm.tianjiumeng.R.id.et_new_pwd;
import static com.yxk.tjm.tianjiumeng.R.id.et_phone;
import static com.yxk.tjm.tianjiumeng.R.id.et_verification;

public class ForgetPwdActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar mToolbar;
    private EditText mEtPhone;
    private EditText mEtVerification;
    private Button mBtnGetVerifi;
    private RelativeLayout mRelativeVerifi;
    private EditText mEtNewPwd;
    private Button mBtnRegist;
    private Button mBtnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd);
        initView();
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mEtPhone = (EditText) findViewById(et_phone);
        mEtVerification = (EditText) findViewById(et_verification);
        mBtnGetVerifi = (Button) findViewById(R.id.btn_get_verifi);
        mRelativeVerifi = (RelativeLayout) findViewById(R.id.relative_verifi);
        mEtNewPwd = (EditText) findViewById(et_new_pwd);
        mBtnOk = (Button) findViewById(R.id.btn_ok);
        mBtnOk.setOnClickListener(this);
        mBtnGetVerifi.setOnClickListener(this);
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
        String phone = mEtPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "注册手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        String verification = mEtVerification.getText().toString().trim();
        if (TextUtils.isEmpty(verification)) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        String pwd = mEtNewPwd.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(this, "请输入六位新密码", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something

    }

    private CountDownTimer downTimer = new CountDownTimer(10 * 1000, 1000) {
        @Override
        public void onTick(long l) {
            mBtnGetVerifi.setText((l / 1000) + "秒");
            mBtnGetVerifi.setEnabled(false);
        }

        @Override
        public void onFinish() {
            mBtnGetVerifi.setEnabled(true);
            mBtnGetVerifi.setText("重新发送");
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        downTimer.cancel();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get_verifi:
                downTimer.start();
                break;
            case R.id.btn_ok:

                break;
        }
    }

}
