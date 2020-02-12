package com.viking.appchina;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Iterator;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvSpeak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvSpeak.setOnClickListener(this);
    }

    public void startSpeakerGoogle() {
        Intent intent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "free_form");
        StringBuilder sb = new StringBuilder();
        sb.append("Speak now:\n");
        sb.append("妶".trim());
        intent.putExtra("android.speech.extra.PROMPT", sb.toString());
        intent.putExtra("android.speech.extra.LANGUAGE_PREFERENCE", "zh");
        intent.putExtra("android.speech.extra.LANGUAGE", "zh");
        intent.putExtra("android.speech.extra.MAX_RESULTS", 3);
        intent.putExtra("android.speech.extras.SPEECH_INPUT_POSSIBLY_COMPLETE_SILENCE_LENGTH_MILLIS", 20000);
        try {
            startActivityForResult(intent, 1111);
        } catch (ActivityNotFoundException unused) {
            Toast.makeText(this, "Sorry! Speech recognition is not supported in this device.", Toast.LENGTH_SHORT).show();
        }
    }


//    public void onActivityResult(int i, int i2, Intent intent) {
//        super.onActivityResult(i, i2, intent);
//        if (i == 1111 && i2 == -1 && intent != null) {
//            double d = -1.0d;
//            Iterator it = intent.getStringArrayListExtra("android.speech.extra.RESULTS").iterator();
//            /*while (it.hasNext()) {
//                double similarity = BaseVoiceRecognizer.similarity(getNameSpeech(), (String) it.next());
//                if (similarity > d) {
//                    d = similarity;
//                }
//            }*/
//            int i3 = d > 0.92d ? 5 : d > 0.8d ? 4 : d > 0.6d ? 3 : d > 0.4d ? 2 : 1;
//            // this.phraseCurrentEntry.setStar(i3);
//            // new BookMarkDB(this).addBookmarkEntry(this.phraseCurrentEntry);
//            StringBuilder sb = new StringBuilder();
//            sb.append(i3);
//            sb.append(" star");
//            //Toast.makeText(this, sb.toString(), 0).show();
//            Toast.makeText(this, "sb", Toast.LENGTH_SHORT).show();
//            //this.curentFragment.updateStar(i3);
//        }
//    }


    @SuppressLint("WrongConstant")
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
            //new BookMarkDB(this).addBookmarkEntry(this.phraseCurrentEntry);
            StringBuilder sb = new StringBuilder();
            sb.append(i3);
            sb.append(" star");
            Toast.makeText(this, sb.toString(), 0).show();
            //this.curentFragment.updateStar(i3);
        }
    }

    public String getNameSpeech() {
        return "妶".trim().replace("?", "").replace("'", "").trim().replace(".", "").trim().trim().replace(",", "").trim().trim().replace("!", "").trim();
    }

    @Override
    public void onClick(View view) {
    }
}
