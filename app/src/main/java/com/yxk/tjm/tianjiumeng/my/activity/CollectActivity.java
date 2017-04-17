package com.yxk.tjm.tianjiumeng.my.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.activity.BaseActivity;
import com.yxk.tjm.tianjiumeng.custom.MyToolbar;
import com.yxk.tjm.tianjiumeng.my.adapter.MyCollectAdapter;
import com.yxk.tjm.tianjiumeng.my.bean.CollectBeannn;
import com.yxk.tjm.tianjiumeng.network.ApiConstants;
import com.yxk.tjm.tianjiumeng.utils.UserUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class CollectActivity extends BaseActivity {
    private static final String TAG = "CollectActivity ";

    @BindView(R.id.toolbar)
    MyToolbar toolbar;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.cb_all)
    CheckBox cbAll;
    @BindView(R.id.btn_delete)
    Button btnDelete;
    @BindView(R.id.relative_bottom)
    RelativeLayout relativeBottom;
    private MyCollectAdapter myCollectAdapter;
    private CollectBeannn collectBeannn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        ButterKnife.bind(this);

        relativeBottom.setVisibility(View.GONE);

        toolbarEditClickListener();
        toolbar.setTitle("我的收藏");

        initData();
    }

    private void initData() {
        OkHttpUtils.get()
                .url(ApiConstants.MY_COLLECT)
                .addParams("userId", UserUtil.getUserId(getApplicationContext()))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "我的收藏 Exception" + e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, "我的收藏 response" + response);
                        collectBeannn = new Gson().fromJson(response, CollectBeannn.class);

                        myCollectAdapter = new MyCollectAdapter(collectBeannn.getCollectitem(), cbAll, btnDelete);
                        recycler.setAdapter(myCollectAdapter);
                    }
                });

    }


    private void toolbarEditClickListener() {
        toolbar.setOnEditClickListener(new MyToolbar.OnEditClickListener() {
            @Override
            public void onEditClik(String tag) {
                switch (tag) {
                    //点击编辑
                    case MyToolbar.EDIT_TAG:
                        relativeBottom.setVisibility(View.VISIBLE);
                        listCheckboxState(false);
                        cbAll.setChecked(false);
                        myCollectAdapter.setChildCheckboxVisible(true);
                        myCollectAdapter.notifyDataSetChanged();
                        break;

                    //点击完成
                    case MyToolbar.OK_TAG:
                        relativeBottom.setVisibility(View.GONE);
                        myCollectAdapter.setChildCheckboxVisible(false);
                        myCollectAdapter.notifyDataSetChanged();
                        break;
                }
            }
        });
    }

    private void listCheckboxState(boolean state) {
        for (CollectBeannn.CollectitemBean collectBean : collectBeannn.getCollectitem()) {
            collectBean.setChecked(state);
        }
    }
}
