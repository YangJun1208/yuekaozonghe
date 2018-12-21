package com.bwei.yuekaolianxi01.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bwei.yuekaolianxi01.Apis;
import com.bwei.yuekaolianxi01.R;
import com.bwei.yuekaolianxi01.adapter.ShopAdapter;
import com.bwei.yuekaolianxi01.bean.ZhanShiBean;
import com.bwei.yuekaolianxi01.persenter.IPersenterImpl;
import com.bwei.yuekaolianxi01.view.IView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FourActivity extends AppCompatActivity implements IView,View.OnClickListener {

    private RecyclerView recyclerView;
    private CheckBox gouwuche_checkbox;
    private TextView num_sum;
    private TextView sum_price;
    private ShopAdapter adapter;
    private IPersenterImpl iPersenter;
    private List<ZhanShiBean.DataBean> data1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);
        recyclerView=findViewById(R.id.gouwuche_recycle);
        gouwuche_checkbox=findViewById(R.id.gouwuche_checkbox);
        num_sum=findViewById(R.id.num_sum);
        sum_price=findViewById(R.id.sum_price);
        iPersenter=new IPersenterImpl(this);

        gouwuche_checkbox.setOnClickListener(this);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new ShopAdapter(this);
        recyclerView.setAdapter(adapter);

        adapter.setOnClickListener(new ShopAdapter.OnsClickListener() {

            @Override
            public void CallBack(List<ZhanShiBean.DataBean> list) {
                int num=0;
                double price=0;
                int numSum=0;
                for (int i=0;i<list.size();i++) {
                    List<ZhanShiBean.DataBean.ListBean> listBeans = list.get(i).getList();
                    for (int a=0;a<listBeans.size();a++){
                        numSum+= listBeans.get(a).getNum();
                        if(listBeans.get(a).isCheck()){
                            num+=listBeans.get(a).getNum();
                            price+=listBeans.get(a).getPrice()*listBeans.get(a).getNum();
                        }
                    }
                }
                if(num<numSum){
                    gouwuche_checkbox.setChecked(false);
                }else{
                    gouwuche_checkbox.setChecked(true);
                }
                sum_price.setText("总价为:"+price);
                num_sum.setText("数量为:"+num);
            }

        });
        loadData();
    }


    private void loadData() {
        Map<String,String> map=new HashMap<>();
        map.put("uid","23015");
        iPersenter.getRequest(Apis.TYPE_ZHAN,map,ZhanShiBean.class);
    }

    @Override
    public void onSucces(Object data) {
        if(data instanceof ZhanShiBean){
            ZhanShiBean zhanShiBean= (ZhanShiBean) data;
            data1 = zhanShiBean.getData();
            if(data1 !=null){
                adapter.setDatas(zhanShiBean.getData());
            }
            sellSum(true);
            gouwuche_checkbox.setChecked(true);

        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.gouwuche_checkbox:
                sellSum(gouwuche_checkbox.isChecked());
                adapter.notifyDataSetChanged();
                break;
                default:
                    break;
        }
    }

    private void sellSum(boolean checked) {
        int price=0;
        int num=0;
        for (int a=0;a<data1.size();a++){
            ZhanShiBean.DataBean bean = data1.get(a);
            bean.setIscheck(checked);
            List<ZhanShiBean.DataBean.ListBean> list = data1.get(a).getList();
            for (int i=0;i<list.size();i++){
                list.get(i).setCheck(checked);
                num+=list.get(i).getNum();
                price+=list.get(i).getPrice()*list.get(i).getNum();
            }
        }
        if(checked){
            sum_price.setText("总价为:"+price);
            num_sum.setText("数量为:"+num);
        }else{
            sum_price.setText("总价为:"+0.0);
            num_sum.setText("数量为:"+0);
        }
    }


}
