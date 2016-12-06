package com.example.fx50j.zhdailynews.Http;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fx50j.zhdailynews.Adapter.Section_Recycler_Adapter;
import com.example.fx50j.zhdailynews.Beans.Section_Bean;
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

public class Http_section_Task extends AsyncTask<String,Integer,Section_Bean> {
    private TextView mtv;
    private ProgressBar mprogress;
    private RecyclerView mrv;
    private Context mcontext;

    public Http_section_Task(TextView tv,ProgressBar progressBar,RecyclerView rv,Context context){
        this.mrv = rv;
        this.mprogress = progressBar;
        this.mtv = tv;
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
    protected Section_Bean doInBackground(String... params) {
        Section_Bean section_bean = new Section_Bean();

        try {
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(8000);
            connection.setReadTimeout(8000);

            if (connection.getResponseCode() == 200){
                byte[] b = new byte[1024];

                InputStream is = connection.getInputStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

                int len;
                while ((len = is.read(b)) != -1){
                    outputStream.write(b,0,len);
                }
                is.close();
                Gson gson = new Gson();
                section_bean = gson.fromJson(outputStream.toString(),Section_Bean.class);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return section_bean;
    }

    @Override
    protected void onPostExecute(Section_Bean section_bean) {

        if (section_bean.getData().size() != 0){
            mtv.setVisibility(View.GONE);
            mprogress.setVisibility(View.GONE);

            mrv.setVisibility(View.VISIBLE);
            Section_Recycler_Adapter adapter = new Section_Recycler_Adapter(section_bean,mcontext);
            LinearLayoutManager manager = new LinearLayoutManager(mcontext,LinearLayoutManager.VERTICAL,false);
            mrv.setLayoutManager(manager);
            mrv.setAdapter(adapter);


        }else {
            mtv.setText("加载失败，请重新检查网络连接...");
            Toast.makeText(mcontext,"加载失败，请重新检查网络连接...",Toast.LENGTH_SHORT).show();
        }

        super.onPostExecute(section_bean);
    }
}
