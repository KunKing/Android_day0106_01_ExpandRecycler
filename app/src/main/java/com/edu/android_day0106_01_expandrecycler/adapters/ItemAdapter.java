package com.edu.android_day0106_01_expandrecycler.adapters;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.SwipeDismissBehavior;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import com.edu.android_day0106_01_expandrecycler.R;

import java.util.List;

/**
 * Created by Ming on 2016/1/5.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> implements SwipeDismissBehavior.OnDismissListener {

    private Context context;
    private List<String> list;
    private RecyclerView recyclerView;

    public ItemAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 调用 android 自带的布局
        //View view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) holder.textView.getLayoutParams();
        SwipeDismissBehavior behavior = new SwipeDismissBehavior();
        behavior.setListener(this);
        layoutParams.setBehavior(behavior);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(list.get(position));
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onDismiss(final View view) {
        ViewCompat.setAlpha(view, 1);
        // 获取 布局 用来 从父布局中删除子控件 获取 recyclerView 中布局的 CoordinatorLayout 用于撤销的恢复
        final ViewGroup parent = (ViewGroup) view.getParent();
        //parent.removeView(view);
        final int position = recyclerView.getChildAdapterPosition(parent);
        final String deleteStr = list.get(position);
        list.remove(position);
        notifyItemRemoved(position);
        // 获取 recyclerView 的 父布局  将  Snackbar 放在最下面
        final CoordinatorLayout coordinatorLayout = (CoordinatorLayout)recyclerView.getParent();

        Snackbar.make(coordinatorLayout, "删除了一个 textView", Snackbar.LENGTH_LONG)
                .setAction("撤销", new View.OnClickListener() {
                    // 实现撤销
                    @Override
                    public void onClick(View v) {
                        // 添加回来 view
                        list.add(position,deleteStr);
                        //parent.addView(view);
                        notifyItemInserted(position);
                    }
                })
                .show();
    }

    @Override
    public void onDragStateChanged(int state) {

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(android.R.id.text1);
        }
    }
}
