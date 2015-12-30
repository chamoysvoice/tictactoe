package com.jesusgarlea.tictactoe;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

    private int player = 1;
    private int[] fields = {0,0,0,0,0,0,0,0,0};

    public void showXY(View view){
        ImageView animated = (ImageView) view;
        animated.setAlpha(0f);
        animated.setRotationX(0l);
        if (player == 1) {
            animated.setImageResource(R.drawable.x);
            player = 2;
        } else {
            animated.setImageResource(R.drawable.o);
            player = 1;
        }
        animated.animate().alpha(1f).rotationXBy(360l).setDuration(700);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
