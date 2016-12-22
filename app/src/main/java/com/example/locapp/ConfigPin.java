package com.example.locapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.locapp.pinsec.PinListener;

/**
 * Created by Vignaraj on 15/12/2016.
 */

public class ConfigPin extends Activity {

    public static final int REQUEST_CODE_SET_PIN = 0;
    public static final int REQUEST_CODE_CHANGE_PIN = 1;
    public static final int REQUEST_CODE_CONFIRM_PIN = 2;
    static SharedPreferences pinLockPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setExistTheme(this);
        setContentView(R.layout.activity_configpin);
        pinLockPrefs = getSharedPreferences("PinLockPrefs", MODE_PRIVATE);
        init();
    }

    private void init() {
        TextView setPin = (TextView) findViewById(R.id.set_pin);
        TextView confirmPin = (TextView) findViewById(R.id.confirm_pin);

        String pin = pinLockPrefs.getString("pin", "");
        if (pin.equals("")) {
            confirmPin.setEnabled(false);
        } else {
            setPin.setText("Change PIN");
        }

        View.OnClickListener clickListener = getOnClickListener();
        setPin.setOnClickListener(clickListener);
        confirmPin.setOnClickListener(clickListener);
    }

    @NonNull
    private View.OnClickListener getOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                String pin = pinLockPrefs.getString("pin", "");

                if (id == R.id.set_pin && pin.equals("")) {
                    Intent intent = new Intent(ConfigPin.this, SetPin.class);
                    startActivityForResult(intent, REQUEST_CODE_SET_PIN);
                } else if (id == R.id.set_pin) {
                    Intent intent = new Intent(ConfigPin.this, ConfirmPin.class);
                    startActivityForResult(intent, REQUEST_CODE_CHANGE_PIN);
                } else if (id == R.id.confirm_pin) {
                    Intent intent = new Intent(ConfigPin.this, ConfirmPin.class);
                    startActivityForResult(intent, REQUEST_CODE_CONFIRM_PIN);
                }
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case REQUEST_CODE_SET_PIN : {
                if(resultCode == PinListener.SUCCESS){
                    Toast.makeText(this, "Pin is set :)", Toast.LENGTH_SHORT).show();
                } else if(resultCode == PinListener.CANCELLED) {
                    Toast.makeText(this, "Pin set cancelled :|", Toast.LENGTH_SHORT).show();
                }
                refreshActivity();
                break;
            }
            case REQUEST_CODE_CHANGE_PIN : {
                if(resultCode == PinListener.SUCCESS){
                    Intent intent = new Intent(ConfigPin.this, SetPin.class);
                    startActivityForResult(intent, REQUEST_CODE_SET_PIN);
                } else if(resultCode == PinListener.CANCELLED){
                    Toast.makeText(this, "Pin change cancelled :|", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case REQUEST_CODE_CONFIRM_PIN : {
                if(resultCode == PinListener.SUCCESS){
                    Toast.makeText(this, "Pin is correct :)", Toast.LENGTH_SHORT).show();
                } else if(resultCode == PinListener.CANCELLED) {
                    Toast.makeText(this, "Pin confirm cancelled :|", Toast.LENGTH_SHORT).show();
                }
                break;
            }

        }
    }

    private void refreshActivity() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}

