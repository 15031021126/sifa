package com.liuhezhineng.ximengsifa.adapter;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.audiorecord.MediaPlayerManager;
import com.liuhezhineng.ximengsifa.audiorecord.RecordBean;
import com.liuhezhineng.ximengsifa.constant.NetConstant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author iqing
 */
public class AudioAdapter extends RecyclerView.Adapter<AudioAdapter.AudioHolder> {

    private List<RecordBean> mList;
    private LayoutInflater mInflater;

    private int mMinRecordWidth;
    private int mMaxRecordWidth;

    public AudioAdapter(Context mContext, List<RecordBean> mList) {
        this.mList = mList;
        mInflater = LayoutInflater.from(mContext);

        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        if (wm != null) {
            Display defaultDisplay = wm.getDefaultDisplay();
            DisplayMetrics metrics = new DisplayMetrics();
            defaultDisplay.getMetrics(metrics);
            mMinRecordWidth = (int) (metrics.widthPixels * 0.25f);
            mMaxRecordWidth = (int) (metrics.widthPixels * 0.6f);
        }
    }

    @NonNull
    @Override
    public AudioHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AudioHolder(mInflater.inflate(R.layout.item_audio, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final AudioHolder holder, int position) {
        final RecordBean recordBean = mList.get(position);
        holder.mFilePath = recordBean.getFilePath();
        String secondStr = Math.round(recordBean.getSeconds()) + "\"";
        holder.mTvSecond.setText(secondStr);
        ViewGroup.LayoutParams lp = holder.mFlRecord.getLayoutParams();
        lp.width = Math.min((int) (mMinRecordWidth + (mMaxRecordWidth / 60f * recordBean.getSeconds())), mMaxRecordWidth);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    static class AudioHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.fl_recorder)
        FrameLayout mFlRecord;
        @BindView(R.id.tv_second)
        TextView mTvSecond;
        @BindView(R.id.record_anim)
        View mViewAnim;

        private String mFilePath;
        private boolean isPlaying;
        private AudioHolder mPreHolder;

        private AudioHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick({R.id.fl_recorder})
        void onViewClick() {
            if (mPreHolder != null && mPreHolder.isPlaying && mPreHolder != this) {
                mPreHolder.mViewAnim.setBackgroundResource(R.drawable.adj);
                MediaPlayerManager.release();
                mPreHolder.isPlaying = false;
            }
            if (isPlaying) {
                mViewAnim.setBackgroundResource(R.drawable.adj);
                MediaPlayerManager.release();
                isPlaying = false;
            } else {
                mViewAnim.setBackgroundResource(R.drawable.anim_play);
                AnimationDrawable anim = (AnimationDrawable) mViewAnim.getBackground();
                anim.start();
                if (!mFilePath.startsWith("/storage") && !mFilePath.startsWith("http://")) {
                    mFilePath = NetConstant.FILE_URL + mFilePath;
                }
                MediaPlayerManager.playSound(mFilePath, mp -> {
                    mViewAnim.setBackgroundResource(R.drawable.adj);
                    isPlaying = false;
                });
                isPlaying = true;
            }
            mPreHolder = mPreHolder == this ? null : this;
        }
    }
}
