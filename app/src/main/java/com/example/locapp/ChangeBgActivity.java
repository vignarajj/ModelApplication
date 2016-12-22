package com.example.locapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Ram on 12/13/2016.
 */

public class ChangeBgActivity extends Activity {
    ImageView btn_white, btn_blue, btn_green, btn_violet, btn_red;

    protected void onCreate(Bundle savedInstaceState) {
        super.onCreate(savedInstaceState);
        Utils.changeToTheme(this, Utils.getLastTheme(this));
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_bg);
        btn_white = (ImageView) findViewById(R.id.btn_white);
        btn_blue = (ImageView) findViewById(R.id.btn_blue);
        btn_green = (ImageView) findViewById(R.id.btn_green);
        btn_violet = (ImageView) findViewById(R.id.btn_violet);
        btn_red = (ImageView) findViewById(R.id.btn_red);

        btn_white.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.changeToTheme(ChangeBgActivity.this, Utils.DEFAULT_THEME);
            }
        });

        btn_blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.changeToTheme(ChangeBgActivity.this, Utils.BLUE_THEME);
            }
        });

        btn_green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.changeToTheme(ChangeBgActivity.this, Utils.GREEN_THEME);
            }
        });

        btn_violet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.changeToTheme(ChangeBgActivity.this, Utils.VIOLET_THEME);
            }
        });

        btn_red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.changeToTheme(ChangeBgActivity.this, Utils.RED_THEME);
            }
        });
    }
}
