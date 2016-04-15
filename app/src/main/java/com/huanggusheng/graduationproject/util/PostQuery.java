package com.huanggusheng.graduationproject.util;

import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.huanggusheng.graduationproject.model.Post;

import java.util.List;

/**
 * 查询帖子
 * Created by Huang on 2016/4/12.
 */
public class PostQuery extends AVQuery<Post> {

    public static boolean IF_END = false;

    private List<Post> data;

    public List<Post> getData() {
        Log.d("getData", "queryAllPost");
//        queryAllPost();
        return data;
    }

    /**
     * 查询所有帖子
     */
    public void queryAllPost()  {
        AVQuery<Post> posts = new AVQuery<Post>("Post");
        posts.findInBackground(new FindCallback<Post>() {
            public void done(List<Post> avObjects, AVException e) {
                if (e == null) {
                    data = avObjects;
                    IF_END = true;
                    Log.e("成功", "查询到" + avObjects.size() + " 条符合条件的数据");
                } else {
                    Log.e("失败", "查询错误: " + e.getMessage());
                }
            }
        });

        AVQuery<Post> query = AVQuery.getQuery("Post");
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> list, AVException e) {
                data = list;
            }
        });
    }
}
