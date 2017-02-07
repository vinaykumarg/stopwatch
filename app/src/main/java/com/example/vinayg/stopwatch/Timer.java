package com.example.vinayg.stopwatch;

import android.app.Fragment;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by vinay.g on 06-Feb-17.
 */
public class Timer extends Fragment {
    private TextView time;
    private EditText tm;
    private ProgressBar pb;
    static MyCountDownTimer myCountDownTimer;
    Long sec;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.timer, container, false);
        final Button start = (Button) view.findViewById(R.id.start);
        Button reset = (Button) view.findViewById(R.id.reset);
        tm = (EditText) view.findViewById(R.id.time);
        time = (TextView) view.findViewById(R.id.tv1);
            pb = (ProgressBar)view.findViewById(R.id.ProgressBar);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String st = tm.getText().toString();
                        sec = Long.parseLong(st);
                        int a = Integer.parseInt(st);
                        pb.setMax(a);
                        tm.setVisibility(View.INVISIBLE);
                        myCountDownTimer = new MyCountDownTimer(sec*1000, 1000);
                        myCountDownTimer.start();

                    }
                });

            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myCountDownTimer!=null) {
                    myCountDownTimer.cancel();
                }
                tm.setVisibility(View.VISIBLE);
                time.setText("");

            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        tm.setVisibility(View.VISIBLE);
    }

    @Override
    public void onResume() {
        super.onResume();
        tm.setVisibility(View.INVISIBLE);
        Log.d("resumed","hi");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("paused","hi");
    }

    public class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            int progress = (int) (millisUntilFinished/1000);
            time.setText(progress+"");
            Log.d("doing",progress+"");
            pb.setProgress(pb.getMax()-progress);
        }

        @Override
        public void onFinish() {
            time.setText("");
            pb.setProgress(pb.getMax());
        }


    }
}
