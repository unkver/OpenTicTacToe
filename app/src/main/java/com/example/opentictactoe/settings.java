package com.example.opentictactoe;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class settings extends AppCompatActivity {

    public void clickSound(){
        SharedPreferences prefs = getSharedPreferences("GameSettings", MODE_PRIVATE);
        boolean soundEnabled = prefs.getBoolean("soundEnabled", true);

        if (soundEnabled) {
            final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.buttonclick_01);
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(MediaPlayer::release);
        }
    }
    public void toMainMenu(View v){
        clickSound();
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        // Button SFX switch
        Switch buttonSFX = findViewById(R.id.buttonSFX);
        SharedPreferences prefs = getSharedPreferences("GameSettings", MODE_PRIVATE);

        boolean soundEnabled = prefs.getBoolean("soundEnabled", true);
        buttonSFX.setChecked(soundEnabled);

        buttonSFX.setOnCheckedChangeListener((buttonView, isChecked) -> {
            prefs.edit().putBoolean("soundEnabled", isChecked).apply();

        });
        // shhh...
        // Themes dropdown menu
        // String[] themes = {"Default", "Light", "Dark", "AMOLED"};
        //Spinner themeSpinner = findViewById(R.id.themeSpinner);

//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
//                this,
//                R.array.theme_options,
//                android.R.layout.simple_spinner_item
//        );
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        themeSpinner.setAdapter(adapter);
//        // Load saved theme
//        int savedTheme = prefs.getInt("selectedTheme", 0);
//        themeSpinner.setSelection(savedTheme);
//        themeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                prefs.edit().putInt("selectedTheme", position).apply();
//                // Apply the theme
//                applyTheme(position);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                // Do nothing
//            }
//        });
//        applyTheme(savedTheme);

    }
    private void applyTheme(int themePosition) {
        // We'll implement this next!
    }

}