package com.liuhezhineng.ximengsifa.module.mine.consultingcomplaint;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;

import com.liuhezhineng.ximengsifa.FirstLevelNodeViewBinder;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.bussiness.peoplesmediation.Office;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.model.JsonCallback;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import me.texy.treeview.TreeNode;
import me.texy.treeview.TreeView;
import me.texy.treeview.base.BaseNodeViewBinder;
import me.texy.treeview.base.BaseNodeViewFactory;

/**
 * @author iqing
 * @description 选择用户
 */
public class ComplaintDistributeActivity extends BaseActivity {

    public static final String TAG = ComplaintDistributeActivity.class.getSimpleName();
    public static final int REQUEST_CODE = 0x00;
    public static final String INTENT_EXTRA = "office";
    private TreeView mTreeView;
    @BindView(R.id.fl_container)
    FrameLayout mFlContainer;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_complaint_distribute;
    }

    @Override
    protected void initData() {
        super.initData();

        initTopBar(R.string.select_personnel);

        TreeNode root = TreeNode.root();
        mTreeView = new TreeView(root,
                ComplaintDistributeActivity.this,
                new BaseNodeViewFactory() {
                    @Override
                    public BaseNodeViewBinder getNodeViewBinder(View view, int level) {
                        switch (level) {
                            case 0:
                                return new FirstLevelNodeViewBinder(view);
                            default:
                                return new FirstLevelNodeViewBinder(view);
                        }
                    }
                });
        //add to view group where you want
        mFlContainer.addView(mTreeView.getView());
        getChildNode(root, "7482ef06de5543a0b9bfd964d9162e72", 0);
    }

    private void getChildNode(final TreeNode treeNode, String officeId, final int level) {
        Map<String, String> map = new HashMap<>(16);
        map.put("officeId", officeId);
        String queryStr = new JSONObject(map).toString();

        OkGo.<BaseBean<List<Office>>>post(NetConstant.ConsultingComplaint.GET_DISTRIBUTE_ORGANIZED)
                .params(Constant.QUERY, queryStr)
                .cacheKey(queryStr)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .execute(new JsonCallback<BaseBean<List<Office>>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<List<Office>>> response) {
                        ArrayList<Office> distributeOfficeList = new ArrayList<>(response.body().getBody());
                        //build the tree as you want
                        for (int i = 0; i < distributeOfficeList.size(); i++) {
                            TreeNode childNode = new TreeNode(distributeOfficeList.get(i));
                            childNode.setLevel(level);
                            treeNode.addChild(childNode);
                            mTreeView.refreshTreeView();
                            // 0人员1机构
                            if ("1".equals(distributeOfficeList.get(i).getType())) {
                                int childLevel = level + 1;
                                getChildNode(childNode, distributeOfficeList.get(i).getId(), childLevel);
                            }
                        }
                    }
                });
    }

    @OnClick(R.id.btn_confirm_selected)
    @Override
    public void onViewClicked(View view) {
        super.onViewClicked(view);
        List<TreeNode> selectedNodes = mTreeView.getSelectedNodes();
        Intent intent = new Intent();
        intent.putExtra(INTENT_EXTRA, ((Office) selectedNodes.get(0).getValue()));
        setResult(RESULT_OK, intent);
        finishActivity();
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ComplaintDistributeActivity.class);
        ((BaseActivity) context).startActivityForResult(intent, REQUEST_CODE);
    }
}
