package com.hua.testpro2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.hua.testpro.manifest.R;

/**
 * Created by hua on 2019/3/2.
 */

public class ActivityA extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);


        DisplayMetrics metrics = getResources().getDisplayMetrics();
        Log.e("@@@hua", " activity A metrics = " + metrics);

        DisplayMetrics metrics2 = getApplication().getResources().getDisplayMetrics();
        Log.e("@@@hua", " activity A app metrics = " + metrics2);

        findViewById(R.id.a).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityA.this, ActivityB.class));
            }
        });
    }
}
