package com.example.julia.dragdroptworecyclerviews;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.DragEvent;
import android.view.View;

import java.util.List;

public class DragListener implements View.OnDragListener {
    private boolean isDropped = false;
    private Listener mListener;

    DragListener(Listener listener) {
        mListener = listener;
    }

    @Override
    public boolean onDrag(View view, DragEvent dragEvent) {
        switch (dragEvent.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                break;
            case DragEvent.ACTION_DROP:
                isDropped = true;
                int positionTarget = -1;

                View viewSource = (View) dragEvent.getLocalState();

                if (view.getId() == R.id.frame_layout_item || view.getId() == R.id.text_empty_list_bottom
                        || view.getId() == R.id.text_empty_list_top) {
                    RecyclerView target;

                    if (view.getId() == R.id.text_empty_list_top) {
                        target = (RecyclerView) view.getRootView()
                                .findViewById(R.id.recyclerview_top);
                    } else if (view.getId() == R.id.text_empty_list_bottom) {
                        target = (RecyclerView) view.getRootView()
                                .findViewById(R.id.recyclerview_bottom);
                    } else {
                        target = (RecyclerView) view.getParent();
                        positionTarget = (int) view.getTag();
                    }

                    RecyclerView source = (RecyclerView) viewSource.getParent();

                    ListAdapter adapterSource = (ListAdapter) source.getAdapter();
                    int positionSource = (int) viewSource.getTag();

                    String list = adapterSource.getList().get(positionSource);
                    List<String> listSource = adapterSource.getList();

                    listSource.remove(positionSource);
                    adapterSource.updateList(listSource);
                    adapterSource.notifyDataSetChanged();

                    ListAdapter adapterTarget = (ListAdapter) target.getAdapter();
                    List<String> customListTarget = adapterTarget.getList();
                    if (positionTarget >= 0) {
                        customListTarget.add(positionTarget, list);
                    } else {
                        customListTarget.add(list);
                    }
                    adapterTarget.updateList(customListTarget);
                    adapterTarget.notifyDataSetChanged();
                    view.setVisibility(View.VISIBLE);

                    if (source.getId() == R.id.recyclerview_bottom
                            && adapterSource.getItemCount() < 1) {
                        mListener.setEmptyListBottom(true);
                    }

                    if (view.getId() == R.id.text_empty_list_bottom) {
                        mListener.setEmptyListBottom(false);
                    }

                    if (source.getId() == R.id.recyclerview_top
                            && adapterSource.getItemCount() < 1) {
                        mListener.setEmptyListTop(true);
                    }

                    if (view.getId() == R.id.text_empty_list_top) {
                        mListener.setEmptyListTop(false);
                    }
                }
                break;
        }

        if (!isDropped) {
            ((View) dragEvent.getLocalState()).setVisibility(View.VISIBLE);
        }

        return true;
    }
}