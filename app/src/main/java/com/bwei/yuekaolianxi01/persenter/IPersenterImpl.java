package com.bwei.yuekaolianxi01.persenter;

import com.bwei.yuekaolianxi01.callback.MyCallBack;
import com.bwei.yuekaolianxi01.model.IModelImpl;
import com.bwei.yuekaolianxi01.view.IView;

import java.util.Map;

public class IPersenterImpl implements IPersenter {

    private IModelImpl iModel;
    private IView iView;
    public IPersenterImpl(IView iView){
        iModel=new IModelImpl();
        this.iView=iView;
    }

    @Override
    public void getRequest(String dataUrl, Map<String, String> params, Class clazz) {
        iModel.getRequest(dataUrl, params, clazz, new MyCallBack() {
            @Override
            public void onSuccess(Object data) {
                iView.onSucces(data);
            }
        });
    }
    public void deteach(){
        iView=null;
        iModel=null;
    }
}
