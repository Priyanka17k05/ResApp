package com.app.resapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    // 0=x
    // 1=0
    Boolean gameActive = true;

    int activePlayer= 0;

    int gameState[]= {5, 5, 5, 5, 5 , 5, 5, 5, 5};

    // 0=x. 1=0, 2= null....0
    int[] [] winPositions = {{0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6} };


    public void playTap(View view) {
        ImageView img = (ImageView) view;

        int tappedImg = Integer.parseInt(img.getTag().toString());

        if(!gameActive){
            gameReset(view);
        }
        if (gameState[tappedImg] == 5) {
            gameState[tappedImg] = activePlayer;
            img.setTranslationY(-1000f);

            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("O's Turn - Tap to play");
            } else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("X's Turn - Tap to play");
            }
            img.animate().translationYBy(1000f).setDuration(300);

        }
        for (int[] winPos : winPositions) {
            if(gameState[winPos[0]] == gameState[winPos[1]] &&
                    gameState[winPos[1]] == gameState[winPos[2]]
            &&gameState[winPos[0]] != 5){

                String winner;
                gameActive=false;
                if(gameState[winPos[0]]==0){
                    winner= "X has won the game";
                }
                else
                {
                    winner= "O has won the game";
                }
                //updating the status for winner announcement

                TextView status= findViewById(R.id.status);
                status.setText(winner);
            }


        }
    }

    public void gameReset(View view){
        gameActive= true;
        activePlayer=0;
        for(int i=0;i<gameState.length;i++){
            gameState[i]=5;
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("Tap to play");
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}