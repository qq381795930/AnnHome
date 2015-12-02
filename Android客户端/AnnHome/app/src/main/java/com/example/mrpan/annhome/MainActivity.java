package com.example.mrpan.annhome;

import android.animation.Animator;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.ChangeTransform;
import android.transition.Transition;
import android.util.Log;
import android.util.Pair;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class MainActivity extends Activity {

    private RecyclerView mRecyclerView;

    private MyAdapter myAdapter;

    private SlidingMenu slidingMenu = null;

    private SwipeRefreshLayout mSwipeRefreshWidget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    //初始化操作
    private void init(){
        //绑定控件
        Button btnMenu=(Button)findViewById(R.id.btnMenu);
        Button btnPerson=(Button)findViewById(R.id.btnPerson);
        btnMenu.setOnClickListener(new MyOnClickListener());
        btnPerson.setOnClickListener(new MyOnClickListener());


        // 设置抽屉菜单
        slidingMenu = new SlidingMenu(this);
        slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN); // 触摸边界拖出菜单
        slidingMenu.setMenu(R.layout.slidingmenu_left);
        slidingMenu.setSecondaryMenu(R.layout.slidingmenu_right);
//        if (log_State) {
//            slidingMenu.setSecondaryMenu(R.layout.slidingmenu_right_havelogin);
//        } else {
//            slidingMenu.setSecondaryMenu(R.layout.slidingmenu_right);
//        }
        slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        // 将抽屉菜单与主页面关联起来
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        // 拿到RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        // 设置LinearLayoutManager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 设置ItemAnimator
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // 设置固定大小
        mRecyclerView.setHasFixedSize(true);
        // 初始化自定义的适配器
        myAdapter = new MyAdapter(this, getItems());
        // 为mRcyclerView设置适配器
        mRecyclerView.setAdapter(myAdapter);

        this.myAdapter.setOnItemClickListener(new MyAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Toast.makeText(MainActivity.this, "11111111111111", Toast.LENGTH_SHORT).show();
                startActivity(view);
            }
        });
        this.myAdapter.setOnItemLongClickListener(new MyAdapter.MyItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int postion) {
                Toast.makeText(MainActivity.this, "22222222222222", Toast.LENGTH_SHORT).show();
            }
        });

        //下拉刷新
        mSwipeRefreshWidget = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_widget);
        mRecyclerView = (RecyclerView) findViewById(android.R.id.list);

        //mSwipeRefreshWidget.setColorScheme(R.color.color1, R.color.color2,
            //    R.color.color3, R.color.color4);
        //mSwipeRefreshWidget.setOnRefreshListener(this);

        // 这句话是为了，第一次进入页面的时候显示加载进度条
//        mSwipeRefreshWidget.setProgressViewOffset(false, 0, (int) TypedValue
//                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
//                        .getDisplayMetrics()));

//        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
//
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView,
//                                             int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                if (newState == RecyclerView.SCROLL_STATE_IDLE
//                        //&& lastVisibleItem + 1 == adapter.getItemCount()
//                        ) {
//                    mSwipeRefreshWidget.setRefreshing(true);
//                    //网络请求数据代码，sendRequest .....
//                }
//            }
//        });
    }

    //设置数据
    private ArrayList<HashMap<String,Object>> getItems(){
        ArrayList<HashMap<String,Object>> items=new ArrayList<HashMap<String, Object>>();
        for(int i=0;i<5;i++) {
            HashMap<String,Object> item=new HashMap<String,Object>();
            item.put("title", "上海");
            items.add(item);
        }

        return items;
    }

    //跳转事件
    public void startActivity(final View v) {
        View pic = v.findViewById(R.id.pic);
        Transition ts = new ChangeTransform();
        ts.setDuration(3000);
        this.getWindow().setExitTransition(ts);
        Bundle bundle = ActivityOptions.makeSceneTransitionAnimation((Activity) this,
                Pair.create(pic, 1 + "pic"),
                Pair.create(pic, 1 + "pic")).toBundle();
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("pos", 1);
        startActivity(intent);
    }
    public class MyOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnMenu:
                    slidingMenu.showMenu();
                    break;
                case R.id.btnPerson:
                    slidingMenu.showSecondaryMenu();
                    break;
                default:
                    break;
            }
        }
    }
}
