package com.example.scorekeep;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;

public class MainActivity extends AppCompatActivity {

    private int scoreTeam1 = 0, scoreTeam2 = 0;
    private static final String SCORE_TEAM_1 = "SCORE_TEAM_1";
    private static final String SCORE_TEAM_2 = "SCORE_TEAM_2";

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(SCORE_TEAM_1, scoreTeam1);
        outState.putInt(SCORE_TEAM_2, scoreTeam2);

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageButton minusTeam1, minusTeam2, plusTeam1, plusTeam2;
        TextView viewTeam1, viewTeam2;
        Button buttonReset;

        minusTeam1 = findViewById(R.id.minusTeam1);
        minusTeam2 = findViewById(R.id.minusTeam2);
        plusTeam1 = findViewById(R.id.plusTeam1);
        plusTeam2 = findViewById(R.id.plusTeam2);
        viewTeam1 = findViewById(R.id.scoreTeam1);
        viewTeam2 = findViewById(R.id.scoreTeam2);
        buttonReset = findViewById(R.id.buttonReset);

        if (savedInstanceState != null) {
            scoreTeam1 = savedInstanceState.getInt(SCORE_TEAM_1);
            scoreTeam2 = savedInstanceState.getInt(SCORE_TEAM_2);
            if (scoreTeam1 != 0) {
                viewTeam1.setText(String.valueOf(scoreTeam1));
                minusTeam1.setClickable(true);
                minusTeam1.setImageResource(R.drawable.ic_minus_enabled);
                minusTeam1.setBackgroundResource(R.drawable.button_enabled_bg);
            }
            if (scoreTeam2 != 0) {
                viewTeam2.setText(String.valueOf(scoreTeam2));
                minusTeam2.setClickable(true);
                minusTeam2.setImageResource(R.drawable.ic_minus_enabled);
                minusTeam2.setBackgroundResource(R.drawable.button_enabled_bg);
            }
        }

        minusTeam1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (scoreTeam1 >= 1) {
                    scoreTeam1--;
                    viewTeam1.setText(String.valueOf(scoreTeam1));
                }
                if (scoreTeam1 == 0) {
                    minusTeam1.setClickable(false);
                    minusTeam1.setImageResource(R.drawable.ic_minus_disabled);
                    minusTeam1.setBackgroundResource(R.drawable.button_disabled_bg);
                }
            }
        });

        minusTeam2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (scoreTeam2 >= 1) {
                    scoreTeam2--;
                    viewTeam2.setText(String.valueOf(scoreTeam2));
                }
                if (scoreTeam2 == 0) {
                    minusTeam2.setClickable(false);
                    minusTeam2.setImageResource(R.drawable.ic_minus_disabled);
                    minusTeam2.setBackgroundResource(R.drawable.button_disabled_bg);
                }
            }
        });

        plusTeam1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (scoreTeam1 == 0) {
                    minusTeam1.setClickable(true);
                    minusTeam1.setImageResource(R.drawable.ic_minus_enabled);
                    minusTeam1.setBackgroundResource(R.drawable.button_enabled_bg);
                }
                scoreTeam1++;
                viewTeam1.setText(String.valueOf(scoreTeam1));
            }
        });

        plusTeam2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (scoreTeam2 == 0) {
                    minusTeam2.setClickable(true);
                    minusTeam2.setImageResource(R.drawable.ic_minus_enabled);
                    minusTeam2.setBackgroundResource(R.drawable.button_enabled_bg);
                }
                scoreTeam2++;
                viewTeam2.setText(String.valueOf(scoreTeam2));
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (scoreTeam1 != 0) {
                    scoreTeam1 = 0;
                    viewTeam1.setText(String.valueOf(scoreTeam1));
                    minusTeam1.setClickable(false);
                    minusTeam1.setImageResource(R.drawable.ic_minus_disabled);
                    minusTeam1.setBackgroundResource(R.drawable.button_disabled_bg);
                }
                if (scoreTeam2 != 0) {
                    scoreTeam2 = 0;
                    viewTeam2.setText(String.valueOf(scoreTeam1));
                    minusTeam2.setClickable(false);
                    minusTeam2.setImageResource(R.drawable.ic_minus_disabled);
                    minusTeam2.setBackgroundResource(R.drawable.button_disabled_bg);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        int nightMode = AppCompatDelegate.getDefaultNightMode();
        MenuItem item = menu.findItem(R.id.toggle_dark_mode);

        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            item.setTitle(R.string.light_mode).setIcon(R.drawable.ic_round_light_mode_24);
        } else {
            item.setTitle(R.string.dark_mode).setIcon(R.drawable.ic_round_dark_mode_24);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.toggle_dark_mode) {
            int nightMode = AppCompatDelegate.getDefaultNightMode();
            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
            recreate();
        }
        return super.onOptionsItemSelected(item);
    }
}