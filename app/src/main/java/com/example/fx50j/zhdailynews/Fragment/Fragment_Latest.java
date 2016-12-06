package com.example.fx50j.zhdailynews.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.fx50j.zhdailynews.API.API;
import com.example.fx50j.zhdailynews.Beans.Latest_Bean;
import com.example.fx50j.zhdailynews.Http.Http_AsyncTask;
import com.example.fx50j.zhdailynews.R;

/**
 * Created by FX50J on 2016/11/10.
 */

public class Fragment_Latest extends Fragment {
    private ProgressBar mprogress;
    private RecyclerView mrecycler_latest;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_latest,null);

        mrecycler_latest = (RecyclerView) v.findViewById(R.id.recycler_latest);
        mprogress = (ProgressBar) v.findViewById(R.id.progress_latest);

        initView();

        return v;
    }

    private void initView() {

        Http_AsyncTask myAsynctask = new Http_AsyncTask(mprogress,mrecycler_latest,getContext());
        myAsynctask.execute(API.LATEST);

    }
}
