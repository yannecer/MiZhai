package com.necer.home;

import android.databinding.ViewDataBinding;
import android.os.Bundle;

import com.necer.base.BaseFragment;
import com.necer.mizhai.R;
import com.necer.mizhai.databinding.FragmentHomeBinding;

/**
 * Created by 闫彬彬 on 2017/10/12.
 * QQ:619008099
 */

public class HomeFragment extends BaseFragment{


    private FragmentHomeBinding fragmentHomeBinding;

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void setData(Bundle savedInstanceState, ViewDataBinding viewDataBinding) {
        fragmentHomeBinding = (FragmentHomeBinding) viewDataBinding;




    }
}
