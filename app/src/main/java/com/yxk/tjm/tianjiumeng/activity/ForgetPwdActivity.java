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
import com.yxk.tjm.tianjiumeng.network.ApiConstants;
import com.yxk.tjm.tianjiumeng.utils.MD5Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

import static com.yxk.tjm.tianjiumeng.R.id.et_new_pwd;
import static com.yxk.tjm.tianjiumeng.R.id.et_phone;
import static com.yxk.tjm.tianjiumeng.R.id.et_verification;

/**
 * 忘记密码
 */
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
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        String verification = mEtVerification.getText().toString().trim();
        if (TextUtils.isEmpty(verification)) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        String pwd = mEtNewPwd.getText().toString().trim();
        if (TextUtils.isEmpty(pwd) && pwd.length() < 6) {
            Toast.makeText(this, "请输入六位新密码", Toast.LENGTH_SHORT).show();
            return;
        }

        String md5Pwd = MD5Util.MD5(pwd);
        OkHttpUtils.post()
                .url(ApiConstants.FORGET_PWD)
                .addParams("userName", phone)
                .addParams("smscode", verification)
                .addParams("passWord", md5Pwd)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("ForgetPwdActivity ", "submit() response:" + response);
                        try {
                            JSONObject jo = new JSONObject(response);
                            int success = (int) jo.get("success");
                            if (success == 1) {
                                Toast.makeText(ForgetPwdActivity.this, "用户不存在！", Toast.LENGTH_SHORT).show();
                            } else if (success == 0) {
                                Toast.makeText(ForgetPwdActivity.this, "修改成功！", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(ForgetPwdActivity.this, "修改失败！", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

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
                submit();
                break;
        }
    }

}
