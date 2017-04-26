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
import com.yxk.tjm.tianjiumeng.network.ApiConstants;
import com.yxk.tjm.tianjiumeng.utils.LogUtil;
import com.yxk.tjm.tianjiumeng.utils.MD5Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

import static com.yxk.tjm.tianjiumeng.R.id.et_verification;


/**
 * 注册
 */
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
        if (TextUtils.isEmpty(pwd) && pwd.length() < 6) {
            Toast.makeText(this, "请输入六位密码", Toast.LENGTH_SHORT).show();
            return;
        }

        String md5Pwd = MD5Util.MD5(pwd);
        OkHttpUtils.post()
                .url(ApiConstants.REGIST)
                .addParams("userName", phone)
                .addParams("passWord", md5Pwd)
                .addParams("smscode", verification)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.e("RegistActivity ", "btn_regist response:" + response);
                        try {
                            JSONObject jo = new JSONObject(response);
                            int success = (int) jo.get("success");
                            if (success == 1) {
                                Toast.makeText(RegistActivity.this, "该用户已注册！", Toast.LENGTH_SHORT).show();
                            } else if (success == 0) {
                                Toast.makeText(RegistActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
    }

    private CountDownTimer downTimer = new CountDownTimer(60 * 1000, 1000) {
        @Override
        public void onTick(long l) {
            mBtnGetVerifi.setEnabled(false);
            mBtnGetVerifi.setText((l / 1000) + "秒");
            LogUtil.e("CountDownTimer", "倒计时");
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
                checkUserIsRegist();
                break;
            case R.id.btn_regist:
                submit();
                break;
        }
    }

    /**
     * 判断用户
     */
    private void checkUserIsRegist() {
        String phone = mEtPhone.getText().toString().trim();
        OkHttpUtils.get()
                .url(ApiConstants.CHECK_USER)
                .addParams("userName", phone)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.e("判断是否注册过 response: ", response);
                        try {
                            JSONObject jo = new JSONObject(response);
                            if ((int) jo.get("success") == 0) {
                                Toast.makeText(RegistActivity.this, "该用户已注册!", Toast.LENGTH_SHORT).show();
                            } else {
                                sendVerification();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void sendVerification() {
        String phone = mEtPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        downTimer.start();

        OkHttpUtils.get()
                .url(ApiConstants.SEND_VERIFI_CODE)
                .addParams("userName", phone)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.e("验证码response: ", response);
                        try {
                            JSONObject jo = new JSONObject(response);
                            if ((int) jo.get("success") == 1) {
                                Toast.makeText(RegistActivity.this, "获取验证码失败!", Toast.LENGTH_SHORT).show();
                            } else if((int) jo.get("success") == 0) {
                              //  mEtVerification.setText((String) jo.get("Vcode"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

}
