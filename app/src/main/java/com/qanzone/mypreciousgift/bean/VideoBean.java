package com.qanzone.mypreciousgift.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 10243 on 2017/4/23.
 */

public class VideoBean implements Serializable{
    private String video_name;
    private String video_url;

    public VideoBean(String video_name, String video_url) {
        this.video_name = video_name;
        this.video_url = video_url;
    }

    public String getVideo_name() {
        return video_name;
    }

    public void setVideo_name(String video_name) {
        this.video_name = video_name;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    //tv的数据集合
    public static List<VideoBean> getTvList() {
        ArrayList<VideoBean> tvList = new ArrayList<>();

        tvList.add(new VideoBean("浙江", "http://ivi.bupt.edu.cn/hls/zjhd.m3u8"));
        tvList.add(new VideoBean("CCTV1综合", "http://14.18.17.142:9009/live/chid=001"));
        tvList.add(new VideoBean("CCTV2财经", "http://14.18.17.142:9009/live/chid=002"));
        tvList.add(new VideoBean("CCTV3综艺", "http://14.18.17.142:9009/live/chid=008"));
        tvList.add(new VideoBean("CCTV4中文国际", "http://14.18.17.142:9009/live/chid=014"));
        tvList.add(new VideoBean("CCTV5体育", "http://14.18.17.142:9009/live/chid=009"));
        tvList.add(new VideoBean("CCTV6电影", "http://14.18.17.142:9009/live/chid=010"));
        tvList.add(new VideoBean("CCTV7军事农业", "http://14.18.17.142:9009/live/chid=003"));
        tvList.add(new VideoBean("CCTV8电视剧", "http://14.18.17.142:9009/live/chid=011"));
        tvList.add(new VideoBean("CCTV9记录", "http://14.18.17.142:9009/live/chid=015"));
        tvList.add(new VideoBean("CCTV10科教", "http://14.18.17.142:9009/live/chid=004"));
        tvList.add(new VideoBean("CCTV11戏曲", "http://14.18.17.142:9009/live/chid=005"));
        tvList.add(new VideoBean("CCTV12社会与法", "http://14.18.17.142:9009/live/chid=006"));
        tvList.add(new VideoBean("CCTV13新闻", "http://14.18.17.142:9009/live/chid=013"));
        tvList.add(new VideoBean("CCTV14少儿", "http://14.18.17.142:9009/live/chid=012"));
        tvList.add(new VideoBean("CCTV15音乐", "http://14.18.17.142:9009/live/chid=007"));
        tvList.add(new VideoBean("湖北综合", "http://59.175.153.187/channels/zbk/hbtv/flv:sd/live"));
        tvList.add(new VideoBean("湖南电视剧", "http://hlstest.imgo.tv/hva7/index_512k.m3u8"));
        tvList.add(new VideoBean("贵州卫视", "http://14.18.17.142:9009/live/chid=016"));
        tvList.add(new VideoBean("四川卫视", "http://14.18.17.142:9009/live/chid=017"));
        tvList.add(new VideoBean("湖南卫视", "http://14.18.17.142:9009/live/chid=018"));
        tvList.add(new VideoBean("广西卫视", "http://14.18.17.142:9009/live/chid=019"));
        tvList.add(new VideoBean("陕西卫视", "http://14.18.17.142:9009/live/chid=021"));
        tvList.add(new VideoBean("安徽卫视", "http://14.18.17.142:9009/live/chid=022"));
        tvList.add(new VideoBean("江苏卫视", "http://14.18.17.142:9009/live/chid=023"));
        tvList.add(new VideoBean("黑龙江卫视", "http://14.18.17.142:9009/live/chid=024"));
        tvList.add(new VideoBean("山东卫视", "http://14.18.17.142:9009/live/chid=025"));
        tvList.add(new VideoBean("吉林卫视", "http://14.18.17.142:9009/live/chid=026"));
        tvList.add(new VideoBean("天津卫视", "http://14.18.17.142:9009/live/chid=027"));
        tvList.add(new VideoBean("河南卫视", "http://14.18.17.142:9009/live/chid=028"));
        tvList.add(new VideoBean("北京卫视", "http://14.18.17.142:9009/live/chid=029"));
        tvList.add(new VideoBean("东南卫视", "http://14.18.17.142:9009/live/chid=030"));
        tvList.add(new VideoBean("江西卫视", "http://14.18.17.142:9009/live/chid=031"));
        tvList.add(new VideoBean("内蒙古卫视", "http://14.18.17.142:9009/live/chid=032"));
        tvList.add(new VideoBean("重庆卫视", "http://14.18.17.142:9009/live/chid=033"));
        tvList.add(new VideoBean("云南卫视", "http://14.18.17.142:9009/live/chid=034"));
        tvList.add(new VideoBean("广东卫视", "http://14.18.17.142:9009/live/chid=035"));
        tvList.add(new VideoBean("深圳卫视", "http://14.18.17.142:9009/live/chid=036"));
        tvList.add(new VideoBean("浙江卫视", "http://14.18.17.142:9009/live/chid=041"));
        tvList.add(new VideoBean("东方卫视", "http://14.18.17.142:9009/live/chid=042"));
        tvList.add(new VideoBean("山西卫视", "http://14.18.17.142:9009/live/chid=043"));
        tvList.add(new VideoBean("西藏卫视", "http://14.18.17.142:9009/live/chid=044"));
        tvList.add(new VideoBean("甘肃卫视", "http://14.18.17.142:9009/live/chid=045"));
        tvList.add(new VideoBean("青海卫视", "http://14.18.17.142:9009/live/chid=046"));
        tvList.add(new VideoBean("新疆卫视", "http://14.18.17.142:9009/live/chid=047"));
        tvList.add(new VideoBean("河北卫视", "http://14.18.17.142:9009/live/chid=048"));
        tvList.add(new VideoBean("湖北卫视", "http://14.18.17.142:9009/live/chid=049"));
        tvList.add(new VideoBean("旅游卫视", "http://14.18.17.142:9009/live/chid=050"));
        tvList.add(new VideoBean("宁夏卫视", "http://14.18.17.142:9009/live/chid=051"));
        tvList.add(new VideoBean("辽宁卫视", "http://14.18.17.142:9009/live/chid=052"));
        tvList.add(new VideoBean("厦门卫视", "http://14.18.17.142:9009/live/chid=087"));

        tvList.add(new VideoBean("北京體育高清", "http://121.251.49.204/hls/btv6hd.m3u8"));
        tvList.add(new VideoBean("NewTV惊悚悬疑高清", "http://183.207.249.7/PLTV/3/224/3221225561/index.m3u8"));
        tvList.add(new VideoBean("NewTV精品电影高清", "http://183.207.249.7/PLTV/3/224/3221225567/index.m3u8"));
        tvList.add(new VideoBean("NewTV爱情喜剧高清", "http://183.207.249.7/PLTV/3/224/3221225525/index.m3u8"));
        tvList.add(new VideoBean("林正英电影", "http://pl3.live.panda.tv/live_panda/828d3c99c1141d99baf6beb2f7481ac6.flv"));
        tvList.add(new VideoBean("恐怖电影院1", "http://live.gslb.letv.com/gslb?tag=live&stream_id=lb_hkmovie_720p&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=C1S&expect=1"));
        tvList.add(new VideoBean("高清科幻动作电影", "http://dlhls.cdn.zhanqi.tv/zqlive/166623_PXdHI.m3u8"));
        tvList.add(new VideoBean("北京卫视", "rtmp://360skylive.wasu.cn/live/bjws"));
        tvList.add(new VideoBean("CCTV13", "rtmp://ptvlivef.people.com.cn/m18/s1/sd"));
        tvList.add(new VideoBean("CCTV5", "rtmp://203.207.99.19:1935/live/CCTV5"));
        tvList.add(new VideoBean("重庆卫视", "rtmp://live5.cqnews.net:1935/live/TVFLV15"));
        tvList.add(new VideoBean("CCTV-15音乐 ", "http://183.207.249.7/PLTV/3/224/3221225568/index.m3u8"));
        tvList.add(new VideoBean("CCTV6-电影HD", "http://183.207.249.15/PLTV/3/224/3221225548/index.m3u8"));
        tvList.add(new VideoBean("CCTV-7军事与农业", "http://183.207.249.15/PLTV/3/224/3221225546/index.m3u8"));
        return tvList;
    }

    //直播的数据集合
    public static List<VideoBean> getLunBoList() {
        ArrayList<VideoBean> lbList = new ArrayList<>();
        lbList.add(new VideoBean("了不起的挑战", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_lbqdtz_1800&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("电影", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_movie_720p&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("电视剧", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_tv_720p&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("动漫", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_comic_720p&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("体育", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_sports_720p&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("1080P", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_1080P_1080p3m&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("综艺", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_ent_720p&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("音乐", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_music_1080p&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("娱乐", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_yule_720p&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("自制剧", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_make_720p&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("儿歌", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_erge_720p&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("汽车", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_qiche_1800&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("风尚", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_fengshang_720p&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("科技", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_zczb2_3000&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("中超", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_hdz_3000&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("篮球", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_lanqiu_720p&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("网球", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_wangqiu_720p&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("电子竞技", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_dzjj&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("演唱会", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_concert_3000&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("MV", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_mv_720p&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("古典音乐", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_classical_3000&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("音乐节", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_yinyuejie_3000&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("LIVE生活", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_livemusic_1080p3m&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("跟播", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_genbo_720p&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("孤芳不自赏", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_gfbzs_3000&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("守护丽人", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_nzcdtsgs2_3000&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("三生三世十里桃花", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_shssslth_3000&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("甄嬛传", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_zhenhuan_720p&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("最强大脑", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_zqdn_1800&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("动作电影", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_dzdy_720p&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("喜剧电影", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_comedy_720p&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("好莱坞", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_hlw_1800&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("香港电影", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_hkmovie_720p&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("工厂直通车", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_mjpd_1800&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("猫和老鼠", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_mhls_1800&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("芈月传", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_myz_1800&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("太子妃升职记", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_xmblzjh_1800&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("欢乐喜剧人", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_hlxjr_1800&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("凤凰传奇", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_fhcq_1800&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("笑傲江湖", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_xajh_720p&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("成龙", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_cl_720p&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("周星驰", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_zxc_720p&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("党建", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_dangjian_720p&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("熊出没", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_xcm_1800&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("猪猪侠", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_zzx_720p&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("粉红猪小妹", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_ncw_3000&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("奔跑吧兄弟", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_bpbxd&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("跨界喜剧王", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_kjxjw_3000&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("广场舞", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_gcw_1800&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("Dior迪奥", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_zczb8_3000&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("张艺谋", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_zym_720p&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("幻城", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_huancheng_3000&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("还珠格格", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_hzgg_1800&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("士兵突击", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_sbtj_1800&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("西游记", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_xyj_1800&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("欢乐颂", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_zczb5_3000&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("蒙面唱将猜猜猜", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_mmgw_1800&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("邓紫棋", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_dengziqi_1080p3m&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("美剧", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_meiju_1800&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("炉石传说", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_hlj_1800&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("北上广不相信眼泪", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_jcytht_1800&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("琅琊榜", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_lyb_1800&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("何以笙箫默", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_hysxm_1800&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("平凡的世界", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_pfdsj_1800&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("极限挑战", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_lb_jxtz_1800&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        lbList.add(new VideoBean("寂寞空庭春欲晚", "http://live.g3proxy.lecloud.com/gslb?stream_id=lb_jmktcyw_1800&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1"));
        return lbList;
    }
}
