package com.viking.appchina;

import android.content.Context;

public class NoPopupVoiceRecognizer {
    private BaseVoiceRecognizer voiceRecognizer;

    public NoPopupVoiceRecognizer(Context context, VoiceRecognitionListener voiceRecognitionListener) {
        this.voiceRecognizer = new BaseVoiceRecognizer(context, voiceRecognitionListener);
    }

    public void listen(String str) {
        this.voiceRecognizer.start(str);
    }

    public void stop() {
        this.voiceRecognizer.stop();
    }
}
