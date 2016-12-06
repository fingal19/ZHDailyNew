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

import com.example.fx50j.zhdailynews.Adapter.Hot_Recycler_Adapter;
import com.example.fx50j.zhdailynews.Beans.Hot_Bean;
import com.example.fx50j.zhdailynews.Beans.Latest_Bean;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by FX50J on 2016/11/21.
 */

public class Http_hot_Task extends AsyncTask<String,Integer,Hot_Bean> {

    private ProgressBar mprogress;
    private TextView mtv;
    private RecyclerView mrv;
    private Context mcontext;
    public Http_hot_Task(ProgressBar progressBar,TextView textView,RecyclerView recyclerView,Context context){
        this.mprogress = progressBar;
        this.mtv = textView;
        this.mrv = recyclerView;
        this.mcontext = context;
    }


    @Override
    protected void onPreExecute() {
        mtv.setVisibility(View.VISIBLE);
        mtv.setText("加载中...");
        mprogress.setVisibility(View.VISIBLE);
        super.onPreExecute();
    }

    @Override
    protected Hot_Bean doInBackground(String... params) {

        Hot_Bean hot_bean = new Hot_Bean();

        try {
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(8000);
            connection.setReadTimeout(8000);

            if (connection.getResponseCode() == 200){
                InputStream is = connection.getInputStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

                byte[] bytes = new byte[1024];
                int len;
                while ((len = is.read(bytes)) != -1){
                    outputStream.write(bytes,0,len);
                }
                is.close();
                Gson gson = new Gson();
                hot_bean = gson.fromJson(outputStream.toString(),Hot_Bean.class);
            }

            return hot_bean;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return hot_bean;
    }

    @Override
    protected void onPostExecute(Hot_Bean hot_bean) {
        if (hot_bean.getRecent() != null){
            mprogress.setVisibility(View.GONE);
            mtv.setVisibility(View.GONE);

            Hot_Recycler_Adapter adapter = new Hot_Recycler_Adapter(hot_bean,mcontext);
            LinearLayoutManager manager = new LinearLayoutManager(mcontext,LinearLayoutManager.VERTICAL,false);
            mrv.setLayoutManager(manager);
            mrv.setAdapter(adapter);

        }else {
            mprogress.setVisibility(View.GONE);
            mtv.setText("加载失败，请重新检查网络连接...");
        }

        super.onPostExecute(hot_bean);
    }
}
