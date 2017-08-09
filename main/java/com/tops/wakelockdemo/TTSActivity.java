package com.tops.wakelockdemo;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class TTSActivity extends AppCompatActivity {

    EditText editText;
    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tts);

        editText = (EditText) findViewById(R.id.txtData);
    }

    public void onClick(View view) {
        Intent intent = new Intent(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(intent, 11);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 11) {
            tts = new TextToSpeech(TTSActivity.this, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status == TextToSpeech.SUCCESS) {
                        if (tts.isLanguageAvailable(Locale.US)>0) {
                            tts.setLanguage(Locale.US);
                            tts.setPitch(0.8f);
                            tts.setSpeechRate(1.1f);
                            tts.speak(editText.getText().toString(), TextToSpeech.QUEUE_ADD, null);
                        } else {
                            Toast.makeText(TTSActivity.this, "English lang is not installed...", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }
    }
}
