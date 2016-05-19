package com.huanggusheng.graduationproject.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huanggusheng.graduationproject.R;
import com.huanggusheng.graduationproject.activity.BaseActivity;
import com.huanggusheng.graduationproject.model.Post;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Huang on 2016/4/12.
 */
public class PostListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int NO_PICTURE_ITEM = 0;
    private static final int PICTURE_ITEM = 1;

    private LayoutInflater mLayoutInflater;
    private List<Post> mDataList;
    private Context mContext;

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
        boolean isPictureItem = (mDataList.get(position).getPicture() == null);
        return isPictureItem ? PICTURE_ITEM : NO_PICTURE_ITEM;
    }


    /**
     * 渲染具体的ViewHolder
     * @param parent ViewHolder的容器
     * @param viewType item的标志，根据标志选择渲染 带图片或是不带图片的ViewHolder
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == NO_PICTURE_ITEM) {
//            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_no_picture, parent, false);
//            return new RecyclerView.ViewHolder(view){};
            return new NoPictureItemHolder(mLayoutInflater.inflate(R.layout.item_no_picture, parent, false));

        } else {
//            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_with_picture, parent, false);
//            return new RecyclerView.ViewHolder(view) {
//            };
            return new PictureItemHolder(mLayoutInflater.inflate(R.layout.item_with_picture, parent, false));
        }
    }

    /**
     * 绑定ViewHOlder的数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case NO_PICTURE_ITEM:
                NoPictureItemHolder noPictureItemHolder = (NoPictureItemHolder) holder;
//                noPictureItemHolder.avatar.setImageURI();
                noPictureItemHolder.title.setText(mDataList.get(position).getTitle());
                noPictureItemHolder.content.setText(mDataList.get(position).getContent());
                break;
            case PICTURE_ITEM:
                PictureItemHolder pictureItemHolder = (PictureItemHolder) holder;
                pictureItemHolder.title.setText(mDataList.get(position).getTitle());
                pictureItemHolder.content.setText(mDataList.get(position).getContent());
//                Log.e("URL",mDataList.get(position).getPicture().toString());
                //***************warn
//                Log.e("URL", String.valueOf(mDataList.get(position).getPicture()));
//                pictureItemHolder.imageView.setImageURI(Uri.parse(mDataList.get(position).getPicture().getUrl()));
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

        public NoPictureItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.findViewById(R.id.item_container_no_picture).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //进入帖子
                }
            });
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
        ImageView imageView;

        public PictureItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.findViewById(R.id.container_picture).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //跳转帖子详情
                }
            });
        }
    }
}
