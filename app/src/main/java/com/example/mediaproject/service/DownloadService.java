package com.example.mediaproject.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.mediaproject.model.Song;

import java.net.MalformedURLException;
import java.net.URL;

public class DownloadService extends IntentService {
    public DownloadService() {
        super("Download");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        try {
            int result =  DownloadFile(new URL("https://www.rmp-streaming.com/media/big-buck-bunny-360p.mp4"));
            Log.e("Download from ", "https://www.rmp-streaming.com/media/big-buck-bunny-360p.mp4"+result +" MB");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    private int  DownloadFile(URL URLDownload) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 100;
    }
}
