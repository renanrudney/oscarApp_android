package com.example.oscarapp.helpers;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ProgressBar;

import java.io.IOException;

public class ImageAsyncTask extends AsyncTask<String, String, Bitmap> {
//    Context myContext;

    public ImageAsyncTask() {
//        this.myContext = myContext;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        Bitmap bitmap = null;
        try {
            bitmap = DownloadRequest.downloadImage(strings[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
//        imageView.setImageBitmap(bitmap);
    }
}