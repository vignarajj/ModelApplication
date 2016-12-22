package com.example.locapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
    TextView btn_misscall, txt_pin, txt_pattern, txt_info;
    ImageView btn_white, btn_blue, btn_green, btn_violet, btn_red;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Utils.getLastTheme(this) == -1) {
            Utils.onActivityCreateSetTheme(this);
        } else {
            Utils.setExistTheme(this);
        }
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        btn_misscall = (TextView) findViewById(R.id.txt_missedcall);
        txt_pin = (TextView) findViewById(R.id.txt_change_pin);
        txt_pattern = (TextView) findViewById(R.id.txt_change_pattern);
        txt_info = (TextView) findViewById(R.id.txt_info);
        btn_white = (ImageView) findViewById(R.id.btn_white);
        btn_blue = (ImageView) findViewById(R.id.btn_blue);
        btn_green = (ImageView) findViewById(R.id.btn_green);
        btn_violet = (ImageView) findViewById(R.id.btn_violet);
        btn_red = (ImageView) findViewById(R.id.btn_red);

        btn_white.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.changeToTheme(MainActivity.this, Utils.DEFAULT_THEME);
            }
        });

        btn_blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.changeToTheme(MainActivity.this, Utils.BLUE_THEME);
            }
        });

        btn_green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.changeToTheme(MainActivity.this, Utils.GREEN_THEME);
            }
        });

        btn_violet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.changeToTheme(MainActivity.this, Utils.VIOLET_THEME);
            }
        });

        btn_red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.changeToTheme(MainActivity.this, Utils.RED_THEME);
            }
        });

        btn_misscall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MissedCall.class));
            }
        });
        txt_pin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ConfigPin.class));
            }
        });
        txt_pattern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainLockScreen.class));
            }
        });
        txt_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InfoActivity.class));
            }
        });
    }

    public void onResume() {
        super.onResume();
    }
}