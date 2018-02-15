package com.example.admin.samplegame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by admin on 12-02-2018.
 */

public class ImageDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_display);
        ImageView imageView=(ImageView) findViewById(R.id.image);
        int imageId=getIntent().getIntExtra("imageId",0);
        Log.d("imageId",imageId+"");
        imageView.setImageResource(imageId);
        TextView textView =(TextView) findViewById(R.id.desc);
        textView.setText(getIntent().getStringExtra("imageDesc"));
    }
}
