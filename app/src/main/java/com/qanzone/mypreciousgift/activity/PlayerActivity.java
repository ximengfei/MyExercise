package com.qanzone.mypreciousgift.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.qanzone.mypreciousgift.R;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.PowerManager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.dou361.ijkplayer.bean.VideoijkBean;
import com.dou361.ijkplayer.listener.OnShowThumbnailListener;
import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;
import com.qanzone.mypreciousgift.bean.VideoBean;
import com.qanzone.mypreciousgift.utils.ConstantKey;
import com.qanzone.mypreciousgift.utils.PublicFunc;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PlayerActivity extends AppCompatActivity {
    private PlayerView player;
    private Context mContext;
    private PowerManager.WakeLock wakeLock;
    //切换分辨率的按钮显示
    private boolean mIsHideStreanm;
    //收藏按钮
    private FloatingActionButton collectFab;
    View rootView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = this;
        rootView = getLayoutInflater().from(this).inflate(R.layout.activity_player, null);
        setContentView(rootView);
//        TextView playingVdeio = (TextView) rootView.findViewById(R.id.playingtext);
        collectFab = (FloatingActionButton) rootView.findViewById(R.id.fab);

        collectFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PublicFunc.showMsg(mContext, "收藏功能，敬请期待");
            }
        });
        /**虚拟按键的隐藏方法*/
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {

                //比较Activity根布局与当前布局的大小
                int heightDiff = rootView.getRootView().getHeight() - rootView.getHeight();
                if (heightDiff > 100) {
                    //大小超过100时，一般为显示虚拟键盘事件
                    rootView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                } else {
                    //大小小于100时，为不显示虚拟键盘或虚拟键盘隐藏
                    rootView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

                }
            }
        });

        /**常亮*/
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "liveTAG");
        wakeLock.acquire();


        Bundle bundleExtra = getIntent().getBundleExtra(ConstantKey.INTENT_ACTIVITY);
        VideoBean videobean = (VideoBean) bundleExtra.getSerializable(ConstantKey.INTENT_SERIALIZABLE);
//        boolean isLive = bundleExtra.getBoolean(ConstantKey.INTENT_BOOLEAN);

        List<VideoijkBean> list = new ArrayList<VideoijkBean>();
        VideoijkBean m1 = new VideoijkBean();
        m1.setStream("标清");
        m1.setUrl(videobean.getVideo_url());
        list.add(m1);
        if (!TextUtils.isEmpty(videobean.getVideo_hd_url())) {
            VideoijkBean m2 = new VideoijkBean();
            m2.setStream("高清");
            m2.setUrl(videobean.getVideo_hd_url());
            list.add(m2);
            mIsHideStreanm = true;
        }

        player = new PlayerView(this, rootView) {
            @Override
            public PlayerView toggleProcessDurationOrientation() {
                return setProcessDurationOrientation(getScreenOrientation() == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT ? PlayStateParams.PROCESS_PORTRAIT : PlayStateParams.PROCESS_LANDSCAPE);
            }

            @Override
            public PlayerView setPlaySource(List<VideoijkBean> list) {
                return super.setPlaySource(list);
            }
        }
                //设置标题
                .setTitle(videobean.getVideo_name())
                //设置3秒后重连
                .setAutoReConnect(true, 3000)
                .setProcessDurationOrientation(PlayStateParams.PROCESS_PORTRAIT)
                .setScaleType(PlayStateParams.fillparent)
                //流量播放提醒
                .setNetWorkTypeTie(true)
                //隐藏分辨率按钮，true隐藏，false为显示
                .hideSteam(!mIsHideStreanm)
                //是否禁止触摸
                .forbidTouch(false)
                .hideFullscreen(false)
                //设置是否禁止隐藏bar，true为一直显示，false为点击可以隐藏或显示
                .setForbidHideControlPanl(false)
                //隐藏中间播放按钮,ture为隐藏，false为不做隐藏处理，但不是显示
                .hideCenterPlayer(false)
                //隐藏旋转按钮，true隐藏，false为显示
                .hideRotation(true)
                .showThumbnail(new OnShowThumbnailListener() {
                    @Override
                    public void onShowThumbnail(ImageView ivThumbnail) {
//                        ivThumbnail.setBackgroundResource(R.drawable.video_place);
                        Picasso.with(mContext).load("https://unsplash.it/400/200/?random").
                                placeholder(R.drawable.video_place).into(ivThumbnail);
                    }
                })
                .setPlaySource(list)
//                .setChargeTie(true,60)
                .startPlay();

    }

//    //隐藏返回键，true隐藏，false为显示
//    PlayerView hideBack(boolean isHide)
//    //隐藏菜单键，true隐藏，false为显示
//    PlayerView hideMenu(boolean isHide)
//    //隐藏分辨率按钮，true隐藏，false为显示
//    PlayerView hideSteam(boolean isHide)
//    //隐藏旋转按钮，true隐藏，false为显示
//    PlayerView hideRotation(boolean isHide)
//    //隐藏全屏按钮，true隐藏，false为显示
//    PlayerView hideFullscreen(boolean isHide)
//    //隐藏中间播放按钮,ture为隐藏，false为不做隐藏处理，但不是显示
//    PlayerView hideCenterPlayer(boolean isHide)



    @Override
    protected void onPause() {
        super.onPause();
        if (player != null) {
            player.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (player != null) {
            player.onResume();
        }
        if (wakeLock != null) {
            wakeLock.acquire();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.onDestroy();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (player != null) {
            player.onConfigurationChanged(newConfig);
        }
    }

    @Override
    public void onBackPressed() {
        if (player != null && player.onBackPressed()) {
            return;
        }
        super.onBackPressed();
        /**demo的内容，恢复设备亮度状态*/
        if (wakeLock != null) {
            wakeLock.release();
        }
    }

}
