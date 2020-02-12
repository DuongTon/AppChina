package com.viking.appchina;

import android.content.Context;
import android.content.SharedPreferences.Editor;


public class ReferenceControl {
    private static final String CODE_LANGUAGE = "CODE_LANGUAGE";
    private static final String COLOR_CHOSEN = "COLOR_CHOSEN";
    private static final String INTERVAL_HALF_HOUR_REMIND = "INTERVAL_HALF_HOUR_REMIND";
    private static final String IS_LOGED_IN_FACEBOOK = "IS_LOGED_IN_FACEBOOK";
    private static final String IS_RATED_APP = "IS_RATED_APP";
    private static final String IS_SPEAKER_ERROR_ON_DEVICE = "IS_SPEAKER_ERROR_ON_DEVICE";
    private static final String IS_TRANSLATE_WORD = "IS_TRANSLATE_WORD";
    private static final String LANGUAGE = "LANGUAGE";
    private static final String MY_REF = "MY_REF";
    private static final String NUMBER_ADS_APP = "NUMBER_ADS_APP";
    private static final String SOUND_WHEN_REMIND = "SOUND_WHEN_REMIND";

    public static void setChooseColor(Context context, int i) {
        Editor edit = context.getSharedPreferences(MY_REF, 0).edit();
        edit.putInt(COLOR_CHOSEN, i);
        edit.commit();
    }

    public static int getChosenColor(Context context, int i) {
        return context.getSharedPreferences(MY_REF, 0).getInt(COLOR_CHOSEN, i);
    }

    public static void setIsTranslateWord(Context context, Boolean bool) {
        Editor edit = context.getSharedPreferences(MY_REF, 0).edit();
        edit.putBoolean(IS_TRANSLATE_WORD, bool.booleanValue());
        edit.commit();
    }

    public static Boolean getIsTranslateWord(Context context) {
        return Boolean.valueOf(context.getSharedPreferences(MY_REF, 0).getBoolean(IS_TRANSLATE_WORD, true));
    }

    public static int getIntValue(Context context, String str) {
        return context.getSharedPreferences(MY_REF, 0).getInt(str, 0);
    }

    public static void putIntValue(Context context, String str, int i) {
        Editor edit = context.getSharedPreferences(MY_REF, 0).edit();
        edit.putInt(str, i);
        edit.commit();
    }

    public static void setLanguageCode(Context context, String str) {
        Editor edit = context.getSharedPreferences(MY_REF, 0).edit();
        edit.putString(CODE_LANGUAGE, str);
        edit.commit();
    }

    public static String getLanguageCode(Context context) {
        return context.getSharedPreferences(MY_REF, 0).getString(CODE_LANGUAGE, "");
    }

    public static void setLanguage(Context context, String str) {
        Editor edit = context.getSharedPreferences(MY_REF, 0).edit();
        edit.putString(LANGUAGE, str);
        edit.commit();
    }

    public static String getLanguage(Context context) {
        return context.getSharedPreferences(MY_REF, 0).getString(LANGUAGE, context.getResources().getString(R.string.language_no_selected));
    }

    public static void setRatedApp(Context context, Boolean bool) {
        Editor edit = context.getSharedPreferences(MY_REF, 0).edit();
        edit.putBoolean(IS_RATED_APP, bool.booleanValue());
        edit.commit();
    }

    public static Boolean isRatedApp(Context context) {
        return Boolean.valueOf(context.getSharedPreferences(MY_REF, 0).getBoolean(IS_RATED_APP, false));
    }

    public static void setLogedInFacebook(Context context, Boolean bool) {
        Editor edit = context.getSharedPreferences(MY_REF, 0).edit();
        edit.putBoolean(IS_LOGED_IN_FACEBOOK, bool.booleanValue());
        edit.commit();
    }

    public static Boolean isLogedInFacebook(Context context) {
        return Boolean.valueOf(context.getSharedPreferences(MY_REF, 0).getBoolean(IS_LOGED_IN_FACEBOOK, false));
    }

    public static int getSoundRemind(Context context) {
        return getIntValue(context, SOUND_WHEN_REMIND);
    }

    public static void setSoundRemind(Context context, int i) {
        putIntValue(context, SOUND_WHEN_REMIND, i);
    }

    public static int getIntervalHaftHourReminder(Context context) {
        return context.getSharedPreferences(MY_REF, 0).getInt(INTERVAL_HALF_HOUR_REMIND, 4);
    }

    public static void setIntervalHaftHourReminder(Context context, int i) {
        putIntValue(context, INTERVAL_HALF_HOUR_REMIND, i);
    }

    public static int getNumAdsApp(Context context) {
        return context.getSharedPreferences(MY_REF, 0).getInt(NUMBER_ADS_APP, 0);
    }

    public static void setNumAdsApp(Context context, int i) {
        putIntValue(context, NUMBER_ADS_APP, i);
    }

    public static void setSpeakerError(Context context, Boolean bool) {
        Editor edit = context.getSharedPreferences(MY_REF, 0).edit();
        edit.putBoolean(IS_SPEAKER_ERROR_ON_DEVICE, bool.booleanValue());
        edit.commit();
    }

    public static Boolean isSpeakerError(Context context) {
        return Boolean.valueOf(context.getSharedPreferences(MY_REF, 0).getBoolean(IS_SPEAKER_ERROR_ON_DEVICE, false));
    }
}
