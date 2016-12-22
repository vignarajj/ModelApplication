package com.example.locapp;

import android.os.Bundle;
import android.widget.Toast;

import com.example.locapp.pinsec.ConfirmPinActivity;

/**
 * Created by Vignaraj on 15/12/2016.
 */

public class ConfirmPin extends ConfirmPinActivity {

    private String currentPin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentPin = ConfigPin.pinLockPrefs.getString("pin", "");
    }

    @Override
    public boolean isPinCorrect(String pin) {
        return pin.equals(currentPin);
    }

    @Override
    public void onForgotPin() {
        Toast.makeText(this, "Sorry. Not Implemented", Toast.LENGTH_SHORT).show();
    }
}