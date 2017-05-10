package com.qanzone.mypreciousgift.fragment;


import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qanzone.mypreciousgift.R;
import com.qanzone.mypreciousgift.base.BaseFragment;
import com.qanzone.mypreciousgift.bean.SearchingBean;
import com.qanzone.mypreciousgift.utils.PublicFunc;
import com.qanzone.mypreciousgift.view.CircleImageView;
import com.qanzone.mypreciousgift.view.GradationScrollView;
import com.qanzone.mypreciousgift.view.NoScrollListview;
import com.qanzone.mypreciousgift.view.WaveSideBar;

import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xmf on 2017/3/20.
 */

public class ExtraFragment extends BaseFragment implements GradationScrollView.ScrollViewListener {

    @BindView(R.id.iv_banner)
    ImageView ivBanner;
    @BindView(R.id.iv_head)
    CircleImageView ivHead;
    @BindView(R.id.listview)
    NoScrollListview listview;
    @BindView(R.id.my_scrollerview)
    GradationScrollView myScrollerview;
    @BindView(R.id.title)
    TextView title;
    @BindArray(R.array.data)
    String[] data;
    @BindView(R.id.tanlayout)
    TabLayout tablayout;
    @BindView(R.id.side_bar)
    WaveSideBar mWaveSideBar;

    private int ivBannerHeight;
    private View inflate;
    private SearchingAdapter mTabAdapter;

    @Override
    protected View initFragmentView() {
        inflate = LayoutInflater.from(mContext).inflate(R.layout.pager_extra, null);
        return inflate;
    }

    @Override
    protected void initFragmentData() {

        ViewTreeObserver vto = ivBanner.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                ivBannerHeight = ivBanner.getHeight();
                myScrollerview.setScrollViewListener(ExtraFragment.this);
                ivBanner.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

        tablayout.addTab(tablayout.newTab().setText("看书"), true);
        tablayout.addTab(tablayout.newTab().setText("图片"), false);
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    //选中第一个
                    mTabAdapter.update(SearchingBean.getJapaneseContacts());
                    mWaveSideBar.setIndexItems("あ", "か", "さ", "た", "な", "は", "ま", "や", "ら", "わ");

                } else {
                    //选中第二个
                    mTabAdapter.update(SearchingBean.getEnglishContacts());
                    mWaveSideBar.setIndexItems("A", "B", "C", "D", "E", "F", "G", "H", "I",
                            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mTabAdapter = new SearchingAdapter(SearchingBean.getJapaneseContacts());
        listview.setAdapter(mTabAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PublicFunc.showMsg(mContext, "暂未开发...");
            }
        });
        mWaveSideBar.setIndexItems("あ", "か", "さ", "た", "な", "は", "ま", "や", "ら", "わ");
//        mWaveSideBar.setOnSelectIndexItemListener(new WaveSideBar.OnSelectIndexItemListener() {
//            @Override
//            public void onSelectIndexItem(String index) {
//                for (int i=0; i<mTabAdapter.getDataSource().size(); i++) {
//                    if (mTabAdapter.getDataSource().get(i).getIndex().equals(index)) {
//                        final int finalI = i;
//                        listview.post(new Runnable() {
//
//                            @Override
//                            public void run() {
//                                listview.smoothScrollToPosition(finalI);
//                            }
//                        });
//                        Log.i("xmf", " index == " + i);
//                        return;
//                    }
//                }
//            }
//        });
    }


    @Override
    public void onScrollChanged(GradationScrollView scrollView, int x, int y, int oldx, int oldy) {
        if (y <= 0) {   //设置标题的背景颜色
            title.setBackgroundColor(Color.argb((int) 0, 144, 151, 166));
        } else if (y > 0 && y <= ivBannerHeight) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
            float scale = (float) y / ivBannerHeight;
            float alpha = (255 * scale);
            title.setTextColor(Color.argb((int) alpha, 255, 255, 255));
            title.setBackgroundColor(Color.argb((int) alpha, 245, 124, 0));
        } else {    //滑动到banner下面设置普通颜色
            title.setBackgroundColor(Color.argb((int) 255, 245, 124, 0));
        }
    }


    public ExtraFragment() {
        super();
    }

    public ExtraFragment(Context context) {
        super(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    private class SearchingAdapter extends BaseAdapter {
        private List<SearchingBean> searchs;

        public List<SearchingBean> getDataSource() {
            return searchs;
        }

        public SearchingAdapter(List<SearchingBean> searchs) {
            this.searchs = searchs;
        }

        @Override
        public int getCount() {
            return searchs.size();
        }

        @Override
        public Object getItem(int i) {
            return searchs.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        public void update(List<SearchingBean> searchs) {
            this.searchs = searchs;
            notifyDataSetChanged();
        }

        @Override
        public View getView(int index, View oldView, ViewGroup parentView) {
            SearchingViewHolder holder;
            if (oldView != null && oldView.getId() == R.id.reading__searching_root_view) {
                holder = (SearchingViewHolder) oldView.getTag();
            } else {
                oldView = LayoutInflater.from(mContext).inflate(R.layout.item_extra_listitem, parentView, false);
                holder = new SearchingViewHolder(oldView);
                oldView.setTag(holder);
            }

            SearchingBean search = searchs.get(index);
            if (index == 0 || !searchs.get(index - 1).getIndex().equals(search.getIndex())) {
                holder.tvIndex.setVisibility(View.VISIBLE);
                holder.index_divider.setVisibility(View.VISIBLE);
                holder.tvIndex.setText(search.getIndex());
            } else {
                holder.tvIndex.setVisibility(View.GONE);
                holder.index_divider.setVisibility(View.GONE);
            }

            holder.tvName.setText(search.getName());

            return oldView;
        }

        class SearchingViewHolder {
            public TextView tvIndex;
            public TextView tvName;
            public ImageView index_divider;
            public ImageView name_divider;

            public SearchingViewHolder(View itemView) {
                tvIndex = (TextView) itemView.findViewById(R.id.tv_index);
                tvName = (TextView) itemView.findViewById(R.id.tv_name);
                index_divider = (ImageView) itemView.findViewById(R.id.index_divider);
                name_divider = (ImageView) itemView.findViewById(R.id.name_divider);
            }
        }
    }


}
