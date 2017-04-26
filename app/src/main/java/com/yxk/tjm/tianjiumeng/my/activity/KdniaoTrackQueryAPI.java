package com.yxk.tjm.tianjiumeng.my.activity;

import android.util.Log;

import com.google.gson.Gson;
import com.yxk.tjm.tianjiumeng.my.bean.TrackBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;

import okhttp3.Call;

public class KdniaoTrackQueryAPI {
    //电商ID
    private String EBusinessID = "1266422";
    //电商加密私钥，快递鸟提供，注意保管，不要泄漏
    private String AppKey = "3cef10c1-fbb1-4f11-a4ab-58aaa23996b5";
    //请求url
    private String ReqURL = "http://api.kdniao.cc/Ebusiness/EbusinessOrderHandle.aspx";


    /**
     * Json方式 查询订单物流轨迹
     *
     * @throws Exception
     */
    public void getOrderTracesByJson(String ShipperCode, String LogisticCode) throws Exception {
        String requestData = "{\"OrderCode\":\"\",\"ShipperCode\":\"" + ShipperCode + "\",\"LogisticCode\":\"" + LogisticCode + "\"}";
        Log.e("requestData", requestData);
        String dataSign = encrypt(requestData, AppKey, "UTF-8");
        OkHttpUtils
                .post()
                .url(ReqURL)
                .addParams("RequestData", urlEncoder(requestData, "UTF-8"))
                .addParams("EBusinessID", EBusinessID)
                .addParams("RequestType", "1002")
                .addParams("DataSign", urlEncoder(dataSign, "UTF-8"))
                .addParams("DataType", "2")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("e", e + "");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("kdn物流", response);
                        Gson gson = new Gson();
                        TrackBean trackBean = gson.fromJson(response, TrackBean.class);
                        if (listener != null) {
                            listener.result(trackBean);
                        }
                    }
                });
    }

    ResultCallListener listener;

    public void SetResultCallListener(ResultCallListener listener) {
        this.listener = listener;
    }

    interface ResultCallListener {
        void result(TrackBean bean);
    }


    /**
     * MD5加密
     *
     * @param str     内容
     * @param charset 编码方式
     * @throws Exception
     */
    @SuppressWarnings("unused")
    private String MD5(String str, String charset) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(str.getBytes(charset));
        byte[] result = md.digest();
        StringBuffer sb = new StringBuffer(32);
        for (int i = 0; i < result.length; i++) {
            int val = result[i] & 0xff;
            if (val <= 0xf) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(val));
        }
        return sb.toString().toLowerCase();
    }

    /**
     * base64编码
     *
     * @param str     内容
     * @param charset 编码方式
     * @throws UnsupportedEncodingException
     */
    private String base64(String str, String charset) throws UnsupportedEncodingException {
        String encoded = base64Encode(str.getBytes(charset));
        return encoded;
    }

    @SuppressWarnings("unused")
    private String urlEncoder(String str, String charset) throws UnsupportedEncodingException {
        String result = URLEncoder.encode(str, charset);
        return result;
    }

    /**
     * 电商Sign签名生成
     *
     * @param content  内容
     * @param keyValue Appkey
     * @param charset  编码方式
     * @return DataSign签名
     * @throws UnsupportedEncodingException ,Exception
     */
    @SuppressWarnings("unused")
    private String encrypt(String content, String keyValue, String charset) throws UnsupportedEncodingException, Exception {
        if (keyValue != null) {
            return base64(MD5(content + keyValue, charset), charset);
        }
        return base64(MD5(content, charset), charset);
    }

    /**
     * 向指定 URL 发送POST方法的请求
     */
    @SuppressWarnings("unused")
    private String sendPost(String ReqURL) {


        return null;
    }


    private static char[] base64EncodeChars = new char[]{
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
            'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
            'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z', '0', '1', '2', '3',
            '4', '5', '6', '7', '8', '9', '+', '/'};

    public static String base64Encode(byte[] data) {
        StringBuffer sb = new StringBuffer();
        int len = data.length;
        int i = 0;
        int b1, b2, b3;
        while (i < len) {
            b1 = data[i++] & 0xff;
            if (i == len) {
                sb.append(base64EncodeChars[b1 >>> 2]);
                sb.append(base64EncodeChars[(b1 & 0x3) << 4]);
                sb.append("==");
                break;
            }
            b2 = data[i++] & 0xff;
            if (i == len) {
                sb.append(base64EncodeChars[b1 >>> 2]);
                sb.append(base64EncodeChars[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]);
                sb.append(base64EncodeChars[(b2 & 0x0f) << 2]);
                sb.append("=");
                break;
            }
            b3 = data[i++] & 0xff;
            sb.append(base64EncodeChars[b1 >>> 2]);
            sb.append(base64EncodeChars[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]);
            sb.append(base64EncodeChars[((b2 & 0x0f) << 2) | ((b3 & 0xc0) >>> 6)]);
            sb.append(base64EncodeChars[b3 & 0x3f]);
        }
        return sb.toString();
    }
}
