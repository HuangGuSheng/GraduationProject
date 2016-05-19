package com.huanggusheng.graduationproject.model;

import android.content.Context;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;

/**
 * 玩转 单例模式 ୧(๑•̀⌄•́๑)૭✧"
 * Created by Huang on 2016/4/6.
 */
public class User extends AVUser {
    /**
     * 缓存账户
     */
    private static volatile AVUser currentUser = null;

    public static AVUser getInstance(Context context) {
        AVUser user = currentUser ;         // <<<<<<<在这里创建临时变量
        if (user == null) {
            synchronized (AVUser.class) {
                user = currentUser;
                if (user == null) {
                    user = AVUser.getCurrentUser();
                    currentUser = user;
                }
            }
        }
        return user;            // <<<<<<注意这里返回的是临时变量
    }

}
