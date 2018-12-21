package com.bwei.yuekaolianxi01.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwei.yuekaolianxi01.R;
import com.bwei.yuekaolianxi01.bean.RightBean;

import java.util.ArrayList;
import java.util.List;

public class RightAdapter extends RecyclerView.Adapter<RightAdapter.ViewHolder> {

    private Context context;
    private List<RightBean.DataBean> data;

    public RightAdapter(Context context) {
        this.context = context;
        data=new ArrayList<>();
    }

    public void setDatas(List<RightBean.DataBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RightAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.right_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RightAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.textView.setText(data.get(i).getName());
        ItemAdapter itemAdapter = new ItemAdapter(context, data.get(i).getList());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        viewHolder.recyclerView.setLayoutManager(linearLayoutManager);
        viewHolder.recyclerView.setAdapter(itemAdapter);
        viewHolder.right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onclick!=null){
                    onclick.onSuccess(i,data.get(i).getCid());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView recyclerView;
        private TextView textView;
        private ConstraintLayout right;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.title);
            recyclerView=itemView.findViewById(R.id.recycle);
            right=itemView.findViewById(R.id.right);
        }
    }

    Onclick onclick;

    public void setOnclick(Onclick onclick){
        this.onclick=onclick;
    }
    public interface Onclick {
        void onSuccess(int i,String cid);
    }
}
