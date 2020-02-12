package com.viking.appchina;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import java.util.Iterator;
import java.util.Locale;

public class BaseVoiceRecognizer implements RecognitionListener {
    private String correctSentence;
    private VoiceRecognitionListener listener;
    private Intent recognizerIntent;
    private SpeechRecognizer speech = null;

    public static String getErrorText(int i) {
        switch (i) {
            case 1:
                return "Network timeout";
            case 2:
                return "Network error";
            case 3:
                return "Audio recording error";
            case 4:
                return "Error from server";
            case 5:
                return "Client side error";
            case 6:
                return "No speech input";
            case 7:
                return "No match";
            case 8:
                return "RecognitionService busy";
            case 9:
                return "Insufficient permissions";
            default:
                return "Didn't understand, please try again.";
        }
    }

    public void onBeginningOfSpeech() {
    }

    public void onBufferReceived(byte[] bArr) {
    }

    public void onEndOfSpeech() {
    }

    public void onEvent(int i, Bundle bundle) {
    }

    public void onPartialResults(Bundle bundle) {
    }

    public void onReadyForSpeech(Bundle bundle) {
    }

    public BaseVoiceRecognizer(Context context, VoiceRecognitionListener voiceRecognitionListener) {
        this.listener = voiceRecognitionListener;
        this.speech = SpeechRecognizer.createSpeechRecognizer(context);
        this.speech.setRecognitionListener(this);
        this.recognizerIntent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        this.recognizerIntent.putExtra("android.speech.extra.LANGUAGE_PREFERENCE", "zh");
        this.recognizerIntent.putExtra("android.speech.extra.LANGUAGE", "zh");
        this.recognizerIntent.putExtra("calling_package", context.getPackageName());
        this.recognizerIntent.putExtra("android.speech.extra.LANGUAGE_MODEL", "web_search");
        this.recognizerIntent.putExtra("android.speech.extra.MAX_RESULTS", 3);
        this.recognizerIntent.putExtra("android.speech.extras.SPEECH_INPUT_POSSIBLY_COMPLETE_SILENCE_LENGTH_MILLIS", BaseImageDownloader.DEFAULT_HTTP_READ_TIMEOUT);
    }

    public void start(String str) {
        this.correctSentence = str;
        this.speech.startListening(this.recognizerIntent);
        this.listener.onStartOfSpeech();
    }

    public void stop() {
        this.speech.cancel();
    }

    public void onRmsChanged(float f) {
        this.listener.onSpeeching((double) f);
    }

    public void onError(int i) {
        this.listener.onErrorOfSpeech(getErrorText(i));
    }

    public void onResults(Bundle bundle) {
        String str = "";
        Iterator it = bundle.getStringArrayList("results_recognition").iterator();
        double d = -1.0d;
        while (it.hasNext()) {
            String str2 = (String) it.next();
            double similarity = similarity(this.correctSentence, str2);
            if (similarity > d) {
                str = str2;
                d = similarity;
            }
        }
        this.listener.onResultOfSpeech(d, str);
    }

    public static double similarity(String str, String str2) {
        if (str.length() < str2.length()) {
            String str3 = str2;
            str2 = str;
            str = str3;
        }
        int length = str.length();
        if (length == 0) {
            return 1.0d;
        }
        double editDistance = (double) (length - editDistance(str, str2));
        double d = (double) length;
        Double.isNaN(editDistance);
        Double.isNaN(d);
        return editDistance / d;
    }

    public static int editDistance(String str, String str2) {
        String lowerCase = str.toLowerCase(Locale.getDefault());
        String lowerCase2 = str2.toLowerCase(Locale.getDefault());
        int[] iArr = new int[(lowerCase2.length() + 1)];
        for (int i = 0; i <= lowerCase.length(); i++) {
            int i2 = i;
            for (int i3 = 0; i3 <= lowerCase2.length(); i3++) {
                if (i == 0) {
                    iArr[i3] = i3;
                } else if (i3 > 0) {
                    int i4 = i3 - 1;
                    int i5 = iArr[i4];
                    if (lowerCase.charAt(i - 1) != lowerCase2.charAt(i4)) {
                        i5 = Math.min(Math.min(i5, i2), iArr[i3]) + 1;
                    }
                    iArr[i4] = i2;
                    i2 = i5;
                }
            }
            if (i > 0) {
                iArr[lowerCase2.length()] = i2;
            }
        }
        return iArr[lowerCase2.length()];
    }
}
