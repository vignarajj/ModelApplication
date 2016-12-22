package com.example.locapp.pinsec;

import android.os.Bundle;

import com.example.locapp.R;

public abstract class SetPinActivity extends BasePinActivity {


    /**
     * Stores the first PIN entered by user. Used for confirmation
     */
    private String firstPin = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLabel(getString(R.string.message_enter_new_pin));
        disableForgotButton();
    }


    /**
     * Implementation of BasePinActivity method
     * @param pin PIN value entered by user
     */
    @Override
    public final void onCompleted(String pin) {
        resetStatus();
        if ("".equals(firstPin)) {
            firstPin = pin;
            setLabel(getString(R.string.message_confirm_pin));
        } else {
            if (pin.equals(firstPin)) {
                onPinSet(pin);
                setResult(SUCCESS);
                finish();
            } else {
                setLabel(getString(R.string.message_pin_mismatch));
                firstPin = "";
            }
        }
        resetStatus();
    }

    /**
     * Abstract method which gives the PIN entered by user
     * @param pin PIN value entered by user
     */
    public abstract void onPinSet(String pin);

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(CANCELLED);
        finish();
    }
}
