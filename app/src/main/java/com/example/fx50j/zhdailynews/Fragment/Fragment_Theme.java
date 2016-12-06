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
import com.example.fx50j.zhdailynews.Http.Http_Theme_Task;
import com.example.fx50j.zhdailynews.R;

/**
 * Created by FX50J on 2016/11/10.
 */

public class Fragment_Theme extends Fragment {
    private ProgressBar mprogress;
    private TextView mtv;
    private RecyclerView mrv;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_theme,null);
        mprogress = (ProgressBar) v.findViewById(R.id.progress_theme);
        mtv = (TextView) v.findViewById(R.id.toast_theme);
        mrv = (RecyclerView) v.findViewById(R.id.recycler_theme);

        initView();
        return v;
    }

    private void initView() {
        Http_Theme_Task task = new Http_Theme_Task(mprogress,mtv,mrv,getContext());
        task.execute(API.THEME);
    }
}
