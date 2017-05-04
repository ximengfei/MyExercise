package com.qanzone.mypreciousgift.bean;

import com.qanzone.mypreciousgift.utils.PublicFunc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xmf on 2017/4/18.
 */

public class SearchingBean implements Comparable<SearchingBean>{
    private String index;
    private String name;
    private String pinyin; //姓名对应的拼音

    public SearchingBean(String name) {
        this.name = name;
//        Log.e("xmf", name);
        pinyin = PublicFunc.getPinYin(name); // 根据姓名获取拼音
        try {
            index = pinyin.substring(0, 1).toUpperCase(); // 获取拼音首字母并转成大写
        } catch (Exception e) {
            index = "#";
        }
        if (!index.matches("[A-Z]")) { // 如果不在A-Z中则默认为“#”
            index = "#";
        }
    }

    public SearchingBean(String index, String name) {
        this.name = name;
        this.index = index;
    }

    public String getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public String getPinyin() {
        return pinyin;
    }

    @Override
    public int compareTo(SearchingBean another) {
        if (index.equals("#") && !another.getIndex().equals("#")) {
            return 1;
        } else if (!index.equals("#") && another.getIndex().equals("#")){
            return -1;
        } else {
            return pinyin.compareToIgnoreCase(another.getPinyin());
        }
    }

    //测试数据
    public static List<SearchingBean> getJapaneseContacts() {
        List<SearchingBean> contacts = new ArrayList<>();
        contacts.add(new SearchingBean("あ", "江户川コナン"));
        contacts.add(new SearchingBean("あ", "油女シノ"));
        contacts.add(new SearchingBean("あ", "犬夜叉"));
        contacts.add(new SearchingBean("か", "旗木カカシ"));
        contacts.add(new SearchingBean("か", "神楽"));
        contacts.add(new SearchingBean("か", "黒崎一護"));
        contacts.add(new SearchingBean("さ", "桜木花道"));
        contacts.add(new SearchingBean("さ", "坂田銀時"));
        contacts.add(new SearchingBean("さ", "殺生丸"));
        contacts.add(new SearchingBean("な", "奈良シカマル"));
        contacts.add(new SearchingBean("は", "旗木カカシ"));
        contacts.add(new SearchingBean("は", "日向ネジ"));
        contacts.add(new SearchingBean("や", "越前リョーマ"));
        contacts.add(new SearchingBean("や", "野比のび太"));
        contacts.add(new SearchingBean("や", "野原しんのすけ"));
        contacts.add(new SearchingBean("ら", "流川楓"));
        return contacts;
    }

    public static List<SearchingBean> getEnglishContacts() {
        List<SearchingBean> contacts = new ArrayList<>();
        contacts.add(new SearchingBean("A", "Abbey"));
        contacts.add(new SearchingBean("A", "Alex"));
        contacts.add(new SearchingBean("A", "Amy"));
        contacts.add(new SearchingBean("A", "Anne"));
        contacts.add(new SearchingBean("B", "Betty"));
        contacts.add(new SearchingBean("B", "Bob"));
        contacts.add(new SearchingBean("B", "Brian"));
        contacts.add(new SearchingBean("C", "Carl"));
        contacts.add(new SearchingBean("C", "Candy"));
        contacts.add(new SearchingBean("C", "Carlos"));
        contacts.add(new SearchingBean("C", "Charles"));
        contacts.add(new SearchingBean("C", "Christina"));
        contacts.add(new SearchingBean("D", "David"));
        contacts.add(new SearchingBean("D", "Daniel"));
        contacts.add(new SearchingBean("E", "Elizabeth"));
        contacts.add(new SearchingBean("E", "Eric"));
        contacts.add(new SearchingBean("E", "Eva"));
        contacts.add(new SearchingBean("F", "Frances"));
        contacts.add(new SearchingBean("F", "Frank"));
        contacts.add(new SearchingBean("I", "Ivy"));
        contacts.add(new SearchingBean("J", "James"));
        contacts.add(new SearchingBean("J", "John"));
        contacts.add(new SearchingBean("J", "Jessica"));
        contacts.add(new SearchingBean("K", "Karen"));
        contacts.add(new SearchingBean("K", "Karl"));
        contacts.add(new SearchingBean("K", "Kim"));
        contacts.add(new SearchingBean("L", "Leon"));
        contacts.add(new SearchingBean("L", "Lisa"));
        contacts.add(new SearchingBean("P", "Paul"));
        contacts.add(new SearchingBean("P", "Peter"));
        contacts.add(new SearchingBean("S", "Sarah"));
        contacts.add(new SearchingBean("S", "Steven"));
        contacts.add(new SearchingBean("R", "Robert"));
        contacts.add(new SearchingBean("R", "Ryan"));
        contacts.add(new SearchingBean("T", "Tom"));
        contacts.add(new SearchingBean("T", "Tony"));
        contacts.add(new SearchingBean("W", "Wendy"));
        contacts.add(new SearchingBean("W", "Will"));
        contacts.add(new SearchingBean("W", "William"));
        contacts.add(new SearchingBean("Z", "Zoe"));
        return contacts;
    }


}
