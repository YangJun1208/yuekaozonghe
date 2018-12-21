package com.bwei.yuekaolianxi01.iview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.bwei.yuekaolianxi01.R;

public class CustomTitleView extends LinearLayout {

    private Context context;

    public CustomTitleView(Context context) {
        super(context);
        init(context);
    }

    public CustomTitleView(Context context,  AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomTitleView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view=View.inflate(context,R.layout.item_layout,null);
        final EditText editText=view.findViewById(R.id.editText);
        Button button=view.findViewById(R.id.button);
        addView(view);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onclickListener!=null){
                    onclickListener.onSuccess(editText.getText().toString());
                }
            }
        });
    }

    OnclickListener onclickListener;

    public void setOnclickListener(OnclickListener onclickListener){
        this.onclickListener=onclickListener;
    }
    public interface OnclickListener {
        void onSuccess(String str);
    }
}
