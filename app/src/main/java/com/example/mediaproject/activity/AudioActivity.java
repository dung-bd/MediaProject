package com.example.mediaproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.mediaproject.adapter.SongAdapter;
import com.example.mediaproject.databinding.ActivityAudioBinding;
import com.example.mediaproject.model.Song;
import com.example.mediaproject.viewmodel.ViewModel;

import java.util.List;

public class AudioActivity extends AppCompatActivity {
    private static final int MY_CODE = 10;
    private ActivityAudioBinding binding;
    private SongAdapter songAdapter;
    private ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAudioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        viewModel.getListSongs().observe(this, new Observer<List<Song>>() {
            @Override
            public void onChanged(List<Song> songs) {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
                binding.rcvSong.setLayoutManager(linearLayoutManager);

                songAdapter.setData(songs);
                binding.rcvSong.setAdapter(songAdapter);
            }
        });

        songAdapter = new SongAdapter( new SongAdapter.ClickSong() {
            @Override
            public void clickSong(Song song) {
                clickPlaySong(song);
            }
        });
    }
    
    private void clickPlaySong(Song song){
        starter(AudioActivity.this, MY_CODE, song);
    }
    public static void starter(Activity activity, int code, Song song) {
        Intent intent = new Intent(activity, PlayAudioActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("key_song", song);
        intent.putExtras(bundle);
        activity.startActivityForResult(intent, code);
    }


}