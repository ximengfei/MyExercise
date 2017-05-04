package com.qanzone.mypreciousgift.bean;

import cn.bmob.v3.BmobObject;

/**
 * 版本更新的
 * Created by xmf on 2017/4/19.
 */

public class version extends BmobObject {
    private String updatalog;
    private String apkpath;
    private int servicecode;

    public String getUpdatalog() {
        return updatalog;
    }

    public void setUpdatalog(String updatalog) {
        this.updatalog = updatalog;
    }

    public String getApkpath() {
        return apkpath;
    }

    public void setApkpath(String apkpath) {
        this.apkpath = apkpath;
    }

    public int getServicecode() {
        return servicecode;
    }

    public void setServicecode(int servicecode) {
        this.servicecode = servicecode;
    }

    @Override
    public String toString() {
        return "updatalog == " + updatalog + "; apkpath == " + apkpath + "; servicecode == " + servicecode;
    }
}

//添加数据
//    Person p2 = new Person();
//p2.setName("lucky");
//        p2.setAddress("北京海淀");
//        p2.save(new SaveListener<String>() {
//@Override
//public void done(String objectId,BmobException e) {
//        if(e==null){
//        toast("添加数据成功，返回objectId为："+objectId);
//        }else{
//        toast("创建数据失败：" + e.getMessage());
//        }
//        }
//        });

    //查找Person表里面id为6b6c11c537的数据
//    BmobQuery<Person> bmobQuery = new BmobQuery<Person>();
//bmobQuery.getObject("6b6c11c537", new >QueryListener<Person>() {
//@Override
//public void done(Person object,BmobException e) {
//        if(e==null){
//        toast("查询成功");
//        }else{
//        toast("查询失败：" + e.getMessage());
//        }
//        }
//        });