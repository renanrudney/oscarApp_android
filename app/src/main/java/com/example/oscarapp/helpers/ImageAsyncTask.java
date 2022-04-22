package com.example.oscarapp.helpers;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.IOException;

public class ImageAsyncTask extends AsyncTask<String, String, Bitmap> {
    private ProgressBar progressBar;
    private ImageView imageView;

    public ImageAsyncTask(ProgressBar progressBar, ImageView imageView) {
        this.progressBar = progressBar;
        this.imageView = imageView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBar.setVisibility(View.VISIBLE);
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
        imageView.setImageBitmap(bitmap);
        progressBar.setVisibility(View.INVISIBLE);
    }
}