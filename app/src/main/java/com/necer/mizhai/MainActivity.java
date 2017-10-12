package com.necer.mizhai;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.necer.base.BaseActivity;
import com.necer.bean.TabEntity;
import com.necer.home.HomeFragment;
import com.necer.house.HouseFragment;
import com.necer.mizhai.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {


    private String[] mTitles = {"首页", "新房", "点评", "我的"};
    private int[] mIconUnselectIds = {
            R.mipmap.home, R.mipmap.house, R.mipmap.comment, R.mipmap.user};
    private int[] mIconSelectIds = {
            R.mipmap.home_passed, R.mipmap.house_passed, R.mipmap.comment_press, R.mipmap.user_pressed};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    private HomeFragment homeFragment;
    private HouseFragment houseFragment;

    private ActivityMainBinding mainBinding;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void setData(Bundle savedInstanceState, ViewDataBinding viewDataBinding) {
        mainBinding = (ActivityMainBinding) viewDataBinding;

        initTab();
        switchTo(0);

    }


    private void switchTo(int position) {
       FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        if (homeFragment != null) {
            fragmentTransaction.hide(homeFragment);
        }
        if (houseFragment != null) {
            fragmentTransaction.hide(houseFragment);
        }

        switch (position) {
            case 0:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    fragmentTransaction.add(R.id.fl_body, homeFragment);
                }
                fragmentTransaction.show(homeFragment);

                break;
            case 1:
                if (houseFragment == null) {
                    houseFragment = new HouseFragment();
                    fragmentTransaction.add(R.id.fl_body, houseFragment);
                }
                fragmentTransaction.show(houseFragment);
                break;
            case 2:

                break;
            case 3:

                break;

        }
        fragmentTransaction.commit();
        mainBinding.tabLayout.setCurrentTab(position);

    }


    private void initTab() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        mainBinding.tabLayout.setTabData(mTabEntities);
        mainBinding.tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switchTo(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });
    }


}
