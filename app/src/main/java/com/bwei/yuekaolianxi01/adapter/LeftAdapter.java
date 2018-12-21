package com.bwei.yuekaolianxi01.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwei.yuekaolianxi01.R;
import com.bwei.yuekaolianxi01.bean.LeftBean;

import java.util.ArrayList;
import java.util.List;

public class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.ViewHolder> {

    private List<LeftBean.DataBean> data;
    private Context context;

    public LeftAdapter(Context context) {
        this.context = context;
        data=new ArrayList<>();
    }

    public void setDatas(List<LeftBean.DataBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LeftAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.left_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LeftAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.textView.setText(data.get(i).getName());
        viewHolder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClickListeners!=null){
                    onClickListeners.onSuccess(i,data.get(i).getCid());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private ConstraintLayout add;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            add=itemView.findViewById(R.id.add);
            textView=itemView.findViewById(R.id.left_view);
        }
    }

    OnClickListeners onClickListeners;

    public void SetOnClickListener(OnClickListeners onClickListeners){
        this.onClickListeners=onClickListeners;
    }
    public interface OnClickListeners {
        void onSuccess(int i,int cid);
    }
}
