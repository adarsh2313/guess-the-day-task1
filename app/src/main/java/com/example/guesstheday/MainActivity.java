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

public class MainActivity extends AppCompatActivity {
    Button b_startgame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b_startgame = (Button) findViewById(R.id.startgame);
        b_startgame.setOnClickListener(new View.OnClickListener() {
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