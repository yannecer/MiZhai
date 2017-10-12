package com.necer.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import necer.network.RxManager;

/**
 * Created by 闫彬彬 on 2017/10/12.
 * QQ:619008099
 */

public abstract class BaseFragment<T extends BasePresenter, E extends BaseModel> extends Fragment {


    protected View mLayoutView;//fragment布局view
    public T mPresenter;
    public E mModel;

    protected Context mContext;
    protected String TAG;//当前页面联网标识

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mLayoutView =inflater.inflate(getLayout(), container, false);

        ViewDataBinding viewDataBinding = DataBindingUtil.bind(mLayoutView);
        TAG = getContext().getPackageName() + "." + getClass().getSimpleName();
        ButterKnife.bind(mLayoutView);
        mContext = getContext();
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        if (this instanceof BaseView && mPresenter != null && mModel != null) {
            mPresenter.setVM(this, mModel);
        }
        this.setData(savedInstanceState, viewDataBinding);
        return mLayoutView;
    }


    protected abstract int getLayout();
    protected abstract void setData(Bundle savedInstanceState, ViewDataBinding viewDataBinding);

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxManager.getInstance().clear(TAG);
    }
}
