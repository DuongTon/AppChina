package com.viking.appchina;

public interface VoiceRecognitionListener {
    void onErrorOfSpeech(String str);

    void onResultOfSpeech(double d, String str);

    void onSpeeching(double d);

    void onStartOfSpeech();
}
