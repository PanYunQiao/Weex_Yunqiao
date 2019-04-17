package com.example.apple.yunqiao_weex.Activity.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.apple.yunqiao_weex.Activity.Basic.BasicYunQiaoActivity;
import com.example.apple.yunqiao_weex.Adapter.MyAdapter;
import com.example.apple.yunqiao_weex.CustomView.CustomAndroidView.MyListView;
import com.example.apple.yunqiao_weex.CustomView.Listener.OnDeleteListener;
import com.example.apple.yunqiao_weex.R;

import java.util.ArrayList;
import java.util.List;

public class MyListViewActivity extends BasicYunQiaoActivity {

    MyListView myListView;

    MyAdapter myAdapter;

    List<String> contentList = new ArrayList<>();

    @Override
    protected void getIntentParams() {

    }

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_my_list_view);
        initList();
        myListView = findViewById(R.id.my_list_view);
        myListView.setOnDeleteListener(new OnDeleteListener() {
            @Override
            public void onDelete(int selectedItem) {
                contentList.remove(selectedItem);
                myAdapter.notifyDataSetChanged();
            }
        });
        myAdapter = new MyAdapter(this,0,contentList);
        myListView.setAdapter(myAdapter);
    }

    @Override
    protected void prepareData() {

    }

    private void initList() {
        contentList.add("Content Item 1");
        contentList.add("Content Item 2");
        contentList.add("Content Item 3");
        contentList.add("Content Item 4");
        contentList.add("Content Item 5");
        contentList.add("Content Item 6");
        contentList.add("Content Item 7");
        contentList.add("Content Item 8");
        contentList.add("Content Item 9");
        contentList.add("Content Item 10");
        contentList.add("Content Item 11");
        contentList.add("Content Item 12");
        contentList.add("Content Item 13");
        contentList.add("Content Item 14");
        contentList.add("Content Item 15");
        contentList.add("Content Item 16");
        contentList.add("Content Item 17");
        contentList.add("Content Item 18");
        contentList.add("Content Item 19");
        contentList.add("Content Item 20");
    }
}
