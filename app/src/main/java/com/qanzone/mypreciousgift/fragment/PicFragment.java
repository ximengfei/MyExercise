package com.qanzone.mypreciousgift.fragment;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.qanzone.mypreciousgift.R;
import com.qanzone.mypreciousgift.base.BaseFragment;

/**
 * Created by admin on 2017/3/20.
 */

public class PicFragment extends BaseFragment {
    @Override
    protected View initFragmentView() {
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.layout_developing, null);
        return contentView;
    }

    @Override
    protected void initFragmentData() {

    }

    public PicFragment(Context mContext) {
        super(mContext);
    }

    public PicFragment() {
        super();
    }
}
