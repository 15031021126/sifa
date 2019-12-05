package com.liuhezhineng.ximengsifa.audiorecord;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.liuhezhineng.ximengsifa.R;

public class RecordDialogManager {

    private Dialog mDialog;
    private Context mContext;
    private ImageView mIcon, mVoice;
    private TextView mLabel;

    public RecordDialogManager(Context mContext) {
        this.mContext = mContext;
    }

    public void showRecordingDialog() {
        mDialog = new Dialog(mContext, R.style.Theme_AudioDialog);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.dialog_audio, null);
        mDialog.setContentView(view);

        mIcon = mDialog.findViewById(R.id.img_recdlg_icon);
        mVoice = mDialog.findViewById(R.id.img_recdlg_voice);
        mLabel = mDialog.findViewById(R.id.txt_recdlg_label);

        mDialog.show();
    }

    public void dismissDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
            mDialog = null;
        }
    }

    public void recording() {
        if (mDialog != null && mDialog.isShowing()) {
            mIcon.setVisibility(View.VISIBLE);
            mVoice.setVisibility(View.VISIBLE);
            mLabel.setVisibility(View.VISIBLE);

            mIcon.setImageResource(R.drawable.recorder);
            mLabel.setText(R.string.str_audiorecdlg_label_recording);
        }
    }

    /**
     * 更新声音级别的图片
     *
     * @param level must be 1-7
     */
    public void updateVoiceLevel(int level) {
        if (mDialog != null && mDialog.isShowing()) {
            //通过level获取resId
            int resId = mContext.getResources().getIdentifier("v" + level,
                    "drawable", mContext.getPackageName());
            mVoice.setImageResource(resId);
        }
    }

    public void wantToCancel() {
        if (mDialog != null && mDialog.isShowing()) {
            mIcon.setVisibility(View.VISIBLE);
            mVoice.setVisibility(View.GONE);
            mLabel.setVisibility(View.VISIBLE);

            mIcon.setImageResource(R.drawable.cancel);
            mLabel.setText(R.string.str_audiorecbtn_want_cancel);
        }
    }

    public void tooShort() {
        if (mDialog != null && mDialog.isShowing()) {
            mIcon.setVisibility(View.VISIBLE);
            mVoice.setVisibility(View.GONE);
            mLabel.setVisibility(View.VISIBLE);

            mIcon.setImageResource(R.drawable.voice_to_short);
            mLabel.setText(R.string.str_audiorecdlg_label_too_short);
        }
    }
}
