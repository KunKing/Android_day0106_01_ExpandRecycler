package com.edu.android_day0106_01_expandrecycler.widget;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Ming on 2016/1/6.
 *
 * CoordinatorLayout  换成
 */
public class SwipeDismissBehaviorImpl extends CoordinatorLayout.Behavior<View> {

    private static final String TAG = "BehaviorImpl";

    public SwipeDismissBehaviorImpl() {
    }

    public SwipeDismissBehaviorImpl(Context context, AttributeSet attrs) {
        super();
    }


    /**
     * 关系的是什么滚动
     *
     * @param coordinatorLayout
     * @param child
     * @param directTargetChild
     * @param target
     * @param nestedScrollAxes
     * @return
     */
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout,
                                       View child, View directTargetChild,
                                       View target, int nestedScrollAxes) {


        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    /**
     * 真实发生滚动 发生在  CoordinatorLayout 上的滚动
     *
     * @param coordinatorLayout
     * @param child
     * @param target
     * @param dxConsumed
     * @param dyConsumed
     * @param dxUnconsumed
     * @param dyUnconsumed
     */
    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout,
                               View child, View target, int dxConsumed,
                               int dyConsumed, int dxUnconsumed, int dyUnconsumed) {

        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }

    /**
     * 准备滚动前 即将发生的滚动，还没有分发
     * @param coordinatorLayout
     * @param child
     * @param target
     * @param dx
     * @param dy
     * @param consumed
     */
    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        if (dy>0){
            // 向下
            Log.d(TAG, "onNestedPreScroll: "+child);
            child.setVisibility(View.GONE);
        }else {
            child.setVisibility(View.VISIBLE);
        }
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
    }
}
