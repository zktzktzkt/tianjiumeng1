package com.yxk.tjm.tianjiumeng.home.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
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
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.yxk.tjm.tianjiumeng.App;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.activity.BaseActivity;
import com.yxk.tjm.tianjiumeng.custom.AmountView;
import com.yxk.tjm.tianjiumeng.custom.CustomPopWindow;
import com.yxk.tjm.tianjiumeng.home.adapter.ImagePickerAdapter;
import com.yxk.tjm.tianjiumeng.home.bean.PopTextrueBean;
import com.yxk.tjm.tianjiumeng.imageloader.GlideImageLoader;
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

public class MyCustomActivity extends BaseActivity implements ImagePickerAdapter.OnRecyclerViewItemClickListener {
    private static final String TAG = "MyCustomActivity ";
    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    private ImagePickerAdapter adapter;
    private ArrayList<ImageItem> selImageList; //当前选择的所有图片
    private int maxImgCount = 6;               //允许选择图片最大数

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

        initImagePicker();
        initWidget();
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


    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case IMAGE_ITEM_ADD:
                //打开选择,本次允许选择的数量
                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                Intent intent1 = new Intent(MyCustomActivity.this, ImageGridActivity.class);
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
                if (!UserUtil.isLogin(App.getAppContext())) {
                    To.show(App.getAppContext(), "请先登录！", Toast.LENGTH_SHORT);
                    return;
                }
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

        if (names != null && names.size() > 0) {//判断上传图片后，返回的保存图片id的集合
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

        if (images == null || images.size() == 0) {
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

        if (images != null && images.size() > 0) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        for (int i = 0; i < images.size(); i++) {
                            Bitmap bitmap = BitmapFactory.decodeFile(images.get(i).path);
                            Bitmap decodeBitmap = FileUtils.decodeSampledBitmapFromBitmap(bitmap, 300, 300);
                            String path = saveFile(decodeBitmap, String.valueOf(System.currentTimeMillis()));
                            Log.e("上传图片 picPath", path);
                            String result = ImgUtil.uploadFile(ApiConstants.HOME_SUBMIT_IMG, path, "file");

                            JSONObject jo = new JSONObject(result);
                            Log.e("上传图片 result:", result);
                            Message message = Message.obtain();
                            message.obj = (String) jo.get("tailorPicId");
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
