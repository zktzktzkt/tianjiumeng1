package com.yxk.tjm.tianjiumeng.my.activity;

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
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.yxk.tjm.tianjiumeng.App;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.activity.BaseActivity;
import com.yxk.tjm.tianjiumeng.my.adapter.WaitAppraiseAdapter;
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

public class ImmediateAppraiseActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ratingbar)
    RatingBar ratingbar;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.et_describe)
    EditText etDescribe;
    @BindView(R.id.tv_toolbar)
    TextView tvToolbar;
    private WaitAppraiseAdapter waitAppraiseAdapter;
    private ProgressDialog progressDialog;
    private int goodsId;
    private String orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immediate_appraise);
        App.getActivityManager().pushActivity(this);
        ButterKnife.bind(this);

        goodsId = getIntent().getIntExtra("goodsId", -1);
        orderId = getIntent().getStringExtra("orderId");

        setToolbarNavigationClick();

        initData();
    }

    private void initData() {
        LinearLayoutManager layoutManager = new GridLayoutManager(this, 4);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layoutManager);
        waitAppraiseAdapter = new WaitAppraiseAdapter();
        recycler.setAdapter(waitAppraiseAdapter);

        waitAppraiseAdapter.setOnItemClickListener(new WaitAppraiseAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                pickImage();
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
                progressDialog.dismiss();

            } else {
                names.add(name);
            }
        }
    };

    /**
     * 上传图片
     */
    private void uploadPic() {
        progressDialog = new ProgressDialog(ImmediateAppraiseActivity.this);
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
                        Bitmap decodeBitmap = FileUtils.decodeSampledBitmapFromBitmap(bitmap, 350, 350);
                        String path = saveFile(decodeBitmap, String.valueOf(System.currentTimeMillis()));
                        Log.e("上传图片 picPath", path);
                        String name = ImgUtil.uploadFile(ApiConstants.MY_ORDER_REVIEW_PIC, path, "file");
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
     * 提交
     */
    private void submit() {
        String describe = etDescribe.getText().toString().trim();
        if (TextUtils.isEmpty(describe)) {
            To.showShort(getApplicationContext(), "评价描述不能为空");
            return;
        }
        JsonObject jo = new JsonObject();
        jo.addProperty("userId", UserUtil.getUserId(App.getAppContext()));
        jo.addProperty("goodsId", goodsId + "");
        jo.addProperty("reviewText", describe);
        jo.addProperty("satisfyNo", ratingbar.getRating() + "");
        jo.addProperty("orderId", orderId);

        if (names != null && names.size() > 0) {
            JsonArray ja = new JsonArray();
            for (int i = 0; i < names.size(); i++) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("reviewPicIds", names.get(i));
                ja.add(jsonObject);
            }
            jo.add("reviewPicId", ja);
        }
        OkHttpUtils.postString()
                .url(ApiConstants.MY_ORDER_REVIEW_INFO)
                .content(jo.toString())
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.e("ImmediateAppraiseActivity ", "submit()  Exception: " + e);
                        progressDialog.dismiss();
                        To.showLong(App.getAppContext(), "上传失败了~");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.e("ImmediateAppraiseActivity ", "submit(): " + response);
                        progressDialog.dismiss();
                        To.showLong(App.getAppContext(), "上传成功！");
                        finish();
                    }
                });

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


    @OnClick(R.id.btn_submit)
    public void onClick() {
        uploadPic();
    }

    //-------------------以下是多图选择-------------------------------------

    private ArrayList<String> mSelectPath;
    private static final int REQUEST_IMAGE = 2;
    protected static final int REQUEST_STORAGE_READ_ACCESS_PERMISSION = 101;
    protected static final int REQUEST_STORAGE_WRITE_ACCESS_PERMISSION = 102;

    private void pickImage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN // Permission was added in API Level 16
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE,
                    getString(R.string.mis_permission_rationale),
                    REQUEST_STORAGE_READ_ACCESS_PERMISSION);
        } else {
            int maxNum = 6;
            MultiImageSelector selector = MultiImageSelector.create(ImmediateAppraiseActivity.this);
            selector.showCamera(true);
            selector.count(maxNum);
            selector.multi();
            selector.origin(mSelectPath);
            selector.start(ImmediateAppraiseActivity.this, REQUEST_IMAGE);
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
                            ActivityCompat.requestPermissions(ImmediateAppraiseActivity.this, new String[]{permission}, requestCode);
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
                waitAppraiseAdapter.setMatchData(mSelectPath);
                waitAppraiseAdapter.notifyDataSetChanged();
            }

        }
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
