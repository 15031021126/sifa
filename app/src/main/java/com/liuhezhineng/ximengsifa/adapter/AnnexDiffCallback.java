package com.liuhezhineng.ximengsifa.adapter;

import android.support.v7.util.DiffUtil;

import java.util.ArrayList;

/**
 * @author : qingfeng
 * e-mail : 1913518036@qq.com
 * time   : 2019/03/07
 * desc   :
 * version: 1.0
 */
public class AnnexDiffCallback extends DiffUtil.Callback {

    private ArrayList<String> mOldAnnexList;
    private ArrayList<String> mNewAnnexList;

    public AnnexDiffCallback(ArrayList<String> oldAnnexList, ArrayList<String> newAnnexList) {
        mOldAnnexList = oldAnnexList;
        mNewAnnexList = newAnnexList;
    }

    @Override
    public int getOldListSize() {
        return null == mOldAnnexList ? 0 : mOldAnnexList.size();
    }

    @Override
    public int getNewListSize() {
        return null == mNewAnnexList ? 0 : mNewAnnexList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldAnnexList.get(oldItemPosition).getClass().equals(mNewAnnexList.get(newItemPosition).getClass());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldAnnexList.get(oldItemPosition).equals(mNewAnnexList.get(newItemPosition));
    }
}
