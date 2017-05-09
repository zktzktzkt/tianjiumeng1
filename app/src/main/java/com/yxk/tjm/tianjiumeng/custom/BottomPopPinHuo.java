package com.yxk.tjm.tianjiumeng.custom;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.yxk.tjm.tianjiumeng.App;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.event.BusProvider;
import com.yxk.tjm.tianjiumeng.event.EventOne;
import com.yxk.tjm.tianjiumeng.home.adapter.PopSpecPinHuoAdapter;
import com.yxk.tjm.tianjiumeng.home.bean.HotStrugDetailBean;
import com.yxk.tjm.tianjiumeng.network.ApiConstants;
import com.yxk.tjm.tianjiumeng.utils.LogUtil;
import com.yxk.tjm.tianjiumeng.utils.To;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by ningfei on 2017/3/9.
 */

public class BottomPopPinHuo<T> extends PopupWindow {
    public static final String ADD_SHOP_CART = "ADD_SHOP_CART";
    public static final String BUY = "BUY";
    public static String COMMON = "COMMON";

    private View view;
    private final RecyclerView recycler;
    private final Button btn_confirm;
    List<HotStrugDetailBean.HWsBean> hWsBeanList;

    String productId;
    int nowprice;
    private final AmountView amount_view;
    Context context;

    public BottomPopPinHuo(Context context, List<T> list, String productId, int nowprice) {
        super(context);
        this.context = context;
        view = View.inflate(context, R.layout.dialog_spec, null);
        setContentView(view);

        this.hWsBeanList = (List<HotStrugDetailBean.HWsBean>) list;
        this.productId = productId;
        this.nowprice = nowprice;

        for (int i = 0; i < hWsBeanList.size(); i++) {
            hWsBeanList.get(i).setSelected(false);
        }

        recycler = (RecyclerView) view.findViewById(R.id.recycler);
        btn_confirm = (Button) view.findViewById(R.id.btn_confirm);
        amount_view = (AmountView) view.findViewById(R.id.amount_view);

        config();

        setTouchListener();

        initRecyclerData(context);

        initConfirmBtnListener();

    }

    private void initConfirmBtnListener() {
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (COMMON == BUY) {
                    String goodsHW = "";
                    for (int i = 0; i < hWsBeanList.size(); i++) {
                        if (hWsBeanList.get(i).isSelected()) {
                            goodsHW = hWsBeanList.get(i).getGoodsHeight() + "x" + hWsBeanList.get(i).getGoodsWide();
                        }
                    }
                    if (TextUtils.isEmpty(goodsHW)) {
                        To.showShort(context, "请选择产品规格！");
                        return;
                    }

                    EventOne eventOne = new EventOne("跳转提交订单页面");
                    eventOne.setAmount(amount_view.getEditContent());
                    BusProvider.getInstance().post(eventOne);
                    dismiss();

                } /*else if (COMMON == ADD_SHOP_CART) {

                    addNetShopcart();
                }*/
            }
        });
    }

    /**
     * 加入购物车
     */
    private void addNetShopcart() {
        try {
            String goodsHW = "";
            for (int i = 0; i < hWsBeanList.size(); i++) {
                if (hWsBeanList.get(i).isSelected()) {
                    goodsHW = hWsBeanList.get(i).getGoodsHeight() + "x" + hWsBeanList.get(i).getGoodsWide();
                }
            }
            if (TextUtils.isEmpty(goodsHW)) {
                To.showShort(context, "请选择产品规格！");
                return;
            }
            JSONObject jo = new JSONObject();
            jo.put("goodsId", Integer.parseInt(productId));//商品Id
            jo.put("userId", 1);//购买者的id(int)
            jo.put("goodsAccant", Integer.parseInt(amount_view.getEditContent()));//商品数量(int)
            jo.put("goodsPrice", nowprice);//商品单价(int)
            jo.put("goodsHW", goodsHW);//商品的宽*高

            LogUtil.e("BottomPop", "addNetShopcart()" + jo.toString());

            OkHttpUtils
                    .postString()
                    .content(jo.toString())
                    .mediaType(MediaType.parse("application/json; charset=utf-8"))
                    .url(ApiConstants.DETAIL_ADD_SHOPCAR)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            LogUtil.e("addNetShopcart", " Exception: " + e);
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            LogUtil.e("addNetShopcart", " response: " + response);
                            if (COMMON == BUY) {
                                BusProvider.getInstance().post(new EventOne("结束当前页面"));
                            } else {
                                To.showShort(App.getAppContext(), "添加成功");
                            }
                            dismiss();
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void initRecyclerData(Context context) {

        recycler.setLayoutManager(new GridLayoutManager(context, 4));
        final PopSpecPinHuoAdapter popSpecAdapter = new PopSpecPinHuoAdapter();
        popSpecAdapter.setMatchData(hWsBeanList);
        recycler.setAdapter(popSpecAdapter);

        popSpecAdapter.setOnItemClickListener(new PopSpecPinHuoAdapter.OnItemClickListener() {
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
