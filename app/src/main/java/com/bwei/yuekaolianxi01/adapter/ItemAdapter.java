package com.bwei.yuekaolianxi01.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwei.yuekaolianxi01.R;
import com.bwei.yuekaolianxi01.bean.RightBean;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private Context context;
    private List<RightBean.DataBean.ListBean> data;

    public ItemAdapter(Context context, List<RightBean.DataBean.ListBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=View.inflate(context, R.layout.item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder viewHolder, int i){

        Glide.with(context).load(data.get(i).getIcon()).into(viewHolder.imageView);

        viewHolder.textView.setText(data.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image);
            textView=itemView.findViewById(R.id.title_item);
        }
    }
}
