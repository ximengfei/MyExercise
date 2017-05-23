package com.qanzone.mypreciousgift.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.qanzone.mypreciousgift.R;
import com.qanzone.mypreciousgift.adapter.VideoAdapter;
import com.qanzone.mypreciousgift.bean.NetVideoData;
import com.qanzone.mypreciousgift.bean.VideoBean;
import com.qanzone.mypreciousgift.utils.ConstantKey;
import com.qanzone.mypreciousgift.utils.PublicFunc;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * 此页面区别于VideoListActivity,是从网络获取直播数据
 */
public class NetVideoListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, SearchView.OnQueryTextListener {

    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.net_video_list)
    ListView netVideoList;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.activity_video_list)
    LinearLayout activityVideoList;
    @BindView(R.id.load_data_error)
    RelativeLayout loadDataError;
    private Context mContext;
    private VideoAdapter mVideoAdapter;
    private SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_video_list);
        ButterKnife.bind(this);

        mContext = this;
        //返回按钮的设置
        toolBar.setNavigationIcon(R.drawable.back);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //设置标题和menu
        toolBar.setTitle("电视直播");
        toolBar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolBar);

        //下拉刷新的相关设置
        refreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.yellow);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 请求数据 完成之后 调用结束刷新方法
                queryNetVideoList();

            }
        });

        //ListView的相关设置
        List<NetVideoData> emptyDate = new ArrayList<>();
        mVideoAdapter = new VideoAdapter(mContext, emptyDate);
        netVideoList.setAdapter(mVideoAdapter);
        netVideoList.setOnItemClickListener(this);
//        netVideoList.setTextFilterEnabled(true);
        //下拉刷新和list view的冲突解决
        netVideoList.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem == 0)
                {
                    refreshLayout.setEnabled(true);
                }   else {
                    refreshLayout.setEnabled(false);
                }

            }
        });

        refreshLayout.setRefreshing(true);
        //联网获取数据
        queryNetVideoList();
    }

    private void queryNetVideoList() {
        BmobQuery<NetVideoData> query = new BmobQuery<NetVideoData>();
        query.addWhereEqualTo("videoType", 1);
        query.findObjects(new FindListener<NetVideoData>() {
            @Override
            public void done(List<NetVideoData> object, BmobException e) {
                refreshLayout.setRefreshing(false);
                if(e==null){
                    if (loadDataError.getVisibility() == View.VISIBLE)
                        PublicFunc.fadeViewOut(loadDataError, new Runnable() {
                            @Override
                            public void run() {
                                loadDataError.setVisibility(View.GONE);
                            }
                        });

                    PublicFunc.showMsg(mContext, "查询成功：共"+object.size()+"条数据。Enjoy Time!!");

                    //数据获取成功！！！ 更新ListView
                    mVideoAdapter.update(object);

                }else{
                    PublicFunc.showMsg(mContext, "获取数据失败："+e.getMessage()+","+e.getErrorCode());
                    //显示错误页面
                    loadDataError.setVisibility(View.VISIBLE);
                    PublicFunc.fadeViewIn(loadDataError, null);
                }
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
        //坑爹啊，作茧自缚，播放页面不想改了，就按照以前的数据结构来写吧...
        NetVideoData netVideoData = mVideoAdapter.getDataSource().get(index);
        VideoBean videoBean = new VideoBean(netVideoData.getVideoName(), netVideoData.getVideoUrl(),
                netVideoData.getVideoHdUrl());
        Intent intent = new Intent(mContext, PlayerActivity.class);
        Bundle b = new Bundle();
        b.putBoolean(ConstantKey.INTENT_BOOLEAN, false);
        b.putSerializable(ConstantKey.INTENT_SERIALIZABLE, videoBean);
        intent.putExtra(ConstantKey.INTENT_ACTIVITY, b);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.base_toolbar_menu,menu);
        MenuItem menuItem=menu.findItem(R.id.action_search);//
        searchView= (SearchView) MenuItemCompat.getActionView(menuItem);//加载searchview
        searchView.setOnQueryTextListener(this);//为搜索框设置监听事件
        searchView.setSubmitButtonEnabled(true);//设置是否显示搜索按钮
        searchView.setQueryHint("search");//设置提示信息
        searchView.setIconifiedByDefault(true);//设置搜索默认为图标
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        if (!TextUtils.isEmpty((query))){
            //搜索结果页面
        }
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        return true;
    }
}
