package com.bwei.yuekaolianxi01.model;

import com.bwei.yuekaolianxi01.callback.MyCallBack;

import java.util.Map;

public interface IModel {
    void getRequest(String dataurl, Map<String,String> params, Class clazz, MyCallBack callBack);
}
