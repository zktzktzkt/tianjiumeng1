package com.yxk.tjm.tianjiumeng.my.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yxk.tjm.tianjiumeng.App;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.activity.BaseActivity;
import com.yxk.tjm.tianjiumeng.custom.MyDialog;
import com.yxk.tjm.tianjiumeng.my.bean.WaitPayBean;
import com.yxk.tjm.tianjiumeng.network.ApiConstants;
import com.yxk.tjm.tianjiumeng.utils.DateUtil;
import com.yxk.tjm.tianjiumeng.utils.LogUtil;
import com.yxk.tjm.tianjiumeng.utils.To;
import com.yxk.tjm.tianjiumeng.utils.UserUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.Call;

public class WaitPayActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView recycler;
    private Toolbar mToolbar;
    private MyDialog dialog;
    private View cancelPayDialog;
    private List<WaitPayBean> waitPayBeanList;

    /**
     * 记录条目点击的position
     */
    int pos = -1;
    private TextView tv_1;
    private TextView tv_2;
    private TextView tv_3;
    private TextView tv_4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_pay);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolbarNavigationClick();
        recycler = (RecyclerView) findViewById(R.id.recycler);

        getData();

        recycler.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.btn_cancel_pay:

                        pos = position;

                        cancelPayDialog = View.inflate(WaitPayActivity.this, R.layout.dialog_cancel_pay, null);

                        tv_1 = (TextView) cancelPayDialog.findViewById(R.id.tv_1);
                        tv_2 = (TextView) cancelPayDialog.findViewById(R.id.tv_2);
                        tv_3 = (TextView) cancelPayDialog.findViewById(R.id.tv_3);
                        tv_4 = (TextView) cancelPayDialog.findViewById(R.id.tv_4);

                        MyDialog.Builder builder = new MyDialog.Builder(WaitPayActivity.this);
                        dialog = builder.style(R.style.Dialog)
                                .view(cancelPayDialog)
                                .heightdp(280)
                                .widthdp(240)
                                .cancelTouchout(true)
                                .addViewOnclick(R.id.btn_confirm, WaitPayActivity.this)
                                .addViewOnclick(R.id.rl_one, WaitPayActivity.this)
                                .addViewOnclick(R.id.rl_two, WaitPayActivity.this)
                                .addViewOnclick(R.id.rl_three, WaitPayActivity.this)
                                .addViewOnclick(R.id.rl_four, WaitPayActivity.this)
                                .build();
                        dialog.show();

                        break;
                }
            }
        });
    }

    private void getData() {
        OkHttpUtils.get()
                .url(ApiConstants.MY_ORDER)
                .addParams("userId", UserUtil.getUserId(App.getAppContext()))
                .addParams("state", "1")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.e("WaitPayActivity response:", response);
                        waitPayBeanList = new Gson().fromJson(response, new TypeToken<List<WaitPayBean>>() {
                        }.getType());

                        recycler.setAdapter(new BaseQuickAdapter<WaitPayBean, BaseViewHolder>(R.layout.item_wait_pay, waitPayBeanList) {
                            @Override
                            protected void convert(BaseViewHolder helper, WaitPayBean item) {
                                helper.addOnClickListener(R.id.btn_cancel_pay);
                                Glide.with(App.getAppContext()).load(item.getGoodsShowpic()).into((ImageView) helper.getView(R.id.img_pic));
                                helper.setText(R.id.tv_date, DateUtil.longToString(item.getCreateDate(), "yyyy.MM.dd"));
                                helper.setText(R.id.tv_return_size, "尺寸：" + item.getSize() + "cm");
                                helper.setText(R.id.tv_texture, "材质：" + item.getGoodsMaterial());
                                helper.setText(R.id.tv_num, "数量：" + item.getAmount() + "个");
                                helper.setText(R.id.tv_detail, item.getGoodsDescr());
                            }
                        });
                    }
                });
    }

    private void setToolbarNavigationClick() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_confirm:
                if (!cancelPayDialog.findViewById(R.id.iv_1).isSelected()
                        && !cancelPayDialog.findViewById(R.id.iv_2).isSelected()
                        && !cancelPayDialog.findViewById(R.id.iv_3).isSelected()
                        && !cancelPayDialog.findViewById(R.id.iv_4).isSelected()) {
                    To.showShort(App.getAppContext(), "请选择取消原因！");
                    return;
                }

                if (cancelPayDialog.findViewById(R.id.iv_1).isSelected()) {
                    confirm(tv_1.getText().toString().trim());
                } else if (cancelPayDialog.findViewById(R.id.iv_2).isSelected()) {
                    confirm(tv_2.getText().toString().trim());
                } else if (cancelPayDialog.findViewById(R.id.iv_3).isSelected()) {
                    confirm(tv_3.getText().toString().trim());
                } else if (cancelPayDialog.findViewById(R.id.iv_4).isSelected()) {
                    confirm(tv_4.getText().toString().trim());
                }
                break;
            case R.id.rl_one:
                setItemSelected(R.id.rl_one);
                break;
            case R.id.rl_two:
                setItemSelected(R.id.rl_two);
                break;
            case R.id.rl_three:
                setItemSelected(R.id.rl_three);
                break;
            case R.id.rl_four:
                setItemSelected(R.id.rl_four);
                break;
        }
    }

    private void confirm(String cancelReason) {
        OkHttpUtils.post()
                .url(ApiConstants.MY_ORDER_CANCEL)
                .addParams("orderId", waitPayBeanList.get(pos).getOrderId())
                .addParams("cancelReason", cancelReason)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        pos = -1;
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            JSONObject jo = new JSONObject(response);
                            if ((int) jo.get("success") == 0) {
                                To.showShort(App.getAppContext(), "提交成功");
                                getData();
                                dialog.dismiss();
                            }
                            pos = -1;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    public void setItemSelected(int resId) {
        if (resId == R.id.rl_one) {
            cancelPayDialog.findViewById(R.id.iv_1).setSelected(true);
            cancelPayDialog.findViewById(R.id.iv_2).setSelected(false);
            cancelPayDialog.findViewById(R.id.iv_3).setSelected(false);
            cancelPayDialog.findViewById(R.id.iv_4).setSelected(false);
        } else if (resId == R.id.rl_two) {
            cancelPayDialog.findViewById(R.id.iv_1).setSelected(false);
            cancelPayDialog.findViewById(R.id.iv_2).setSelected(true);
            cancelPayDialog.findViewById(R.id.iv_3).setSelected(false);
            cancelPayDialog.findViewById(R.id.iv_4).setSelected(false);
        } else if (resId == R.id.rl_three) {
            cancelPayDialog.findViewById(R.id.iv_1).setSelected(false);
            cancelPayDialog.findViewById(R.id.iv_2).setSelected(false);
            cancelPayDialog.findViewById(R.id.iv_3).setSelected(true);
            cancelPayDialog.findViewById(R.id.iv_4).setSelected(false);
        } else if (resId == R.id.rl_four) {
            cancelPayDialog.findViewById(R.id.iv_1).setSelected(false);
            cancelPayDialog.findViewById(R.id.iv_2).setSelected(false);
            cancelPayDialog.findViewById(R.id.iv_3).setSelected(false);
            cancelPayDialog.findViewById(R.id.iv_4).setSelected(true);
        }
    }
}
