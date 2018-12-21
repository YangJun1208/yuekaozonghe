package com.bwei.yuekaolianxi01.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwei.yuekaolianxi01.R;
import com.bwei.yuekaolianxi01.bean.TitleBean;
import com.bwei.yuekaolianxi01.bean.ZhanShiBean;
import com.bwei.yuekaolianxi01.iview.CustomView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.ViewHolder> {

    private Context context;
    private List<ZhanShiBean.DataBean.ListBean> datas;

    public GoodsAdapter(Context context, List<ZhanShiBean.DataBean.ListBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public GoodsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.goods_item,viewGroup,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final GoodsAdapter.ViewHolder viewHolder, final int i){
        viewHolder.goods_title.setText(datas.get(i).getTitle());
        viewHolder.goods_price.setText(datas.get(i).getPrice()+"");
        String s = datas.get(i).getImages().split("\\|")[0].replace("https", "http");
        Glide.with(context).load(s).into(viewHolder.image_goods);

        viewHolder.shop_checkbox.setChecked(datas.get(i).isCheck());

        viewHolder.shop_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                datas.get(i).setCheck(isChecked);
                if(onClicks!=null){
                    onClicks.CallBack();
                }
            }
        });


        viewHolder.customView.setData(i,this,datas);
        viewHolder.customView.setOnClicksListeners(new CustomView.OnClicksListeners() {
            @Override
            public void CallBack() {
                if(onClicks!=null){
                    onClicks.CallBack();
                }
            }
        });

    }

    public void setDatas(boolean bool){

        for(ZhanShiBean.DataBean.ListBean listBeans:datas){
            listBeans.setCheck(bool);
        }
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CheckBox shop_checkbox;
        private ImageView image_goods;
        private TextView goods_title,goods_price;
        private CustomView customView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            shop_checkbox=itemView.findViewById(R.id.shop_checkbox);
            image_goods=itemView.findViewById(R.id.image_goods);
            goods_price=itemView.findViewById(R.id.goods_price);
            goods_title=itemView.findViewById(R.id.goods_title);
            customView=itemView.findViewById(R.id.custom_jia);
        }
    }

    OnClicks onClicks;

    public void setOnClicks(OnClicks onClicks){
        this.onClicks=onClicks;
    }
    public interface OnClicks {
        void CallBack();
    }
}
