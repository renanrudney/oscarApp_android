package com.example.oscarapp.helpers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class DownloadRequest {
    public static Bitmap downloadImage(String stringURL) throws IOException {
        URL url = new URL(stringURL);
        InputStream inputStream = url.openStream();
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        inputStream.close();
        return bitmap;
    }
}
