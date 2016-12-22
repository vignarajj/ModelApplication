package com.example.locapp;

/**
 * Created by Vignaraj on 15/12/2016.
 */
import android.content.SharedPreferences;

import com.example.locapp.pinsec.SetPinActivity;

public class SetPin extends SetPinActivity {

    @Override
    public void onPinSet(String pin) {
        SharedPreferences.Editor edit = ConfigPin.pinLockPrefs.edit();
        edit.putString("pin", pin);
        edit.commit();
        setResult(SUCCESS);
        finish();
    }
}
