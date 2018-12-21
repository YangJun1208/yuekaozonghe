package com.bwei.yuekaolianxi01.model;

import com.bwei.yuekaolianxi01.callback.MyCallBack;
import com.bwei.yuekaolianxi01.okhttp.ICallBack;
import com.bwei.yuekaolianxi01.okhttp.OkHttpUtils;

import java.util.Map;

public class IModelImpl implements IModel {
    @Override
    public void getRequest(String dataurl, Map<String, String> params, Class clazz, final MyCallBack callBack) {
        OkHttpUtils.getInstance().postOnrequeue(dataurl, params, clazz, new ICallBack() {
            @Override
            public void onResponce(Object obj) {
                callBack.onSuccess(obj);
            }

            @Override
            public void onFailure(Exception e) {
                callBack.onSuccess(e);
            }
        });
    }
}
