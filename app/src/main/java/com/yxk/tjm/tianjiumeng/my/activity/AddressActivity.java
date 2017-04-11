package com.yxk.tjm.tianjiumeng.my.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.activity.BaseActivity;
import com.yxk.tjm.tianjiumeng.shopcar.adapter.AddressAdapter;
import com.yxk.tjm.tianjiumeng.shopcar.bean.AddressBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddressActivity extends BaseActivity {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btn_add_address)
    Button btnAddAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        ButterKnife.bind(this);
        setToolbarNavigationClick();

        List<AddressBean> list = new ArrayList<>();
        list.add(new AddressBean());
        list.add(new AddressBean());
        list.add(new AddressBean());
        list.add(new AddressBean());
        list.add(new AddressBean());
        list.add(new AddressBean());

        recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setAdapter(new AddressAdapter(list));
    }

    private void setToolbarNavigationClick() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @OnClick(R.id.btn_add_address)
    public void onClick() {
        startActivity(new Intent(this, AddAddressActivity.class));
    }
}
