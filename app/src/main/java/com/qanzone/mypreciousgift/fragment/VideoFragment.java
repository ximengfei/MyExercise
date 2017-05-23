package com.qanzone.mypreciousgift.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.qanzone.mypreciousgift.R;
import com.qanzone.mypreciousgift.activity.NetVideoListActivity;
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
    CardView tv;
    @BindView(R.id.lunb0)
    CardView lunb0;
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
        //1.02版本以前的跳转方式
//        startActivity(new Intent(mContext, VideoListActivity.class).putExtra(ConstantKey.VIDEOLIST_TYPE, VideoListActivity.INTENT_TV));

        //1.03 新的网络数据列表页面
        startActivity(new Intent(mContext, NetVideoListActivity.class));
    }

    @OnClick(R.id.lunb0)
    void lunboClick() {
//        PublicFunc.showMsg(mContext, "lunboclick");
        //1.02版本以前的跳转方式
//        startActivity(new Intent(mContext, VideoListActivity.class).putExtra(ConstantKey.VIDEOLIST_TYPE, VideoListActivity.INTENT_LUNBO));

        //1.04 新的数据列表页面
        startActivity(new Intent(mContext, VideoListActivity.class));
    }
}
