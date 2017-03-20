package com.yxk.tjm.tianjiumeng.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.yxk.tjm.tianjiumeng.R;

import static com.yxk.tjm.tianjiumeng.R.id.et_verification;

public class RegistActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar mToolbar;
    private EditText mEtPhone;
    private EditText mEtVerification;
    private Button mBtnGetVerifi;
    private RelativeLayout mRelativeVerifi;
    private EditText mEtPwd;
    private Button mBtnRegist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        initView();
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mEtPhone = (EditText) findViewById(R.id.et_phone);
        mEtVerification = (EditText) findViewById(et_verification);
        mBtnGetVerifi = (Button) findViewById(R.id.btn_get_verifi);
        mRelativeVerifi = (RelativeLayout) findViewById(R.id.relative_verifi);
        mEtPwd = (EditText) findViewById(R.id.et_pwd);
        mBtnRegist = (Button) findViewById(R.id.btn_regist);

        mBtnGetVerifi.setOnClickListener(this);
        mBtnRegist.setOnClickListener(this);
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
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        String verification = mEtVerification.getText().toString().trim();
        if (TextUtils.isEmpty(verification)) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        String pwd = mEtPwd.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(this, "请输入六位密码", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }

    private CountDownTimer downTimer = new CountDownTimer(10 * 1000, 1000) {
        @Override
        public void onTick(long l) {
            mBtnGetVerifi.setEnabled(false);
            mBtnGetVerifi.setText((l / 1000) + "秒");
            Log.e("CountDownTimer", "倒计时");
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
