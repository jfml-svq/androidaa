package com.josefco.androidaa.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.josefco.androidaa.R;

import java.io.ByteArrayOutputStream;

public class ImageUtils {

    public static byte[] fromImageViewToByteArray(ImageView imageView) {
        imageView.buildDrawingCache();
        Bitmap bitmap = imageView.getDrawingCache();
        return fromBitmapToByteArray(bitmap);
    }

    public static byte[] fromBitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos);
        return bos.toByteArray();
    }





}
