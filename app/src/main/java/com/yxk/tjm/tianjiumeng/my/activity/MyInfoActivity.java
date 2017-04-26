package com.yxk.tjm.tianjiumeng.my.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.yxk.tjm.tianjiumeng.App;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.activity.BaseActivity;
import com.yxk.tjm.tianjiumeng.custom.CircleImageView;
import com.yxk.tjm.tianjiumeng.my.bean.MyInfoBean;
import com.yxk.tjm.tianjiumeng.network.ApiConstants;
import com.yxk.tjm.tianjiumeng.utils.FileUtils;
import com.yxk.tjm.tianjiumeng.utils.LogUtil;
import com.yxk.tjm.tianjiumeng.utils.To;
import com.yxk.tjm.tianjiumeng.utils.UserUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;


public class MyInfoActivity extends BaseActivity {
    public static final String TAG = "MyInfoActivity ";

    @BindView(R.id.relative_address)
    RelativeLayout relativeAddress;
    @BindView(R.id.relative_head)
    RelativeLayout relativeHead;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btn_save)
    Button btnSave;
    @BindView(R.id.et_nick_name)
    EditText etNickName;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.rb_man)
    RadioButton rbMan;
    @BindView(R.id.rb_women)
    RadioButton rbWomen;
    @BindView(R.id.iv_head)
    CircleImageView ivHead;
    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);
        createCameraTempFile(savedInstanceState);
        bind = ButterKnife.bind(this);
        setToolbarNavigationClick();

        initData();
    }

    private void setToolbarNavigationClick() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initData() {
        OkHttpUtils
                .get()
                .url(ApiConstants.MY_INFO)
                .addParams("userId", UserUtil.getUserId(getApplicationContext()))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.e(TAG, "initData() " + response);
                        MyInfoBean myInfoBean = new Gson().fromJson(response, MyInfoBean.class);
                        Glide.with(MyInfoActivity.this).load(myInfoBean.getAvatar()).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(ivHead);
                        etNickName.setText(myInfoBean.getNickname());
                        etEmail.setText(myInfoBean.getMail());
                        if (myInfoBean.getSex() == 1) {
                            rbMan.setChecked(true);
                        } else {
                            rbWomen.setChecked(true);
                        }
                    }
                });
    }

    private void submit() {
        String sex = "";
        String etNickName1 = etNickName.getText().toString().trim();
        String etEmail1 = etEmail.getText().toString().trim();
        if (rbMan.isChecked()) {
            sex = "1";
        } else {
            sex = "0";
        }

        OkHttpUtils.post()
                .url(ApiConstants.MY_UPDATE_INFO)
                .addParams("userId", UserUtil.getUserId(getApplicationContext()))
                .addParams("nickname", etNickName1)
                .addParams("sex", sex)
                .addParams("mail", etEmail1)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.e(TAG, response);
                        try {
                            JSONObject jo = new JSONObject(response);
                            if ((int) jo.get("success") == 0) {
                                To.showShort(MyInfoActivity.this, "修改成功!");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
    }

    @OnClick({R.id.relative_head, R.id.relative_address, R.id.btn_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.relative_head:
                initPopWindow();
                break;
            case R.id.relative_address:
                startActivity(new Intent(this, AddressActivity.class));
                break;
            case R.id.btn_save:
                /**上传头像*/
                if (headFile != null) {
                    uploadImg();
                }
                //上传个人信息
                submit();
                break;
        }
    }

    /**
     * 先上传头像，之后再上传个人信息
     */
    private void uploadImg() {
        OkHttpUtils.post()//
                .url(ApiConstants.MY_HEAD_UPDATE)
                .addParams("userId", UserUtil.getUserId(App.getAppContext()))
                .addFile("file", "img_head.jpg", headFile)//
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.e("上传头像response", " response:" + response);
                        try {
                            JSONObject jo = new JSONObject(response);
                            Glide.with(App.getAppContext()).load((String) jo.get("avatarUrl")).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(ivHead);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

    /***************************************************************************************
     * 以下都是相机拍照相关，注意在onCreate里调用 createCameraTempFile(savedInstanceState);
     ****************************************************************************************/
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE2 = 7;
    private static final int PHOTO_REQUEST = 1;
    private static final int CAMERA_REQUEST = 2;
    private static final int PHOTO_CLIP = 3;
    private File tempFile;//调用照相机返回图片临时文件
    private File headFile;//图片
    private Bitmap photo;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CAMERA_REQUEST:
                switch (resultCode) {
                    case -1:// -1表示拍照成功
                        photoClip(Uri.fromFile(tempFile));
                        break;
                    default:
                        break;
                }
                break;
            case PHOTO_REQUEST:
                if (data != null) {
                    photoClip(data.getData());
                }
                break;
            case PHOTO_CLIP:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    if (extras != null) {
                        photo = extras.getParcelable("data");
                        //ivHead.setImageBitmap(photo);
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        photo.compress(Bitmap.CompressFormat.PNG, 100, baos);
                        byte[] bytes = baos.toByteArray();
                        Glide.with(this).load(bytes).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(ivHead);

                        photo = FileUtils.decodeSampledBitmapFromBitmap(photo, 70, 70);
                        try {
                            //把压缩图片保存到文件中
                            headFile = saveFile(photo, "img_head.jpg");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
        }
    }

    /**
     * ** 保存到文件
     *
     * @param bm       Bitmap
     * @param fileName 要保存哪个文件的文件名
     * @return 返回保存的文件
     */
    public File saveFile(Bitmap bm, String fileName) throws IOException {// TODO: 2017/4/17
        FileOutputStream fileOutputStream = openFileOutput(fileName, MODE_PRIVATE);
        BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream);
        bm.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        bos.flush();
        bos.close();
        File file = new File(getFilesDir(), fileName);
        //  Log.e("myCaptureFile", file.getPath());
        return file;
    }

    /**
     * 创建调用系统照相机待存储的临时文件
     *
     * @param savedInstanceState
     */
    private void createCameraTempFile(Bundle savedInstanceState) {
        if (savedInstanceState != null && savedInstanceState.containsKey("tempFile")) {
            tempFile = (File) savedInstanceState.getSerializable("tempFile");
        } else {
            tempFile = new File(checkDirPath(getExternalFilesDir(null) + "/image/"),
                    System.currentTimeMillis() + ".jpg");
        }
    }

    /**
     * 检查文件是否存在
     */
    private static String checkDirPath(String dirPath) {
        if (TextUtils.isEmpty(dirPath)) {
            return "";
        }
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dirPath;
    }

    /**
     * 图片剪裁
     *
     * @param uri
     */
    private void photoClip(Uri uri) {
        // 调用系统中自带的图片剪裁
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, PHOTO_CLIP);
    }


    /**
     * 从相册获取图片
     */
    private void getPicFromPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*");
        startActivityForResult(intent, PHOTO_REQUEST);
    }

    /**
     * 从相机拍照
     */
    private void getPicFromCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 下面这句指定调用相机拍照后的照片存储的路径
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        startActivityForResult(intent, CAMERA_REQUEST);
    }


    /**
     * PopupWindow
     */
    private void initPopWindow() {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_popupwindow, null);
        TextView btnCarema = (TextView) view.findViewById(R.id.btn_camera);
        TextView btnPhoto = (TextView) view.findViewById(R.id.btn_photo);
        TextView btnCancel = (TextView) view.findViewById(R.id.btn_cancel);
        final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
        popupWindow.setOutsideTouchable(true);
        View parent = LayoutInflater.from(this).inflate(R.layout.activity_main, null);
        popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        //popupWindow在弹窗的时候背景半透明
        final WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 0.5f;
        getWindow().setAttributes(params);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                params.alpha = 1.0f;
                getWindow().setAttributes(params);
            }
        });

        btnCarema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MyInfoActivity.this,
                            new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    //跳转到调用系统相机
                    getPicFromCamera();
                    popupWindow.dismiss();
                }

            }
        });
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(MyInfoActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MyInfoActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_CALL_PHONE2);
                } else {
                    //跳转到调用系统图库
                    getPicFromPhoto();
                    popupWindow.dismiss();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }
}
