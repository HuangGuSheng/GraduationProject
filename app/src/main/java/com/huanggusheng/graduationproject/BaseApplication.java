package com.huanggusheng.graduationproject;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.huanggusheng.graduationproject.model.Post;
import com.huanggusheng.graduationproject.model.User;

/**
 * Created by Huang on 2016/4/10.
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //子类化后 注册该类
        AVObject.registerSubclass(Post.class);
        AVOSCloud.initialize(this,"uHn94GvFPFDziarycBFjqHmR-gzGzoHsz","ybyuMWlA8OVzod7rhgpbopFI");
    }
}
