package com.example.vinayg.stopwatch;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by vinay.g on 06-Feb-17.
 */
public class Stopwatch extends Fragment {
    private long startTime = 0L;
    private Handler customHandler = new Handler();
    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;
    private TextView timerValue;
    private Button start,reset;

    @Override
    public void onResume() {
        super.onResume();
        Log.d("onResume","called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("onPause","called");

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("onStart","called");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("onStart","called");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.stopwatch, container, false);
        start = (Button) view.findViewById(R.id.start);
        reset = (Button) view.findViewById(R.id.reset);
        timerValue = (TextView) view.findViewById(R.id.textView);
        Log.d("onCreateView","called");
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (start.getText().toString().equals("START")) {
                    startTime = SystemClock.uptimeMillis();
                    customHandler.postDelayed(updateTimerThread, 0);
                    start.setText("STOP");
                } else {
                    timeSwapBuff += timeInMilliseconds;
                    customHandler.removeCallbacks(updateTimerThread);
                    start.setText("START");
                }
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                timeSwapBuff = 0L;
                timerValue.setText("00:00:00");
                customHandler.removeCallbacks(updateTimerThread);

            }
        });

        return view;
    }


    private Runnable updateTimerThread = new Runnable() {


        public void run() {

            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;

            updatedTime = timeSwapBuff + timeInMilliseconds;

            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;
            int milliseconds = (int) (updatedTime % 1000);
            timerValue.setText("" + mins + ":"
                    + String.format("%02d", secs) + ":"
                    + String.format("%03d", milliseconds));
            customHandler.postDelayed(this, 0);
        }

    };

}
