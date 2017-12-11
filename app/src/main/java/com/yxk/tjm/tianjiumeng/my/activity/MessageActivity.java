package com.yxk.tjm.tianjiumeng.my.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yxk.tjm.tianjiumeng.App;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.activity.BaseActivity;
import com.yxk.tjm.tianjiumeng.custom.CustomLoadMoreView;
import com.yxk.tjm.tianjiumeng.my.bean.MessageBean;
import com.yxk.tjm.tianjiumeng.network.ApiConstants;
import com.yxk.tjm.tianjiumeng.utils.DateUtil;
import com.yxk.tjm.tianjiumeng.utils.UserUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

import okhttp3.Call;


public class MessageActivity extends BaseActivity {

    private RecyclerView recycler;
    private Toolbar mToolbar;
    private List<MessageBean> messageBeanList;
    private int pageCount = 2;
    private MessageAdapter messageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        App.getActivityManager().pushActivity(this);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolbarNavigationClick();
        recycler = (RecyclerView) findViewById(R.id.recycler);
        messageAdapter = new MessageAdapter();
        messageAdapter.setLoadMoreView(new CustomLoadMoreView());
        recycler.setAdapter(messageAdapter);


        messageAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                Log.e("加载更多", "加载更多|||||||||||||||||||||||||||");
                loadMore(pageCount);
            }
        }, recycler);

        OkHttpUtils.get()
                .url(ApiConstants.MY_MESSAGE)
                .addParams("userId", UserUtil.getUserId(getApplicationContext()))
                .addParams("pageNo", "1")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        messageBeanList = new Gson().fromJson(response, new TypeToken<List<MessageBean>>() {
                        }.getType());
                        messageAdapter.setNewData(messageBeanList);
                    }
                });

    }

    private void loadMore(int pageNo) {
        OkHttpUtils.get()
                .url(ApiConstants.MY_MESSAGE)
                .addParams("userId", UserUtil.getUserId(getApplicationContext()))
                .addParams("pageNo", pageNo + "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        messageAdapter.loadMoreFail();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        List<MessageBean> messageBeanList = new Gson().fromJson(response, new TypeToken<List<MessageBean>>() {
                        }.getType());
                        try {
                            JSONArray ja = new JSONArray(response);
                            if ("[]".equals(ja.toString())) {
                                messageAdapter.loadMoreEnd(false);
                            } else {
                                messageAdapter.addData(messageBeanList);
                                pageCount++;
                                messageAdapter.loadMoreComplete();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private class MessageAdapter extends BaseQuickAdapter<MessageBean, BaseViewHolder> {

        private MessageAdapter() {
            super(R.layout.item_message, messageBeanList);
        }

        @Override
        protected void convert(BaseViewHolder helper, MessageBean item) {
            helper.setText(R.id.tv_price, item.getReplyText());
            helper.setText(R.id.tv_time, DateUtil.longToString(item.getReplyTime(), "yyyy-MM-dd"));
        }
    }

    private void setToolbarNavigationClick() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
