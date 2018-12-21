package com.bwei.yuekaolianxi01.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.bwei.yuekaolianxi01.Apis;
import com.bwei.yuekaolianxi01.R;
import com.bwei.yuekaolianxi01.adapter.TitleAdapter;
import com.bwei.yuekaolianxi01.bean.JiaRuBean;
import com.bwei.yuekaolianxi01.bean.TitleBean;
import com.bwei.yuekaolianxi01.persenter.IPersenterImpl;
import com.bwei.yuekaolianxi01.view.IView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.Map;

public class ThreeActivity extends AppCompatActivity implements IView {

    private XRecyclerView recyclerView;
    private IPersenterImpl iPersenter;
    private int mPage;
    private TitleAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.three_activity);

        recyclerView=findViewById(R.id.recycle_three);
        iPersenter=new IPersenterImpl(this);

        final LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(manager);

        adapter = new TitleAdapter(this);
        recyclerView.setAdapter(adapter);

        adapter.setOnClicksListener(new TitleAdapter.OnClicksListener() {
            @Override
            public void onSuccess(int i, int pid) {
                loaData(pid);
            }
        });
        adapter.setOnClickLongLisenter(new TitleAdapter.OnClickLongLisenter() {
            @Override
            public void onSuccess(int i, int pid) {
                Intent intent = new Intent(ThreeActivity.this, FourActivity.class);
                startActivity(intent);
            }
        });

        recyclerView.setLoadingMoreEnabled(true);
        recyclerView.setPullRefreshEnabled(true);
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mPage=1;
                loadData();
            }

            @Override
            public void onLoadMore() {
                mPage++;
                loadData();
            }
        });
        loadData();

    }

    private void loaData(int pid) {
        Map<String,String> map=new HashMap<>();
        map.put("pid",pid+"");
        map.put("uid","23015");
        iPersenter.getRequest(Apis.TYPE_JIARU,map,JiaRuBean.class);
    }

    private void loadData() {
        Map<String,String> map=new HashMap<>();
        map.put("keywords","手机");
        map.put("page",mPage+"");
        iPersenter.getRequest(Apis.TYPE_TITLE,map,TitleBean.class);
    }

    @Override
    public void onSucces(Object data) {
        if(data instanceof TitleBean){
            TitleBean titleBean= (TitleBean) data;
            if(mPage==1){
                adapter.setDatas(titleBean.getData());
            }else{
                adapter.addDatas(titleBean.getData());
            }
        }else if(data instanceof JiaRuBean){
            JiaRuBean jiaRuBean= (JiaRuBean) data;
            Toast.makeText(this, jiaRuBean.getMsg()+"", Toast.LENGTH_SHORT).show();
        }
        recyclerView.loadMoreComplete();
        recyclerView.refreshComplete();
    }
}
