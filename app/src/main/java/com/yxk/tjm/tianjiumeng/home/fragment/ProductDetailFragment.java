package com.yxk.tjm.tianjiumeng.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yxk.tjm.tianjiumeng.App;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.custom.CircleImageView;
import com.yxk.tjm.tianjiumeng.home.activity.AllAppraiseActivity;
import com.yxk.tjm.tianjiumeng.home.activity.ProductDetailActivity;
import com.yxk.tjm.tianjiumeng.home.adapter.ClientShowPicAdapter;
import com.yxk.tjm.tianjiumeng.home.adapter.RecommendForYouAdapter;
import com.yxk.tjm.tianjiumeng.home.bean.ProductInnerDetailBean;
import com.yxk.tjm.tianjiumeng.network.ApiConstants;
import com.yxk.tjm.tianjiumeng.utils.DateUtil;
import com.yxk.tjm.tianjiumeng.utils.LogUtil;
import com.yxk.tjm.tianjiumeng.utils.NumberFormatUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static com.yxk.tjm.tianjiumeng.R.id.tv_client_comment;

public class ProductDetailFragment extends Fragment {
    private static final String TAG = "ProductDetailFragment";

    @BindView(R.id.recycler_recommond)
    RecyclerView recyclerRecommond;
    @BindView(R.id.tv_all_appraise)
    TextView tvAllAppraise;
    @BindView(R.id.img_client_head)
    CircleImageView imgClientHead;
    @BindView(R.id.tv_client_name)
    TextView tvClientName;
    @BindView(R.id.tv_id)
    TextView tvId;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(tv_client_comment)
    TextView tvClientComment;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.manyi)
    TextView manyi;
    @BindView(R.id.ratingbar)
    RatingBar ratingbar;
    @BindView(R.id.official_response)
    TextView officialResponse;
    @BindView(R.id.tv_client_result)
    TextView tvClientResult;
    @BindView(R.id.img_picture)
    ImageView imgPicture;
    @BindView(R.id.tuijian)
    TextView tuijian;
    @BindView(R.id.rl_appraise)
    RelativeLayout rl_appraise;


    private RecyclerView mRecycler;
    private String productId;
    private ProductInnerDetailBean productInnerDetailBean;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);
        ButterKnife.bind(this, view);

        productId = getArguments().getString("productId");

        mRecycler = (RecyclerView) view.findViewById(R.id.recycler);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        OkHttpUtils.get()
                .url(ApiConstants.DETAIL_PAGE_DETAIL)
                .addParams("productId", productId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.e(TAG, "onActivityCreated() response " + response);
                        Gson gson = new Gson();
                        try {
                            JSONObject jo = new JSONObject(response);
                            if ("[]".equals(jo.get("reviewPics").toString()) && "[]".equals(jo.get("reviewVO").toString())) { //没有商品评价
                                rl_appraise.setVisibility(View.GONE);

                                String cnnmdForYou = jo.getJSONArray("cnnmdForYou").toString();
                                List<ProductInnerDetailBean.CnnmdForYouBean> cnnmdList = gson.fromJson(cnnmdForYou, new TypeToken<List<ProductInnerDetailBean.CnnmdForYouBean>>() {
                                }.getType());
                                productInnerDetailBean = new ProductInnerDetailBean();
                                productInnerDetailBean.setCnnmdForYou(cnnmdList);

                                String detailPics1 = jo.get("detailPics").toString();
                                ProductInnerDetailBean.DetailPicsBean detailPics = gson.fromJson(detailPics1, ProductInnerDetailBean.DetailPicsBean.class);
                                productInnerDetailBean.setDetailPics(detailPics);

                            } else {
                                productInnerDetailBean = gson.fromJson(response, ProductInnerDetailBean.class);
                                initDetailInfo();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        initRecommendForYouRecycler();
                    }
                });
    }

    /**
     * 设置为您推荐的上面的数据
     */
    private void initDetailInfo() {
        List<String> piclist = new ArrayList<>();
        for (int i = 0; i < productInnerDetailBean.getReviewPics().size(); i++) {
            piclist.add(productInnerDetailBean.getReviewPics().get(i).getReviewPic());
        }

        //设置用户的信息
        Glide.with(App.getAppContext()).load(productInnerDetailBean.getReviewVO().get(0).getAvatar()).into(imgClientHead);
        tvClientName.setText(NumberFormatUtils.phoneHide(productInnerDetailBean.getReviewVO().get(0).getPhoneNumber()));
        tvId.setText(productInnerDetailBean.getReviewVO().get(0).getUserId() + "");
        tvDate.setText(DateUtil.longToString(productInnerDetailBean.getReviewVO().get(0).getReviewTime(), "yyyy-MM-dd"));
        tvClientComment.setText(productInnerDetailBean.getReviewVO().get(0).getReviewText());
        tvClientResult.setText(productInnerDetailBean.getReviewVO().get(0).getReplyText());
        ratingbar.setRating((long) productInnerDetailBean.getReviewVO().get(0).getSatisfyNo());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecycler.setLayoutManager(linearLayoutManager);
        ClientShowPicAdapter clientShowPicAdapter = new ClientShowPicAdapter();
        clientShowPicAdapter.setMatchData(piclist);
        mRecycler.setAdapter(clientShowPicAdapter);

    }

    private void initRecommendForYouRecycler() {
        //设置大图
        if(null != productInnerDetailBean.getDetailPics().getGoodsPic()){
            Glide.with(this).load(productInnerDetailBean.getDetailPics().getGoodsPic()).into(imgPicture);
        }

        recyclerRecommond.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        RecommendForYouAdapter recommendForYouAdapter = new RecommendForYouAdapter();
        recommendForYouAdapter.setMatchData(productInnerDetailBean.getCnnmdForYou());
        recyclerRecommond.setAdapter(recommendForYouAdapter);
        recommendForYouAdapter.setOnItemClickListener(new RecommendForYouAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                intent.putExtra("productId", productInnerDetailBean.getCnnmdForYou().get(position).getId() + ""); // TODO: 2017/4/11 id以后更换
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

    @OnClick(R.id.tv_all_appraise)
    public void onClick() {
        Intent intent = new Intent(getActivity(), AllAppraiseActivity.class);
        intent.putExtra("productId", productId);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
