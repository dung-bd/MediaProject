package com.example.mediaproject.repo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mediaproject.R;
import com.example.mediaproject.model.Song;

import java.util.ArrayList;
import java.util.List;

public class AudioRepo {
    private MutableLiveData<List<Song>> mutableSongList;
    private MutableLiveData<List<Integer>> mutableSongArray;
    private int positionLiveData;
    public LiveData<List<Song>> getListSongs() {
        if (mutableSongList == null) {
            mutableSongList = new MutableLiveData<>();
            loadSong();
        }
        return mutableSongList;
    }

    public LiveData<List<Integer>> getListArray() {
        if (mutableSongArray == null) {
            mutableSongArray = new MutableLiveData<>();
            loadSongArray();
        }
        return mutableSongArray;
    }

    public int getPosition(){
        positionLiveData = 0;
        return positionLiveData;
    }

    private void loadSong() {
        List<Song> list = new ArrayList<>();
        list.add(new Song("ai chung tinh duoc mai 1",  R.raw.ai_chung_tinh_duoc_mai_1));
        list.add(new Song("ai chung tinh duoc mai 2",  R.raw.ai_chung_tinh_duoc_mai_2));
        list.add(new Song("am tham ben em",  R.raw.am_tham_ben_em));
        list.add(new Song("chung ta cua hien tai",  R.raw.chung_ta_cua_hien_tai));
        list.add(new Song("de vuong",  R.raw.de_vuong));
        list.add(new Song("niu duyen",  R.raw.niu_duyen));
        list.add(new Song("sau lung anh",  R.raw.sau_lung_anh));
        list.add(new Song("there no one at all",  R.raw.there_no_one_at_all));
        mutableSongList.setValue(list);
    }

    private void loadSongArray(){
        List<Integer> list2 = new ArrayList<>();
        list2.add(R.raw.ai_chung_tinh_duoc_mai_1);
        list2.add(R.raw.ai_chung_tinh_duoc_mai_2);
        list2.add(R.raw.chung_ta_cua_hien_tai);
        list2.add(R.raw.am_tham_ben_em);
        list2.add(R.raw.de_vuong);
        list2.add(R.raw.niu_duyen);
        list2.add(R.raw.sau_lung_anh);
        mutableSongArray.setValue(list2);
    }
}
