package com.yxk.tjm.tianjiumeng.home.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.yxk.tjm.tianjiumeng.App;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.activity.BaseActivity;
import com.yxk.tjm.tianjiumeng.custom.AmountView;
import com.yxk.tjm.tianjiumeng.custom.CustomPopWindow;
import com.yxk.tjm.tianjiumeng.home.adapter.MyCustomAdapter;
import com.yxk.tjm.tianjiumeng.home.bean.PopTextrueBean;
import com.yxk.tjm.tianjiumeng.network.ApiConstants;
import com.yxk.tjm.tianjiumeng.utils.FileUtils;
import com.yxk.tjm.tianjiumeng.utils.ImgUtil;
import com.yxk.tjm.tianjiumeng.utils.LogUtil;
import com.yxk.tjm.tianjiumeng.utils.To;
import com.yxk.tjm.tianjiumeng.utils.UserUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.nereo.multi_image_selector.MultiImageSelector;
import okhttp3.Call;
import okhttp3.MediaType;

public class MyCustomActivity extends BaseActivity {
    private static final String TAG = "MyCustomActivity ";

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
    @BindView(R.id.tv_product_pic)
    TextView tvProductPic;
    @BindView(R.id.tv_cm)
    TextView tvCm;
    @BindView(R.id.et_width)
    EditText etWidth;
    @BindView(R.id.et_height)
    EditText etHeight;
    @BindView(R.id.et_length)
    EditText etLength;
    @BindView(R.id.et_commodity_introduce)
    EditText etCommodityIntroduce;
    @BindView(R.id.amount_view)
    AmountView amountView;

    private ArrayList<String> mSelectPath;
    private MyCustomAdapter myCustomAdapter;
    private CustomPopWindow customPopWindow;
    private View contentView;
    private List<PopTextrueBean> popTextrueBeanList;
    private RecyclerView recycler_pop;
    private ProgressDialog progressDialog;
    private String tailorSize;
    private String tailorMaterial;
    private String tailorDecr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_custom);
        App.getActivityManager().pushActivity(this);

        ButterKnife.bind(this);

        setToolbarClick();

        contentView = LayoutInflater.from(MyCustomActivity.this).inflate(R.layout.ppw_textrue, null);
        recycler_pop = (RecyclerView) contentView.findViewById(R.id.recycler_pop);

        //材质pop点击事件
        handlePopClick();

        initData();
    }

    private void initData() {
        OkHttpUtils.get()
                .url(ApiConstants.HOME_CUSTOM_TEXTRUE)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        popTextrueBeanList = new Gson().fromJson(response, new TypeToken<List<PopTextrueBean>>() {
                        }.getType());
                        recycler_pop.setAdapter(new BaseQuickAdapter<PopTextrueBean, BaseViewHolder>(R.layout.ppw_item_textrue, popTextrueBeanList) {
                            @Override
                            protected void convert(BaseViewHolder helper, PopTextrueBean item) {
                                helper.setText(R.id.tv_textrue, item.getTailorMaterialName());
                            }
                        });
                    }
                });

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
        recycler_pop.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                tvTextrue.setText(popTextrueBeanList.get(position).getTailorMaterialName());
                customPopWindow.dissmiss();
            }
        });
    }

    @OnClick({R.id.linear_texture, R.id.btn_submit})
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
                uploadImg();
                break;

        }
    }

    /**
     * 提交
     */
    private void submit() {

        JsonObject jo = new JsonObject();
        jo.addProperty("userId", UserUtil.getUserId(App.getAppContext()));
        jo.addProperty("tailorAmount", amountView.getEditContent());
        jo.addProperty("tailorMaterial", tailorMaterial);
        jo.addProperty("tailorSize", tailorSize);
        jo.addProperty("tailorDecr", tailorDecr);
        if (names != null && names.size() > 0) {
            JsonArray ja = new JsonArray();
            for (int i = 0; i < names.size(); i++) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("tailorPicId", names.get(i));
                ja.add(jsonObject);
            }
            jo.add("tailorPics", ja);
        }
        LogUtil.e(TAG, "jo: " + jo.toString());
        OkHttpUtils.postString()
                .url(ApiConstants.HOME_CUSTOM_SUBMIT)
                .content(jo.toString())
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.e(TAG, "submit()  Exception: " + e);
                        progressDialog.dismiss();
                        To.showLong(App.getAppContext(), "上传失败了~");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.e(TAG, "submit(): " + response);
                        progressDialog.dismiss();
                        To.showLong(App.getAppContext(), "上传成功！");
                        finish();
                    }
                });

    }

    /**
     * 保存返回的值
     */
    private List<String> names = new ArrayList<String>();

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String name = (String) msg.obj;
            if (name.equals("上传完成")) {
                submit();
            } else if (name.equals("")) {
                To.showLong(App.getAppContext(), "图片上传失败了..");
            } else {
                names.add(name);
            }
        }
    };

    /**
     * 上传图片
     */
    private void uploadImg() {
        String length = etLength.getText().toString().trim();
        String width = etWidth.getText().toString().trim();
        String height = etHeight.getText().toString().trim();

        if (mSelectPath == null || mSelectPath.size() == 0) {
            To.showShort(App.getAppContext(), "请选择商品相片!");
            return;
        }

        tailorSize = length + "x" + width + "x" + height;
        if (TextUtils.isEmpty(length) || TextUtils.isEmpty(width) || TextUtils.isEmpty(height)) {
            To.showShort(App.getAppContext(), "长x宽x高不能为空!");
            return;
        }

        tailorMaterial = tvTextrue.getText().toString().trim();
        if (TextUtils.isEmpty(tailorMaterial)) {
            To.showShort(App.getAppContext(), "材质不能为空!");
            return;
        }

        tailorDecr = etCommodityIntroduce.getText().toString().trim();
        if (TextUtils.isEmpty(tailorDecr)) {
            To.showShort(App.getAppContext(), "商品详情介绍不能为空!");
            return;
        }

        progressDialog = new ProgressDialog(MyCustomActivity.this);
        progressDialog.setMessage("正在上传...");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (i == KeyEvent.KEYCODE_BACK) {
                    return true;
                }
                return false;
            }
        });

        if (mSelectPath != null && mSelectPath.size() > 0) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < mSelectPath.size(); i++) {
                        Bitmap bitmap = BitmapFactory.decodeFile(mSelectPath.get(i));
                        Bitmap decodeBitmap = FileUtils.decodeSampledBitmapFromBitmap(bitmap, 300, 300);
                        String path = saveFile(decodeBitmap, String.valueOf(System.currentTimeMillis()));
                        Log.e("上传图片 picPath", path);
                        String name = ImgUtil.uploadFile(ApiConstants.HOME_SUBMIT_IMG, path, "file");
                        Log.e("上传图片 name:", name);
                        Message message = Message.obtain();
                        message.obj = name;
                        handler.sendMessage(message);
                    }
                    Message msg = Message.obtain();
                    msg.obj = "上传完成";
                    handler.sendMessage(msg);
                }
            }).start();
        } else {
            submit();
        }
    }

    /**
     * 保存到文件中
     *
     * @param bitmap
     * @param currentTime
     * @return 文件路径
     */
    public String saveFile(Bitmap bitmap, String currentTime) {
        try {
            FileOutputStream outputStream = openFileOutput(currentTime + ".jpg", MODE_PRIVATE);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            outputStream.close();
            File file = new File(getFilesDir(), currentTime + ".jpg");
            return file.getPath();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
