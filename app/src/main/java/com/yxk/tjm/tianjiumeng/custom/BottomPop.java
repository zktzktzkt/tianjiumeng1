package com.yxk.tjm.tianjiumeng.custom;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.yxk.tjm.tianjiumeng.App;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.home.adapter.PopSpecAdapter;
import com.yxk.tjm.tianjiumeng.home.bean.ProductDetailBeann;
import com.yxk.tjm.tianjiumeng.utils.T;

import java.util.List;

/**
 * Created by ningfei on 2017/3/9.
 */

public class BottomPop extends PopupWindow {
    public static final String ADD_SHOP_CART = "ADD_SHOP_CART";
    public static final String BUY = "BUY";
    public static  String COMMON = "COMMON";

    private  View view;
    private final RecyclerView recycler;
    private final Button btn_confirm;
    List<ProductDetailBeann.HWsBean> hWsBeanList;

    public BottomPop(Context context, List<ProductDetailBeann.HWsBean> hWsBeanList) {
        super(context);
        view = View.inflate(context, R.layout.dialog_spec, null);
        setContentView(view);
        this.hWsBeanList = hWsBeanList;

        recycler = (RecyclerView) view.findViewById(R.id.recycler);
        btn_confirm = (Button) view.findViewById(R.id.btn_confirm);

        config();

        initConfirmBtnListener();

        setTouchListener();

        initRecyclerData(context);

    }

    private void initConfirmBtnListener() {
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(COMMON == BUY){
                    T.showShort(App.getAppContext(), "购买");
                    dismiss();
                }else if(COMMON == ADD_SHOP_CART){
                    T.showShort(App.getAppContext(), "加购物车");
                    dismiss();
                }
            }
        });
    }

    private void initRecyclerData(Context context) {

        recycler.setLayoutManager(new GridLayoutManager(context, 4));
        final PopSpecAdapter popSpecAdapter = new PopSpecAdapter();
        popSpecAdapter.setMatchData(hWsBeanList);
        recycler.setAdapter(popSpecAdapter);

        popSpecAdapter.setOnItemClickListener(new PopSpecAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (position != popSpecAdapter.getLastPos()) {
                    if (popSpecAdapter.getLastPos() != -1) {
                        hWsBeanList.get(popSpecAdapter.getLastPos()).setSelected(false);
                    }
                }

                if (hWsBeanList.get(position).isSelected()) {
                    hWsBeanList.get(position).setSelected(false);
                } else {
                    hWsBeanList.get(position).setSelected(true);
                }

                popSpecAdapter.notifyDataSetChanged();
                popSpecAdapter.setLastPos(position);
            }
        });
    }

    private void config() {
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);

        this.setOutsideTouchable(false);
        // 设置弹出窗体可点击
        this.setFocusable(true);
        // 设置弹出窗体的背景
        this.setBackgroundDrawable(new ColorDrawable(0xb0000000));
        this.setOutsideTouchable(false);
        // 设置弹出窗体显示时的动画，从底部向上弹出
        this.setAnimationStyle(R.style.take_photo_anim);
    }

    private void setTouchListener() {
        this.view.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = view.findViewById(R.id.linear_pop).getTop();

                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        //  dismiss();
                    }
                }
                return true;
            }
        });
    }
}
