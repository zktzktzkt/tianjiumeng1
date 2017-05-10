package com.yxk.tjm.tianjiumeng.my.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yxk.tjm.tianjiumeng.App;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.activity.BaseActivity;
import com.yxk.tjm.tianjiumeng.my.bean.CrystalBean;
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

public class MyCrystalActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.et_cystal_amount)
    EditText etCystalAmount;
    @BindView(R.id.tv_rule)
    TextView tvRule;
    @BindView(R.id.tv_tranable)
    TextView tvTranable;
    @BindView(R.id.tv_income)
    TextView tvIncome;
    private CrystalBean crystalBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_crystal);
        ButterKnife.bind(this);

        setToolbarNavigationClick();

        initData();
    }

    private void initData() {
        OkHttpUtils.get()
                .url(ApiConstants.MY_CRYSTAL_TRANS)
                .addParams("userId", UserUtil.getUserId(getApplicationContext()))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.e(e+"");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.e(response);

                        crystalBean = new Gson().fromJson(response, CrystalBean.class);
                        tvRule.setText(crystalBean.getJewelTranRules().replace("\\n", "\n"));
                        tvTranable.setText(crystalBean.getJeweltranable());
                        tvIncome.setText(crystalBean.getIncome());

                        etCystalAmount.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {

                            }

                            @Override
                            public void afterTextChanged(Editable s) {
                                if (!TextUtils.isEmpty(s.toString())) {
                                    etCystalAmount.setSelection(s.toString().length()); //在第一个字符后显示光标
                                    if (Integer.parseInt(s.toString()) > Integer.parseInt(crystalBean.getJeweltranable())) {
                                        etCystalAmount.setText(crystalBean.getJeweltranable());
                                        return;
                                    }
                                }
                            }
                        });
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

    @OnClick(R.id.btn_submit)
    public void onViewClicked() {
        if (TextUtils.isEmpty(etCystalAmount.getText().toString().trim())) {
            To.showShort(getApplicationContext(), "数量不能为空");

        } else {
            OkHttpUtils.get()
                    .url(ApiConstants.MY_CRYSTAL_AFFIRM_TRANSED)
                    .addParams("userId", UserUtil.getUserId(App.getAppContext()))
                    .addParams("jeweltraned", etCystalAmount.getText().toString().trim())
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            LogUtil.e(e + "");
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            try {
                                JSONObject jo = new JSONObject(response);
                                if ((int) jo.get("seccess") == 0) {
                                    To.showShort(getApplicationContext(), "转换成功");
                                    finish();
                                } else {
                                    To.showShort(getApplicationContext(), "转换失败");
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });
        }
    }
}
