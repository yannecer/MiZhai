package com.necer.base;

/**
 * Created by 闫彬彬 on 2017/10/11.
 * QQ:619008099
 */

public class BasePresenter <T, E>{

    public E mModel;
    public T mView;


    public void setVM(T v, E m) {
        this.mView = v;
        this.mModel = m;
    }
}
