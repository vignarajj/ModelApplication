package com.example.locapp.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.GridView;

import com.example.locapp.pinsec.PinListener;
import com.example.locapp.R;

public class Keypad extends GridView {


    /**
     * Stores the PIN value entered by user
     */
    private static String pin = "";


    /**
     * TypedArray of styled attributes passed to the element
     */
    private final TypedArray styledAttributes;


    /**
     * Stores the context of current activity
     */
    private final Context context;

    public Keypad(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        styledAttributes = context.obtainStyledAttributes(R.style.PinLock, R.styleable.PinLock);
        setNumColumns(3);
        setSpacing();
    }


    /**
     * Setting up vertical and horizontal spacing for the view
     */
    private void setSpacing() {
        final int verticalSpacing = styledAttributes.getDimensionPixelOffset(R.styleable.PinLock_keypadVerticalSpacing, 2);
        final int horizontalSpacing = styledAttributes.getDimensionPixelOffset(R.styleable.PinLock_keypadHorizontalSpacing, 2);
        setVerticalSpacing(verticalSpacing);
        setHorizontalSpacing(horizontalSpacing);
    }


    /**
     * Setting up layout dimensional parameters for the view
     */
    public void setLayoutParameters() {
        final int keypadWidth = styledAttributes.getDimensionPixelOffset(R.styleable.PinLock_keypadWidth, 200);
        final int keypadHeight = styledAttributes.getDimensionPixelOffset(R.styleable.PinLock_keypadHeight, 230);
        final FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(keypadWidth, keypadHeight);
        params.gravity = 17;
        setLayoutParams(params);
    }


    /**
     * Setting listener for PIN lock which handles pinChange, forgotPIN etc
     * @param pinListener Implementation of Interface PinListener.
     * @see PinListener
     */
    public void setPinListener(PinListener pinListener) {
        setAdapter(new KeypadAdapter(context, styledAttributes, pinListener));
    }


    /**
     * Getting the value of key pressed and appending it to current PIN
     */
    public static String onKeyPress(String key) {
        pin = pin.concat(key);
        return pin;
    }


    /**
     * Reset current PIN to initial state
     */
    public static void resetPin(){
        pin = "";
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setLayoutParameters();
    }

    /**
     * Executed just before destroying Keypad object. Used to recycle StyledAttributes properly
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        styledAttributes.recycle();
    }
}
