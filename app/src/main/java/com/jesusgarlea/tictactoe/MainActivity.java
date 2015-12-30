package com.jesusgarlea.tictactoe;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends Activity {

    private int player = 1;
    private int playerOneScore = 0;
    private int playerTwoScore = 0;
    private int[] fields = {0,0,0,0,0,0,0,0,0};
    private int[][] winningConditions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    private boolean gameEnd = false;
    private int counter = 0;
    private String message = "";
    private TextView playertv;

    @Override
    public View findViewById(int id) {
        return super.findViewById(id);
    }

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
        ((LinearLayout)findViewById(R.id.modalMessage)).setVisibility(View.GONE);
        playertv.setText("Player 1 Turn");

    }

    public void showXY(View view){
        int won = 0;
        ImageView animated = (ImageView) view;
        int tag = Integer.parseInt(view.getTag().toString());
        if(fields[tag] == 0 && !gameEnd && counter < 9) {
            counter++;
            animated.setAlpha(0f);
            animated.setRotationX(0l);
            animated.setScaleX(2f);
            animated.setScaleY(2f);
            fields[tag] = player;
            if (player == 1) {
                animated.setImageResource(R.drawable.x);
                playertv.setText("Player 2 Turn");
                player = 2;
            } else {
                animated.setImageResource(R.drawable.o);
                playertv.setText("Player 1 Turn");
                player = 1;
            }
            animated.animate().alpha(1f).scaleY(1f).scaleX(1f).setDuration(700);
            for(int[]win: this.winningConditions){
                if(fields[win[0]] == fields[win[1]] && fields[win[0]] == fields[win[2]] && fields[win[0]] != 0){
                    won = fields[win[0]];
                    if(won == 1){
                        playerOneScore++;
                        ((TextView) findViewById(R.id.playerOneScoreTextView)).setText("Player 1 Score: " + playerOneScore);
                    } else {
                        playerTwoScore++;
                        ((TextView) findViewById(R.id.playerTwoScoreTextView)).setText("Player 1 Score: " + playerTwoScore);
                    }
                    message = "Player " + won + " is the winner!";
                    gameEnd = true;
                    break;
                }
            }
            if(!gameEnd && counter == 9){
                message = "Draw";
                gameEnd = true;
            }

            if(gameEnd){
                LinearLayout t = (LinearLayout)findViewById(R.id.modalMessage);
                t.setVisibility(View.VISIBLE);
                t.setAlpha(0f);
                t.animate().alpha(1f).setDuration(500);
                TextView g =
                        (TextView)findViewById(R.id.gameStatusTextView);
                g.setText(message);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playertv = (TextView) findViewById(R.id.playerTurnTextView);
    }
}
