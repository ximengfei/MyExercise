package com.qanzone.mypreciousgift.utils.music;

/**
 * Created by 10243 on 2017/4/30.
 */

public interface MusicPlayerListener {
    void onError();
    void onPrepare();
    void onComplete();
    void onPause();
    void onResume();
}
