package com.example.vinayg.stopwatch;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  {
    TextView timer;
    TextView stopwatch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timer = (TextView)findViewById(R.id.timer);
        timer.setCompoundDrawablesWithIntrinsicBounds(R.drawable.timer1, 0, 0, 0);
        stopwatch = (TextView)findViewById(R.id.stopwatch);
        stopwatch.setCompoundDrawablesWithIntrinsicBounds(R.drawable.stopwatch1, 0, 0, 0);
        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.setCompoundDrawablesWithIntrinsicBounds(R.drawable.timer, 0, 0, 0);
                timer.setTextColor(Color.parseColor("#357AE8"));
                timer.setBackgroundResource(R.drawable.textlines);
                stopwatch.setCompoundDrawablesWithIntrinsicBounds(R.drawable.stopwatch1, 0, 0, 0);
                stopwatch.setTextColor(Color.parseColor("#303F9F"));
                Fragment fragment = new Timer();
                FragmentManager fm= getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, fragment);
                fragmentTransaction.commit();
            }
        });
        stopwatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopwatch.setCompoundDrawablesWithIntrinsicBounds(R.drawable.stopwatch2, 0, 0, 0);
                stopwatch.setTextColor(Color.parseColor("#357AE8"));
                stopwatch.setBackgroundResource(R.drawable.textlines);
                timer.setBackgroundResource(R.drawable.nolines);
                timer.setCompoundDrawablesWithIntrinsicBounds(R.drawable.timer1, 0, 0, 0);
                timer.setTextColor(Color.parseColor("#303F9F"));
                Fragment fragment = new Stopwatch();
                FragmentManager fm= getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, fragment);
                fragmentTransaction.commit();
            }
        });

    }


}
