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
import com.example.fx50j.zhdailynews.Beans.Latest_Bean;
import com.example.fx50j.zhdailynews.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by FX50J on 2016/11/10.
 */

public class RecyclerAdapter_Latest extends RecyclerView.
        Adapter<RecyclerAdapter_Latest.MyViewHolder> {

    private Context mcontext;
    private Latest_Bean mlatest_bean;
    private Map<String,Bitmap> pics = new HashMap<String,Bitmap>();

    public RecyclerAdapter_Latest(Context mcontext,Latest_Bean latest_bean){
        this.mcontext = mcontext;
        this.mlatest_bean = latest_bean;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mcontext).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mtv.setText(mlatest_bean.getStories().get(position).getTitle());

        if (pics.get(mlatest_bean.getStories().get(position).getImages().get(0)) != null){

            holder.miv.setImageBitmap(pics.get(mlatest_bean.getStories().get(position).getImages().get(0)));
        }else{
            holder.miv.setImageResource(R.mipmap.pic);
            MyTask task = new MyTask(holder.miv);
            task.execute(mlatest_bean.getStories().get(position).getImages().get(0));

        }
    }

    @Override
    public int getItemCount() {
        return mlatest_bean.getStories().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mtv;
        private ImageView miv;

        public MyViewHolder(View itemView) {
            super(itemView);
            mtv = (TextView) itemView.findViewById(R.id.tittle_latest);
            miv = (ImageView) itemView.findViewById(R.id.iv);
        }
    }


    private class MyTask extends AsyncTask<String,Integer,Bitmap>{

        private ImageView miv;
        public MyTask(ImageView miv){
            this.miv = miv;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap = null;
            byte[] b = null;
            try {
                URL url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setReadTimeout(8000);
                connection.setConnectTimeout(8000);
                if (connection.getResponseCode() == 200){
                    InputStream is = connection.getInputStream();
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

                    byte[] bytes = new byte[1024];
                    int len;
                    while((len = is.read(bytes)) != -1){
                        outputStream.write(bytes,0,len);
                    }
                    b = outputStream.toByteArray();
                    bitmap = BitmapFactory.decodeByteArray(b,0,b.length);

                    pics.put(params[0],bitmap);

                }



            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            miv.setImageBitmap(bitmap);
            super.onPostExecute(bitmap);
        }
    }
}
