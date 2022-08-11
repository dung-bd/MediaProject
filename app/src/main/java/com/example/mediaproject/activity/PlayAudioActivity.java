package com.example.mediaproject.activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.mediaproject.R;
import com.example.mediaproject.databinding.ActivityPlayAudioBinding;
import com.example.mediaproject.model.Song;
import com.example.mediaproject.viewmodel.ViewModel;

import java.text.SimpleDateFormat;
import java.util.List;

public class PlayAudioActivity extends AppCompatActivity {

    private ActivityPlayAudioBinding binding;
    private MediaPlayer mediaPlayer;
    private Animation animation;
    private int position;
    private ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlayAudioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        animation = AnimationUtils.loadAnimation(this, R.anim.disc_rotate);

        Song song = (Song) getIntent().getExtras().get("key_song");

        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        position = viewModel.getPosition();
        viewModel.getListSongArray().observe(this, new Observer<List<Integer>>() {
            @Override
            public void onChanged(List<Integer> integers) {
                binding.imageNext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        position++;
                        if (position > 7) {
                            position = 0;
                        }
                        if (mediaPlayer.isPlaying()) {
                            mediaPlayer.stop();
                        }
                        mediaPlayer = MediaPlayer.create(PlayAudioActivity.this, integers.get(position));
                        binding.tvTitle.setText("Loading...");
                        mediaPlayer.start();
                        binding.imagePlay.setImageResource(R.drawable.ic_pause);
                        SetTimeTotal();
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
                                        if (position > 7) {
                                            position = 0;
                                        }
                                        if (mediaPlayer.isPlaying()) {
                                            mediaPlayer.stop();
                                        }
                                        mediaPlayer = MediaPlayer.create(PlayAudioActivity.this, integers.get(position));
                                        binding.tvTitle.setText("Loading...");
                                        mediaPlayer.start();
                                        binding.imagePlay.setImageResource(R.drawable.ic_pause);
                                        SetTimeTotal();
                                    }
                                });

                                handler.postDelayed(this, 500);
                            }
                        }, 100);
                    }
                });

                binding.imagePrev.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        position--;
                        if (position < 0) {
                            position = 7;
                        }
                        if (mediaPlayer.isPlaying()) {
                            mediaPlayer.stop();
                        }
                        mediaPlayer = MediaPlayer.create(PlayAudioActivity.this, integers.get(position));
                        binding.tvTitle.setText("Loading...");
                        mediaPlayer.start();
                        binding.imagePlay.setImageResource(R.drawable.ic_pause);
                        SetTimeTotal();
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
                                        if (position > 7) {
                                            position = 0;
                                        }
                                        if (mediaPlayer.isPlaying()) {
                                            mediaPlayer.stop();
                                        }
                                        mediaPlayer = MediaPlayer.create(PlayAudioActivity.this, integers.get(position));
                                        binding.tvTitle.setText("Loading...");
                                        mediaPlayer.start();
                                        binding.imagePlay.setImageResource(R.drawable.ic_pause);
                                        SetTimeTotal();
                                    }
                                });

                                handler.postDelayed(this, 500);
                            }
                        }, 100);
                    }
                });

                binding.imagePlay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mediaPlayer.isPlaying()) {
                            mediaPlayer.pause();
                            binding.imagePlay.setImageResource(R.drawable.ic_play);
                        } else {
                            mediaPlayer.start();
                            binding.imagePlay.setImageResource(R.drawable.ic_pause);
                        }
                        SetTimeTotal();
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
                                        if (position > 7) {
                                            position = 0;
                                        }
                                        if (mediaPlayer.isPlaying()) {
                                            mediaPlayer.stop();
                                        }
                                        mediaPlayer = MediaPlayer.create(PlayAudioActivity.this, integers.get(position));
                                        binding.tvTitle.setText("Loading...");
                                        mediaPlayer.start();
                                        binding.imagePlay.setImageResource(R.drawable.ic_pause);
                                        SetTimeTotal();
                                    }
                                });

                                handler.postDelayed(this, 500);
                            }
                        }, 100);
                        binding.imageViewDisc.startAnimation(animation);
                    }
                });
            }
        });

        mediaPlayer = MediaPlayer.create(PlayAudioActivity.this, song.getFile());
        binding.tvTitle.setText(song.getTitle());


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

    private void SetTimeTotal() {
        SimpleDateFormat hourForm = new SimpleDateFormat("mm:ss");
        binding.tvEnd.setText(hourForm.format(mediaPlayer.getDuration()));
        binding.seekBar.setMax(mediaPlayer.getDuration());
    }

}