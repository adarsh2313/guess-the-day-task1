package com.example.guesstheday;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class activity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        Intent x2 = getIntent();
        int score = x2.getIntExtra("score",0);
        TextView scorecard = findViewById(R.id.scorecard);
        scorecard.setText("     Your Score:\n           " +score);
        Button res = findViewById(R.id.restart);
        res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibratingfunc();
                openactivity2();
            }
        });
    }

    public void vibratingfunc()
    {
        final Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        final VibrationEffect hap;
        hap = VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE);
        vibrator.vibrate(hap);
    }

    public void openactivity2(){
        Intent x1 = new Intent(this, activity2.class);
        startActivity(x1);
    }
}