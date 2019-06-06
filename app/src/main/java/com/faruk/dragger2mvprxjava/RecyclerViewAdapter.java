package com.faruk.dragger2mvprxjava;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.faruk.dragger2mvprxjava.data.AppData;
import com.faruk.dragger2mvprxjava.utility.Utils;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<AppData> data;
    private RecyclerViewAdapter.ClickListener clickListener;

    public Context mContext;

    public static final int LIST_IMAGE_WIDTH = 150;
    public static final int LIST_IMAGE_HEIGHT = 150;

    @Inject
    public RecyclerViewAdapter(ClickListener clickListener) {
        this.clickListener = clickListener;
        data = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(data.get(position).title);
        Glide.with(mContext)
                .load(data.get(position).image_url)
                .placeholder(Utils.getProgressBarIndeterminate(mContext))
                .error(R.drawable.ic_error_image)
                .override(LIST_IMAGE_WIDTH, LIST_IMAGE_HEIGHT)
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private RelativeLayout layoutContainer;
        private ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.tv_title);
            imageView = itemView.findViewById(R.id.iv_icon);
            layoutContainer = itemView.findViewById(R.id.containerLayout);

            layoutContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.launchIntent(data.get(getAdapterPosition()).title);
                }
            });
        }
    }

    public interface ClickListener {
        void launchIntent(String name);
    }

    public void setData(List<AppData> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }
}

