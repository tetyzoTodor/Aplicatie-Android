package com.example.todor.sensors;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TextView scoreLabel;
    private TextView startLabel;
    private ImageView box;
    private ImageView orange;
    private ImageView pink;
    private ImageView black;

    // Size
    private int frameHeight;
    private int boxSize;

         //Pozitie
    private int boxY;
         //initializare Clase
    private Handler handler = new Handler();
    private Timer timer = new Timer ();
         //Status verificare
    private boolean action_flg = false;
    private boolean start_flg = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreLabel = (TextView) findViewById(R.id.scoreLabel);
        startLabel = (TextView) findViewById(R.id.startLabel);
        box = (ImageView) findViewById(R.id.box);
        orange = (ImageView) findViewById(R.id.orange);
        pink = (ImageView) findViewById(R.id.pink);
        black = (ImageView) findViewById(R.id.black);


        //Miscare inafara ecranului
        orange.setX(-80);
        orange.setY(-80);
        pink.setX(-80);
        pink.setY(-80);
        black.setX(-80);
        black.setY(-80);



    }
    public void changePos(){
        // Mutare cutie
        if (action_flg == true){
            //Atinge
            boxY -= 20;
        }else {
            boxY += 20;
        }
        //Verificare pozitie cutie
        if (boxY < 0) boxY = 0;

        if (boxY> frameHeight -boxSize) boxY = frameHeight - boxSize;

        box.setY(boxY);
    }

    public boolean onTouchEvent(MotionEvent me){
        if (start_flg == false){
            start_flg = true;

            FrameLayout frame = (FrameLayout) findViewById(R.id.frame);
            frameHeight = frame.getHeight();

            boxY =(int)box.getY();
            boxSize = box.getHeight();


            startLabel.setVisibility(View.GONE);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            changePos();
                        }
                    })
                }
            },0,20);
        }
        else{
            if (me.getAction()== MotionEvent.ACTION_DOWN) {
                action_flg = true;
            }
            else if (me.getAction() == MotionEvent.ACTION_UP){
                action_flg = false;
            }

        }


        return true;
    }

}
