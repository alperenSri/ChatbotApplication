package com.example.Final;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class MenuActivity extends AppCompatActivity {
    private Button move;

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch switcher;
    boolean NightMode;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        switcher = findViewById(R.id.switcher);
        sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        NightMode = sharedPreferences.getBoolean("night", false);

        move = findViewById(R.id.Move);
        move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent (MenuActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        if (NightMode){
            switcher.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        switcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NightMode){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("night", false);
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("night",true);
                }
                editor.apply();
            }
        });
        TurnToEnglish();
        TurnToTurkish();
    }

    private void TurnToEnglish() {
        final TextView darkmode = findViewById(R.id.dark_mode);
        Button makeen = findViewById(R.id.makeen);
        Button maketr = findViewById(R.id.maketr);
        Button Move = findViewById(R.id.Move);

        makeen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                darkmode.setText("Dark Mode");
                makeen.setText("English");
                maketr.setText("Turkish");
                Move.setText("Start Now");
            }
        });
    }

    private void TurnToTurkish() {
        final TextView darkmode = findViewById(R.id.dark_mode);
        Button makeen = findViewById(R.id.makeen);
        Button maketr = findViewById(R.id.maketr);
        Button Move = findViewById(R.id.Move);

        maketr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                darkmode.setText("Karanlık Mod");
                makeen.setText("İngilizce");
                maketr.setText("Türkçe");
                Move.setText("Başlat");
            }
        });
    }
}