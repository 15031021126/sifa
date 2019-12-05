package com.liuhezhineng.ximengsifa;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liuhezhineng.ximengsifa.adapter.AnnexAdapter;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.msg.LocalMsg;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.model.JsonCallback;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.QMUITopBar;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;

public class MsgDetailsActivity extends BaseActivity {

    public static final int READ_STATUS = 0x00;
    protected AnnexAdapter mAdapter;
    protected ArrayList<String> mAnnexList;
    @BindView(R.id.top_bar)
    QMUITopBar mTopBar;
    @BindView(R.id.tv_article_title)
    TextView mTvTitle;
    @BindView(R.id.tv_article_from)
    TextView mTvFrom;
    @BindView(R.id.tv_article_date)
    TextView mTvDate;
    @BindView(R.id.tv_article_content)
    TextView mTvContent;
    @BindView(R.id.ll_annex)
    LinearLayout mLlAnnex;
    @BindView(R.id.tv_file)
    TextView mTvFile;

    public static void actionStart(Context context, String msgId) {
        Intent intent = new Intent(context, MsgDetailsActivity.class);
        intent.putExtra(Constant.ID, msgId);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ((BaseActivity) context).startActivityForResult(intent, READ_STATUS);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_msg_details;
    }

    @Override
    protected void initView() {
        super.initView();
        initTopBar(mTopBar, "消息详情");
        initAnnex();
        mTvFile.setVisibility(View.GONE);
    }

    private void initAnnex() {
        RecyclerView mRvAnnex = findViewById(R.id.rv_annex);
        mAnnexList = new ArrayList<>();
        mAdapter = new AnnexAdapter(mActivity, mAnnexList);
        mRvAnnex.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        super.initData();
        // 这里调用接口来让服务器获取已读状态
        // 还有就是从通知点击进来
        HashMap<String, String> map = new HashMap<>(16);
        map.put(Constant.ID, getIntent().getStringExtra(Constant.ID));
        String queryStr = new JSONObject(map).toString();
        OkGo.<BaseBean<LocalMsg>>post(NetConstant.GET_LOCAL_MSG_DETAILS)
                .params(Constant.QUERY, queryStr)
                .execute(new JsonCallback<BaseBean<LocalMsg>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<LocalMsg>> response) {
                        LocalMsg body = response.body().getBody();
                        mTvTitle.setText(body.getTitle());
                        mTvFrom.setText(body.getTypeDesc());
                        mTvDate.setText(body.getCreateDate());
                        mTvContent.setText(body.getContent());
                        setCaseFile(body.getFiles());
                    }
                });
    }

    protected void setCaseFile(String caseFile) {
        // caseFile: "", "|xx", "null|xx"
        if (!TextUtils.isEmpty(caseFile)) {
            ArrayList<String> list = new ArrayList<>();
            String[] fileArray = caseFile.split("\\|");
            for (String path : fileArray) {
                if (!TextUtils.isEmpty(path)) {
                    list.add(path);
                }
            }
            mAdapter.initData(list);
        } else {
            mLlAnnex.setVisibility(View.GONE);
        }
    }

    @Override
    public void finish() {
        setResult(RESULT_OK);
        super.finish();
    }
}
