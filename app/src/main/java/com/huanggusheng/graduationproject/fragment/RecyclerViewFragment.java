package com.huanggusheng.graduationproject.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.adapter.RecyclerViewMaterialAdapter;
import com.huanggusheng.graduationproject.R;
import com.huanggusheng.graduationproject.adapter.PostListAdapter;
import com.huanggusheng.graduationproject.model.Post;
import com.huanggusheng.graduationproject.util.PostQuery;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Huang on 2016/4/12.
 */
public class RecyclerViewFragment extends android.support.v4.app.Fragment {

    private RecyclerView.Adapter mAdapter;
    private List<Post> mDataList;

    @Bind(R.id.post_list)
    RecyclerView mRecyclerView;

    @Bind(R.id.swiprefresh)
    SwipeRefreshLayout mSwipeRefreshLayout;


    public static RecyclerViewFragment newInstance() {
        return new RecyclerViewFragment();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_page, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        //下滑刷新
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });

        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        //底部上滑加载更多
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //待完善
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //带完善
            }
        });
//        getData();

        //查询
        AVQuery<Post> post = new AVQuery<Post>("Post");
        post.limit(10);
        post.orderByDescending("createdAt");
        post.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> list, AVException e) {
                mDataList = list;
                mAdapter = new RecyclerViewMaterialAdapter(new PostListAdapter(view.getContext(),mDataList));
                mRecyclerView.setAdapter(mAdapter);
                MaterialViewPagerHelper.registerRecyclerView(getActivity(), mRecyclerView, null);

            }
        });
    }

    public void getData() {
        PostQuery postQuery = new PostQuery();
        postQuery.queryAllPost();
//        while (PostQuery.IF_END == true) {
            mDataList = postQuery.getData();
//        }
    }

    public void setmDataLists(final View  view) {
        AVQuery<Post> post = new AVQuery<Post>("Post");
        post.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> list, AVException e) {
                mDataList = list;
                mAdapter = new RecyclerViewMaterialAdapter(new PostListAdapter(view.getContext(),mDataList));
                mRecyclerView.setAdapter(mAdapter);
                MaterialViewPagerHelper.registerRecyclerView(getActivity(), mRecyclerView, null);
            }
        });
    }
}
