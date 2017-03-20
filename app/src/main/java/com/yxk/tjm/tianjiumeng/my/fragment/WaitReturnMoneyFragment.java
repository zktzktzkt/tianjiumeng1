package com.yxk.tjm.tianjiumeng.my.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.my.adapter.WaitReturnMoneyAdapter;

public class WaitReturnMoneyFragment extends Fragment {

    private RecyclerView recycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        recycler = (RecyclerView) view.findViewById(R.id.recycler);
        recycler.setAdapter(new WaitReturnMoneyAdapter());
        return view;
    }


}
