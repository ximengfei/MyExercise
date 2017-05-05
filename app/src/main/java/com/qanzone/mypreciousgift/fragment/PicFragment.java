package com.qanzone.mypreciousgift.fragment;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qanzone.mypreciousgift.MainActivity;
import com.qanzone.mypreciousgift.R;
import com.qanzone.mypreciousgift.base.BaseFragment;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by admin on 2017/3/20.
 */

public class PicFragment extends BaseFragment {
    @BindView(R.id.navigation_btn)
    ImageView navigationBtn;
    @BindView(R.id.helloworldtxt)
    TextView helloworldtxt;

    @Override
    protected View initFragmentView() {
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.layout_developing, null);
        return contentView;
    }

    @Override
    protected void initFragmentData() {
        Calendar time = Calendar.getInstance();
        int hour = time.get(Calendar.HOUR_OF_DAY);
        String wenhou;
        if(hour < 6){wenhou = "又是一个不眠夜！";}
        else if (hour < 9){wenhou =  "新的一天开始啦！";}
        else if (hour < 12){wenhou =  "上午工作顺利吗？";}
        else if (hour < 14){wenhou =  "中午好！吃了吗？";}
        else if (hour < 17){wenhou =  "下午好!别打盹哦！";}
        else if (hour < 19){wenhou =  "傍晚好!下班了！";}
        else if (hour < 22){wenhou =  "晚上好!夜色多美啊!";}
        else {wenhou =  "夜深了，还不睡吗?";}

        helloworldtxt.setText(wenhou);
    }

    public PicFragment(Context mContext) {
        super(mContext);
    }

    public PicFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.navigation_btn)
    void showNavigation() {
        ((MainActivity) getActivity()).showNavigation();
    }
}
