package com.example.julia.dragdroptworecyclerviews;

import android.content.ClipData;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

class ListAdapter extends RecyclerView.Adapter<ListViewHolder> {

    private List<String> mList;
    private Listener mListener;

    ListAdapter(List<String> list, Listener listener) {
        this.mList = list;
        this.mListener = listener;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(
                parent.getContext()).inflate(R.layout.list_row, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        holder.text.setText(mList.get(position));
        holder.frameLayout.setTag(position);
        holder.frameLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch(motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        ClipData data = ClipData.newPlainText("", "");
                        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            view.startDragAndDrop(data, shadowBuilder, view, 0);
                        } else {
                            view.startDrag(data, shadowBuilder, view, 0);
                        }
                        return true;
                }
                return false;
            }
        });
        holder.frameLayout.setOnDragListener(new DragListener(mListener));
    }

    DragListener getDragInstance() {
        if (mListener != null) {
            return new DragListener(mListener);
        } else {
            Log.e("ListAdapter", "Listener wasn't initialized!");
            return null;
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    List<String> getList() {
        return mList;
    }

    void updateList(List<String> list) {
        mList = list;
    }
}
