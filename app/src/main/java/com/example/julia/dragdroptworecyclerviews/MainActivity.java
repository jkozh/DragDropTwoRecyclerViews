package com.example.julia.dragdroptworecyclerviews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements Listener {

    @BindView(R.id.recyclerview_top) RecyclerView recyclerViewTop;
    @BindView(R.id.recyclerview_bottom) RecyclerView recyclerViewBottom;
    @BindView(R.id.text_empty_list_top) TextView textEmptyListTop;
    @BindView(R.id.text_empty_list_bottom) TextView textEmptyListBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        LinearLayoutManager layoutManagerTop = new LinearLayoutManager(this);
        layoutManagerTop.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager layoutManagerBottom = new LinearLayoutManager(this);
        layoutManagerBottom.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewTop.setLayoutManager(layoutManagerTop);
        recyclerViewBottom.setLayoutManager(layoutManagerBottom);

        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");

        ListAdapter mTopListAdapter = new ListAdapter(list, this);
        recyclerViewTop.setAdapter(mTopListAdapter);

        List<String> list2 = new ArrayList<>();
        list2.add("C");
        list2.add("D");

        ListAdapter mBottomListAdapter = new ListAdapter(list2, this);
        recyclerViewBottom.setAdapter(mBottomListAdapter);

        textEmptyListTop.setOnDragListener(mTopListAdapter.getDragInstance());
        textEmptyListTop.setVisibility(View.GONE);
        textEmptyListBottom.setOnDragListener(mTopListAdapter.getDragInstance());
        textEmptyListBottom.setVisibility(View.GONE);
    }

    @Override
    public void setEmptyListTop(boolean visibility) {
        textEmptyListTop.setVisibility(visibility ? View.VISIBLE : View.GONE);
        recyclerViewTop.setVisibility(visibility ? View.GONE : View.VISIBLE);
    }

    @Override
    public void setEmptyListBottom(boolean visibility) {
        textEmptyListBottom.setVisibility(visibility ? View.VISIBLE : View.GONE);
        recyclerViewBottom.setVisibility(visibility ? View.GONE : View.VISIBLE);
    }
}
