package com.hua.testpro2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import com.hua.testpro.manifest.R;
import com.hua.testpro2.out_sort.RandomFileCreator;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    private HashMap<String, String> hashMap;
    private static int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Integer integer = 12;
        Integer integer1 = 12;
        Log.e("@@@hua", "equals = " + (integer == integer1));

        hashMap = new HashMap<>(3, 0.8f);
        findViewById(R.id.put).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //hashMap.put("key" + i++, "value" + i);
                RandomFileCreator.createRandomFile(MainActivity.this);
            }
        });

    }



}
