package com.yxk.tjm.tianjiumeng.home.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.activity.BaseActivity;
import com.yxk.tjm.tianjiumeng.custom.CustomPopWindow;
import com.yxk.tjm.tianjiumeng.home.adapter.MyCustomAdapter;
import com.yxk.tjm.tianjiumeng.utils.T;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.nereo.multi_image_selector.MultiImageSelector;

public class MyCustomActivity extends BaseActivity implements View.OnClickListener {

    private static final int REQUEST_IMAGE = 2;
    protected static final int REQUEST_STORAGE_READ_ACCESS_PERMISSION = 101;
    protected static final int REQUEST_STORAGE_WRITE_ACCESS_PERMISSION = 102;

    @BindView(R.id.tv_textrue)
    TextView tvTextrue;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.linear_texture)
    LinearLayout linearTexture;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler)
    RecyclerView recycler;

    private Toolbar mToolbar;
    private RecyclerView mRecycler;
    private ArrayList<String> mSelectPath;
    private MyCustomAdapter myCustomAdapter;
    private CustomPopWindow customPopWindow;
    private TextView tv_boli;
    private TextView tv_pt;
    private TextView tv_crystal;
    private TextView tv_shiying;
    private View contentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_custom);
        ButterKnife.bind(this);

        linearTexture.setOnClickListener(this);
        setToolbarClick();

        //材质pop点击事件
        handlePopClick();

        initData();
    }

    private void initData() {
        LinearLayoutManager layoutManager = new GridLayoutManager(this, 4);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layoutManager);
        myCustomAdapter = new MyCustomAdapter();
        recycler.setAdapter(myCustomAdapter);

        myCustomAdapter.setOnItemClickListener(new MyCustomAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                pickImage();
            }
        });
    }

    private void setToolbarClick() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        toolbar.inflateMenu(R.menu.menu_template);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.title_template:
                        startActivity(new Intent(MyCustomActivity.this, TemplateActivity.class));
                        break;
                }
                return true;
            }
        });
    }


    private void pickImage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN // Permission was added in API Level 16
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE,
                    getString(R.string.mis_permission_rationale),
                    REQUEST_STORAGE_READ_ACCESS_PERMISSION);
        } else {
            int maxNum = 6;
            MultiImageSelector selector = MultiImageSelector.create(MyCustomActivity.this);
            selector.showCamera(true);
            selector.count(maxNum);
            selector.multi();
            selector.origin(mSelectPath);
            selector.start(MyCustomActivity.this, REQUEST_IMAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_STORAGE_READ_ACCESS_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                pickImage();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


    private void requestPermission(final String permission, String rationale, final int requestCode) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.mis_permission_dialog_title)
                    .setMessage(rationale)
                    .setPositiveButton(R.string.mis_permission_dialog_ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MyCustomActivity.this, new String[]{permission}, requestCode);
                        }
                    })
                    .setNegativeButton(R.string.mis_permission_dialog_cancel, null)
                    .create().show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == RESULT_OK) {
                mSelectPath = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT);
                myCustomAdapter.setMatchData(mSelectPath);
                myCustomAdapter.notifyDataSetChanged();
            }

        }
    }

    /**
     * 材质popwindow点击事件
     */
    public void handlePopClick() {
        contentView = LayoutInflater.from(this).inflate(R.layout.pop_textrue, null);

        tv_boli = (TextView) contentView.findViewById(R.id.tv_boli);
        tv_pt = (TextView) contentView.findViewById(R.id.tv_pt);
        tv_crystal = (TextView) contentView.findViewById(R.id.tv_crystal);
        tv_shiying = (TextView) contentView.findViewById(R.id.tv_shiying);

        tv_boli.setOnClickListener(this);
        tv_pt.setOnClickListener(this);
        tv_crystal.setOnClickListener(this);
        tv_shiying.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_texture:
                customPopWindow = new CustomPopWindow.PopupWindowBuilder(this)
                        .setView(contentView)
                        .setFocusable(true)
                        .setOutsideTouchable(true)
                        .create()
                        .showAsDropDown(tvTextrue, 0, 10);
                break;

            case R.id.btn_submit:
                T.showShort(this, "提交");
                break;

            case R.id.tv_boli:
                tvTextrue.setText(tv_boli.getText().toString());
                customPopWindow.dissmiss();
                break;
            case R.id.tv_pt:
                tvTextrue.setText(tv_pt.getText().toString());
                customPopWindow.dissmiss();
                break;
            case R.id.tv_crystal:
                tvTextrue.setText(tv_crystal.getText().toString());
                customPopWindow.dissmiss();
                break;
            case R.id.tv_shiying:
                tvTextrue.setText(tv_shiying.getText().toString());
                customPopWindow.dissmiss();
                break;

        }
    }
}
