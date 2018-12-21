package com.bwei.yuekaolianxi01.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bwei.yuekaolianxi01.Apis;
import com.bwei.yuekaolianxi01.R;
import com.bwei.yuekaolianxi01.adapter.LeftAdapter;
import com.bwei.yuekaolianxi01.adapter.RightAdapter;
import com.bwei.yuekaolianxi01.bean.RightBean;
import com.bwei.yuekaolianxi01.bean.LeftBean;
import com.bwei.yuekaolianxi01.persenter.IPersenterImpl;
import com.bwei.yuekaolianxi01.view.IView;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements IView {

    private RecyclerView left_view;
    private RecyclerView right_view;
    private LeftAdapter adapter;
    private IPersenterImpl iPersenter;
    private RightAdapter adapter1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        left_view=findViewById(R.id.left_textView);
        right_view=findViewById(R.id.right_textView);
        iPersenter=new IPersenterImpl(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        left_view.setLayoutManager(layoutManager);

        left_view.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));


        adapter = new LeftAdapter(this);
        left_view.setAdapter(adapter);


        adapter.SetOnClickListener(new LeftAdapter.OnClickListeners() {
            @Override
            public void onSuccess(int i, int cid) {
                rightData(cid);
            }
        });

        getTypeData();

        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this);
        layoutManager1.setOrientation(OrientationHelper.VERTICAL);
        right_view.setLayoutManager(layoutManager1);

        adapter1 = new RightAdapter(this);
        right_view.setAdapter(adapter1);


        adapter1.setOnclick(new RightAdapter.Onclick() {
            @Override
            public void onSuccess(int i, String cid) {
                Intent intent = new Intent(LoginActivity.this, ThreeActivity.class);
                startActivity(intent);
            }
        });

    }

    private void getTypeData() {
        Map<String, String> map = new HashMap<>();
        iPersenter.getRequest(Apis.TYPE_LEST, map, LeftBean.class);
    }

    private void rightData(int cid) {
        Map<String, String> map = new HashMap<>();
        map.put("cid", cid+"");
        iPersenter.getRequest(Apis.TYPE_RIGHT, map, RightBean.class);
    }

    @Override
    public void onSucces(Object data) {
        if(data instanceof LeftBean){
            LeftBean leftBean= (LeftBean) data;
            adapter.setDatas(leftBean.getData());
        }else if(data instanceof RightBean){
            RightBean rightBean= (RightBean) data;
            adapter1.setDatas(rightBean.getData());
        }
    }
}
