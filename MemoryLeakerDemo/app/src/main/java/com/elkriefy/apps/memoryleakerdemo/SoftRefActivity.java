package com.elkriefy.apps.memoryleakerdemo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MaTriXy on 5/18/15.
 */
public class SoftRefActivity extends AppCompatActivity {
    SoftReference<Bitmap> bitmapSoftReference;
    List<Bitmap> bitmaps;
    TextView mySoftTextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_ref_test);
        mySoftTextview = (TextView) findViewById(R.id.softLiveInfo);
        bitmapSoftReference = new SoftReference<Bitmap>(createBitmap());
        bitmaps = new ArrayList<>();
    }

    private Bitmap createBitmap() {
        return Bitmap.createBitmap(1000, 1000, Bitmap.Config.ARGB_8888);
    }


    public void clickMeSoftly(View view) {
        bitmaps.add(createBitmap());
        mySoftTextview.setText("My Soft Ref = "+ bitmapSoftReference.get());
    }
}
