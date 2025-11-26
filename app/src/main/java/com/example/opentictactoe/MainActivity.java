/*
 OpenTicTacToe 2025 by unkver
 https://github.com/unkver
 unkver@proton.me
 */
package com.example.opentictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void showAbout(View v){
        Toast.makeText(this, "Made with \uD83E\uDE75\ngithub.com/unkver", Toast.LENGTH_SHORT).show();
    }
    public void playAI(View v){
        Intent intent = new Intent(this, gameplay_ai.class);
        startActivity(intent);
    }
    public void play2(View v){
        Intent intent = new Intent(this, gameplay_2.class);
        startActivity(intent);
    }
}