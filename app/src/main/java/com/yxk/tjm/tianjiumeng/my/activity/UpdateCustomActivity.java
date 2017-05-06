package com.yxk.tjm.tianjiumeng.my.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.yxk.tjm.tianjiumeng.App;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.activity.BaseActivity;
import com.yxk.tjm.tianjiumeng.custom.AmountView;
import com.yxk.tjm.tianjiumeng.my.adapter.UpdateCustomAdapter;
import com.yxk.tjm.tianjiumeng.my.bean.UpdataCustomBean;
import com.yxk.tjm.tianjiumeng.network.ApiConstants;
import com.yxk.tjm.tianjiumeng.utils.LogUtil;
import com.yxk.tjm.tianjiumeng.utils.To;
import com.yxk.tjm.tianjiumeng.utils.UserUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;

public class UpdateCustomActivity extends BaseActivity {
    private static final String TAG = "UpdateCustomActivity ";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_product_pic)
    TextView tvProductPic;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.tv_cm)
    TextView tvCm;
    @BindView(R.id.et_width)
    EditText etWidth;
    @BindView(R.id.et_height)
    EditText etHeight;
    @BindView(R.id.et_length)
    EditText etLength;
    @BindView(R.id.amount_view)
    AmountView amountView;
    @BindView(R.id.tv_textrue)
    TextView tvTextrue;
    @BindView(R.id.linear_texture)
    LinearLayout linearTexture;
    @BindView(R.id.et_commodity_introduce)
    EditText etCommodityIntroduce;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.tv_toolbar)
    TextView tvToolbar;

    private UpdateCustomAdapter updateCustomAdapter;
    private String tailorId;
    private String updateCustom;
    private UpdataCustomBean updataCustomBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_custom);
        App.getActivityManager().pushActivity(this);
        ButterKnife.bind(this);

        tailorId = getIntent().getStringExtra("tailorId");
        updateCustom = getIntent().getStringExtra("updateCustom");
        if (!TextUtils.isEmpty(updateCustom)) {
            tvToolbar.setText("修改定制");
        }

        setToolbarNavigationClick();

        initData();
    }

    private void initData() {
        LinearLayoutManager layoutManager = new GridLayoutManager(this, 4);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layoutManager);
        updateCustomAdapter = new UpdateCustomAdapter();
        recycler.setAdapter(updateCustomAdapter);

        qryData();
    }

    /**
     * 查询数据
     */
    private void qryData() {
        OkHttpUtils.get()
                .url(ApiConstants.MY_CUSTOM_QRY)
                .addParams("tailorId", tailorId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.e(TAG, response);
                        Gson gson = new Gson();
                        updataCustomBean = gson.fromJson(response, UpdataCustomBean.class);
                        updateCustomAdapter.setMatchData(updataCustomBean.getTailorPics());
                        String[] sizeArr = updataCustomBean.getTailorSize().split("x");
                        if (sizeArr.length > 0) {
                            etLength.setText(sizeArr[0]);
                            etWidth.setText(sizeArr[1]);
                            etHeight.setText(sizeArr[2]);
                        }
                        tvTextrue.setText(updataCustomBean.getTailorMaterial());
                        amountView.etAmount.setText(updataCustomBean.getTailorAmount());
                        etCommodityIntroduce.setText(updataCustomBean.getTailorDecr());
                    }
                });
    }

    /**
     * 提交
     */
    private void submit() {
        String describe = etCommodityIntroduce.getText().toString().trim();
        if (TextUtils.isEmpty(describe)) {
            To.showShort(getApplicationContext(), "描述不能为空");
            return;
        }

        String length = etLength.getText().toString().trim();
        String width = etWidth.getText().toString().trim();
        String height = etHeight.getText().toString().trim();
        if (TextUtils.isEmpty(length) && TextUtils.isEmpty(width) && TextUtils.isEmpty(height)) {
            To.showShort(getApplicationContext(), "长x宽x高不能为空");
            return;
        }

        String material = tvTextrue.getText().toString().trim();

        JsonObject jo = new JsonObject();
        jo.addProperty("userId", UserUtil.getUserId(App.getAppContext()));
        jo.addProperty("tailorId", tailorId);
        jo.addProperty("tailorAmount", amountView.getEditContent());
        jo.addProperty("tailorMaterial", material);
        jo.addProperty("tailorSize", length + "x" + width + "x" + height);
        jo.addProperty("tailorDecr", describe);
        OkHttpUtils.postString()
                .url(ApiConstants.MY_CUSTOM_UPDATE)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .content(jo.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.e(TAG, "submit()  Exception: " + e);
                        To.showLong(App.getAppContext(), "修改失败了~");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.e(TAG, "submit(): " + response);
                        To.showLong(App.getAppContext(), "修改成功！");
                        finish();
                    }
                });

    }


    @OnClick(R.id.btn_submit)
    public void onClick() {
        submit();
    }

    private void setToolbarNavigationClick() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


}
