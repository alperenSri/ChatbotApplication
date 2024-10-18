package com.example.Final;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

import java.util.Locale;

public class MenuActivity extends AppCompatActivity {
    private Button move;

    private TextView my_text_view;

    private TextView switch_text_view;
    private boolean isDefaultBackground = true;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch switcher;
    boolean NightMode;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        switcher = findViewById(R.id.switcher);
        sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        NightMode = sharedPreferences.getBoolean("night", false);

        Button buttonChangeBackground = findViewById(R.id.buttonChangeBackground);
        Button buttonChangeBackground2 = findViewById(R.id.buttonChangeBackground2);
        Button buttonChangeBackground3 = findViewById(R.id.buttonChangeBackground3);

        ImageButton showLayoutButton = findViewById(R.id.show_hidden);
        final RelativeLayout hiddenLayout = findViewById(R.id.hidden_layout);

        String lang = sharedPreferences.getString("language", "en");
        final View layoutRoot = findViewById(R.id.layoutRoot);
        Switch switchLanguage = findViewById(R.id.Language);

        my_text_view = findViewById(R.id.background_options);
        switch_text_view = findViewById(R.id.switch_text);


        showLayoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle visibility
                if (hiddenLayout.getVisibility() == View.VISIBLE) {
                    hiddenLayout.setVisibility(View.GONE);
                } else {
                    hiddenLayout.setVisibility(View.VISIBLE);
                }
            }
        });


        buttonChangeBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View z) {
                if (isDefaultBackground) {
                    layoutRoot.setBackgroundResource(R.drawable.background1);
                    my_text_view.setTextColor(Color.WHITE);
                    switch_text_view.setTextColor(Color.WHITE);

                } else {
                    layoutRoot.setBackgroundColor(Color.parseColor("#FFF1EDED"));
                    my_text_view.setTextColor(Color.parseColor("#673AB7"));
                    switch_text_view.setTextColor(Color.parseColor("#673AB7"));

                }
                isDefaultBackground = !isDefaultBackground;
            }
        });

        buttonChangeBackground2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View z) {
                if (isDefaultBackground) {
                    layoutRoot.setBackgroundResource(R.drawable.img);
                    my_text_view.setTextColor(Color.WHITE);
                    switch_text_view.setTextColor(Color.WHITE);
                } else {
                    layoutRoot.setBackgroundColor(Color.parseColor("#FFF1EDED"));
                    my_text_view.setTextColor(Color.parseColor("#673AB7"));
                    switch_text_view.setTextColor(Color.parseColor("#673AB7"));
                }
                isDefaultBackground = !isDefaultBackground;
            }
        });

        buttonChangeBackground3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View z) {
                if (isDefaultBackground) {
                    layoutRoot.setBackgroundResource(R.drawable.img_1);
                    my_text_view.setTextColor(Color.WHITE);
                    switch_text_view.setTextColor(Color.WHITE);
                } else {
                    layoutRoot.setBackgroundColor(Color.parseColor("#FFF1EDED"));
                    my_text_view.setTextColor(Color.parseColor("#673AB7"));
                    switch_text_view.setTextColor(Color.parseColor("#673AB7"));
                }
                isDefaultBackground = !isDefaultBackground;
            }
        });


        if (lang.equals("tr")) {
            switchLanguage.setChecked(true);
        }
        setLocale(lang);

        switchLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View l) {
                String lang = switchLanguage.isChecked() ? "tr" : "en";
                setLocale(lang);
                editor = sharedPreferences.edit();
                editor.putString("language", lang);
                editor.apply();
                recreate(); // Recreate activity to apply language change
            }
        });

        move = findViewById(R.id.Move);
        move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View m) {
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
            public void onClick(View n) {
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
    }
    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        config.setLocale(locale);
        resources.updateConfiguration(config, displayMetrics);
    }
}