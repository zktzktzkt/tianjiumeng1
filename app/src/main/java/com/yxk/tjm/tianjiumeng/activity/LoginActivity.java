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

import com.yxk.tjm.tianjiumeng.MainActivity;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.custom.CircleImageView;
import com.yxk.tjm.tianjiumeng.network.ApiConstants;
import com.yxk.tjm.tianjiumeng.utils.LogUtil;
import com.yxk.tjm.tianjiumeng.utils.MD5Util;
import com.yxk.tjm.tianjiumeng.utils.SPUtil;
import com.yxk.tjm.tianjiumeng.utils.UserUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

import static com.yxk.tjm.tianjiumeng.R.id.et_phone;

/**
 * 登录
 */
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
        mEtPhone = (EditText) findViewById(et_phone);
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

    /**
     * 提交登录
     */
    private void submit() {
        final String phone = mEtPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        String pwd = mEtPwd.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(this, "至少六位密码", Toast.LENGTH_SHORT).show();
            return;
        }

        String md5Pwd = MD5Util.MD5(pwd);
        OkHttpUtils
                .post()
                .url(ApiConstants.LOGIN)
                .addParams("userName", phone)
                .addParams("passWord", md5Pwd)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.e("LoginActivity ", "submit() response:" + response);

                        try {
                            JSONObject jo = new JSONObject(response);
                            int success = (int) jo.get("success");
                            if (success == 1) {
                                Toast.makeText(LoginActivity.this, "用户不存在！", Toast.LENGTH_SHORT).show();
                            } else if (success == 0) {
                                Toast.makeText(LoginActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                                SPUtil.putAndApply(getApplicationContext(), "userName", phone);
                                //设置状态为已登录
                                UserUtil.setLoginState(getApplicationContext(), true);
                                //保存userid
                                UserUtil.setUserId(getApplicationContext(), (int) jo.get("userId"));

                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();
                            } else {
                                Toast.makeText(LoginActivity.this, "密码错误！", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                submit();
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
