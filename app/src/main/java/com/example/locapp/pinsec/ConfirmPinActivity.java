
package com.example.locapp.pinsec;

import android.os.Bundle;

import com.example.locapp.*;

public abstract class ConfirmPinActivity extends BasePinActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLabel(getString(R.string.message_enter_pin));
    }


    /**
     * Implementation of BasePinActivity method
     * @param pin PIN value entered by user
     */
    @Override
    public final void onCompleted(String pin) {
        resetStatus();
        if (isPinCorrect(pin)) {
            setResult(SUCCESS);
            finish();
        } else {
            setLabel(getString(R.string.message_invalid_pin));
        }
    }


    /**
     * Abstract method which decides the PIN entered by user is correct or not
     * @param pin PIN value entered by user
     * @return Boolean value indicates the status of PIN entered
     */
    public abstract boolean isPinCorrect(String pin);


    /**
     * Abstract method which handles PIN forgot scenario
     */
    @Override
    public abstract void onForgotPin();


    @Override
    public void onBackPressed() {
        setResult(CANCELLED);
        finish();
    }
}
