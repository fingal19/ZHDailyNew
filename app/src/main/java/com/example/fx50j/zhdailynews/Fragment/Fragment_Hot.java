package com.example.fx50j.zhdailynews.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.fx50j.zhdailynews.API.API;
import com.example.fx50j.zhdailynews.Http.Http_hot_Task;
import com.example.fx50j.zhdailynews.R;

/**
 * Created by FX50J on 2016/11/10.
 */

public class Fragment_Hot extends Fragment {

    private ProgressBar mprogress;
    private TextView mtv;
    private RecyclerView mrecycler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_hot,null);
        mprogress = (ProgressBar) v.findViewById(R.id.progress_hot);
        mrecycler = (RecyclerView) v.findViewById(R.id.recycler_hot);
        mtv = (TextView) v.findViewById(R.id.toast_hot);
        init_view();
        return v;
    }

    private void init_view() {
        Http_hot_Task hot_task = new Http_hot_Task(mprogress,mtv,mrecycler,getContext());
        hot_task.execute(API.HOT);
    }
}
