package com.yxk.tjm.tianjiumeng.category.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.activity.BaseActivity;
import com.yxk.tjm.tianjiumeng.category.bean.RightCategoryBean;
import com.yxk.tjm.tianjiumeng.custom.MyRadioGroup;
import com.yxk.tjm.tianjiumeng.home.activity.ProductDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.yxk.tjm.tianjiumeng.R.id.rb_new_product;
import static com.yxk.tjm.tianjiumeng.R.id.rb_price;
import static com.yxk.tjm.tianjiumeng.R.id.rb_sale_amount;

public class RightHeaderListActivity extends BaseActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_right_header_list);
        ButterKnife.bind(this);

        setToolbarNavigationClick();

        myRadioGroup.setOnCheckedChangeListener(new MyRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(MyRadioGroup group, int checkedId) {
                switch (checkedId) {
                    case rb_sale_amount:
                        ivSaleAmount.setSelected(true);
                        ivPrice.setSelected(false);
                        break;

                    case rb_price:
                        ivPrice.setSelected(true);
                        ivSaleAmount.setSelected(false);
                        break;

                    case rb_new_product:
                        ivPrice.setSelected(false);
                        ivSaleAmount.setSelected(false);
                        break;
                }
            }
        });

        List<RightCategoryBean> rightList = new ArrayList<>();
        rightList.add(new RightCategoryBean(R.drawable.pic_a));
        rightList.add(new RightCategoryBean(R.drawable.pic_b));
        rightList.add(new RightCategoryBean(R.drawable.pic_c));
        rightList.add(new RightCategoryBean(R.drawable.pic_e));
        rightList.add(new RightCategoryBean(R.drawable.pic_d));
        rightList.add(new RightCategoryBean(R.drawable.pic_g));
        rightList.add(new RightCategoryBean(R.drawable.pic_f));
        rightList.add(new RightCategoryBean(R.drawable.pic_c));
        rightList.add(new RightCategoryBean(R.drawable.pic_b));
        recycler.setLayoutManager(new GridLayoutManager(this, 2));
        recycler.setAdapter(new BaseQuickAdapter<RightCategoryBean, BaseViewHolder>(R.layout.item_right_header_category_list, rightList) {
            @Override
            protected void convert(BaseViewHolder helper, RightCategoryBean item) {
                // helper.getLayoutPosition()  //获取当前position
                helper.setImageResource(R.id.img_pic, item.getResImage());
            }
        });

        recycler.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(RightHeaderListActivity.this, ProductDetailActivity.class));
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
