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
    View timerfragment;
    View stopwatchfragment;
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
                stopwatch.setBackgroundResource(R.drawable.nolines);
                stopwatch.setCompoundDrawablesWithIntrinsicBounds(R.drawable.stopwatch1, 0, 0, 0);
                stopwatch.setTextColor(Color.parseColor("#000000"));
                Fragment mFragment = getFragmentManager().findFragmentById(R.id.fragment);
                if (mFragment instanceof Timer)
                    return;
                Fragment fragment = new Timer();
                replaceFragment(fragment);
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
                timer.setTextColor(Color.parseColor("#000000"));
                Fragment fragment = new Stopwatch();
                replaceFragment(fragment);
            }
        });

    }
    private void replaceFragment (Fragment fragment){
        String backStateName =  fragment.getClass().getName();
        String fragmentTag = backStateName;

        FragmentManager manager = getFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate (backStateName, 0);

        if (!fragmentPopped && manager.findFragmentByTag(fragmentTag) == null){ //fragment not in back stack, create it.
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.fragment, fragment, fragmentTag);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(backStateName);
            ft.commit();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    public void onBackPressed(){
        if (getSupportFragmentManager().getBackStackEntryCount() == 1){
            finish();
        }
        else {
            super.onBackPressed();
        }
    }

}
