package com.qanzone.mypreciousgift.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.widget.Toast;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;

import java.io.File;

/**
 * Created by xmf on 2017/4/18.
 * 工具类
 */

public class PublicFunc {
    /**
     * 获取状态栏的高度
     * @param con
     * @return
     */
    public static int getStatusBarHeight(Context con) {
        int result = 0;
        int resourceId = con.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = con.getResources().getDimensionPixelSize(resourceId);
            }
        return result;
    }

    public static StringBuffer sb = new StringBuffer();
    /**
     * 获取汉字字符串的汉语拼音，英文字符不变
     */
    public static String getPinYin(String chines) {
        sb.setLength(0);
        char[] nameChar = chines.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < nameChar.length; i++) {
            if (nameChar[i] > 128) {
                try {
                    sb.append(PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                sb.append(nameChar[i]);
            }
        }
        return sb.toString();
    }

    /**
     * 提示信息
     *
     * @param context
     * @param msgId
     */
    public static void showMsg(Context context, int msgId) {
        String msg = context.getResources().getString(msgId);
        showMsg(context, msg);

    }

    private static String oldMsg;
    private static long time;

    /**
     * Toast一条信息
     *
     * @param context
     * @param msg
     */
    public static void showMsg(Context context, String msg) {
        if (!TextUtils.isEmpty(msg)) {
            if (msg.contains("SocketException")
                    || msg.contains("ConnectException")) {
                msg = "网络连接错误";
            } else if (msg.contains("TimeoutException")) {
                msg = "连接超时";
            } else if (msg.contains("!DOCTYPE")) {
                msg = "服务器错误";
            } else if (msg.contains("Server") || msg.contains("subscribe")
                    || msg.contains("Exception")) {
                return;
            }


            if (!msg.equals(oldMsg)) { // 当显示的内容不一样时，即断定为不是同一个Toast
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                time = System.currentTimeMillis();
            } else {
                // 显示内容一样时，只有间隔时间大于2秒时才显示
                if (System.currentTimeMillis() - time > 2000) {
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                    time = System.currentTimeMillis();
                }
            }
            oldMsg = msg;

        }
    }

    // 获取到当前的包名信息
    private static PackageInfo getPackageInfo(Context context) {
        PackageInfo pi = null;

        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);

            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pi;
    }

    // 版本号
    public static int getVersionCode(Context context) {
        return getPackageInfo(context).versionCode;
    }

    // 版本名称
    public static String getVersionName(Context context) {
        return getPackageInfo(context).versionName;
    }

    // 获取缓存的目录
    public static String getDiskCacheDir(Context context) {
        String cachePath = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return cachePath;
    }

    /**
     * 得到安装的intent
     * @param apkFile
     * @return
     */
    public static Intent getInstallIntent(File apkFile) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(apkFile.getAbsolutePath())),
                "application/vnd.android.package-archive");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    /**
     * 显示一个简单的弹框，参数不能为空！！
     * @param con
     * @param title
     * @param content
     * @param oktext
     * @param okListener
     * @param noText
     * @param noListener
     * @return
     */
    public static AlertDialog showDialog(Context con, String title, String content, String oktext, DialogInterface.OnClickListener okListener,
                             String noText, DialogInterface.OnClickListener noListener) {
        AlertDialog.Builder Builder = new AlertDialog.Builder(con);
        if (title != null && !title.isEmpty())
            Builder.setTitle(title);
        if (content != null && !content.isEmpty())
            Builder.setMessage(content);
        if (oktext != null && okListener != null && !oktext.isEmpty())
            Builder.setPositiveButton(oktext, okListener);
        if (noText != null && noListener != null && !noText.isEmpty())
            Builder.setNegativeButton(noText, noListener);
        return Builder.show();
    }

    // ### 动画相关
    public static final int ANIM_DURATION_SHORT = 200;
    public static void fadeViewIn(View view, final Runnable onEnd) {
        fadeView(view, 0.0f, 1.0f, ANIM_DURATION_SHORT, false, onEnd);
    }
    public static void fadeViewOut(View view, final Runnable onEnd) {
        fadeView(view, 1.0f, 0.0f, ANIM_DURATION_SHORT, false, onEnd);
    }
    public static void fadeView(View view, float fromAlpha, float toAlpha, int duration, boolean fillAfter, final Runnable onEnd) {
        fadeView(view, fromAlpha, toAlpha, duration, fillAfter, new AccelerateDecelerateInterpolator(), onEnd);
    }
    public static void fadeView(View view, float fromAlpha, float toAlpha, int duration, boolean fillAfter, Interpolator interplator, final Runnable onEnd) {
        final AlphaAnimation anim = new AlphaAnimation(fromAlpha, toAlpha);
        anim.setDuration(duration);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
            @Override
            public void onAnimationEnd(Animation animation) {
                MainThread.runLater(onEnd);
            }
        });
        anim.setInterpolator(interplator);
        anim.setFillEnabled(fillAfter);
        anim.setFillAfter(fillAfter);
        view.startAnimation(anim);
    }
    // ### 动画相关

}
