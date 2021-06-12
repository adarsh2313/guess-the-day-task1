package com.example.guesstheday;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Random;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class activity2 extends AppCompatActivity {
    int score =0;
    String[] arr = {"SUNDAY","MONDAY","TUESDAY","WEDNESDAY","THURSDAY","FRIDAY","SATURDAY"};
    int x1,x2,x3,x4;
    String ans;
    LocalDate x;
    DayOfWeek y;
    String ss;
    Button op1,op2,op3,op4;
    TextView ques,scorecard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        generateques();
        if(savedInstanceState != null)    // For restoring value on screen rotation
        {
            score = savedInstanceState.getInt("score");
            x1 = savedInstanceState.getInt("op1");
            x2 = savedInstanceState.getInt("op2");
            x3 = savedInstanceState.getInt("op3");
            x4 = savedInstanceState.getInt("op4");
            ss = savedInstanceState.getString("ques");
            ans = savedInstanceState.getString("answer");
            ques.setText(ss + "\n YYYY-MM-DD");
            scorecard.setText("Score:\n"+score);
            op1.setText(arr[x1]);
            op2.setText(arr[x2]);
            op3.setText(arr[x3]);
            op4.setText(arr[x4]);
        }
    }

    public void generateques(){
        setContentView(R.layout.activity_2);
        x = createRandomDate(1700,2300);
        ss = String.valueOf(x);
        y = x.getDayOfWeek();
        ans = String.valueOf(y);
        ques = findViewById(R.id.textView);
        scorecard = findViewById(R.id.scorecard);
        ques.setText(ss + "\n YYYY-MM-DD");
        scorecard.setText("Score:\n"+score);
        settingoptions();
        clickingoptions();
    }
    public static int createRandomIntBetween(int start, int end) {   // Creates a random number in between the given values
        return start + (int)Math.round(Math.random() * (end - start));
    }

    public static LocalDate createRandomDate(int startYear, int endYear) {  // For creating a random date
        int day = createRandomIntBetween(1, 28);
        int month = createRandomIntBetween(1, 12);
        int year = createRandomIntBetween(startYear, endYear);
        return LocalDate.of(year, month, day);
    }
    public void settingoptions(){     // For generating 4 unique options (one of them is the answer)
        op1 = findViewById(R.id.button3);
        op2 = findViewById(R.id.button4);
        op3 = findViewById(R.id.button5);
        op4 = findViewById(R.id.button6);
        Random r=new Random();
        do{
            x1=r.nextInt(arr.length);
            do{
                x2=r.nextInt(arr.length);
            }while(x2==x1);
            do{
                x3=r.nextInt(arr.length);
            }while(x3==x1 || x3==x2 );
            do{
                x4=r.nextInt(arr.length);
            }while(x4==x1 || x4==x2 || x4==x3);
        }while(arr[x1]!=ans && arr[x2]!=ans && arr[x3]!=ans && arr[x4]!=ans);
        op1.setText(arr[x1]);
        op2.setText(arr[x2]);
        op3.setText(arr[x3]);
        op4.setText(arr[x4]);
    }

    public void clickingoptions(){    // To check if the clicked option is the answer or not
        op1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(arr[x1]==ans){
                    crctans();
                }
                else
                    wrngans();
            }
        });
        op2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(arr[x2]==ans){
                    crctans();
                }
                else
                    wrngans();
            }
        });
        op3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(arr[x3]==ans){
                    crctans();
                }
                else
                    wrngans();
            }
        });
        op4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(arr[x4]==ans){
                    crctans();
                }
                else
                    wrngans();
            }
        });
    }

    public void crctans(){     // On clicking a correct answer
        vibratingfunc();
        score+=5;
        new CountDownTimer(1000, 1000) {
            public void onFinish() {
                generateques();
            }

            public void onTick(long millisUntilFinished) {
                setContentView(R.layout.crctans);
            }
        }.start();
    }

    public void wrngans(){   // On clicking a wrong option
        vibratingfunc();
        new CountDownTimer(1000, 1000) {
            public void onFinish() {
                openactivity3();
            }

            public void onTick(long millisUntilFinished) {
                setContentView(R.layout.wrngans);
            }
        }.start();

    }

    public void vibratingfunc()  // For buttons to vibrate when pressed
    {
        final Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        final VibrationEffect hap;
        hap = VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE);
        vibrator.vibrate(hap);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outstate){  // For storing values on screen rotation
        super.onSaveInstanceState(outstate);
        outstate.putInt("score",score);
        outstate.putString("ques",ss);
        outstate.putString("answer",ans);
        outstate.putInt("op1",x1);
        outstate.putInt("op2",x2);
        outstate.putInt("op3",x3);
        outstate.putInt("op4",x4);
    }

    public void openactivity3(){     // Activity 3 is opened on clicking wrong option
        Intent x2 = new Intent(this, activity3.class);
        x2.putExtra("score",score);
        startActivity(x2);
    }
}