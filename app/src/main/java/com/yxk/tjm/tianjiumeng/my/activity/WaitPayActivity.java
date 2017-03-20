package com.yxk.tjm.tianjiumeng.my.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.activity.BaseActivity;
import com.yxk.tjm.tianjiumeng.custom.MyDialog;
import com.yxk.tjm.tianjiumeng.my.bean.WaitPayBean;

import java.util.ArrayList;
import java.util.List;

public class WaitPayActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView recycler;
    private Toolbar mToolbar;
    private MyDialog dialog;
    private View cancelPayDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_pay);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolbarNavigationClick();

        List<WaitPayBean> list = new ArrayList<>();
        list.add(new WaitPayBean());
        list.add(new WaitPayBean());
        list.add(new WaitPayBean());
        list.add(new WaitPayBean());
        list.add(new WaitPayBean());
        list.add(new WaitPayBean());

        recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setAdapter(new BaseQuickAdapter<WaitPayBean, BaseViewHolder>(R.layout.item_wait_pay, list) {
            @Override
            protected void convert(BaseViewHolder helper, WaitPayBean item) {
                helper.addOnClickListener(R.id.btn_cancel_pay);
            }
        });

        recycler.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.btn_cancel_pay:
                        cancelPayDialog = View.inflate(WaitPayActivity.this, R.layout.dialog_cancel_pay, null);
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
                dialog.dismiss();
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
