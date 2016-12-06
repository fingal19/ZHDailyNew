package com.example.fx50j.zhdailynews.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fx50j.zhdailynews.Beans.Theme_Bean;
import com.example.fx50j.zhdailynews.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by FX50J on 2016/11/22.
 */

public class Theme_Recycler_Adapter extends RecyclerView.Adapter<Theme_Recycler_Adapter.MyHolder> {
    private Theme_Bean theme_bean;
    private Context mcontext;
    private Map<String,Bitmap> map_pic = new HashMap<String,Bitmap>();

    public Theme_Recycler_Adapter(Theme_Bean theme_bean,Context context){
        this.theme_bean = theme_bean;
        this.mcontext = context;
    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(mcontext).inflate(R.layout.item_theme,parent,false));
    }

           @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            holder.mtitle.setText(theme_bean.getOthers().get(position).getName());
            holder.mtext.setText(theme_bean.getOthers().get(position).getDescription());

            if (map_pic.get(theme_bean.getOthers().get(position).getThumbnail()) != null){
                holder.miv.setImageBitmap(map_pic.get(theme_bean.getOthers().get(position).getThumbnail()));
            }else {
                holder.miv.setImageResource(R.mipmap.pic);
                Pic_Task pic_task = new Pic_Task(holder.miv);
                pic_task.execute(theme_bean.getOthers().get(position).getThumbnail());
            }

    }

    @Override
    public int getItemCount() {
        return theme_bean.getOthers().size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private TextView mtitle;
        private TextView mtext;
        private ImageView miv;
        public MyHolder(View itemView) {
            super(itemView);
            mtitle = (TextView) itemView.findViewById(R.id.tittle_theme);
            mtext = (TextView) itemView.findViewById(R.id.text_theme);
            miv = (ImageView) itemView.findViewById(R.id.iv_theme);
        }
    }

    public class Pic_Task extends AsyncTask<String,Integer,Bitmap>{
        private ImageView miv;
        public Pic_Task(ImageView iv){
            this.miv = iv;
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


            map_pic.put(params[0],bitmap);

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
}
