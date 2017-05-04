package com.qanzone.mypreciousgift.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by admin on 2017/3/20.
 */

public abstract class BaseFragment extends Fragment {
    /**
     * 初始化的flag
     */
    private boolean mIsInit = false;
    protected Context mContext;
    private View view;
    private Unbinder bind;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view;
    }

    /**
     * 初始化显示的View
     * @return
     */
    protected abstract View initFragmentView();

    /**
     * 是否已经初始化了
     * @return
     */
    public boolean isInit() {
        return mIsInit;
    }

    public void initData() {
        mIsInit = true;
        initFragmentData();
    }

    /**
     * 初始化数据
     */
    protected abstract void initFragmentData();

    public BaseFragment(Context mContext) {
        this.mContext = mContext;
        view = initFragmentView();
        bind = ButterKnife.bind(this, view);
    }

    public BaseFragment() {
        super();
//        this.mContext = getActivity();
//        view = initFragmentView();
//        bind = ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView() {
        bind.unbind();
        super.onDestroyView();
    }
}
