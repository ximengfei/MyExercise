package com.qanzone.mypreciousgift.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.qanzone.mypreciousgift.R;
import com.qanzone.mypreciousgift.activity.VideoListActivity;
import com.qanzone.mypreciousgift.base.BaseFragment;
import com.qanzone.mypreciousgift.utils.ConstantKey;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xmf on 2017/3/20.
 */

public class VideoFragment extends BaseFragment {
    @BindView(R.id.tv)
    RelativeLayout tv;
    @BindView(R.id.lunb0)
    RelativeLayout lunb0;
    @BindView(R.id.container)
    FrameLayout container;
    private View contentview;

    @Override
    protected View initFragmentView() {
        contentview = LayoutInflater.from(mContext).inflate(R.layout.pager_video, null);
        return contentview;
    }

    @Override
    protected void initFragmentData() {


    }

    public VideoFragment() {
        super();
    }

    public VideoFragment(Context mContext) {
        super(mContext);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.tv)
    void tvClick(){
//        PublicFunc.showMsg(mContext, "tvclick");
        startActivity(new Intent(mContext, VideoListActivity.class).putExtra(ConstantKey.VIDEOLIST_TYPE, VideoListActivity.INTENT_TV));

    }

    @OnClick(R.id.lunb0)
    void lunboClick() {
//        PublicFunc.showMsg(mContext, "lunboclick");
        startActivity(new Intent(mContext, VideoListActivity.class).putExtra(ConstantKey.VIDEOLIST_TYPE, VideoListActivity.INTENT_LUNBO));
    }
}
