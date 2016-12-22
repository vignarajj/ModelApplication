package com.example.locapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * Created by Vignaraj on 14/12/2016.
 */
public class Utils {
    private static int sTheme;
    public final static int DEFAULT_THEME = 0;
    public final static int BLUE_THEME = 1;
    public final static int GREEN_THEME = 2;
    public final static int VIOLET_THEME = 3;
    public final static int RED_THEME = 4;

    public final static String THEME_PREF="theme_pref";

    public static void changeToTheme(Activity activity, int theme) {
        setThemes(activity, theme);
        sTheme = theme;
        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));
    }
    public static void onActivityCreateSetTheme(Activity activity) {
        switch (sTheme) {
            default:
            case DEFAULT_THEME:
                activity.setTheme(R.style.WhiteTheme);
                break;
            case BLUE_THEME:
                activity.setTheme(R.style.BlueTheme);
                break;
            case GREEN_THEME:
                activity.setTheme(R.style.GreenTheme);
                break;
            case VIOLET_THEME:
                activity.setTheme(R.style.VioletTheme);
                break;
            case RED_THEME:
                activity.setTheme(R.style.RedTheme);
                break;
        }
    }
    public static int getLastTheme(Activity activity){
        SharedPreferences prefs = activity.getSharedPreferences(THEME_PREF, Context.MODE_PRIVATE);
        int lastTheme = prefs.getInt("theme", -1);
        return lastTheme;
    }
    public static void setThemes(Activity activity, int theme){
        SharedPreferences.Editor editor = activity.getSharedPreferences(THEME_PREF, Context.MODE_PRIVATE).edit();
        editor.putInt("theme", theme);
        editor.commit();
    }
    public static void setExistTheme(Activity activity){
        int lastTheme = getLastTheme(activity);
        switch (lastTheme) {
            default:
            case DEFAULT_THEME:
                activity.setTheme(R.style.WhiteTheme);
                break;
            case BLUE_THEME:
                activity.setTheme(R.style.BlueTheme);
                break;
            case GREEN_THEME:
                activity.setTheme(R.style.GreenTheme);
                break;
            case VIOLET_THEME:
                activity.setTheme(R.style.VioletTheme);
                break;
            case RED_THEME:
                activity.setTheme(R.style.RedTheme);
                break;
        }
    }
}
