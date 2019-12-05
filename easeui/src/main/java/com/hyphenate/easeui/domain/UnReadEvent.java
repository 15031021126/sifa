package com.hyphenate.easeui.domain;

/**
 * <pre>
 *     author : qingfeng
 *     e-mail : 1913518036@qq.com
 *     time   : 2018/12/14
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class UnReadEvent {

    private int unReadCount;

    public UnReadEvent(int unReadCount) {
        this.unReadCount = unReadCount;
    }

    public int getUnReadCount() {
        return unReadCount;
    }

    public void setUnReadCount(int unReadCount) {
        this.unReadCount = unReadCount;
    }
}
