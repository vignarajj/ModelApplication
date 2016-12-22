package com.example.locapp.pinsec;

public interface PinListener {


    /**
     * Response code for operation success
     */
    int SUCCESS = 0;


    /**
     * Response code for operation cancelled
     */
    int CANCELLED = 1;


    /**
     * Response code for invalid PIN
     */
    int INVALID = 3;


    /**
     * Response code for forgot PIN
     */
    int FORGOT = 4;


    /**
     * Invokes when user completes entering PIN
     * @param pin PIN value entered by user
     */
    void onCompleted(String pin);


    /**
     * Invokes when user clicks on Keypad
     * @param length Current length of PIN
     */
    void onPinValueChange(int length);


    /**
     * Invokes when user clicks forgot button
     */
    void onForgotPin();
}
