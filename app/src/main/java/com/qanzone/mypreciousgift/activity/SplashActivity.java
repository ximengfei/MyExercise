package com.qanzone.mypreciousgift.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.lidroid.xutils.BitmapUtils;
import com.qanzone.mypreciousgift.MainActivity;
import com.qanzone.mypreciousgift.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;
import pl.droidsonroids.gif.GifImageView;

public class SplashActivity extends AppCompatActivity {

    private final int TIMERTASKCOUNT = 0x1111;
    @BindView(R.id.gif_bg)
    GifImageView gifBg;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.activity_splash)
    RelativeLayout activitySplash;
    private int mShowTime = 3;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mShowTime = mShowTime - 1;
            if (mShowTime == 0) {

                toMainActivity();

            } else {
                tvTime.setText(mShowTime + "s");
                mHandler.sendEmptyMessageDelayed(TIMERTASKCOUNT, 1000);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bmob.initialize(this, "b9d6f672031979436bfd043492cb246e");
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        StatusBarUtil.setTranslucent(this);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mHandler.sendEmptyMessageDelayed(TIMERTASKCOUNT, 1000);
    }

    @OnClick(R.id.ll_ad_skip_btn) void stopSplash() {
        toMainActivity();
    }

    private void toMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void finish() {
        mHandler.removeMessages(TIMERTASKCOUNT);
        super.finish();
    }
}
