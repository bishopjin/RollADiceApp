package com.example.rolladice;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    /* declaration */
    ImageView diceFace, diceFace2;
    TextView dice_result;
    Button  btn_roll_dice;
    AnimationDrawable dice_anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        diceFace = findViewById(R.id.dice_view);
        diceFace2 = findViewById(R.id.dice_view2);
        btn_roll_dice = findViewById(R.id.btn_roll);
        dice_result = findViewById(R.id.textView_inst);

        /* click event */
        btn_roll_dice.setOnClickListener(view -> {
            Random rand = new Random();

            /* animation */
            diceFace.setVisibility(View.INVISIBLE);
            diceFace2.setVisibility(View.VISIBLE);

            diceFace2.setBackgroundResource(R.drawable.roll_dice_animation);
            dice_anim = (AnimationDrawable) diceFace2.getBackground();

            dice_anim.start();

            new CountDownTimer(3000, 1000) {
                public void onTick(long millisUntilFinished) {
                    String timer = "Dice result in... " + millisUntilFinished / 1000;
                    dice_result.setText(timer);
                }

                public void onFinish() {
                    /* rolling */
                    int roll_dice_result = rand.nextInt(6) + 1;
                    /* stop the animation */
                    dice_anim.stop();
                    diceFace2.setVisibility(View.INVISIBLE);
                    diceFace.setVisibility(View.VISIBLE);

                    switch (roll_dice_result) {
                        case 1 :
                            diceFace.setImageResource(R.drawable.dice_one);
                            break;
                        case 2 :
                            diceFace.setImageResource(R.drawable.dice_two);
                            break;
                        case 3 :
                            diceFace.setImageResource(R.drawable.dice_three);
                            break;
                        case 4 :
                            diceFace.setImageResource(R.drawable.dice_four);
                            break;
                        case 5 :
                            diceFace.setImageResource(R.drawable.dice_five);
                            break;
                        case 6 :
                            diceFace.setImageResource(R.drawable.dice_six);
                            break;
                    }

                    dice_result.setText(R.string.instruction);
                }
            }.start();
        });
    }
}