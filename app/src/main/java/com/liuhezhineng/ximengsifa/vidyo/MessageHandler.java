package com.liuhezhineng.ximengsifa.vidyo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.vidyo.provider.ChitVideo;


/**
 * Message handler to handle vidyo out event.
 */
public class MessageHandler extends Handler {
    private ConferenceActivity mActivity;

    public String TAG = "MessageHandler";

    public MessageHandler(final ConferenceActivity activity) {
        super();
        mActivity = activity;
    }

    @Override
    public void handleMessage(final Message msg) {

        if (mActivity != null && mActivity.isFinishing()) {
            return;
        }

        Bundle b = msg.getData();
        int event = msg.what;
        String info = b.getString(ChitVideo.KEY_INFO);
        int errCode = b.getInt(ChitVideo.KEY_ERROR_CODE);

        mActivity.RequestResult(event, info, errCode);

    }

}
