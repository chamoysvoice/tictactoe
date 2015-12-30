package com.jesusgarlea.tictactoe;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    private int player = 1;
    private int[] fields = {0,0,0,0,0,0,0,0,0};
    private int[][] winningConditions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    private boolean gameEnd = false;
    private int counter = 0;

    public void reset(View view){
        this.player = 1;
        for(int i = 0; i < 9; i++){
            this.fields[i] = 0;
        }
        this.gameEnd = false;
        counter = 0;
        ((ImageView) findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);
    }

    public void showXY(View view){
        int won = 0;
        ImageView animated = (ImageView) view;
        int tag = Integer.parseInt(view.getTag().toString());
        if(fields[tag] == 0 && !gameEnd && counter < 9) {
            counter++;
            animated.setAlpha(0f);
            animated.setRotationX(0l);
            fields[tag] = player;
            if (player == 1) {
                animated.setImageResource(R.drawable.x);
                player = 2;
            } else {
                animated.setImageResource(R.drawable.o);
                player = 1;
            }
            animated.animate().alpha(1f).rotationXBy(360l).setDuration(700);
            for(int[]win: this.winningConditions){
                if(fields[win[0]] == fields[win[1]] && fields[win[0]] == fields[win[2]] && fields[win[0]] != 0){
                    won = fields[win[0]];
                    Toast.makeText(getApplicationContext(), "User " + won + " has won", Toast.LENGTH_SHORT).show();
                    gameEnd = true;
                    break;
                }
            }
            if(!gameEnd && counter == 9){
                Toast.makeText(getApplicationContext(), "Draw", Toast.LENGTH_SHORT).show();
                gameEnd = true;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
