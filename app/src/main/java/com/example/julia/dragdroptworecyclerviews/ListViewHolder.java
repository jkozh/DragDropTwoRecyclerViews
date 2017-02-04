package com.example.julia.dragdroptworecyclerviews;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

class ListViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.text) TextView text;
    @BindView(R.id.frame_layout_item) FrameLayout frameLayout;

    ListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}