package com.huanggusheng.graduationproject;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.huanggusheng.graduationproject.model.Post;
import com.huanggusheng.graduationproject.model.User;

/**
 * Created by Huang on 2016/4/10.
 */
public class BaseApplication extends Application {
    public static final String LIKES = "likes";
    public static final String STATUS_DETAIL = "StatusDetail";
    public static final String DETAIL_ID = "detailId";
    public static final String CREATED_AT = "createdAt";
    public static final String FOLLOWER = "follower";
    public static final String FOLLOWEE = "followee";
    @Override
    public void onCreate() {
        super.onCreate();
        //子类化后 注册该类
        AVObject.registerSubclass(Post.class);
        AVOSCloud.initialize(this,"uHn94GvFPFDziarycBFjqHmR-gzGzoHsz","ybyuMWlA8OVzod7rhgpbopFI");
        Fresco.initialize(this);
    }
}
