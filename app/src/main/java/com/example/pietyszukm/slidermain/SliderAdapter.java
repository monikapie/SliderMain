package com.example.pietyszukm.slidermain;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by pietyszukm on 19.07.2016.
 */
public class SliderAdapter extends PagerAdapter {
    private ArrayList<Integer> images;
    public static final String[] IMAGE_NAME = {"first", "second", "third"};
    private LayoutInflater inflater;
    private Context context;

    public SliderAdapter(Context context, ArrayList<Integer> images){
        this.context = context;
        this.images = images;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View imageLayout = inflater.inflate(R.layout.image_layout, container ,false);
        assert imageLayout != null;
        final Resources res = context.getResources();
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inSampleSize = 2;
        final Bitmap bmp = BitmapFactory.decodeResource(res, images.get(position), options);
        final ImageView imageView = (ImageView) imageLayout.findViewById(R.id.image);
        final TextView textView = (TextView) imageLayout.findViewById(R.id.text);
        imageView.setImageBitmap(bmp);
        textView.setText(IMAGE_NAME[position]+" floor");
        container.addView(imageLayout, 0);
        return imageLayout;
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

}
