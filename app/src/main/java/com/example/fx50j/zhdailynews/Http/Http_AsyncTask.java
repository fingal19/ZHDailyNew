package com.example.fx50j.zhdailynews.Http;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.fx50j.zhdailynews.Adapter.RecyclerAdapter_Latest;
import com.example.fx50j.zhdailynews.Beans.Latest_Bean;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by FX50J on 2016/11/18.
 */

public class Http_AsyncTask extends AsyncTask<String,Integer,Latest_Bean> {
    private ProgressBar mprogress;
    private RecyclerView mrecycler;
    private Context mcontext;


    public Http_AsyncTask(ProgressBar progressBar,RecyclerView recyclerView,Context context){
        this.mprogress = progressBar;
        this.mrecycler = recyclerView;
        this.mcontext = context;
    }

    @Override
    protected void onPreExecute() {
        mprogress.setVisibility(View.VISIBLE);
        mrecycler.setVisibility(View.GONE);
        super.onPreExecute();
    }

    @Override
    protected Latest_Bean doInBackground(String... params) {
        Latest_Bean latest_bean = new Latest_Bean();
        try {
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(8000);
            connection.setReadTimeout(8000);
            if (connection.getResponseCode() == 200) {
                InputStream is = connection.getInputStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] bytes = new byte[1024];
                int len;
                while ((len = is.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, len);
                }
                Gson gson = new Gson();
                is.close();
                latest_bean = gson.fromJson(outputStream.toString(), Latest_Bean.class);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return latest_bean;
    }

    @Override
    protected void onPostExecute(Latest_Bean latest_bean) {

        if (latest_bean.getDate() != null){
            mprogress.setVisibility(View.GONE);
            mrecycler.setVisibility(View.VISIBLE);

            RecyclerAdapter_Latest adapter_latest = new RecyclerAdapter_Latest(mcontext,latest_bean);
            mrecycler.setAdapter(adapter_latest);
            LinearLayoutManager manager = new LinearLayoutManager(mcontext,LinearLayoutManager.VERTICAL,false);
            mrecycler.setLayoutManager(manager);
        }else {
            mprogress.setVisibility(View.GONE);
            mrecycler.setVisibility(View.GONE);

            Toast.makeText(mcontext,"获取信息失败，请检查网络连接...",Toast.LENGTH_SHORT).show();
        }



        super.onPostExecute(latest_bean);
    }
}
