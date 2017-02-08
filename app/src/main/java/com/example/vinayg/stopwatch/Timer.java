package com.example.vinayg.stopwatch;

import android.app.Fragment;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.vinayg.stopwatch.R.mipmap.ic_launcher;

/**
 * Created by vinay.g on 06-Feb-17.
 */
public class Timer extends Fragment {
    private TextView TimerView;
    private EditText Timerinput;
    private ProgressBar progressBar;
    Intent resultIntent;
    static MyCountDownTimer myCountDownTimer;
    Long CountDown;
    NotificationCompat.Builder notification;
    TaskStackBuilder stackBuilder;
    NotificationManager manager;
    Button start,reset,stop;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.timer, container, false);
        start = (Button) view.findViewById(R.id.start);
        reset = (Button) view.findViewById(R.id.reset);
        stop = (Button) view.findViewById(R.id.stop);
        Timerinput = (EditText) view.findViewById(R.id.time);
        progressBar = (ProgressBar)view.findViewById(R.id.ProgressBar);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String st = Timerinput.getText().toString();
                if(!st.equals("")) {
                    if(myCountDownTimer==null) {
                        CountDown = Long.parseLong(st);
                        int a = Integer.parseInt(st);
                        progressBar.setMax(a);
                        myCountDownTimer = new MyCountDownTimer(CountDown * 1000, 1000);
                        myCountDownTimer.start();
                    }
                } else {
                    Toast.makeText(getActivity(),"enter time",Toast.LENGTH_SHORT);
                }
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myCountDownTimer!=null) {
                    myCountDownTimer.cancel();
                }
                Timerinput.setText("");
                progressBar.setProgress(0);

            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
        return view;
    }





    public class MyCountDownTimer extends CountDownTimer {
        

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            int progress = (int) (millisUntilFinished/1000);
            Timerinput.setText(progress+"");
            Log.d("doing",progress+"");
            progressBar.setProgress(progressBar.getMax()-progress);
        }

        @Override
        public void onFinish() {
            TimerView.setText("");
            progressBar.setProgress(progressBar.getMax());
            NotificationCompat.Builder  notification = new NotificationCompat.Builder(getActivity());
            notification.setContentTitle("Timer");
            notification.setContentText("Alert");
            notification.setTicker("Time is up!");
            notification.setSmallIcon(ic_launcher);
            Intent resultIntent = new Intent(getActivity(), MainActivity.class);
            PendingIntent contentIntent = PendingIntent.getActivity(getActivity(), 0, resultIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            notification.setContentIntent(contentIntent);
            NotificationManager manager = (NotificationManager)getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(0, notification.build());
        }


    }
}

    
