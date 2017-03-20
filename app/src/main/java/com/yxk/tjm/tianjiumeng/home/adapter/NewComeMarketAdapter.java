package com.yxk.tjm.tianjiumeng.home.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.home.bean.NewComeMarketItem;

import java.util.List;

/**
 * Created by zkt on 2017/3/7.
 */

public class NewComeMarketAdapter extends BaseMultiItemQuickAdapter<NewComeMarketItem, BaseViewHolder> {

    public NewComeMarketAdapter(List<NewComeMarketItem> data) {
        super(data);
        addItemType(NewComeMarketItem.OTHER, R.layout.item_new_come_market);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewComeMarketItem item) {
        switch (helper.getItemViewType()) {
            case NewComeMarketItem.OTHER:

                break;
        }
    }
}
