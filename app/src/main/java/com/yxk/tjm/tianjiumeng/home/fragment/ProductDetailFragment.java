package com.yxk.tjm.tianjiumeng.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.home.activity.ProductDetailActivity;
import com.yxk.tjm.tianjiumeng.home.adapter.ClientShowPicAdapter;
import com.yxk.tjm.tianjiumeng.home.adapter.RecommendForYouAdapter;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductDetailFragment extends Fragment {

    @BindView(R.id.recycler_recommond)
    RecyclerView recyclerRecommond;
    private RecyclerView mRecycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);
        ButterKnife.bind(this, view);

        mRecycler = (RecyclerView) view.findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecycler.setLayoutManager(linearLayoutManager);
        ClientShowPicAdapter clientShowPicAdapter = new ClientShowPicAdapter();
        clientShowPicAdapter.setMatchData(Arrays.asList(R.drawable.pic_a, R.drawable.pic_a, R.drawable.pic_a));
        mRecycler.setAdapter(clientShowPicAdapter);

        initRecommendForYouRecycler();
        return view;
    }

    private void initRecommendForYouRecycler() {
        recyclerRecommond.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        RecommendForYouAdapter recommendForYouAdapter = new RecommendForYouAdapter();
        recommendForYouAdapter.setMatchData(Arrays.asList(R.drawable.pic_a, R.drawable.pic_b, R.drawable.pic_c, R.drawable.pic_d, R.drawable.pic_d, R.drawable.pic_e, R.drawable.pic_a));
        recyclerRecommond.setAdapter(recommendForYouAdapter);
        recommendForYouAdapter.setOnItemClickListener(new RecommendForYouAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startActivity(new Intent(getActivity(), ProductDetailActivity.class));
                getActivity().finish();
            }
        });
    }

}
