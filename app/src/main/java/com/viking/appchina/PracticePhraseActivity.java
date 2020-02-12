package com.viking.appchina;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;


public class PracticePhraseActivity extends AppCompatActivity {

    int countSpeech = 0;
    int indexCurrent = 0;
    boolean isSearch = false;
    boolean isSuccessVoice = false;
    double levelsoundBefore = -2.5d;
    Speaker speaker;
    NoPopupVoiceRecognizer voice;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.speaker = new Speaker(this, findViewById(R.id.fr_speaker));
        this.speaker.setColorDefault(Color.RED);
        this.speaker.show();
        findViewById(R.id.fr_speaker).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(PracticePhraseActivity.this, "android.permission.RECORD_AUDIO") != 0) {
                    ActivityCompat.requestPermissions(PracticePhraseActivity.this, new String[]{"android.permission.RECORD_AUDIO"}, 10);
                } else if (!UtilFunction.isOnline(PracticePhraseActivity.this)) {
                    // khong co mang
                } else if (ReferenceControl.isSpeakerError(PracticePhraseActivity.this).booleanValue()) {
                    PracticePhraseActivity.this.startSpeakerGoogle();
                } else if (PracticePhraseActivity.this.speaker.isVoicing()) {
                    if (PracticePhraseActivity.this.voice != null) {
                        PracticePhraseActivity.this.voice.listen(PracticePhraseActivity.this.getNameSpeech());
                        PracticePhraseActivity.this.countSpeech++;
                    }
                } else {
                    speaker.moveUp(500);
                    PracticePhraseActivity practicePhraseActivity = PracticePhraseActivity.this;
                    StringBuilder sb = new StringBuilder();
                    sb.append(PracticePhraseActivity.this.getString(R.string.start_speaking));
                    sb.append(": ");
                    sb.append("好");
                    Toast makeText = Toast.makeText(practicePhraseActivity, sb.toString(), Toast.LENGTH_LONG);
                    makeText.setGravity(17, 0, 0);
                    makeText.show();
                    new Timer().schedule(new TimerTask() {
                        public void run() {
                            PracticePhraseActivity.this.handlerStartVoice.sendEmptyMessage(0);
                        }
                    }, 500);
                }
            }
        });
    }
    public void startSpeakerGoogle() {
        Intent intent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "free_form");
        StringBuilder sb = new StringBuilder();
        sb.append("Speak now:\n");
        sb.append("好");
        intent.putExtra("android.speech.extra.PROMPT", sb.toString());
        intent.putExtra("android.speech.extra.LANGUAGE_PREFERENCE", "zh");
        intent.putExtra("android.speech.extra.LANGUAGE", "zh");
        intent.putExtra("android.speech.extra.MAX_RESULTS", 3);
        intent.putExtra("android.speech.extras.SPEECH_INPUT_POSSIBLY_COMPLETE_SILENCE_LENGTH_MILLIS", BaseImageDownloader.DEFAULT_HTTP_READ_TIMEOUT);
        try {
            startActivityForResult(intent, 1111);
        } catch (ActivityNotFoundException unused) {
            Toast.makeText(getApplicationContext(), "Sorry! Speech recognition is not supported in this device.", Toast.LENGTH_SHORT).show();
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1111 && i2 == -1 && intent != null) {
            double d = -1.0d;
            Iterator it = intent.getStringArrayListExtra("android.speech.extra.RESULTS").iterator();
            while (it.hasNext()) {
                double similarity = BaseVoiceRecognizer.similarity(getNameSpeech(), (String) it.next());
                if (similarity > d) {
                    d = similarity;
                }
            }
            int i3 = d > 0.92d ? 5 : d > 0.8d ? 4 : d > 0.6d ? 3 : d > 0.4d ? 2 : 1;
            //this.phraseCurrentEntry.setStar(i3);
           // new BookMarkDB(this).addBookmarkEntry(this.phraseCurrentEntry);
            StringBuilder sb = new StringBuilder();
            sb.append(i3);
            sb.append(" star");
            Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();
            //this.curentFragment.updateStar(i3);
        }
    }

    public String getNameSpeech() {
        return "好".trim().replace("?", "").replace("'", "").trim().replace(".", "").trim().trim().replace(",", "").trim().trim().replace("!", "").trim();
    }



    final android.os.Handler handlerStartVoice = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message message) {
            PracticePhraseActivity practicePhraseActivity = PracticePhraseActivity.this;
             countSpeech = 0;
            startVoice();
            return false;
        }
    });

    public void startVoice() {
        if (this.voice == null) {
            this.voice = new NoPopupVoiceRecognizer(this, new VoiceRecognitionListener() {
                public void onStartOfSpeech() {
                    PracticePhraseActivity.this.speaker.setVoicing(true);
                    PracticePhraseActivity practicePhraseActivity = PracticePhraseActivity.this;
                    practicePhraseActivity.levelsoundBefore = -2.5d;
                    practicePhraseActivity.speaker.update(PracticePhraseActivity.this.levelsoundBefore);
                    if (!PracticePhraseActivity.this.isSuccessVoice) {
                        new android.os.Handler().postDelayed(new Runnable() {
                            public void run() {
                                if (!PracticePhraseActivity.this.isSuccessVoice) {
                                    Toast.makeText(PracticePhraseActivity.this, "ERROR. TRY AGAIN", Toast.LENGTH_SHORT).show();
                                    PracticePhraseActivity.this.speaker.stop();
                                    PracticePhraseActivity.this.speaker.moveDown(600);
                                    PracticePhraseActivity.this.voice.stop();
                                    ReferenceControl.setSpeakerError(PracticePhraseActivity.this, Boolean.valueOf(true));
                                }
                            }
                        }, 5000);
                    }
                }

                public void onSpeeching(double d) {
                    double d2;
                    PracticePhraseActivity practicePhraseActivity = PracticePhraseActivity.this;
                    practicePhraseActivity.isSuccessVoice = true;
                    if (d > practicePhraseActivity.levelsoundBefore) {
                        d2 = PracticePhraseActivity.this.levelsoundBefore + 0.9d;
                    } else {
                        d2 = PracticePhraseActivity.this.levelsoundBefore - 0.4d;
                    }
                    PracticePhraseActivity.this.speaker.update(d2);
                    PracticePhraseActivity.this.levelsoundBefore = d2;
                }

                public void onResultOfSpeech(double d, String str) {
                    int i = d > 0.92d ? 5 : d > 0.8d ? 4 : d > 0.6d ? 3 : d > 0.4d ? 2 : 1;
                    //PracticePhraseActivity.this.phraseCurrentEntry.setStar(i);
                    //new BookMarkDB(PracticePhraseActivity.this).addBookmarkEntry(PracticePhraseActivity.this.phraseCurrentEntry);
                    PracticePhraseActivity practicePhraseActivity = PracticePhraseActivity.this;
                    StringBuilder sb = new StringBuilder();
                    sb.append(i);
                    sb.append(" star");
                    Toast.makeText(practicePhraseActivity, sb.toString(), Toast.LENGTH_SHORT).show();
                    //PracticePhraseActivity.this.curentFragment.updateStar(i);
                    PracticePhraseActivity.this.speaker.stop();
                    PracticePhraseActivity.this.speaker.moveDown(600);
                    PracticePhraseActivity.this.voice.stop();
                    PracticePhraseActivity.this.countSpeech = 0;
                }

                public void onErrorOfSpeech(String str) {
                    if (PracticePhraseActivity.this.countSpeech > 3) {
                        if (PracticePhraseActivity.this.speaker.isVoicing()) {
                            PracticePhraseActivity practicePhraseActivity = PracticePhraseActivity.this;
                            StringBuilder sb = new StringBuilder();
                            sb.append(str);
                            sb.append(". TRY AGAIN");
                            Toast.makeText(practicePhraseActivity, sb.toString(), Toast.LENGTH_SHORT).show();
                            PracticePhraseActivity.this.speaker.stop();
                            PracticePhraseActivity.this.speaker.moveDown(600);
                            PracticePhraseActivity.this.voice.stop();
                        }
                        return;
                    }
                    PracticePhraseActivity.this.voice.listen(PracticePhraseActivity.this.getNameSpeech());
                    PracticePhraseActivity.this.countSpeech++;
                }
            });
        }
        this.voice.listen(getNameSpeech());
    }

}
