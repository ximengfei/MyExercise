package com.qanzone.mypreciousgift;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.cpacm.FloatingMusicMenu;
import com.jaeger.library.StatusBarUtil;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.qanzone.mypreciousgift.activity.AdviceFeedbackActivity;
import com.qanzone.mypreciousgift.base.BaseFragment;
import com.qanzone.mypreciousgift.bean.version;
import com.qanzone.mypreciousgift.fragment.ArticleFragment;
import com.qanzone.mypreciousgift.fragment.ExtraFragment;
import com.qanzone.mypreciousgift.fragment.PicFragment;
import com.qanzone.mypreciousgift.fragment.VideoFragment;
import com.qanzone.mypreciousgift.utils.ConstantKey;
import com.qanzone.mypreciousgift.utils.DataCleanManager;
import com.qanzone.mypreciousgift.utils.NetUtil;
import com.qanzone.mypreciousgift.utils.PreferenceUtils;
import com.qanzone.mypreciousgift.utils.PublicFunc;
import com.qanzone.mypreciousgift.utils.music.MusicPlayWindow;
import com.qanzone.mypreciousgift.utils.music.MusicPlayerListener;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

public class MainActivity extends FragmentActivity implements BottomNavigationBar.OnTabSelectedListener, NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.bottom_navigation)
    BottomNavigationBar bottomNavigation;


    int lastSelectPosition = 0;
    @BindView(R.id.content)
    FrameLayout content;
    @BindView(R.id.navigationview)
    NavigationView navigationview;
    @BindView(R.id.drawerlayout)
    DrawerLayout drawerlayout;
    @BindView(R.id.fab_play)
    FloatingActionButton fabPlay;
    @BindView(R.id.fab_next)
    FloatingActionButton fabNext;
    @BindView(R.id.fmm)
    FloatingMusicMenu fmm;
    @BindView(R.id.music_seekbar)
    AppCompatSeekBar musicSeekbar;  //音乐的进度条
    @BindView(R.id.music_seekbar_parent)
    RelativeLayout musicSeekbarParent; //音乐进度条的父布局
    private MusicHandler musicHandler;
    private boolean musicSeekBarIsShowing = false; //音乐进度条的父布局当前是否正在显示
    //音乐控制条的定时器
    private Timer time;
    private TimerTask TimerTask;

    private BaseFragment mLastFragment;
    private BaseFragment mCurrentFragment;
    private VideoFragment mVideoFragment;
//    private ExtraFragment mExtraFragment;
    private ArticleFragment mArticleFragment;
    private PicFragment mPicFragment;
    private final String NEW_APK_NAME = "newVersion.apk";
    private static final int REQUEST_FOR_INSTALL_NEWAPP = 1000;
    private HttpHandler<File> mDownLoadHelper;        // 文件下载管理器
    private TextView tv_progress;                    // 下载进度
    private ProgressBar pb_progress;                // 下载进度

    private AlertDialog downloadDialog;
    private AlertDialog.Builder dialogBuilder;
    private Context mContext;

    private AudioManager mAudioManager;
    private AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListener;
    private MusicPlayWindow mMusicWindow;

    //NOTE:菜单中版本名字显示的下标，当菜单结构更改时需要改变
    private final int MENU_VERSION_NAME_SHOW_INDEX = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext = this;
        checkNewVersion();
//        StatusBarUtil.setColor(MainActivity.this, 0x607D8B);
        initBottomNavigation();
        StatusBarUtil.setTranslucentForImageViewInFragment(MainActivity.this, 0, null);
        initFragment();
        //初始化navigationview
        initSlideNavigation();

        initMusic();
    }

    private void initMusic() {
        musicHandler = new MusicHandler();
        mAudioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
        mAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
            @Override
            public void onAudioFocusChange(int focusChange) {
                if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                    mMusicWindow.pausePlay();
                }
                if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                    //重新获取焦点
//                    mMusicWindow.resumePlay();
                }
            }
        };

        mMusicWindow = new MusicPlayWindow(mContext, new MusicPlayerListener() {
            @Override
            public void onError() {

            }

            @Override
            public void onPrepare() {
                fmm.start();
                fabPlay.setImageResource(R.drawable.ic_play);
                musicHandler.sendEmptyMessage(0);
                if (mAudioManager != null && mAudioFocusChangeListener != null) {
                    mAudioManager.requestAudioFocus(mAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
                }
            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onPause() {
                musicHandler.removeMessages(0);
                fabPlay.setImageResource(R.drawable.ic_pause);
                fmm.stop();
            }

            @Override
            public void onResume() {
                musicHandler.sendEmptyMessage(0);
                fabPlay.setImageResource(R.drawable.ic_play);
                fmm.start();
            }
        });

        fabPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mMusicWindow.startPlay(R.raw.test_song);

            }
        });

        fabNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //显示进度条
                if (!musicSeekBarIsShowing) {
                    musicSeekbarParent.setVisibility(View.VISIBLE);
                    musicSeekBarIsShowing = true;
                    PublicFunc.fadeViewIn(musicSeekbarParent, null);
                    if (time == null) {
                        time = new Timer();

                    }

                    //坑爹，每次都需要新建一个Task任务
                    TimerTask = new TimerTask() {
                        @Override
                        public void run() {
                            MainActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    PublicFunc.fadeViewOut(musicSeekbarParent, new Runnable() {
                                        @Override
                                        public void run() {
                                            musicSeekBarIsShowing = false;
                                            musicSeekbarParent.setVisibility(View.GONE);
                                        }
                                    });
                                }
                            });

                        }
                    };

                    time.schedule(TimerTask, 4000);
                }
            }
        });

        musicSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mMusicWindow.seekTo(seekBar.getProgress());
            }
        });
    }

    private void initSlideNavigation() {
        navigationview.getMenu().getItem(2).setTitle("当前版本：" + PublicFunc.getVersionName(mContext));
        //为了让menu中的icon显示其本来的颜色
        navigationview.setItemIconTintList(null);

        navigationview.setNavigationItemSelectedListener(this);

    }

    @Override
    protected void onDestroy() {
        if (mAudioManager != null && mAudioFocusChangeListener != null) {
            mAudioManager.abandonAudioFocus(mAudioFocusChangeListener);
        }

        //清理定时器
        if (time != null) {
            time.cancel();
            time = null;
        }

        mMusicWindow.dealloc();
        musicHandler.removeMessages(0);
        super.onDestroy();
    }

    //检查版本更新
    private void checkNewVersion() {
        //查找表里面id为gWREYYYb的数据
        BmobQuery<version> bmobQuery = new BmobQuery<version>();
        bmobQuery.getObject("gWREYYYb", new QueryListener<version>() {
            @Override
            public void done(version result, BmobException e) {
                if (e == null) {
                    int clientVersionCode = PublicFunc.getVersionCode(MainActivity.this);
                    int serverVersionCode = result.getServicecode();
                    if (clientVersionCode < serverVersionCode) {
                        int netWorkState = NetUtil.getNetWorkState(MainActivity.this);
                        if (netWorkState == NetUtil.NETWORK_MOBILE) {

                            //当前网络是移动网络
                        } else if (netWorkState == NetUtil.NETWORK_WIFI) {
                            //WIFi情况下
                            showUpdateDialog(result);
                        }
                    } else {
                        //版本不需要更新
                        PreferenceUtils.setBoolean(MainActivity.this, ConstantKey.SP_NEW_VERSION_DOWNLOAD, false);
                    }

                } else {
                    PublicFunc.showMsg(MainActivity.this, "查询新版本信息失败... 失败原因 " + e.getMessage());
                }
            }
        });
    }


    //显示版本更新的dialog
    private void showUpdateDialog(final version result) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_new_update_view, null);
        builder.setView(view);
        final AlertDialog updateDialog = builder.show();
        updateDialog.setCanceledOnTouchOutside(false);
        updateDialog.setCancelable(true);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        updateDialog.getWindow().setLayout((int) (dm.widthPixels * 0.8), ViewGroup.LayoutParams.WRAP_CONTENT);
        Button ok = (Button) view.findViewById(R.id.btn_update_id_ok);
        Button cancel = (Button) view.findViewById(R.id.btn_update_id_cancel);
        //更新的内容
        TextView tvContent = (TextView) view.findViewById(R.id.tv_update_content);
        //版本号
        TextView tvUpdateTile = (TextView) view.findViewById(R.id.tv_update_title);
        //已经下载的提醒
        final TextView tvUpdateMsgSize = (TextView) view.findViewById(R.id.tv_update_msg_size);
        final File file = new File(PublicFunc.getDiskCacheDir(this) + File.separator + NEW_APK_NAME);
        final boolean boolean1 = PreferenceUtils.getBoolean(this, ConstantKey.SP_NEW_VERSION_DOWNLOAD);
        if (file.exists() && file.getName().equals(NEW_APK_NAME) && boolean1) {
            tvUpdateMsgSize.setText("( ¯•ω•¯ ) 新版本已经下载，是否安装？");
            ok.setText("(≖ᴗ≖๑)安装");
        } else {
            tvUpdateMsgSize.setText("");
        }
        String updatalog = result.getUpdatalog();
        if (updatalog.contains("\\n")) {
            updatalog = updatalog.replace("\\n", "\n");
        }
        tvContent.setText(updatalog);
//        tvUpdateTile.setText("(版本号:V" + result.getServicecode() + ".0)");
        tvUpdateTile.setText("(更新日期:" + result.getUpdatedAt());

        ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                updateDialog.dismiss();
                //更新
                if (file.exists() && file.getName().equals(NEW_APK_NAME) && boolean1) {
                    Intent intent = PublicFunc.getInstallIntent(file);
                    startActivityForResult(intent, REQUEST_FOR_INSTALL_NEWAPP);
                } else {
                    file.delete();
                    downloadAppFile(result.getApkpath(), file.getAbsolutePath());
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //取消
                updateDialog.dismiss();
            }
        });
    }

    private void initFragment() {
        mPicFragment = new PicFragment(this);
//        mExtraFragment = new ExtraFragment(this);
        mArticleFragment = new ArticleFragment(this);
        mVideoFragment = new VideoFragment(this);

        mCurrentFragment = mPicFragment;
        mLastFragment = null;

        selectShowFragment();
    }

    /**
     * 切换当前显示的fragment
     */
    private void selectShowFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (mCurrentFragment != mLastFragment) {
            if (mLastFragment == null) {
                if (!mCurrentFragment.isAdded()) {
                    transaction.add(R.id.content, mCurrentFragment).commit();
                } else {
                    transaction.show(mCurrentFragment).commit();
                }
            } else {
                if (!mCurrentFragment.isAdded()) {
                    transaction.hide(mLastFragment).add(R.id.content, mCurrentFragment).commit();
                } else {
                    transaction.hide(mLastFragment).show(mCurrentFragment).commit();
                }
            }

            if (!mCurrentFragment.isInit())
                mCurrentFragment.initData();
            mLastFragment = mCurrentFragment;
        }
    }

    private void initBottomNavigation() {
        bottomNavigation.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigation.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigation.setAutoHideEnabled(true);

        bottomNavigation.addItem(new BottomNavigationItem(R.drawable.bottom_navigation_heart, "心情记录").setActiveColorResource(R.color.brown))
                .addItem(new BottomNavigationItem(R.drawable.bottom_navigation_video, "电视直播").setActiveColorResource(R.color.grey))
                .addItem(new BottomNavigationItem(R.drawable.bottom_navigation_extra, "拓展功能").setActiveColorResource(R.color.orange)).
                setFirstSelectedPosition(lastSelectPosition).initialise();
        bottomNavigation.setTabSelectedListener(this);
    }

    @Override
    public void onTabSelected(int position) {
        switch (position) {
            case 0:
//                StatusBarUtil.setColor(MainActivity.this, Color.parseColor("#8D6E63"));
                mCurrentFragment = mPicFragment;
                selectShowFragment();
                break;
            case 1:
//                StatusBarUtil.setColor(MainActivity.this, Color.parseColor("#607D8B"));
                mCurrentFragment = mVideoFragment;
                selectShowFragment();
                break;
            case 2:
//                StatusBarUtil.setColor(MainActivity.this, Color.parseColor("#F57C00"));
                mCurrentFragment = mArticleFragment;

                selectShowFragment();
                break;
        }

    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    protected void downloadAppFile(String updateurl, final String desturl) {
        mDownLoadHelper = new HttpUtils().download(updateurl, desturl, true, true, new RequestCallBack<File>() {

            @Override
            public void onFailure(HttpException arg0, String arg1) {
                downloadDialog.setTitle("(｡ŏ_ŏ) 下载失败...");
                tv_progress.setTextColor(getResources().getColor(R.color.red));
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        downloadDialog.dismiss();
                    }
                }, 2000);
            }

            @Override
            public void onSuccess(ResponseInfo<File> arg0) {
                downloadDialog.dismiss();
                PreferenceUtils.setBoolean(MainActivity.this, ConstantKey.SP_NEW_VERSION_DOWNLOAD, true);
                Intent intent = PublicFunc.getInstallIntent(new File(desturl));
                startActivityForResult(intent, REQUEST_FOR_INSTALL_NEWAPP);
            }

            @Override
            public void onLoading(long total, long current, boolean isUploading) {
                super.onLoading(total, current, isUploading);
                int x = Long.valueOf(current).intValue();
                int totalS = Long.valueOf(total).intValue();
                pb_progress.setProgress((int) (getDoublePercent(x, totalS) * 100));
                tv_progress.setText(getStringPercent(x, totalS));
            }

            @Override
            public void onStart() {
                super.onStart();
                initProgressDialog();
                pb_progress.setMax(100);

            }

            @Override
            public void onCancelled() {
                super.onCancelled();
                downloadDialog.dismiss();
                mDownLoadHelper.cancel();
            }
        });
    }

    protected void initProgressDialog() {
        dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setIcon(R.mipmap.ic_launcher);
        dialogBuilder.setTitle("(ง •̀_•́)ง正在努力下载中");
        dialogBuilder.setCancelable(false);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.layout_download_new_version_seekbar, null);
        pb_progress = (ProgressBar) dialogView.findViewById(R.id.pb_download_progress);
        tv_progress = (TextView) dialogView.findViewById(R.id.tv_download_current_progress);
        dialogBuilder.setView(dialogView);
        downloadDialog = dialogBuilder.show();
    }

    private double getDoublePercent(int x, int total) {
        double x_double = x * 1.0;
        double tempresult = x_double / total;
        // String result = "";// 接受百分比的值
        // 百分比格式，后面不足2位的用0补齐 ##.00%
        // DecimalFormat df1 = new DecimalFormat("0.00%");
        // result = df1.format(tempresult);
        return tempresult;
    }

    private String getStringPercent(int x, int total) {
        double x_double = x * 1.0;
        double tempresult = x_double / total;
        String result = "";// 接受百分比的值
        // 百分比格式，后面不足2位的用0补齐 ##.00%
        DecimalFormat df1 = new DecimalFormat("0.00%");
        result = df1.format(tempresult);
        return result;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_FOR_INSTALL_NEWAPP && resultCode == RESULT_CANCELED) {
//            PublicFunc.showMsg(this, "");
//            finish();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.advice_feedback:
                startActivity(new Intent(mContext, AdviceFeedbackActivity.class));

                break;
            case R.id.clear_cache:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(getResources().getString(R.string.serious_dialog));
                builder.setMessage("当前的缓存大小为：" + getTotalCache(this));
                builder.setPositiveButton("立即清理", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DataCleanManager.clearAllCache(mContext);
                        PublicFunc.showMsg(mContext, "清理缓存成功, 当前缓存大小为" + getTotalCache(mContext));
                    }
                });
                builder.setNegativeButton("还是不啦", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
                break;
            case R.id.version_name:
//                noting
                return false;
        }
        drawerlayout.closeDrawer(Gravity.LEFT);
        return true;
    }

    public String getTotalCache(Context context) {
        String totalCacheSize = "";
        try {
            totalCacheSize = DataCleanManager.getTotalCacheSize(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalCacheSize;
    }

    private void updateProgress() {
        int musicDuration = mMusicWindow.getDuration();
        if (musicDuration == -1) return;
        int musicProgress = mMusicWindow.getCurrentPosition();
        fmm.setProgress(musicProgress * 100 / musicDuration);
    }

    //显示侧滑菜单
    public void showNavigation() {
        drawerlayout.openDrawer(Gravity.LEFT);
    }

    public class MusicHandler extends Handler {

        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            updateProgress();
            updateMusicSeekBar();
            sendEmptyMessageDelayed(0, 1000);
        }
    }

    //更新拖拽音乐的进度条
    private void updateMusicSeekBar() {
        int musicDuration = mMusicWindow.getDuration();
        if (musicDuration == -1) return;
        int musicProgress = mMusicWindow.getCurrentPosition();

        musicSeekbar.setMax(musicDuration);
        musicSeekbar.setProgress(musicProgress);
    }
}
