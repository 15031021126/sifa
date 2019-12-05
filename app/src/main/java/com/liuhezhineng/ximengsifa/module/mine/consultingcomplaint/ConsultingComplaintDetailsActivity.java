package com.liuhezhineng.ximengsifa.module.mine.consultingcomplaint;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.adapter.AnnexAdapter;
import com.liuhezhineng.ximengsifa.adapter.ConsultingComplaintReplyAdapter;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.advisory.AdvisoryBean;
import com.liuhezhineng.ximengsifa.bean.advisory.CommentList;
import com.liuhezhineng.ximengsifa.bean.advisory.ConsultingDetailsBean;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.model.DialogCallBack;
import com.liuhezhineng.ximengsifa.utils.UserHelper;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author AIqinfeng
 * @description 留言咨询\投诉意见详情页面
 */
public class ConsultingComplaintDetailsActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_advisory_type)
    TextView mTvAdvisoryType;
    @BindView(R.id.tv_advisory_date)
    TextView mTvAdvisoryDate;
    @BindView(R.id.tv_advisory_content)
    TextView mTvAdvisoryContent;
    @BindView(R.id.tv_reply_count)
    TextView mTvReplyCount;
    @BindView(R.id.rv_reply)
    RecyclerView mRvReply;
    @BindView(R.id.rv_annex)
    RecyclerView mRvAnnex;
    @BindView(R.id.ll_annex)
    LinearLayout mLlAnnex;
    @BindView(R.id.tv_file)
    TextView mTvFile;
    @BindView(R.id.tv_reply)
    TextView mTvReply;

    private String caseFile;

    AdvisoryBean mBean;
    String type;
    private String todoType;
    ArrayList<CommentList> mList;
    ConsultingComplaintReplyAdapter mAdapter;

    /**
     * @param context
     * @param bean
     * @param type
     */
    public static void actionStart(Context context, AdvisoryBean bean, String type, String todoType) {
        Intent intent = new Intent(context, ConsultingComplaintDetailsActivity.class);
        intent.putExtra(Constant.ADVISORY, bean);
        intent.putExtra(Constant.STRING, type);
        intent.putExtra("todoType", todoType);
        context.startActivity(intent);
    }

    protected AnnexAdapter mAnnexAdapter;
    protected ArrayList<String> mAnnexList;

    private void initAnnex() {
        mTvFile.setVisibility(View.GONE);
        mAnnexList = new ArrayList<>();
        mAnnexAdapter = new AnnexAdapter(mActivity, mAnnexList);
        mRvAnnex.setAdapter(mAnnexAdapter);
    }

    protected void setCaseFile(String caseFile) {
        this.caseFile = caseFile;
        // caseFile: "", "|xx", "null|xx"
        if (!TextUtils.isEmpty(caseFile)) {
            ArrayList<String> list = new ArrayList<>();
            String[] fileArray = caseFile.split("\\|");
            for (String path : fileArray) {
                if (!TextUtils.isEmpty(path)) {
                    list.add(path);
                }
            }
            mAnnexAdapter.initData(list);
        } else {
            mLlAnnex.setVisibility(View.GONE);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_advisory_details;
    }

    @Override
    protected void initView() {
        super.initView();

        initAnnex();

        type = getIntent().getStringExtra(Constant.STRING);
        todoType = getIntent().getStringExtra("todoType");
        if (Constant.CONSULTING.equals(type)) {
            initTopBar(R.string.consulting_details);
        } else {
            initTopBar(R.string.complaint_details);
        }

        mList = new ArrayList<>();
        mAdapter = new ConsultingComplaintReplyAdapter(mActivity, mList, type, todoType);
        mRvReply.setAdapter(mAdapter);
        mRvReply.setLayoutManager(new LinearLayoutManager(mActivity){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        mRvReply.addItemDecoration(
                new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL));
    }

    @Override
    protected void initData() {
        super.initData();
        mBean = (AdvisoryBean) getIntent().getSerializableExtra(Constant.ADVISORY);
        if (Constant.CONSULTING.equals(type)) {
            getAdvisoryDetails();
        } else {
            getComplaintsDetails();
        }
    }

    String id;

    @Override
    @OnClick({R.id.tv_reply})
    public void onViewClicked(View view) {
        super.onViewClicked(view);
        QaActivity.actionStart(mActivity, id, type);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == Constant.QA || requestCode == Constant.NORMAL_CODE) {
                initData();
            }
        }
    }

    private void getAdvisoryDetails() {
        Map<String, String> map = new HashMap<>(16);
        map.put(Constant.ID, mBean.getId());
        map.put("problemType", mBean.getProblemType());
        map.put("type", mBean.getType());
        String queryStr = new JSONObject(map).toString();
        commit(queryStr, NetConstant.GET_ADVISORY_DETAILS_BY_ID);
    }

    private void getComplaintsDetails() {
        Map<String, String> map = new HashMap<>(16);
        // 投诉建议 id
        map.put(Constant.ID, mBean.getId());
        String queryStr = new JSONObject(map).toString();
        commit(queryStr, NetConstant.GET_COMPLAINTS_DETAILS);
    }

    private void commit(String queryStr, String url) {
        OkGo.<BaseBean<ConsultingDetailsBean>>post(url)
                .params(NetConstant.QUERY, queryStr)
                .execute(new DialogCallBack<BaseBean<ConsultingDetailsBean>>(mActivity) {
                    @Override
                    public void onSuccess(Response<BaseBean<ConsultingDetailsBean>> response) {
                        ConsultingDetailsBean consultingDetailsBean = response.body().getBody();
                        showDetails(consultingDetailsBean);
                    }
                });
    }

    private void showDetails(ConsultingDetailsBean consultingDetailsBean) {
        id = consultingDetailsBean.getId();
        // 自己投诉的
        if (UserHelper.getUser().getId().equals(consultingDetailsBean.getCreateBy().getId())
                // 在我的咨询/投诉列表里面
                && (getString(R.string.my_advisory).equals(todoType) || getString(R.string.my_complaint).equals(todoType))) {
            mTvReply.setVisibility(View.GONE);
        }
        setCaseFile(consultingDetailsBean.getFiles());
        mTvAdvisoryType.setText(consultingDetailsBean.getTypeDesc() + "-" + consultingDetailsBean.getProblemTypeDesc());
        // 如果为空这说明是投诉建议，显示其类型
        if (TextUtils.isEmpty(consultingDetailsBean.getTypeDesc())) {
            mTvAdvisoryType.setText(consultingDetailsBean.getRemarkTypeDesc());
        }
        mTvTitle.setText(consultingDetailsBean.getTitle());
        mTvAdvisoryDate.setText(consultingDetailsBean.getCreateDate());
        mTvAdvisoryContent.setText(consultingDetailsBean.getContent());
        List<CommentList> list = consultingDetailsBean.getCommentList();
        if (list != null && list.size() > 0) {
            mTvReplyCount.setText("共有" + (list.size()) + "条回复");
            mAdapter.initData(list);
        } else {
            mTvReplyCount.setText("共有" + 0 + "条回复");
        }
    }
}
