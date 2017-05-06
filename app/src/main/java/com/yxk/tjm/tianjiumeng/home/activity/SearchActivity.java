package com.yxk.tjm.tianjiumeng.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.yxk.tjm.tianjiumeng.App;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.activity.BaseActivity;
import com.yxk.tjm.tianjiumeng.home.bean.SearchBean;
import com.yxk.tjm.tianjiumeng.network.ApiConstants;
import com.yxk.tjm.tianjiumeng.utils.LogUtil;
import com.yxk.tjm.tianjiumeng.utils.To;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by ningfei on 2017/3/17.
 */

public class SearchActivity extends BaseActivity {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.search)
    EditText search;
    private List<SearchBean> searchBeanList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        App.getActivityManager().pushActivity(this);

        ButterKnife.bind(this);

        initData();

        recycler.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_text:
                        searchListActivity(searchBeanList.get(position).getKeywords());
                        break;
                }
            }
        });
    }

    private void initData() {
        OkHttpUtils.get()
                .url(ApiConstants.SEARCH)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        searchBeanList = new Gson().fromJson(response, new TypeToken<List<SearchBean>>() {
                        }.getType());

                        recycler.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
                        recycler.setAdapter(new SearchAdapter());
                    }
                });

        EditSearchListener();

    }

    private void EditSearchListener() {
        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {

                    String keytag = search.getText().toString().trim();

                    if (TextUtils.isEmpty(keytag)) {
                        Toast.makeText(SearchActivity.this, "请输入搜索关键字", Toast.LENGTH_SHORT).show();
                        return false;
                    }

                    // 搜索功能主体
                    searchListActivity(keytag);

                    return true;
                }
                return false;
            }
        });
    }

    private void searchListActivity(final String keytag) {
        JsonObject jo = new JsonObject();
        jo.addProperty("keywords", keytag);
        jo.addProperty("pageNo", "1");

        OkHttpUtils.postString()
                .url(ApiConstants.SEARCH_QRY)
                .content(jo.toString())
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.e("searchListActivity ", response);
                        try {
                            JSONArray ja = new JSONArray(response);
                            if ("[]".equals(ja.toString())) {
                                To.showShort(getApplicationContext(), "未查询到数据");
                            } else {
                                Intent intent = new Intent(SearchActivity.this, SearchListActivity.class);
                                intent.putExtra("searchData", response);
                                intent.putExtra("keywords", keytag);
                                startActivity(intent);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

    }

    public class SearchAdapter extends BaseQuickAdapter<SearchBean, BaseViewHolder> {

        public SearchAdapter() {
            super(R.layout.item_search_hot, searchBeanList);
        }

        @Override
        protected void convert(BaseViewHolder helper, SearchBean item) {
            helper.setText(R.id.tv_text, item.getKeywords());
            helper.addOnClickListener(R.id.tv_text);
        }
    }

    @OnClick(R.id.iv_back)
    public void onClick() {
        finish();
        overridePendingTransition(R.anim.enter_alpha, R.anim.out_alpha);
    }
}
