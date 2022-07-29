package com.example.mediaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.example.mediaproject.databinding.ActivityPlayAudioBinding;
import com.example.mediaproject.model.Song;

import java.text.SimpleDateFormat;

public class PlayAudioActivity extends AppCompatActivity {

    private ActivityPlayAudioBinding binding;
    private static final String KEY_SONG = "key_song";
    private static final int MY_CODE = 10;
    MediaPlayer mediaPlayer;
    Animation animation;
    ImageView image;
    private int[] songArray = {R.raw.ai_chung_tinh_duoc_mai_1, R.raw.ai_chung_tinh_duoc_mai_2, R.raw.chung_ta_cua_hien_tai, R.raw.am_tham_ben_em, R.raw.de_vuong, R.raw.niu_duyen, R.raw.sau_lung_anh, R.raw.sau_lung_anh};
    private int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlayAudioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        animation = AnimationUtils.loadAnimation(this, R.anim.disc_rotate);
        image = findViewById(R.id.imageViewDisc);

        Song song = (Song) getIntent().getExtras().get(KEY_SONG);

         mediaPlayer = MediaPlayer.create(PlayAudioActivity.this, song.getFile());
        binding.tvTitle.setText(song.getTitle());


        binding.imagePlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(mediaPlayer.isPlaying()){
                   mediaPlayer.pause();
                   binding.imagePlay.setImageResource(R.drawable.ic_play);
               }else{
                   mediaPlayer.start();
                   binding.imagePlay.setImageResource(R.drawable.ic_pause);
               }
               SetTimeTotal();
               UpdateTimeSong();
                image.startAnimation(animation);
            }
        });

        binding.imageStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mediaPlayer.release();
                binding.imagePlay.setImageResource(R.drawable.ic_play);
                mediaPlayer = MediaPlayer.create(PlayAudioActivity.this, song.getFile());
                binding.tvTitle.setText(song.getTitle());
            }
        });

        binding.imageNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               position++;
               if(position > 7){
                   position = 0;
               }
               if(mediaPlayer.isPlaying()){
                   mediaPlayer.stop();
               }
                mediaPlayer = MediaPlayer.create(PlayAudioActivity.this, songArray[position]);
                binding.tvTitle.setText("Loading...");
                mediaPlayer.start();
                binding.imagePlay.setImageResource(R.drawable.ic_pause);
                SetTimeTotal();
                UpdateTimeSong();
            }
        });

        binding.imagePrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position--;
                if(position < 0){
                    position = 7;
                }
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                mediaPlayer = MediaPlayer.create(PlayAudioActivity.this, songArray[position]);
                binding.tvTitle.setText("Loading...");
                mediaPlayer.start();
                binding.imagePlay.setImageResource(R.drawable.ic_pause);
                SetTimeTotal();
                UpdateTimeSong();
            }
        });
        binding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(binding.seekBar.getProgress());
            }
        });


    }


    private void UpdateTimeSong(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat hourForm = new SimpleDateFormat("mm:ss");
                binding.tvStart.setText(hourForm.format(mediaPlayer.getCurrentPosition()));
                binding.seekBar.setProgress(mediaPlayer.getCurrentPosition());

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        position++;
                        if(position > 7){
                            position = 0;
                        }
                        if(mediaPlayer.isPlaying()){
                            mediaPlayer.stop();
                        }
                        mediaPlayer = MediaPlayer.create(PlayAudioActivity.this, songArray[position]);
                        binding.tvTitle.setText("Loading...");
                        mediaPlayer.start();
                        binding.imagePlay.setImageResource(R.drawable.ic_pause);
                        SetTimeTotal();
                        UpdateTimeSong();
                    }
                });

                handler.postDelayed(this, 500);
            }
        }, 100);
    }

    private void SetTimeTotal(){
        SimpleDateFormat hourForm = new SimpleDateFormat("mm:ss");
        binding.tvEnd.setText(hourForm.format(mediaPlayer.getDuration()));
        binding.seekBar.setMax(mediaPlayer.getDuration());
    }

}