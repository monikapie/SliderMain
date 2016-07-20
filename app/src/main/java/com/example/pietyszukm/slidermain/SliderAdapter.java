package com.example.pietyszukm.slidermain;
//http://stackoverflow.com/questions/477572/strange-out-of-memory-issue-while-loading-an-image-to-a-bitmap-object
//MemoryOutOfBound bug resolved - images must be put in assets folder and special function for accessing this images
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by pietyszukm on 19.07.2016.
 */
public class SliderAdapter extends PagerAdapter {
    private ArrayList<Integer> images;
    public static final String[] IMAGE_NAME = {"first", "second", "third"};
    View imageLayout;
    private Context context;
    int x = 0;

    public SliderAdapter(Context context, ArrayList<Integer> images){
        this.context = context;
        this.images = images;
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
        LayoutInflater inflater = (LayoutInflater) container.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        imageLayout = inflater.inflate(R.layout.image_layout, container ,false);
        loadImage(imageLayout, position);
        container.addView(imageLayout, 0);
        return imageLayout;
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    public static Bitmap getAssetImage(Context context, String filename) throws IOException {
        AssetManager assets = context.getResources().getAssets();
        InputStream buffer = new BufferedInputStream((assets.open("drawable/" + filename + ".jpg")));
        Bitmap bitmap = BitmapFactory.decodeStream(buffer);
        return bitmap;
    }

    public void  loadImage(View imageLayout, int position){
            try {
                final Bitmap b = getAssetImage(context, IMAGE_NAME[position]);
                final ImageView imageView = (ImageView) imageLayout.findViewById(R.id.image);
                final TextView textView = (TextView) imageLayout.findViewById(R.id.text);
                imageView.setImageBitmap(b);
                textView.setText(context.getString(R.string.picture_title, IMAGE_NAME[position]));
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
