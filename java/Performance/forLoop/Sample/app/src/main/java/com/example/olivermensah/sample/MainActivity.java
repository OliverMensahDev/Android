package com.example.olivermensah.sample;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private ImageView background;
    private TextView memoryUsage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        background  = findViewById(R.id.background);
        memoryUsage = findViewById(R.id.memoryUsage);
        Button red, green , yellow, blue;
        red = findViewById(R.id.red);
        green = findViewById(R.id.green);
        yellow = findViewById(R.id.yellow);
        blue = findViewById(R.id.blue);

        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runLoop(0);
            }
        });
        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadImage();
            }
        });
        yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRamUsage();
            }
        });
        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runLoop(1);
            }
        });
    }

    final static int ITERATIONS = 40000;

    protected void runLoop(int num) {
        if(num == 0) {
            new Thread() {
                @Override
                public void run() {
                    //****************************problem 2.1*****************************************
                    super.run();
                    long sum = 0;
                    for (int i = 0; i < ITERATIONS; i++) {
                        sum += poorPerformanceLoop();
                        //sleepDelay();
                    }
                    Log.d(TAG, "Avg execution time : " + (float) sum / ITERATIONS);
                }
            }.start();
        }
        if(num == 1) {
            new Thread() {
                @Override
                public void run() {
                    //****************************problem 2.1*****************************************
                    super.run();
                    long sum = 0;
                    for (int i = 0; i < ITERATIONS; i++) {
                        sum += goodPerformanceLoop();
                        //sleepDelay();
                    }
                    Log.d(TAG, "Avg execution time : " + (float) sum / ITERATIONS);
                }
            }.start();
        }
    }

    public long poorPerformanceLoop() {
        long timeStart = System.currentTimeMillis();
        ArrayList<Double> doubleNumbers = new ArrayList<>();
        for (int j = 0; j < 20; j++) {
            String[] stringsNums = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                    "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};
            Double dObj = new Double(stringsNums[j]);
            doubleNumbers.add(dObj);
        }
        return System.currentTimeMillis() - timeStart;
    }
    int[] data = {1, 2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
    ArrayList<Integer> doubleNumbers = new ArrayList<>();
    public long goodPerformanceLoop() {
        long timeStart = System.currentTimeMillis();
        for (int j = 0; j < 20; j++) {
            doubleNumbers.add(data[j]);
        }
        return System.currentTimeMillis() - timeStart;
    }

    protected void startRamUsage() {
        @SuppressLint("DefaultLocale") String ramUsage = String.format("%.2f", ((float) (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1000000))+"MB\nfree:"+String.format("%.2f MB",(float) Runtime.getRuntime().freeMemory() / 1000000);
        memoryUsage.setText(ramUsage);
    }

    protected void loadImage() {
        //*****************************************problem 2.3******************************************
        background.setImageResource(R.drawable.background);

    }

}
