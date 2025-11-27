/*
 OpenTicTacToe 2025 by unkver
 https://github.com/unkver
 unkver@proton.me
 */
package com.example.opentictactoe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    public void clickSound(){
        SharedPreferences prefs = getSharedPreferences("GameSettings", MODE_PRIVATE);
        boolean soundEnabled = prefs.getBoolean("soundEnabled", true);

        if (soundEnabled) {
            final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.buttonclick_01);
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(MediaPlayer::release);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void playAI(View v){
        clickSound();
        Intent intent = new Intent(this, gameplay_ai.class);
        startActivity(intent);
    }
    public void play2(View v){
        clickSound();
        Intent intent = new Intent(this, gameplay_2.class);
        startActivity(intent);
    }
    public void showSettings(View v){
        clickSound();
        Intent intent = new Intent(this, settings.class);
        startActivity(intent);
    }
}