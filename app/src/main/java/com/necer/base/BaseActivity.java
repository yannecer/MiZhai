package com.necer.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.necer.mizhai.R;
import com.necer.utils.StatusbarUI;

import butterknife.ButterKnife;
import necer.network.RxManager;

/**
 * Created by 闫彬彬 on 2017/10/11.
 * QQ:619008099
 */

public abstract class BaseActivity<T extends BasePresenter, E extends BaseModel> extends AppCompatActivity {

    public T mPresenter;
    public E mModel;
    public Context mContext;


    protected String TAG;//当前页面联网标识

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewDataBinding viewDataBinding = DataBindingUtil.setContentView(this, getLayout());

        StatusbarUI.setStatusBarUIMode(this, getResources().getColor(R.color.colorMain), false);
        TAG = getPackageName() + "." + getClass().getSimpleName();
        ButterKnife.bind(this);
        mContext = this;
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        if (this instanceof BaseView && mPresenter != null && mModel != null) {
            mPresenter.setVM(this, mModel);
        }
        this.setData(savedInstanceState, viewDataBinding);
    }


    protected abstract int getLayout();

    protected abstract void setData(Bundle savedInstanceState, ViewDataBinding viewDataBinding);


    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxManager.getInstance().clear(TAG);
    }



}
