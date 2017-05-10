package com.yxk.tjm.tianjiumeng.my.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.yxk.tjm.tianjiumeng.App;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.activity.BaseActivity;
import com.yxk.tjm.tianjiumeng.network.ApiConstants;
import com.yxk.tjm.tianjiumeng.utils.LogUtil;
import com.yxk.tjm.tianjiumeng.utils.To;
import com.yxk.tjm.tianjiumeng.utils.UserUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class MyCommissionActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rl_weixin)
    RelativeLayout rlWeixin;
    @BindView(R.id.rl_alipay)
    RelativeLayout rlAlipay;
    @BindView(R.id.btn_add_address)
    Button btnAddAddress;
    @BindView(R.id.iv_weixin)
    ImageView ivWeixin;
    @BindView(R.id.iv_alipay)
    ImageView ivAlipay;
    @BindView(R.id.et_balance)
    EditText etBalance;
    private int canPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_commission);
        ButterKnife.bind(this);
        App.getActivityManager().pushActivity(this);

        setToolbarNavigationClick();

        initData();
    }

    private void initData() {
        OkHttpUtils.get()
                .url(ApiConstants.MY_CRYSTAL_QRY_COMMISSION)
                .addParams("userId", UserUtil.getUserId(App.getAppContext()))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            JSONObject jo = new JSONObject(response);
                            canPrice = (int) jo.get("rakeoff") - 200;

                            if ((int) jo.get("rakeoff") <= 200) {
                                etBalance.setHint("余额：" + canPrice + "元");
                            } else {
                                etBalance.setHint("可提现余额：" + canPrice + "元");
                                textChangeListener();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        LogUtil.e(response);
                    }
                });
    }


    private void textChangeListener() {
        etBalance.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(etBalance.getText().toString().trim())) {
                    etBalance.setSelection(s.toString().length()); //在第一个字符后显示光标
                    if (Integer.parseInt(s.toString()) > canPrice) {
                        etBalance.setText(canPrice + "");
                        return;
                    }
                }
            }
        });
    }

    private void setToolbarNavigationClick() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @OnClick({R.id.rl_weixin, R.id.rl_alipay, R.id.btn_add_address})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_weixin:
                ivWeixin.setSelected(true);
                ivAlipay.setSelected(false);
                break;

            case R.id.rl_alipay:
                ivAlipay.setSelected(true);
                ivWeixin.setSelected(false);
                break;

            case R.id.btn_add_address:
                if (!ivWeixin.isSelected() && !ivAlipay.isSelected()) {
                    To.showShort(App.getAppContext(), "请选择支付方式");
                    return;
                }

                if (canPrice <= 200) {
                    To.showShort(App.getAppContext(), "余额低于200不可提现");
                    return;
                }
                break;

        }
    }

}
