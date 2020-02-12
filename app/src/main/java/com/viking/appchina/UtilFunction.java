package com.viking.appchina;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;

import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.view.ViewCompat;

import java.io.IOException;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.Arrays;


public class UtilFunction {

    public static boolean existFileAsset(Context context, String str, String str2) {
        try {
            return Arrays.asList(context.getResources().getAssets().list(str)).contains(str2);
        } catch (IOException unused) {
            return false;
        }
    }

    public static void sleep(int i) {
        try {
            Thread.sleep((long) i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void closeKeyboard(Activity activity) {
        ((InputMethodManager) activity.getSystemService("input_method")).hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    public static void closeKeyboard(Context context, EditText editText) {
        if (context != null && editText != null) {
            ((InputMethodManager) context.getSystemService("input_method")).hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }

    public static void openKeyboard(Context context, EditText editText) {
        if (context != null && editText != null) {
            ((InputMethodManager) context.getSystemService("input_method")).showSoftInput(editText, 0);
        }
    }

    public static void fadeinView(final View view, int i, final int i2, int i3) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setFillBefore(false);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setDuration((long) i3);
        alphaAnimation.setAnimationListener(new AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                view.findViewById(i2).setVisibility(8);
            }
        });
        view.findViewById(i).startAnimation(alphaAnimation);
    }

    public static void fadeinView(final Activity activity, int i, final int i2, int i3) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setFillBefore(false);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setDuration((long) i3);
        alphaAnimation.setAnimationListener(new AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                activity.findViewById(i2).setVisibility(8);
            }
        });
        activity.findViewById(i).startAnimation(alphaAnimation);
    }

    public static void fadeInView(View view, int i) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setFillBefore(false);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setDuration((long) i);
        view.startAnimation(alphaAnimation);
    }

    public static void fadein(Activity activity, int i, int i2) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setFillBefore(false);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setDuration((long) i2);
        activity.findViewById(i).startAnimation(alphaAnimation);
    }

    public static int getSreenWidth(Context context, double d) {
        double d2 = (double) context.getResources().getDisplayMetrics().widthPixels;
        Double.isNaN(d2);
        return (int) (d2 * d);
    }

    public static int getSreenHeight(Context context, double d) {
        double d2 = (double) context.getResources().getDisplayMetrics().heightPixels;
        Double.isNaN(d2);
        return (int) (d2 * d);
    }



    public static int getAlphaColor(int i, int i2) {
        return (i & ViewCompat.MEASURED_SIZE_MASK) | (((i2 * 255) / 100) << 24);
    }

    public static boolean isOnline(Context context) {
        @SuppressLint("WrongConstant") NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

}
