package com.yxk.tjm.tianjiumeng.home.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.activity.BaseActivity;
import com.yxk.tjm.tianjiumeng.custom.CustomLoadMoreView;
import com.yxk.tjm.tianjiumeng.home.bean.SearchListBean;
import com.yxk.tjm.tianjiumeng.network.ApiConstants;
import com.yxk.tjm.tianjiumeng.utils.LogUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.MediaType;

public class SearchListActivity extends BaseActivity {

    @BindView(R.id.tv_toolbar)
    TextView tvToolbar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private String searchData;
    private String keywords;
    private List<SearchListBean> searchList;

    private int pageCount = 2;
    private SearchListAdapter searchListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);
        ButterKnife.bind(this);

        searchData = getIntent().getStringExtra("searchData");
        keywords = getIntent().getStringExtra("keywords");

        if (!TextUtils.isEmpty(searchData)) {
            searchList = new Gson().fromJson(searchData, new TypeToken<List<SearchListBean>>() {
            }.getType());
            recycler.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
            searchListAdapter = new SearchListAdapter();
            searchListAdapter.setLoadMoreView(new CustomLoadMoreView());
            recycler.setAdapter(searchListAdapter);

            recycler.addOnItemTouchListener(new OnItemClickListener() {
                @Override
                public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

                }
            });

            searchListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    Log.e("加载更多", "加载更多|||||||||||||||||||||||||||");
                    loadMore(keywords, pageCount);
                }
            }, recycler);

        }
    }

    private void loadMore(final String keywords, final int pageNo) {
        JsonObject jo = new JsonObject();
        jo.addProperty("keywords", keywords);
        jo.addProperty("pageNo", pageNo + "");
        LogUtil.e("searchListActivity ", "pageNo:" + pageNo);

        OkHttpUtils.postString()
                .url(ApiConstants.SEARCH_QRY)
                .content(jo.toString())
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        searchListAdapter.loadMoreFail();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.e("searchListActivity ", response);
                        List<SearchListBean> searchMoreList = new Gson().fromJson(response, new TypeToken<List<SearchListBean>>() {
                        }.getType());
                        try {
                            JSONArray ja = new JSONArray(response);
                            if ("[]".equals(ja.toString())) {
                                searchListAdapter.loadMoreEnd(false);
                            } else {
                                searchListAdapter.addData(searchMoreList);
                                pageCount++;
                                searchListAdapter.loadMoreComplete();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

    }


    private class SearchListAdapter extends BaseQuickAdapter<SearchListBean, BaseViewHolder> {

        private SearchListAdapter() {
            super(R.layout.item_right_header_category_list, searchList);
        }

        @Override
        protected void convert(BaseViewHolder helper, SearchListBean item) {
            helper.setText(R.id.tv_name, item.getName());
            helper.setText(R.id.tv_price, getResources().getString(R.string.RMB) + item.getNowprice());
            Glide.with(getApplicationContext()).load(item.getShowpic()).into((ImageView) helper.getView(R.id.img_pic));
        }
    }

}
