package com.example.locapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.locapp.lockpattern.LockPatternUtils;
import com.example.locapp.lockpattern.SetLockPatternActivity;
import com.example.locapp.lockpattern.VerifyLockPatternActivity;

/**
 * Created by Vignaraj on 15/12/2016.
 */
public class MainLockScreen extends Activity {
    private static final int REQUEST_CODE_SET_LOCK_PATTERN = 10001;
    private static final int REQUEST_CODE_VERIFY_LOCK_PATTERN = 10002;

    protected MainView mView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setExistTheme(this);
        //
        mView = new MainView(MainLockScreen.this);
        //
        String savedData = LockPatternUtils.loadFromPreferences(this);
        if (savedData == null) {
            Intent intent = new Intent(this, SetLockPatternActivity.class);
            startActivityForResult(intent, REQUEST_CODE_SET_LOCK_PATTERN);
        } else {
            Intent intent = new Intent(this, VerifyLockPatternActivity.class);
            startActivityForResult(intent, REQUEST_CODE_VERIFY_LOCK_PATTERN);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_SET_LOCK_PATTERN:
                if (resultCode == Activity.RESULT_OK) {
                    mView.updateView(MainView.STATUS_LOCK_PATTERN_SAVED);
                    Toast.makeText(MainLockScreen.this, "Pattern saved", Toast.LENGTH_LONG).show();
                    onBackPressed();
                } else {
                    mView.updateView(MainView.STATUS_LOCK_PATTERN_NOT_SAVED);
                }
                break;

            case REQUEST_CODE_VERIFY_LOCK_PATTERN:
                if (resultCode == Activity.RESULT_OK) {
                    Toast.makeText(MainLockScreen.this, "Pattern verified", Toast.LENGTH_LONG).show();
                    mView.updateView(MainView.STATUS_LOCK_PATTERN_VERFIED);
                    onBackPressed();
                } else {
                    mView.updateView(MainView.STATUS_LOCK_PATTERN_VERFIED_FAILED);
                }
                break;
        }
    }
}
