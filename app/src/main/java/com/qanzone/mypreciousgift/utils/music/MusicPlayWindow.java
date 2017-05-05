package com.qanzone.mypreciousgift.utils.music;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by 10243 on 2017/4/30.
 */

public class MusicPlayWindow implements MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener{

    private Context mContext;
    private MusicPlayerListener mListener;
    private MediaPlayer mPlayer;
    private boolean isResourceOk = false;
    public MusicPlayWindow(Context con, MusicPlayerListener listener) {
        mListener = listener;
        mContext = con;
        mPlayer = new MediaPlayer();

    }

    public void startPlay(int url) {

        if (!isResourceOk) {
            try {
                mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                Uri setDataSourceuri = Uri.parse("android.resource://com.qanzone.mypreciousgift/"+url);
                mPlayer.setDataSource(mContext, setDataSourceuri);
                mPlayer.prepareAsync();

                mPlayer.setOnErrorListener(this);
                mPlayer.setOnPreparedListener(this);
                mPlayer.setOnCompletionListener(this);
                isResourceOk = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            if (mPlayer.isPlaying()) {
                pausePlay();
            } else {
                resumePlay();
            }
        }
    }

//    暂停播放
    public void pausePlay() {
        if (mPlayer != null && mPlayer.isPlaying()) {
            mPlayer.pause();
            if (mListener != null) mListener.onPause();
        }
    }

//    恢复播放
    public void resumePlay() {
        if (mPlayer != null) {
            mPlayer.start();
            if (mListener != null) mListener.onResume();
        }
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        mPlayer.start();
        if (mListener != null) mListener.onComplete();
    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
        dealloc();
        if (mListener != null) mListener.onError();
        return true;
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        mPlayer.start();
        if (mListener != null) mListener.onPrepare();
    }

    public int getDuration() {
        return mPlayer.getDuration();
    }

    public int getCurrentPosition() {
        return mPlayer.getCurrentPosition();
    }

    public void dealloc() {
        mPlayer.stop();
        mPlayer.release();
    }

    //播放对应进度条的进度
    public void seekTo(int progress) {
        if (mPlayer != null) {
            mPlayer.seekTo(progress);
        }
    }
}
