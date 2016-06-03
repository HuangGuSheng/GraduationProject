package com.huanggusheng.graduationproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.huanggusheng.graduationproject.R;
import com.huanggusheng.graduationproject.activity.BaseActivity;
import com.huanggusheng.graduationproject.activity.PostDatilActivity;
import com.huanggusheng.graduationproject.model.Post;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Huang on 2016/4/12.
 */
public class PostListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int NO_PICTURE_ITEM = 0;
    private static final int PICTURE_ITEM = 1;

    private LayoutInflater mLayoutInflater;
    private List<Post> mDataList;
    private Context mContext;

    /**
     * 构造方法
     * @param context
     * @param mDataList
     */
    public PostListAdapter(Context context,List<Post> mDataList) {
        this.mContext = context;
        this.mDataList = mDataList;
        mLayoutInflater = LayoutInflater.from(context);
    }



    @Override
    public int getItemCount() {
        return mDataList.size();
    }


    /**
     * 决定布局使用哪种类型
     * @param position 数据源List的下标
     * @return 一个int标志，传递给onCreateViewHolder第二个参数
     */
    @Override
    public int getItemViewType(int position) {
        //这样写可能不妥，后期测试再做修改
        if (mDataList.get(position).getIfhavePic() == false) {
            return 0;
        } else {
            return 1;
        }


    }


    /**
     * 渲染具体的ViewHolder
     * @param parent ViewHolder的容器
     * @param viewType item的标志，根据标志选择渲染 带图片或是不带图片的ViewHolder
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == NO_PICTURE_ITEM) {
            return new NoPictureItemHolder(mLayoutInflater.inflate(R.layout.item_no_picture, parent, false));

        } else {
            return new PictureItemHolder(mLayoutInflater.inflate(R.layout.item_with_picture, parent, false));
        }
    }

    /**
     * 绑定ViewHOlder的数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getLayoutPosition();
                mOnItemClickLitener.onItemClick(holder.itemView, pos);
            }
        });
        switch (holder.getItemViewType()) {
            case NO_PICTURE_ITEM:
                NoPictureItemHolder noPictureItemHolder = (NoPictureItemHolder) holder;

                noPictureItemHolder.title.setText(mDataList.get(position).getTitle());

                String content = mDataList.get(position).getContent();
                if (content.length() > 40) {
                    noPictureItemHolder.content.setText(content.substring(0, 38)+ "...");
                } else {
                    noPictureItemHolder.content.setText(content);
                }
                break;
            case PICTURE_ITEM:
                PictureItemHolder pictureItemHolder = (PictureItemHolder) holder;
                pictureItemHolder.title.setText(mDataList.get(position).getTitle());
                String s = mDataList.get(position).getContent();
                if (s.length() > 40) {
                    pictureItemHolder.content.setText(s.substring(0, 38)+"...");

                } else {
                    pictureItemHolder.content.setText(s);

                }
                Uri uri = Uri.parse(mDataList.get(position).getImageUrl());
                pictureItemHolder.draweeView.setImageURI(uri);
                break;
        }
    }



    /**
     * 仅有文字的帖子item
     */
    public class NoPictureItemHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.card_avatar_no_picture)
        ImageView avatar;

        @Bind(R.id.card_user_no_picture)
        TextView user;

        @Bind(R.id.card_title_no_picture)
        TextView title;

        @Bind(R.id.card_content_no_picture)
        TextView content;

        public NoPictureItemHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.findViewById(R.id.item_container_no_picture);
        }
    }

    /**
     * 带图片的item
     */
    public class PictureItemHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.card_avatar)
        ImageView avatar;

        @Bind(R.id.card_user)
        TextView user;

        @Bind(R.id.card_title)
        TextView title;

        @Bind(R.id.card_content)
        TextView content;

        @Bind(R.id.card_picture)
        SimpleDraweeView draweeView;

        public PictureItemHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.findViewById(R.id.container_picture);
        }
    }

    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view , int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }


}
