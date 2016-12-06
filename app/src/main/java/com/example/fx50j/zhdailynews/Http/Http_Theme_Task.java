package com.example.fx50j.zhdailynews.Http;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fx50j.zhdailynews.API.API;
import com.example.fx50j.zhdailynews.Adapter.Theme_Recycler_Adapter;
import com.example.fx50j.zhdailynews.Beans.Theme_Bean;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by FX50J on 2016/11/22.
 */

public class Http_Theme_Task extends AsyncTask<String,Integer,Theme_Bean> {
    private TextView mtv;
    private ProgressBar mprogress;
    private RecyclerView mrv;
    private Context mcontext;

    public Http_Theme_Task(ProgressBar progress,TextView tv,RecyclerView rv,Context context){
        this.mprogress = progress;
        this.mtv = tv;
        this.mrv = rv;
        this.mcontext = context;
    }


    @Override
    protected void onPreExecute() {
        mtv.setText("加载中...");
        mtv.setVisibility(View.VISIBLE);
        mprogress.setVisibility(View.VISIBLE);
        mrv.setVisibility(View.GONE);
        super.onPreExecute();
    }

    @Override
    protected Theme_Bean doInBackground(String... params) {
        Theme_Bean theme_bean = new Theme_Bean();
        try {
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(8000);
            connection.setConnectTimeout(8000);
            if (connection.getResponseCode() == 200){
                InputStream is = connection.getInputStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

                int len;
                byte[] b = new byte[1024];
                while ((len = is.read(b)) != -1){
                    outputStream.write(b,0,len);
                }
                is.close();
                Gson gson = new Gson();
                theme_bean = gson.fromJson(outputStream.toString(),Theme_Bean.class);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return theme_bean;
    }

    @Override
    protected void onPostExecute(Theme_Bean theme_bean) {
        if (theme_bean.getLimit() != 0){
            mprogress.setVisibility(View.GONE);
            mtv.setVisibility(View.GONE);

            mrv.setVisibility(View.VISIBLE);

            LinearLayoutManager manager = new LinearLayoutManager(mcontext,LinearLayoutManager.VERTICAL,false);
            Theme_Recycler_Adapter adapter = new Theme_Recycler_Adapter(theme_bean,mcontext);
            mrv.setLayoutManager(manager);
            mrv.setAdapter(adapter);
        }else {
            mtv.setText("加载失败，请重新检查网络连接...");
            Toast.makeText(mcontext,"加载失败，请重新检查网络连接...",Toast.LENGTH_SHORT).show();
        }
        super.onPostExecute(theme_bean);
    }
}
