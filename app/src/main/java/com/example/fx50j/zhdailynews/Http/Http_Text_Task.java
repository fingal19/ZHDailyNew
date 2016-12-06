package com.example.fx50j.zhdailynews.Http;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.example.fx50j.zhdailynews.API.API;
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

public class Http_Text_Task extends AsyncTask<Integer,Integer,String> {
    private TextView mtext;

    public Http_Text_Task(TextView textView){
        this.mtext = textView;
    }

    @Override
    protected String doInBackground(Integer... params) {
        String text = null;
        try {
            URL url = new URL(API.SEARCH_ID + params[0]);
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

                text = outputStream.toString();


            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return text;
    }

    @Override
    protected void onPostExecute(String s) {
        if (s != null){
            mtext.setText(s);
        }else {
            mtext.setText("加载失败...");
        }
        super.onPostExecute(s);
    }
}
