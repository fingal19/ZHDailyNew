package com.example.fx50j.zhdailynews.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fx50j.zhdailynews.Beans.Section_Bean;
import com.example.fx50j.zhdailynews.R;

import java.util.Random;

/**
 * Created by FX50J on 2016/11/22.
 */

public class Section_Recycler_Adapter extends RecyclerView.Adapter<Section_Recycler_Adapter.MyHolder> {

    private Section_Bean section_bean;
    private Context mcontext;
//    private Map<String,Bitmap> pic_map = new HashMap<String,Bitmap>();
    private String[] colors = {
        "#87CEFA","#778899","#6495ED","#9370DB","#EE82EE","#FF69B4","#00FF7F"
};

    public Section_Recycler_Adapter(Section_Bean section_bean, Context context){
        this.section_bean = section_bean;
        this.mcontext = context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(mcontext).inflate(R.layout.item_section,parent,false));
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.mtitle.setText(section_bean.getData().get(position).getName());
        holder.mtext.setText(section_bean.getData().get(position).getDescription());

        if (section_bean.getData().get(position).getDescription() == null){
            holder.mtext.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return section_bean.getData().size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private TextView mtitle;
        private TextView mtext;
        private CardView mcard;
        public MyHolder(View itemView) {
            super(itemView);
            mtitle = (TextView) itemView.findViewById(R.id.title_section);
            mtext = (TextView) itemView.findViewById(R.id.text_section);
            mcard = (CardView) itemView.findViewById(R.id.section_card);

            mcard.setCardBackgroundColor(Color.parseColor(colors[new Random().nextInt(colors.length)]));
        }
    }
}
