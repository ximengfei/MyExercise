package com.qanzone.mypreciousgift.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qanzone.mypreciousgift.MainActivity;
import com.qanzone.mypreciousgift.R;
import com.qanzone.mypreciousgift.base.BaseFragment;
import com.qanzone.mypreciousgift.utils.PublicFunc;
import com.squareup.picasso.Picasso;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * https://link.zhihu.com/?target=https%3A//unsplash.it/800/400/%3Frandom 随机获取一张图片
 * https://interface.meiriyiwen.com/article/today?dev=1 获取今天的文章
 * https://interface.meiriyiwen.com/article/random?dev=1 随机的文章
 * https://interface.meiriyiwen.com/article/day?dev=1&date=20170216 特定某一天的文章
 * Created by xmf on 2017/5/9.
 */

public class ArticleFragment extends BaseFragment implements DatePickerDialog.OnDateSetListener {
    private final int REQUEST_SUCCESS = 10000;
    private final int REQUEST_FAILE = 10001;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    String today_article_url = "https://interface.meiriyiwen.com/article/today?dev=1";
    String random_iv_url = "https://unsplash.it/800/400/?random";
//    String random_iv_url = "https://link.zhihu.com/?target=https%3A//unsplash.it/800/400/%3Frandom";
    @BindView(R.id.article_author)
    TextView articleAuthor;
    @BindView(R.id.article_content)
    TextView articleContent;
    //第一次加载失败时的错误页面
    @BindView(R.id.load_data_error)
    RelativeLayout loadDataError;
    @BindView(R.id.progressbar)
    ProgressBar progressbar;
    private MainActivity mainActivity;

    boolean isCalling;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;
            progressbar.setVisibility(View.GONE);
            if (what == REQUEST_SUCCESS) {
            }
            else if (what == REQUEST_FAILE) {
                PublicFunc.showMsg(mContext, "获取失败");
            }
        }
    };
    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        monthOfYear = monthOfYear + 1;
        String month = "" + monthOfYear;
        String day = "" + dayOfMonth;
        if (monthOfYear <= 9) month = "0" + monthOfYear;
        if (dayOfMonth <= 9) day = "0" + dayOfMonth;
        yyy(RequestType.DATE_URL, "https://interface.meiriyiwen.com/article/day?dev=1&date=" + year + month + day);
        Log.e("xmf", "https://interface.meiriyiwen.com/article/day?dev=1&date=" + year + month + day);
    }


    public enum RequestType{
        RANDOM_URL, DATE_URL
    }
    @Override
    protected View initFragmentView() {
        return LayoutInflater.from(mContext).inflate(R.layout.pager_article, null);
    }

    @Override
    protected void initFragmentData() {
        toolbar.inflateMenu(R.menu.article_toolbar_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.action_random) {
                    if (isCalling) {
                        PublicFunc.showMsg(mContext, "已经在加载中了");
                        return false;
                    }
                    yyy(RequestType.RANDOM_URL, "https://interface.meiriyiwen.com/article/random?dev=1");
                }
                else if (itemId == R.id.action_calendar) {
                    showCalendar();

                }
                return true;
            }


        });
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);

        //设置字体
//        Typeface face = Typeface.createFromAsset(mContext.getAssets(), "font/songti.ttf");
//        articleContent.setTypeface(face);

        xxx();
        loadDataError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isCalling) return;
                xxx();
            }
        });


    }

    private void showCalendar() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.setAccentColor(getResources().getColor(R.color.colorPrimaryDark));
        dpd.show(getActivity().getFragmentManager(), "Datepickerdialog");
    }

    private void yyy(RequestType type, String url) {
        isCalling = true;
        getArticleData(url, new Listener() {
            @Override
            public void success(String title, String content, String author) {
                isCalling = false;
            }

            @Override
            public void faile() {
                isCalling = false;

            }
        });
    }
    public ArticleFragment(Context context) {
        super(context);
        mainActivity = (MainActivity) mContext;
    }

    public ArticleFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    private void getArticleData(String url, final Listener listener) {
//        https://unsplash.it/600/400/?random
        Random random = new Random();
        int wid = 900 - random.nextInt(201);
        int hid = 500 - random.nextInt(201);
        Picasso.with(mContext).load("https://unsplash.it/"+wid + "/"+hid +"/?random").placeholder(R.drawable.default_extra_header).into(iv);
        progressbar.setVisibility(View.VISIBLE);
        OkHttpClient mOkHttpClient = new OkHttpClient();
        Request.Builder requestBuilder = new Request.Builder().url(url);
        //可以省略，默认是GET请求
        requestBuilder.method("GET", null);
        Request request = requestBuilder.build();
        Call mcall = mOkHttpClient.newCall(request);
        mcall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mHandler.sendEmptyMessage(REQUEST_FAILE);
                if (listener != null) listener.faile();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                mHandler.sendEmptyMessage(REQUEST_SUCCESS);

                //子线程
                String str = response.body().string();
                try {
                    JSONObject jo = new JSONObject(str);
                    JSONObject data = jo.getJSONObject("data");
                    final String author = data.getString("author");
                    final String title = data.getString("title");
                    final String content = data.getString("content");

                    mainActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            collapsingToolbarLayout.setTitle(title);
                            articleAuthor.setText(author);
                            String replace = content.replace("<p>", "<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
//                            String replace1 = replace.replace("</p>", "</p><br />");
                            articleContent.setText(Html.fromHtml(replace));
                        }
                    });
                    if (listener != null) listener.success(title, content, author);
                } catch (JSONException e) {
                    if (listener != null) listener.faile();
                    e.printStackTrace();
                }
            }
        });
    }

    public static interface Listener {
        void success(String title, String content, String author);
        void faile();
    }


    private void xxx() {
        isCalling = true;

        getArticleData(today_article_url, new Listener() {
            @Override
            public void success(final String title, final String content, final String author) {
                mainActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        isCalling = false;
                        if (loadDataError.getVisibility() == View.VISIBLE)
                            PublicFunc.fadeViewOut(loadDataError, new Runnable() {

                                @Override
                                public void run() {
                                loadDataError.setVisibility(View.GONE);

                                }
                            });


                    }
                });
            }

            @Override
            public void faile() {
                isCalling = false;
                mainActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (loadDataError.getVisibility() == View.GONE) {
                            loadDataError.setVisibility(View.VISIBLE);
                            PublicFunc.fadeViewIn(loadDataError, null);
                        }
                    }
                });
            }
        });
    }
}

