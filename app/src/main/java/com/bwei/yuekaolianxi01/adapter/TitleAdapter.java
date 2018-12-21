package com.bwei.yuekaolianxi01.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwei.yuekaolianxi01.R;
import com.bwei.yuekaolianxi01.bean.TitleBean;

import java.util.ArrayList;
import java.util.List;

public class TitleAdapter extends RecyclerView.Adapter<TitleAdapter.ViewHolder> {

    private List<TitleBean.DataBean> data;
    private Context context;

    public TitleAdapter(Context context) {
        this.context = context;
        data=new ArrayList<>();
    }

    public void setDatas(List<TitleBean.DataBean> mData) {
        //this.data = data;
        data.clear();
        data.addAll(mData);
        notifyDataSetChanged();
    }

    public void addDatas(List<TitleBean.DataBean> mData) {
        //this.data = data;
        //data.clear();
        data.addAll(mData);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TitleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.title_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TitleAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.text_title.setText(data.get(i).getTitle());
        viewHolder.text_price.setText(data.get(i).getPrice()+"");
        String s = data.get(i).getImages().split("\\|")[0].replace("https", "http");
        Glide.with(context).load(s).into(viewHolder.imageView);
        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClicksListener!=null){
                    onClicksListener.onSuccess(i,data.get(i).getPid());
                }
            }
        });

        viewHolder.jiaru_item.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(onClickLongLisenter!=null){
                    onClickLongLisenter.onSuccess(i,data.get(i).getPid());
                }
                return false;
            }
        });

    }



    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView text_title,text_price;
        private ConstraintLayout jiaru_item;
        private Button button;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.three_image);
            text_price=itemView.findViewById(R.id.three_price);
            text_title=itemView.findViewById(R.id.three_title);
            jiaru_item=itemView.findViewById(R.id.jiaru_item);
            button=itemView.findViewById(R.id.button_three);
        }
    }

    OnClicksListener onClicksListener;

    OnClickLongLisenter onClickLongLisenter;

    public void setOnClicksListener(OnClicksListener onClicksListener){
        this.onClicksListener=onClicksListener;
    }

    public interface OnClicksListener {
        void onSuccess(int i,int pid);
    }

    public void setOnClickLongLisenter(OnClickLongLisenter onClickLongLisenter){
        this.onClickLongLisenter=onClickLongLisenter;
    }

    public interface OnClickLongLisenter {
        void onSuccess(int i,int pid);
    }
}
