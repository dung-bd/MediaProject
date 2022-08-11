package com.example.mediaproject.viewmodel;

import androidx.lifecycle.LiveData;

import com.example.mediaproject.model.Song;
import com.example.mediaproject.repo.AudioRepo;

import java.util.ArrayList;
import java.util.List;

public class ViewModel extends androidx.lifecycle.ViewModel {
    AudioRepo audioRepo = new AudioRepo();

    public LiveData<List<Song>> getListSongs() {
        return audioRepo.getListSongs();
    }

    public LiveData<List<Integer>> getListSongArray(){
        return audioRepo.getListArray();
    }
    public int getPosition(){
        return audioRepo.getPosition();
    }
}
