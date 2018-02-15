package com.example.admin.samplegame;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by admin on 11-02-2018.
 */

public class ImageAdapter extends ArrayAdapter<Image> {
    private Context mContext;

    public ImageAdapter(Context c, ArrayList<Image> images) {
        super(c,0,images);
        mContext = c;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(285, 285));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }
        Image image=getItem(position);
        imageView.setImageResource(image.getImageId());
        return imageView;
    }


}
