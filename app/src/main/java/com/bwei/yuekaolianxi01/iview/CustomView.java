package com.bwei.yuekaolianxi01.iview;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bwei.yuekaolianxi01.R;
import com.bwei.yuekaolianxi01.adapter.GoodsAdapter;
import com.bwei.yuekaolianxi01.bean.ZhanShiBean;

import java.util.List;

public class CustomView extends LinearLayout {

    private Context mContext;
    private EditText shu;

    public CustomView(Context context) {
        super(context);
        init(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    int num=0;
    private void init(final Context context) {
        View view=View.inflate(context, R.layout.item_custom,null);
        Button jia= view.findViewById(R.id.jia);
        Button jian=view.findViewById(R.id.jian);
        shu = view.findViewById(R.id.shu);
        addView(view);

        jia.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                num++;
                shu.setText(num+"");
                list.get(position).setNum(num);
                onClicksListeners.CallBack();
                goodsAdapter.notifyItemChanged(position);
            }
        });
        jian.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(num>1){
                    num--;
                    shu.setText(num+"");
                    list.get(position).setNum(num);
                    onClicksListeners.CallBack();
                    goodsAdapter.notifyItemChanged(position);
                }else{
                    Toast.makeText(context, "有底线的", Toast.LENGTH_SHORT).show();
                }
            }
        });

        shu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                num=Integer.parseInt(String.valueOf(s));
                try {
                    list.get(position).setNum(num);
                }catch (Exception e){
                    list.get(position).setNum(1);
                }
                if(onClicksListeners!=null){
                    onClicksListeners.CallBack();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    OnClicksListeners onClicksListeners;

    public void setOnClicksListeners(OnClicksListeners onClicksListeners){
        this.onClicksListeners=onClicksListeners;
    }


    public interface OnClicksListeners {
        void CallBack();
    }

    private int position;
    private GoodsAdapter goodsAdapter;
    private List<ZhanShiBean.DataBean.ListBean> list;

    public void setData(int position,GoodsAdapter goodsAdapter,List<ZhanShiBean.DataBean.ListBean> list){
        this.goodsAdapter=goodsAdapter;
        this.position=position;
        this.list=list;
        num=list.get(position).getNum();
        shu.setText(num+"");
    }

}
