package com.yxk.tjm.tianjiumeng.my.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;

import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.activity.BaseActivity;
import com.yxk.tjm.tianjiumeng.custom.MyToolbar;
import com.yxk.tjm.tianjiumeng.my.adapter.MyCollectAdapter;
import com.yxk.tjm.tianjiumeng.my.bean.CollectBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CollectActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    MyToolbar toolbar;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.cb_all)
    CheckBox cbAll;
    @BindView(R.id.btn_delete)
    Button btnDelete;
    @BindView(R.id.relative_bottom)
    RelativeLayout relativeBottom;
    private List<CollectBean> list;
    private MyCollectAdapter myCollectAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        ButterKnife.bind(this);

        relativeBottom.setVisibility(View.GONE);

        toolbarEditClickListener();

        list = new ArrayList<>();
        list.add(new CollectBean());
        list.add(new CollectBean());
        list.add(new CollectBean());
        list.add(new CollectBean());
        list.add(new CollectBean());
        list.add(new CollectBean());
        list.add(new CollectBean());

        toolbar.setTitle("我的收藏");
        myCollectAdapter = new MyCollectAdapter(list, cbAll, btnDelete);
        recycler.setAdapter(myCollectAdapter);
    }


    private void toolbarEditClickListener() {
        toolbar.setOnEditClickListener(new MyToolbar.OnEditClickListener() {
            @Override
            public void onEditClik(String tag) {
                switch (tag) {
                    //点击编辑
                    case MyToolbar.EDIT_TAG:
                        relativeBottom.setVisibility(View.VISIBLE);
                        listCheckboxState(false);
                        cbAll.setChecked(false);
                        myCollectAdapter.setChildCheckboxVisible(true);
                        myCollectAdapter.notifyDataSetChanged();
                        break;

                    //点击完成
                    case MyToolbar.OK_TAG:
                        relativeBottom.setVisibility(View.GONE);
                        myCollectAdapter.setChildCheckboxVisible(false);
                        myCollectAdapter.notifyDataSetChanged();
                        break;
                }
            }
        });
    }

    private void listCheckboxState(boolean state) {
        for (CollectBean collectBean : list) {
            collectBean.setChecked(state);
        }
    }
}
