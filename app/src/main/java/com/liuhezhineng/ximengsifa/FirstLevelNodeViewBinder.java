package com.liuhezhineng.ximengsifa;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.Office;

import me.texy.treeview.TreeNode;
import me.texy.treeview.base.CheckableNodeViewBinder;

/**
 * Created by zxy on 17/4/23.
 */

public class FirstLevelNodeViewBinder extends CheckableNodeViewBinder {

    TextView textView;
    ImageView imageView;
    CheckBox checkBox;
    LinearLayout linearLayout;

    public FirstLevelNodeViewBinder(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.node_name_view);
        imageView = itemView.findViewById(R.id.arrow_img);
        checkBox = itemView.findViewById(R.id.checkBox);
        linearLayout = itemView.findViewById(R.id.node_container);
    }

    @Override
    public int getCheckableViewId() {
        return R.id.checkBox;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_first_level;
    }

    @Override
    public void bindView(final TreeNode treeNode) {
        Office office = (Office) treeNode.getValue();
        textView.setText(office.getName());
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) linearLayout.getLayoutParams();
        layoutParams.setMargins(treeNode.getLevel() * 24, 0, 0, 0);
        linearLayout.setLayoutParams(layoutParams);
        // 0人员1机构 在 RecyclerView 中 if else 一定要对应，应为有复用机制
        if ("1".equals(office.getType())) {
            checkBox.setVisibility(View.GONE);
            imageView.setVisibility(View.VISIBLE);
        } else {
            checkBox.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.INVISIBLE);
        }
        imageView.setRotation(treeNode.isExpanded() ? 90 : 0);
    }

    @Override
    public void onNodeToggled(TreeNode treeNode, boolean expand) {
        if (expand) {
            imageView.animate().rotation(90).setDuration(200).start();
        } else {
            imageView.animate().rotation(0).setDuration(200).start();
        }
    }
}
