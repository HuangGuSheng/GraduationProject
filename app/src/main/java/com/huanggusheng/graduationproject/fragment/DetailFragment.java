package com.huanggusheng.graduationproject.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.huanggusheng.graduationproject.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Huang on 2016/6/3.
 */
public class DetailFragment extends Fragment {
    @Bind(R.id.detail_avatar)
    ImageView avatar;

    @Bind(R.id.detail_user)
    TextView user;

    @Bind(R.id.detail_title)
    TextView title;

    @Bind(R.id.detail_content)
    TextView content;

    @Bind(R.id.detail_picture)
    SimpleDraweeView draweeView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post_detail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
