package com.qanzone.mypreciousgift.fragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.qanzone.mypreciousgift.R;
import com.qanzone.mypreciousgift.base.BaseFragment;
import com.qanzone.mypreciousgift.utils.PublicFunc;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

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

public class ArticleFragment extends BaseFragment {
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    String today_article_url = "https://interface.meiriyiwen.com/article/today?dev=1";
    String random_iv_url = "https://link.zhihu.com/?target=https%3A//unsplash.it/800/400/%3Frandom";
    @BindView(R.id.article_author)
    TextView articleAuthor;
    @BindView(R.id.article_content)
    TextView articleContent;
    //第一次加载失败时的错误页面
    @BindView(R.id.load_data_error)
    RelativeLayout loadDataError;
    @BindView(R.id.progressbar)
    ProgressBar progressbar;

    boolean isCalling;

    @Override
    protected View initFragmentView() {
        return LayoutInflater.from(mContext).inflate(R.layout.pager_article, null);
    }

    @Override
    protected void initFragmentData() {

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

    public ArticleFragment(Context mContext) {
        super(mContext);
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
                goneProgress();

                if (listener != null) listener.faile();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                goneProgress();
                //子线程
                String str = response.body().string();
                try {
                    JSONObject jo = new JSONObject(str);
                    JSONObject data = jo.getJSONObject("data");
                    String author = data.getString("author");
                    String title = data.getString("title");
                    String content = data.getString("content");
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

    private void goneProgress() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressbar.setVisibility(View.GONE);
                new BitmapUtils(mContext).display(iv, random_iv_url);
            }
        });
    }

    private void xxx() {
        isCalling = true;
        getArticleData(today_article_url, new Listener() {
            @Override
            public void success(final String title, final String content, final String author) {
                getActivity().runOnUiThread(new Runnable() {
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

                        collapsingToolbarLayout.setTitle(title);
                        articleAuthor.setText(author);
                        String replace = content.replace("<p>", "<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
                        String replace1 = replace.replace("</p>", "</p><br />");
                        articleContent.setText(Html.fromHtml(replace1));
                    }
                });
            }

            @Override
            public void faile() {
                isCalling = false;
                getActivity().runOnUiThread(new Runnable() {
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

