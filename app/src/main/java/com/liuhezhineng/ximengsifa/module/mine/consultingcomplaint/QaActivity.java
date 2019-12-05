package com.liuhezhineng.ximengsifa.module.mine.consultingcomplaint;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.blankj.utilcode.util.ToastUtils;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.advisory.CommentList;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.model.MyStringCallback;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.StringRes;
import butterknife.BindView;

/**
 * @author AIqinfeng
 * @description 咨询、投诉追问/追答页面
 */
public class QaActivity extends BaseActivity implements android.view.View.OnClickListener {

    @BindView(R.id.et_qa)
    EditText mEtQa;

    // 追问时的实体类
    CommentList mBean;
    // commentType回复类型 0追问 1追答字段
    String commentType;
    // 用来区分时追问还是追答
    int idRes;
    // 追答时的 id
    String id;
    // 留言/投诉 追问/追答
    String url;
    private String businessType;

    MyStringCallback myStringCallback = new MyStringCallback(mActivity) {
        @Override
        public void onSuccess(Response<String> response) {
            try {
                JSONObject jsonObject = new JSONObject(response.body());
                int status = jsonObject.getInt(Constant.STATUS);
                if (status == 0) {
                    setResult(RESULT_OK);
                    finishActivity();
                } else {
                    ToastUtils.showLong(jsonObject.getString(Constant.MSG));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    public static void actionStart(Context context, CommentList list, @StringRes int idRes, String businessType) {
        Intent intent = new Intent(context, QaActivity.class);
        intent.putExtra(Constant.BEAN, list);
        intent.putExtra(Constant.STRING, idRes);
        intent.putExtra("businessType", businessType);
        ((BaseActivity) context).startActivityForResult(intent, Constant.QA);
    }

    /**
     *  reply
     * @param context
     * @param id
     * @param businessType
     */
    public static void actionStart(Context context, String id, String businessType) {
        Intent intent = new Intent(context, QaActivity.class);
        intent.putExtra(Constant.ID, id);
        intent.putExtra("businessType", businessType);
        ((BaseActivity) context).startActivityForResult(intent, Constant.QA);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_qa;
    }

    @Override
    protected void initView() {
        super.initView();

        Intent intent = getIntent();
        businessType = intent.getStringExtra("businessType");
        id = intent.getStringExtra(Constant.ID);
        if (!TextUtils.isEmpty(id)) {
            initTopBar(R.string.reply);
            mEtQa.setHint(R.string.please_enter_your_reply_details);
        } else {
            idRes = intent.getIntExtra(Constant.STRING, R.string.asked);
            initTopBar(idRes);
            if (idRes == R.string.asked) {
                commentType = "0";
            } else if (idRes == R.string.answered) {
                mEtQa.setHint(R.string.please_enter_your_answered_details);
                commentType = "1";
            }
            mBean = (CommentList) intent.getSerializableExtra(Constant.BEAN);
        }

        mTopBar.addRightTextButton("提交", R.id.btn_ok).setOnClickListener(this);
    }

    private void qa(String qa) {
        // commentId被回复的建议的id
        // guestbookId投诉建议id
        // content追问内容
        // commentType回复类型0追问1追答
        Map<String, String> map = new HashMap<>(16);
        map.put("guestbookId", mBean.getGuestbookId());
        map.put("content", qa);
        map.put("commentId", mBean.getId());
        map.put("commentType", commentType);
        String queryStr = new JSONObject(map).toString();
        commit(queryStr);
    }

    @Override
    public void onClick(View v) {
        String qa = mEtQa.getText().toString().trim();
        if (TextUtils.isEmpty(qa)) {
            ToastUtils.showLong("请输入" + getString(idRes) + "内容");
            return;
        }

        // id 不为空的时候是从回复而来
        if (!TextUtils.isEmpty(id)) {
            if (Constant.CONSULTING.equals(businessType)) {
                url = NetConstant.ConsultingComplaint.ADD_ADVISORY_REPLY;
            } else {
                url = NetConstant.ConsultingComplaint.ADD_COMPLAINTS_REPLY;
            }
            reply(qa);
        } else {
            if (Constant.CONSULTING.equals(businessType)) {
                url = NetConstant.ConsultingComplaint.ADD_ADVISORY_QA;
            } else {
                url = NetConstant.ConsultingComplaint.ADD_COMPLAINTS_QA;
            }
            qa(qa);
        }
    }

    /**
     * reply
     * @param qa 回复内容
     */
    private void reply(String qa) {
        // id	        |	[string]	|	默认值	|	留言id
        // reContent	|	[string]	|		    |  	回复内容
        Map<String, String> map = new HashMap<>(16);
        map.put(Constant.ID, id);
        map.put("reContent", qa);
        String queryStr = new JSONObject(map).toString();
        commit(queryStr);
    }

    private void commit(String queryStr) {
        OkGo.<String>post(url)
                .params(NetConstant.QUERY, queryStr)
                .execute(myStringCallback);
    }
}