package com.bwei.yuekaolianxi01.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bwei.yuekaolianxi01.R;
import com.bwei.yuekaolianxi01.bean.ZhanShiBean;
import com.bwei.yuekaolianxi01.iview.CustomView;

import java.util.ArrayList;
import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {

    private List<ZhanShiBean.DataBean> data;
    private Context context;

    public ShopAdapter(Context context) {
        this.context = context;
        data=new ArrayList<>();
    }

    public void setDatas(List<ZhanShiBean.DataBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ShopAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.shop_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ShopAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.shop_textView.setText(data.get(i).getSellerName());

        final GoodsAdapter goodsAdapter = new GoodsAdapter(context,data.get(i).getList());

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        viewHolder.shop_recycle.setLayoutManager(layoutManager);
        viewHolder.shop_recycle.setAdapter(goodsAdapter);

        viewHolder.checkBox.setChecked(data.get(i).isIscheck());

        goodsAdapter.setOnClicks(new GoodsAdapter.OnClicks() {
            @Override
            public void CallBack() {
                if(onClickListener!=null){
                    onClickListener.CallBack(data);
                }
                List<ZhanShiBean.DataBean.ListBean> list = data.get(i).getList();
                boolean isAllCkeck=true;
                for (ZhanShiBean.DataBean.ListBean listBean:list){
                    if(!listBean.isCheck()){
                        isAllCkeck=false;
                        break;
                    }
                }
                viewHolder.checkBox.setChecked(isAllCkeck);
                data.get(i).setIscheck(isAllCkeck);
            }
        });

        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.get(i).setIscheck(viewHolder.checkBox.isChecked());
                goodsAdapter.setDatas(viewHolder.checkBox.isChecked());
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CheckBox checkBox;
        private TextView shop_textView;
        private RecyclerView shop_recycle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox=itemView.findViewById(R.id.shop_item);
            shop_textView=itemView.findViewById(R.id.shop_textView);
            shop_recycle=itemView.findViewById(R.id.shop_recycle);
        }
    }

    OnsClickListener onClickListener;

    public void setOnClickListener(OnsClickListener onClickListener){
        this.onClickListener=onClickListener;
    }

    public interface OnsClickListener {
        void CallBack(List<ZhanShiBean.DataBean> list);
    }
}
