package com.yxk.tjm.tianjiumeng.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.yxk.tjm.tianjiumeng.R;


/**
 * 自定义组件：购买数量，带减少增加按钮
 * Created by hiwhitley on 2016/7/4.
 */
public class AmountView extends RelativeLayout implements View.OnClickListener, TextWatcher {

    private static final String TAG = "AmountView";
    private int amount = 1; //购买数量
    private int goods_storage = 100; //商品库存

    private OnAmountChangeListener mListener;

    public EditText etAmount;
    private ImageButton btnDecrease;
    private ImageButton btnIncrease;
    private LinearLayout linear_layout;

    public AmountView(Context context) {
        this(context, null);
    }

    public AmountView(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.view_amount, this);
        etAmount = (EditText) findViewById(R.id.etAmount);
        btnDecrease = (ImageButton) findViewById(R.id.btnDecrease);
        btnIncrease = (ImageButton) findViewById(R.id.btnIncrease);
        linear_layout = (LinearLayout) findViewById(R.id.linear_layout);

        btnDecrease.setOnClickListener(this);
        btnIncrease.setOnClickListener(this);
        etAmount.addTextChangedListener(this);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AmountView);
        int indexCount = typedArray.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArray.getIndex(i);
            switch (index) {
                case R.styleable.AmountView_AddBtnDrawable:
                    btnIncrease.setBackground(typedArray.getDrawable(index));
                    break;
                case R.styleable.AmountView_ReduceBtnDrawable:
                    btnDecrease.setBackground(typedArray.getDrawable(index));
                    break;
                case R.styleable.AmountView_AllWidth:
                    linear_layout.getLayoutParams().width = (int) typedArray.getDimension(index, LayoutParams.WRAP_CONTENT);
                    break;
                case R.styleable.AmountView_etColor:
                    etAmount.setTextColor(typedArray.getColor(index, getResources().getColor(R.color.tabBg)));
                    break;
                case R.styleable.AmountView_AddBtnWidth:
                    btnIncrease.getLayoutParams().width = (int) typedArray.getDimension(index, LayoutParams.WRAP_CONTENT);
                    btnIncrease.getLayoutParams().height = (int) typedArray.getDimension(index, LayoutParams.WRAP_CONTENT);
                    break;
                case R.styleable.AmountView_ReduceBtnWidth:
                    btnDecrease.getLayoutParams().width = (int) typedArray.getDimension(index, LayoutParams.WRAP_CONTENT);
                    btnDecrease.getLayoutParams().height = btnDecrease.getLayoutParams().width;
                    break;
            }
        }
        typedArray.recycle();
    }

    public void setOnAmountChangeListener(OnAmountChangeListener onAmountChangeListener) {
        this.mListener = onAmountChangeListener;
    }

    public void setGoods_storage(int goods_storage) {
        this.goods_storage = goods_storage;
    }

    public String getEditContent(){
        return etAmount.getText().toString().trim();
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btnDecrease) {
            if (amount > 1) {
                amount--;
                etAmount.setText(amount + "");
            }
        } else if (i == R.id.btnIncrease) {
            if (amount < goods_storage) {
                amount++;
                etAmount.setText(amount + "");
            }
        }

        etAmount.clearFocus();

        if (mListener != null) {
            mListener.onAmountChange(this, amount);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.toString().isEmpty()) {
            etAmount.setText(1 + "");
            etAmount.setSelection(1); //在第一个字符后显示光标
            return;
        }
        amount = Integer.valueOf(s.toString());
        etAmount.setSelection(s.toString().length());
        if (amount > goods_storage) {
            etAmount.setText(goods_storage + "");
            return;
        }

        if (mListener != null) {
            mListener.onAmountChange(this, amount);
        }
    }


    public interface OnAmountChangeListener {
        void onAmountChange(View view, int amount);
    }

}
