package com.yxk.tjm.tianjiumeng.category.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yxk.tjm.tianjiumeng.App;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.activity.BaseActivity;
import com.yxk.tjm.tianjiumeng.category.bean.RightCategoryBean;
import com.yxk.tjm.tianjiumeng.custom.MyRadioGroup;
import com.yxk.tjm.tianjiumeng.home.activity.ProductDetailActivity;
import com.yxk.tjm.tianjiumeng.network.ApiConstants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

import static com.yxk.tjm.tianjiumeng.R.id.rb_new_product;
import static com.yxk.tjm.tianjiumeng.R.id.rb_price;
import static com.yxk.tjm.tianjiumeng.R.id.rb_sale_amount;

public class RightHeaderListActivity extends BaseActivity {
    private static final String TAG = "RightHeaderListActivity ";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(rb_sale_amount)
    RadioButton rbSaleAmount;
    @BindView(R.id.iv_sale_amount)
    ImageView ivSaleAmount;
    @BindView(rb_price)
    RadioButton rbPrice;
    @BindView(R.id.iv_price)
    ImageView ivPrice;
    @BindView(rb_new_product)
    RadioButton rbNewProduct;
    @BindView(R.id.my_radio_group)
    MyRadioGroup myRadioGroup;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private int typeId;
    private int brandId;
    private List<RightCategoryBean> rightList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_right_header_list);
        ButterKnife.bind(this);

        typeId = getIntent().getIntExtra("typeId", -1);
        Log.e(TAG, "typeId:" + typeId);
        brandId = getIntent().getIntExtra("brandId", -1);

        setToolbarNavigationClick();

        typeChecked();

        /**
         * 初始化数据  // 1 是乱序显示，2是根据销量，3是根据价格，4是根据新品
         * */
        initData("1");

        recycler.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(RightHeaderListActivity.this, ProductDetailActivity.class));
            }
        });
    }

    /**
     * 根据选项排序
     */
    private void typeChecked() {
        myRadioGroup.setOnCheckedChangeListener(new MyRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(MyRadioGroup group, int checkedId) {
                switch (checkedId) {
                    case rb_sale_amount:
                        initData("2");
                        ivSaleAmount.setSelected(true);
                        ivPrice.setSelected(false);
                        break;

                    case rb_price:
                        initData("3");
                        ivPrice.setSelected(true);
                        ivSaleAmount.setSelected(false);
                        break;

                    case rb_new_product:
                        initData("4");
                        ivPrice.setSelected(false);
                        ivSaleAmount.setSelected(false);
                        break;
                }
            }
        });
    }

    private void initData(String no) {
        OkHttpUtils.get()
                .url(ApiConstants.CATEGORY_LIST)
                .addParams("no", no)  // 1 是乱序显示，2是根据销量，3是根据价格，4是根据新品
                .addParams("typeId", typeId + "")
                .addParams("brandId", brandId + "")
                .addParams("pageNo", "1")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, response);
                        rightList = new Gson().fromJson(response, new TypeToken<List<RightCategoryBean>>() {
                        }.getType());

                        recycler.setLayoutManager(new GridLayoutManager(RightHeaderListActivity.this, 2));
                        recycler.setAdapter(new BaseQuickAdapter<RightCategoryBean, BaseViewHolder>(R.layout.item_right_header_category_list, rightList) {
                            @Override
                            protected void convert(BaseViewHolder helper, RightCategoryBean item) {
                                Glide.with(App.getAppContext()).load(item.getShowpic()).into((ImageView) helper.getView(R.id.img_pic));
                                helper.setText(R.id.tv_name, item.getName());
                                helper.setText(R.id.tv_price, getResources().getString(R.string.RMB) + item.getNowprice());
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


}
