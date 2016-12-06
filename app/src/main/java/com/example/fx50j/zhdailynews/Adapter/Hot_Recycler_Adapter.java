package com.example.fx50j.zhdailynews.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fx50j.zhdailynews.API.API;
import com.example.fx50j.zhdailynews.Beans.Hot_Bean;
import com.example.fx50j.zhdailynews.Beans.New_id_Bean;
import com.example.fx50j.zhdailynews.R;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by FX50J on 2016/11/21.
 */

public class Hot_Recycler_Adapter extends RecyclerView.Adapter<Hot_Recycler_Adapter.MyViewHolder> {

    private Map<String,Bitmap> map_pic = new HashMap<String, Bitmap>();
    private Map<Integer,New_id_Bean> news_id = new HashMap<Integer, New_id_Bean>();

    private Hot_Bean hot_bean;
    private Context mcontext;
    public Hot_Recycler_Adapter(Hot_Bean hot_bean, Context context){
        this.hot_bean = hot_bean;
        this.mcontext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Hot_Recycler_Adapter.MyViewHolder(LayoutInflater.from(mcontext)
                .inflate(R.layout.item_hot,parent,false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mtittle.setText(hot_bean.getRecent().get(position).getTitle());

        int id = hot_bean.getRecent().get(position).getNews_id();

        if (news_id.get(id) == null){
            News_Id_Task id_task = new News_Id_Task();
            id_task.execute(id);

        }else {
            if (map_pic.get(news_id.get(id).getImage()) != null){
                holder.miv.setImageBitmap(map_pic.get(news_id.get(id).getImage()));
            }else {
                holder.miv.setImageResource(R.mipmap.pic);
                Pic_Task pic_task = new Pic_Task(holder.miv);
                pic_task.execute(news_id.get(id).getImage());
            }

        }


    }

    @Override
    public int getItemCount() {
        return hot_bean.getRecent().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView miv;
        private TextView mtittle;
        public MyViewHolder(View itemView) {
            super(itemView);
            miv = (ImageView) itemView.findViewById(R.id.iv_hot);
            mtittle = (TextView) itemView.findViewById(R.id.tittle_hot);
        }
    }

    public class Text_Task extends AsyncTask<Integer,Integer,String>{
        private TextView mtext;

        public Text_Task(TextView text){
            this.mtext = text;
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


    public class Pic_Task extends AsyncTask<String,Integer,Bitmap>{
        private ImageView miv;

        public Pic_Task(ImageView image){
            this.miv = image;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap = null;
            byte[] b = null;
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

                    b = outputStream.toByteArray();
                    bitmap = BitmapFactory.decodeByteArray(b,0,b.length);
                    map_pic.put(params[0],bitmap);

                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null){
                miv.setImageBitmap(bitmap);
            }else {
                miv.setImageResource(R.mipmap.pic);
            }
            super.onPostExecute(bitmap);
        }
    }


    public class News_Id_Task extends AsyncTask<Integer,Integer,New_id_Bean>{


        @Override
        protected New_id_Bean doInBackground(Integer... params) {
            New_id_Bean id_bean = null;
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

                    Gson gson = new Gson();
                    id_bean = gson.fromJson(outputStream.toString(),New_id_Bean.class);
                    news_id.put(params[0],id_bean);

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return id_bean;
        }


    }

}
