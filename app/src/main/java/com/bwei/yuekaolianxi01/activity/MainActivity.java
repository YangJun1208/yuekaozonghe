package com.bwei.yuekaolianxi01.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.yuekaolianxi01.R;
import com.bwei.yuekaolianxi01.iview.CustomFlawView;
import com.bwei.yuekaolianxi01.iview.CustomTitleView;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

public class MainActivity extends AppCompatActivity{

    private CustomTitleView customTitleView;
    private CustomFlawView customFlawView;
    private ImageView button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customFlawView=findViewById(R.id.custom_flaw);
        button=findViewById(R.id.QQ);
        customTitleView=findViewById(R.id.custom_title);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMShareAPI umShareAPI =  UMShareAPI.get(MainActivity.this);
                umShareAPI.getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ, new UMAuthListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                        Log.i("dj", "UMAuthListener onComplete");
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media, int i) {

                    }
                });
            }
        });
        customFlawView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        CustomFlawView rv=findViewById(R.id.rv);
        customTitleView.setOnclickListener(new CustomTitleView.OnclickListener() {
            @Override
            public void onSuccess(String str) {
                TextView textView = new TextView(MainActivity.this);
                textView.setText(str);
                customFlawView.addView(textView);
            }
        });
        for (int i=0;i<10;i++){
            TextView tv = new TextView(MainActivity.this);
            tv.setText("数据:" + i);
            tv.setTextColor(Color.RED);
            rv.addView(tv);
        }




    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }



}
