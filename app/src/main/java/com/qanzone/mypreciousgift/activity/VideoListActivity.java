package com.qanzone.mypreciousgift.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.qanzone.mypreciousgift.R;
import com.qanzone.mypreciousgift.bean.SearchingBean;
import com.qanzone.mypreciousgift.bean.VideoBean;
import com.qanzone.mypreciousgift.fragment.ExtraFragment;
import com.qanzone.mypreciousgift.utils.ConstantKey;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VideoListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.video_list)
    ListView videoList;
    @BindView(R.id.activity_video_list)
    LinearLayout activityVideoList;
    public static final String INTENT_TV = "TV直播";
    public static final String INTENT_LUNBO = "轮播台";
    private Boolean mIsLive;
    private VideoAdapter mVideoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);
        ButterKnife.bind(this);


        String stringExtra = getIntent().getStringExtra(ConstantKey.VIDEOLIST_TYPE);
        if (stringExtra.isEmpty()) finish();

        titleText.setText(stringExtra);


        if (INTENT_TV.equals(stringExtra)) {
            mVideoAdapter = new VideoAdapter(VideoBean.getTvList());
            mIsLive = false;

        } else if (INTENT_LUNBO.equals(stringExtra)) {
            mVideoAdapter = new VideoAdapter(VideoBean.getLunBoList());
            mIsLive = true;
        }
        videoList.setAdapter(mVideoAdapter);
        videoList.setOnItemClickListener(this);
    }

    @OnClick(R.id.title_back)
        void back() {
            finish();
        }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
        VideoBean videoBean = mVideoAdapter.getDataSource().get(index);
        Intent intent = new Intent(this, PlayerActivity.class);
        Bundle b = new Bundle();
        b.putBoolean(ConstantKey.INTENT_BOOLEAN, mIsLive);
        b.putSerializable(ConstantKey.INTENT_SERIALIZABLE, videoBean);
        intent.putExtra(ConstantKey.INTENT_ACTIVITY, b);
        startActivity(intent);

    }

    private class VideoAdapter extends BaseAdapter{

        private List<VideoBean> videoDatas;

        public List<VideoBean> getDataSource() {
            return videoDatas;
        }

        public VideoAdapter(List<VideoBean> videoDatas) {
            this.videoDatas = videoDatas;
        }

        @Override
        public int getCount() {
            return videoDatas.size();
        }

        @Override
        public Object getItem(int i) {
            return videoDatas.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        public void update(List<VideoBean> videoDatas) {
            this.videoDatas = videoDatas;
            notifyDataSetChanged();
        }

        @Override
        public View getView(int index, View oldView, ViewGroup parentView) {
            VideoListViewHolder holder;
            if (oldView != null && oldView.getId() == R.id.item_video_list) {
                holder = (VideoListViewHolder) oldView.getTag();
            } else {
                oldView = LayoutInflater.from(VideoListActivity.this).inflate(R.layout.item_video_play, parentView, false);
                holder = new VideoListViewHolder(oldView);
                oldView.setTag(holder);
            }


            holder.videoName.setText(videoDatas.get(index).getVideo_name());

            return oldView;
        }

        class VideoListViewHolder {
            public TextView videoName;


            public VideoListViewHolder(View itemView) {
                videoName = (TextView) itemView.findViewById(R.id.video_name);
            }
        }
    }

}
