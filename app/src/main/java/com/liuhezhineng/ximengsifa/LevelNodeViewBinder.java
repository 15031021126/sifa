package com.liuhezhineng.ximengsifa;

import android.view.View;
import android.widget.CheckBox;

import me.texy.treeview.TreeNode;
import me.texy.treeview.base.CheckableNodeViewBinder;

/**
 * <pre>
 *     author : qingfeng
 *     e-mail : 1913518036@qq.com
 *     time   : 2019/01/04
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class LevelNodeViewBinder extends CheckableNodeViewBinder {
    private CheckBox mCheckBox;

    public LevelNodeViewBinder(View itemView) {
        super(itemView);
        mCheckBox = itemView.findViewById(R.id.checkBox);
    }

    @Override
    public int getCheckableViewId() {
        return mCheckBox.getId();
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_level;
    }

    @Override
    public void bindView(final TreeNode treeNode) {
        mCheckBox.setText(treeNode.getValue().toString());
    }

    @Override
    public int getToggleTriggerViewId() {
        return itemView.getId();
    }
}
