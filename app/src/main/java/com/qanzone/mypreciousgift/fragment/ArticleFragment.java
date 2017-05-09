package com.qanzone.mypreciousgift.fragment;

import android.content.Context;
import android.graphics.Color;
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
    String test_article = "<p>\\u8fd9\\u4e2a\\u6545\\u4e8b\\u662f\\u963f\\u62c9\\u4f2f\\u5386\\u53f2\\u5b66\\u5bb6\\u963f\\u91cc\\u00b7\\u4f0a\\u590f\\u5409\\u5728\\u54c8\\u91cc\\u53d1\\u963f\\u91cc\\u00b7\\u9a6c\\u59c6\\u6069\\uff08\\u516c\\u5143786-833\\u5e74\\uff09\\u5728\\u4f4d\\u7684\\u65f6\\u5019\\u8bb2\\u7684\\uff1a<\\/p><p>\\u6709\\u4e9b\\u503c\\u5f97\\u4fe1\\u4efb\\u7684\\u4eba\\u66fe\\u7ecf\\u5728\\u6587\\u5b57\\u8bb0\\u8f7d\\u4e2d\\u8bf4\\uff08\\u4f46\\u662f\\u53ea\\u6709\\u5b89\\u62c9\\u662f\\u5168\\u77e5\\u5168\\u80fd\\u800c\\u4e14\\u4e0d\\u7761\\u89c9\\u7684\\uff09\\uff0c\\u4ece\\u524d\\u5728\\u5f00\\u7f57\\u6709\\u4e00\\u4e2a\\u4eba\\uff0c\\u62e5\\u6709\\u5de8\\u989d\\u8d22\\u5bcc\\uff0c\\u7136\\u800c\\u51fa\\u624b\\u5f88\\u677e\\uff0c\\u751f\\u6d3b\\u653e\\u8361\\uff0c\\u4ee5\\u81f4\\u5bb6\\u4ea7\\u8361\\u5c3d\\uff0c\\u53ea\\u5269\\u4e0b\\u7236\\u4eb2\\u9057\\u7559\\u4e0b\\u7684\\u623f\\u5b50\\u3002\\u8fc7\\u4e86\\u4e0d\\u4e45\\uff0c\\u4ed6\\u4e0d\\u5f97\\u4e0d\\u9760\\u52b3\\u4f5c\\u8c0b\\u751f\\u3002\\u4ed6\\u5e72\\u6d3b\\u90a3\\u4e48\\u8f9b\\u82e6\\uff0c\\u6709\\u4e00\\u5929\\u665a\\u4e0a\\u4e0d\\u514d\\u5728\\u81ea\\u5df1\\u7684\\u82b1\\u56ed\\u91cc\\u7684\\u4e00\\u682a\\u65e0\\u82b1\\u679c\\u6811\\u4e0b\\u7761\\u7740\\u4e86\\uff0c\\u505a\\u8d77\\u68a6\\u6765\\u3002\\u68a6\\u4e2d\\uff0c\\u6709\\u4e00\\u4e2a\\u4eba\\u6765\\u62dc\\u8bbf\\u4ed6\\uff0c\\u90a3\\u4eba\\u6d51\\u8eab\\u6e7f\\u900f\\u53c8\\u6e7f\\u900f\\uff0c\\u4ece\\u5634\\u91cc\\u62ff\\u51fa\\u4e00\\u679a\\u91d1\\u5e01\\uff0c\\u5bf9\\u4ed6\\u8bf4\\uff1a\\u201c\\u60a8\\u7684\\u8d22\\u5bcc\\u5728\\u6ce2\\u65af\\uff0c\\u5728\\u4f0a\\u65af\\u6cd5\\u7f55\\uff0c\\u5230\\u90a3\\u91cc\\u53bb\\u5bfb\\u627e\\u5427\\u3002\\u201d<\\/p><p>\\u7b2c\\u4e8c\\u5929\\u4e00\\u65e9\\uff0c\\u8fd9\\u4e2a\\u4eba\\u9192\\u6765\\u5c31\\u51fa\\u53d1\\u4e86\\u3002\\u4ed6\\u957f\\u9014\\u8dcb\\u6d89\\uff0c\\u9047\\u5230\\u4e86\\u6c99\\u6f20\\u3001\\u6d77\\u6d0b\\u3001\\u76d7\\u532a\\u3001\\u5076\\u50cf\\u5d07\\u62dc\\u8005\\uff0c\\u6cb3\\u5ddd\\u3001\\u91ce\\u517d\\uff0c\\u4ee5\\u53ca\\u4eba\\u7c7b\\u7684\\u79cd\\u79cd\\u5371\\u9669\\u3002\\u7ec8\\u4e8e\\u5230\\u4e86\\u4f0a\\u65af\\u6cd5\\u7f55\\uff0c\\u4f46\\u662f\\u4ed6\\u4e00\\u8fdb\\u57ce\\u95e8\\uff0c\\u5929\\u5c31\\u9ed1\\u4e86\\u4e0b\\u6765\\u3002\\u4ed6\\u8d70\\u8fdb\\u4e86\\u4e00\\u5ea7\\u6e05\\u771f\\u5bfa\\uff0c\\u5728\\u9662\\u5b50\\u91cc\\u8eba\\u4e0b\\u6765\\u7761\\u89c9\\u3002\\u6e05\\u771f\\u5bfa\\u6709\\u4e00\\u5ea7\\u623f\\u5b50\\u3002\\u7531\\u4e8e\\u5168\\u77e5\\u5168\\u80fd\\u7684\\u5b89\\u62c9\\u7684\\u5b89\\u6392\\uff0c\\u6709\\u4e00\\u5e2e\\u76d7\\u532a\\u8fdb\\u4e86\\u6e05\\u771f\\u5bfa\\uff0c\\u7136\\u540e\\u4ece\\u8fd9\\u91cc\\u95ef\\u8fdb\\u9694\\u58c1\\u7684\\u623f\\u5b50\\u3002\\u4f46\\u662f\\u76d7\\u532a\\u7684\\u58f0\\u97f3\\u60ca\\u52a8\\u4e86\\u623f\\u5b50\\u7684\\u4e3b\\u4eba\\uff0c\\u4ed6\\u9192\\u4e86\\u8fc7\\u6765\\uff0c\\u5927\\u58f0\\u547c\\u6551\\uff0c\\u5de1\\u903b\\u961f\\u961f\\u957f\\u7ec8\\u4e8e\\u7387\\u9886\\u5b98\\u5175\\u6765\\u5230\\uff0c\\u628a\\u76d7\\u532a\\u5413\\u5f97\\u722c\\u4e0a\\u5c4b\\u9876\\u9003\\u4e4b\\u592d\\u592d\\u3002\\u961f\\u957f\\u547d\\u4ee4\\u5728\\u6e05\\u771f\\u5bfa\\u641c\\u67e5\\uff0c\\u53d1\\u73b0\\u4e86\\u8fd9\\u4e2a\\u5f00\\u7f57\\u6765\\u7684\\u4eba\\uff0c\\u628a\\u4ed6\\u7528\\u7af9\\u97ad\\u4e00\\u987f\\u597d\\u6253\\uff0c\\u51e0\\u4e4e\\u6253\\u5f97\\u4ed6\\u65ad\\u4e86\\u6c14\\u3002<\\/p><p>\\u4e24\\u5929\\u540e\\uff0c\\u4ed6\\u5728\\u76d1\\u72f1\\u91cc\\u82cf\\u9192\\u8fc7\\u6765\\u3002\\u961f\\u957f\\u628a\\u4ed6\\u53eb\\u53bb\\uff0c\\u95ee\\u4ed6\\uff1a\\u201c\\u4f60\\u662f\\u8c01\\uff0c\\u4ece\\u54ea\\u6765\\u7684\\uff1f\\u201d<\\/p><p>\\u8fd9\\u4e2a\\u4eba\\u8bf4\\uff1a\\u201c\\u6211\\u4ece\\u77e5\\u540d\\u7684\\u57ce\\u5e02\\u5f00\\u7f57\\u6765\\u7684\\uff0c\\u6211\\u540d\\u5b57\\u53eb\\u7a46\\u7f55\\u7a46\\u5fb7\\u00b7\\u963f\\u91cc\\u00b7\\u9a6c\\u683c\\u91cc\\u6bd4\\u3002\\u201d\\u961f\\u957f\\u95ee\\u4ed6\\uff1a\\u201c\\u4f60\\u4e3a\\u4ec0\\u4e48\\u5230\\u4f0a\\u65af\\u6cd5\\u7f55\\u6765\\uff1f\\u201d\\u8fd9\\u4e2a\\u4eba\\u60f3\\uff0c\\u8fd8\\u4e0d\\u5982\\u8bf4\\u5b9e\\u8bdd\\u7684\\u597d\\uff0c\\u5c31\\u5bf9\\u961f\\u957f\\u8bf4\\uff1a\\u201c\\u6211\\u662f\\u88ab\\u68a6\\u4e2d\\u7684\\u4e00\\u4e2a\\u4eba\\u6240\\u6307\\u5f15\\uff0c\\u5230\\u4f0a\\u65af\\u7f55\\u6765\\u7684\\uff0c\\u56e0\\u4e3a\\u4ed6\\u8bf4\\u6211\\u7684\\u8d22\\u5bcc\\u5728\\u8fd9\\u91cc\\u7b49\\u7740\\u6211\\u3002\\u53ef\\u662f\\u7b49\\u6211\\u5230\\u4e86\\u4f0a\\u65af\\u6cd5\\u7f55\\uff0c\\u4ed6\\u6240\\u8bf4\\u7684\\u8d22\\u5bcc\\uff0c\\u5374\\u539f\\u6765\\u662f\\u4f60\\u90a3\\u4e48\\u6177\\u6168\\u5730\\u8d50\\u7ed9\\u6211\\u7684\\u4e00\\u987f\\u97ad\\u5b50\\u3002\\u201d<\\/p><p>\\u961f\\u957f\\u542c\\u4e86\\uff0c\\u7981\\u4e0d\\u4f4f\\u54c8\\u54c8\\u5927\\u7b11\\uff0c\\u7b11\\u5f97\\u5634\\u91cc\\u7684\\u81fc\\u9f7f\\u90fd\\u9732\\u4e86\\u51fa\\u6765\\u3002\\u6700\\u540e\\uff0c\\u4ed6\\u8bf4\\uff1a\\u201c\\u554a\\u54df\\uff0c\\u4f60\\u8fd9\\u4e2a\\u592a\\u4e0d\\u806a\\u654f\\u7684\\u4eba\\u5440\\uff0c\\u6211\\u63a5\\u8fde\\u4e09\\u6b21\\u68a6\\u89c1\\u5f00\\u7f57\\u7684\\u4e00\\u5ea7\\u623f\\u5b50\\uff0c\\u5b83\\u7684\\u5ead\\u9662\\u91cc\\u6709\\u4e00\\u4e2a\\u82b1\\u56ed\\uff0c\\u82b1\\u56ed\\u5f80\\u4e0b\\u659c\\u7684\\u4e00\\u5934\\u6709\\u4e00\\u5ea7\\u65e5\\u6677\\uff0c\\u8d70\\u8fc7\\u65e5\\u6677\\u6709\\u4e00\\u682a\\u65e0\\u82b1\\u679c\\u6811\\uff0c\\u8d70\\u8fc7\\u65e0\\u82b1\\u679c\\u6811\\u6709\\u4e00\\u4e2a\\u55b7\\u6cc9\\uff0c\\u55b7\\u6cc9\\u5e95\\u4e0b\\u57cb\\u7740\\u4e00\\u5927\\u5806\\u94b1\\u3002\\u53ef\\u662f\\u6211\\u4ece\\u6765\\u6ca1\\u6709\\u53bb\\u7406\\u4f1a\\u8fd9\\u4e9b\\u8352\\u8bde\\u7684\\u68a6\\u5146\\uff0c\\u7136\\u800c\\u4f60\\u554a\\uff0c\\u4f60\\u8fd9\\u4e2a\\u6bdb\\u9a74\\u548c\\u9b54\\u9b3c\\u517b\\u7684\\u5bb6\\u4f19\\uff0c\\u7adf\\u7136\\u76f8\\u4fe1\\u4e00\\u4e2a\\u68a6\\uff0c\\u8d70\\u4e86\\u8fd9\\u4e48\\u591a\\u8def\\uff0c\\u4e0d\\u51c6\\u4f60\\u518d\\u5728\\u4f0a\\u65af\\u6cd5\\u7f55\\u9732\\u9762\\u4e86\\u3002\\u628a\\u8fd9\\u51e0\\u4e2a\\u5c0f\\u94b1\\u62ff\\u53bb\\uff0c\\u6eda\\u5427\\uff01\\u201d<\\/p><p>\\u8fd9\\u4e2a\\u4eba\\u62ff\\u4e0a\\u4e86\\u94b1\\uff0c\\u8d70\\u4e0a\\u4e86\\u56de\\u5bb6\\u7684\\u65c5\\u7a0b\\u3002\\u4ed6\\u5728\\u4ed6\\u7684\\u82b1\\u56ed\\uff08\\u5c31\\u662f\\u961f\\u957f\\u68a6\\u89c1\\u7684\\u90a3\\u4e2a\\u82b1\\u56ed\\uff09\\u7684\\u55b7\\u6cc9\\u4e0b\\u9762\\u6316\\u51fa\\u4e86\\u4e00\\u5927\\u7b14\\u8d22\\u5b9d\\uff0c\\u5b89\\u62c9\\u5c31\\u662f\\u8fd9\\u6837\\u5927\\u91cf\\u5730\\u8d50\\u798f\\u7ed9\\u4ed6\\uff0c\\u62a5\\u507f\\u4e86\\u4ed6\\uff0c\\u62ac\\u4e3e\\u4e86\\u4ed6\\u3002\\u5b89\\u62c9\\u662f\\u6148\\u60b2\\u4e3a\\u6000\\u7684\\uff0c\\u5b89\\u62c9\\u662f\\u65e0\\u6240\\u4e0d\\u5728\\u7684\\u3002<\\/p>";
    @BindView(R.id.article_author)
    TextView articleAuthor;
    @BindView(R.id.article_content)
    TextView articleContent;
    @BindView(R.id.load_data_error)
    RelativeLayout loadDataError;
    @BindView(R.id.progressbar)
    ProgressBar progressbar;

    @Override
    protected View initFragmentView() {
        return LayoutInflater.from(mContext).inflate(R.layout.pager_article, null);
    }

    @Override
    protected void initFragmentData() {

        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);


        xxx();
        loadDataError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
            }
        });
    }

    private void xxx() {
        getArticleData(today_article_url, new Listener() {
            @Override
            public void success(final String title, final String content, final String author) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        collapsingToolbarLayout.setTitle(title);
                        articleAuthor.setText(author);
                        String replace = content.replace("<p>", "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<p>");
                        String replace1 = replace.replace("</p>", "</p><br /><br />");
                        articleContent.setText(Html.fromHtml(replace1));
                    }
                });
            }

            @Override
            public void faile() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loadDataError.setVisibility(View.VISIBLE);
                        PublicFunc.fadeViewIn(loadDataError, null);
                    }
                });
            }
        });
    }
}

