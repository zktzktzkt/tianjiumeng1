package com.yxk.tjm.tianjiumeng.my.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
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
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.yxk.tjm.tianjiumeng.App;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.activity.BaseActivity;
import com.yxk.tjm.tianjiumeng.home.adapter.ImagePickerAdapter;
import com.yxk.tjm.tianjiumeng.imageloader.GlideImageLoader;
import com.yxk.tjm.tianjiumeng.my.adapter.WaitAppraiseAdapter;
import com.yxk.tjm.tianjiumeng.network.ApiConstants;
import com.yxk.tjm.tianjiumeng.utils.FileUtils;
import com.yxk.tjm.tianjiumeng.utils.ImgUtil;
import com.yxk.tjm.tianjiumeng.utils.LogUtil;
import com.yxk.tjm.tianjiumeng.utils.To;
import com.yxk.tjm.tianjiumeng.utils.UserUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;

public class ImmediateAppraiseActivity extends BaseActivity implements ImagePickerAdapter.OnRecyclerViewItemClickListener {

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
    private String describe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immediate_appraise);
        App.getActivityManager().pushActivity(this);
        ButterKnife.bind(this);

        goodsId = getIntent().getIntExtra("goodsId", -1);
        orderId = getIntent().getStringExtra("orderId");

        setToolbarNavigationClick();

        initImagePicker();
        initWidget();
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
        describe = etDescribe.getText().toString().trim();
        if (TextUtils.isEmpty(describe)) {
            To.showShort(getApplicationContext(), "评价描述不能为空");
            return;
        }
        if (images == null || images.size() == 0) {
            To.showShort(getApplicationContext(), "照片不能为空");
            return;
        }
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

        if (images != null && images.size() > 0) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        for (int i = 0; i < images.size(); i++) {
                            Bitmap bitmap = BitmapFactory.decodeFile(images.get(i).path);
                            Bitmap decodeBitmap = FileUtils.decodeSampledBitmapFromBitmap(bitmap, 350, 350);
                            String path = saveFile(decodeBitmap, String.valueOf(System.currentTimeMillis()));
                            Log.e("上传图片 picPath", path);
                            String result = ImgUtil.uploadFile(ApiConstants.MY_ORDER_REVIEW_PIC, path, "file");
                            JSONObject jo = new JSONObject(result);
                            Log.e("上传图片 result:", result);
                            Message message = Message.obtain();
                            message.obj = (String) jo.get("reviewPicId");
                            handler.sendMessage(message);
                        }
                        Message msg = Message.obtain();
                        msg.obj = "上传完成";
                        handler.sendMessage(msg);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } else {
            /**提交*/
            submit();
        }
    }

    /**
     * 提交
     */
    private void submit() {
        JsonObject jo = new JsonObject();
        jo.addProperty("userId", UserUtil.getUserId(App.getAppContext()));
        jo.addProperty("goodsId", goodsId + "");
        jo.addProperty("reviewText", describe);
        jo.addProperty("satisfyNo", ratingbar.getRating() + "");
        jo.addProperty("orderId", orderId);

        if (names != null && names.size() > 0) {  //判断上传图片后，返回的保存图片id的集合
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
    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    private ImagePickerAdapter adapter;
    private ArrayList<ImageItem> selImageList; //当前选择的所有图片
    private int maxImgCount = 6;               //允许选择图片最大数


    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(true);                           //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(maxImgCount);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
    }

    private void initWidget() {
        selImageList = new ArrayList<>();
        adapter = new ImagePickerAdapter(this, selImageList, maxImgCount);
        adapter.setOnItemClickListener(this);

        recycler.setLayoutManager(new GridLayoutManager(this, 4));
        recycler.setHasFixedSize(true);
        recycler.setAdapter(adapter);
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
    public void onItemClick(View view, int position) {
        switch (position) {
            case IMAGE_ITEM_ADD:
                //打开选择,本次允许选择的数量
                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                Intent intent1 = new Intent(ImmediateAppraiseActivity.this, ImageGridActivity.class);
                startActivityForResult(intent1, REQUEST_CODE_SELECT);
                break;
            default:
                //打开预览
                Intent intentPreview = new Intent(this, ImagePreviewDelActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                break;
        }

    }

    ArrayList<ImageItem> images = null;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                if (images != null) {
                    selImageList.clear();
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
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
