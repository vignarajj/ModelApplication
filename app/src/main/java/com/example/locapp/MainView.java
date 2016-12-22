package com.example.locapp;

import android.widget.TextView;


public class MainView {
    public static final int STATUS_LOCK_PATTERN_SAVED = 0;
    public static final int STATUS_LOCK_PATTERN_NOT_SAVED = 1;
    public static final int STATUS_LOCK_PATTERN_VERFIED = 2;
    public static final int STATUS_LOCK_PATTERN_VERFIED_FAILED = 3;

    private MainLockScreen mActivity = null;

    protected TextView mTipTextView = null;

    public MainView(MainLockScreen activity) {
        mActivity = activity;
        //
        Utils.setExistTheme(activity);
        mActivity.setContentView(R.layout.activity_mainview);
        //
        mTipTextView = (TextView) mActivity.findViewById(R.id.tip_textview);
    }

    public void updateView(int status) {
        switch (status) {
            case STATUS_LOCK_PATTERN_SAVED:
                mTipTextView.setText("Pattern Saved");
                break;
            case STATUS_LOCK_PATTERN_NOT_SAVED:
                mTipTextView.setText("Not Saved");
                break;

            case STATUS_LOCK_PATTERN_VERFIED:
                mTipTextView.setText("Pattern Verified");
                break;

            case STATUS_LOCK_PATTERN_VERFIED_FAILED:
                mTipTextView.setText("Pattern Verification Failed");
                break;
        }
    }
}