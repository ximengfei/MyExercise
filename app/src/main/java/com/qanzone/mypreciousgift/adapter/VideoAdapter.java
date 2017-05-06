package com.qanzone.mypreciousgift.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.qanzone.mypreciousgift.R;
import com.qanzone.mypreciousgift.bean.NetVideoData;


import java.util.List;

/**
 * Created by 10243 on 2017/5/6.
 */

public class VideoAdapter extends BaseAdapter {

    private List<NetVideoData> videoDatas;
    private Context mContext;

    public List<NetVideoData> getDataSource() {
        return videoDatas;
    }

    public VideoAdapter(Context context, List<NetVideoData> videoDatas) {
        this.videoDatas = videoDatas;
        mContext = context;
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

    public void update(List<NetVideoData> videoDatas) {
        this.videoDatas = videoDatas;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int index, View oldView, ViewGroup parentView) {
        VideoListViewHolder holder;
        if (oldView != null && oldView.getId() == R.id.item_video_list) {
            holder = (VideoListViewHolder) oldView.getTag();
        } else {
            oldView = LayoutInflater.from(mContext).inflate(R.layout.item_video_play, parentView, false);
            holder = new VideoListViewHolder(oldView);
            oldView.setTag(holder);
        }


        holder.videoName.setText(videoDatas.get(index).getVideoName());

        return oldView;
    }

    class VideoListViewHolder {
        public TextView videoName;


        public VideoListViewHolder(View itemView) {
            videoName = (TextView) itemView.findViewById(R.id.video_name);
        }
    }
}
