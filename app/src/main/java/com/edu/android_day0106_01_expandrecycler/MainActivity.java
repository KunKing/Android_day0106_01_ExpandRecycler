package com.edu.android_day0106_01_expandrecycler;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.SwipeDismissBehavior;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.edu.android_day0106_01_expandrecycler.activities.CoordinatorActivity;

public class MainActivity extends AppCompatActivity implements SwipeDismissBehavior.OnDismissListener, View.OnClickListener {

    private TextView textView;
    private CoordinatorLayout.LayoutParams layoutParams;
    private SwipeDismissBehavior behavior;
    private CoordinatorLayout coordinatorLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.tv);
        textView.setOnClickListener(this);

        // 获取协调者布局
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator);
        // 获取父容器的 控件属性
        layoutParams = (CoordinatorLayout.LayoutParams)
                textView.getLayoutParams();
        // 滑动动画效果 消失 !!!SwipeDismissBehavior
        behavior = new SwipeDismissBehavior();
        // 消失监听  左滑 右滑 有不同 一般是右滑 可以自定义控件
        behavior.setListener(this);
        layoutParams.setBehavior(behavior);

        //------------toolbar  AppCompatActivity 用 v7 的 toolbar 不要使用原生的 toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // 把 Toolbar 当做 actionBar 使用(一定是要在 没有 actionbar 的情况下)
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //-------------- 消失监听 start

    /**
     * 消失
     *
     * @param view
     */
    @Override
    public void onDismiss(final View view) {
        // view.setVisibility(View.GONE);// 这个是 消失
        // 滑走 之后 就 回不来了 删除
        ViewGroup parent = (ViewGroup) view.getParent();
        parent.removeView(view);

        ViewCompat.setAlpha(view, 1);
        //ViewCompat.setTranslationX(view,0);

        //Toast.makeText(MainActivity.this, "删除了一个 textView", Toast.LENGTH_SHORT).show();
        /**
         * 参数1： 如果 view 是 CoordinatorLayout 那么 Snackbar 自带滑动删除效果
         *         不是的话，Snackbar 不能滑动删除，得自己写。
         */
        Snackbar.make(coordinatorLayout,"删除了一个 textView",Snackbar.LENGTH_LONG)
                .setAction("撤销", new View.OnClickListener() {
                    // 实现撤销
                    @Override
                    public void onClick(View v) {
                        // 添加回来 view
                        coordinatorLayout.addView(view);
                    }
                })
                .show();
    }

    @Override
    public void onDragStateChanged(int state) {

    }
    //-------------- 消失监听 end

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, CoordinatorActivity.class);
        startActivity(intent);

    }
}
