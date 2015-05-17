package com.elkriefy.apps.memoryleakerdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


public class MainActivityHandlers extends AppCompatActivity {

    final String TAG = "MainActivityHandlers";

    private final Handler mLeakyHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // We DO Our Stuff here...
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLeakyHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "mLeakyHandler leak test");
            }
        }, 6000);

        // Go back to the previous Activity.
        finish();
    }

}
