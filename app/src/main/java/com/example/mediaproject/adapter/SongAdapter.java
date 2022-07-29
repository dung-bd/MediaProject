package com.example.mediaproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediaproject.R;
import com.example.mediaproject.model.Song;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {

    private Context context;
    private List<Song> songList;
    private ClickSong clickSong;

    public SongAdapter(Context context, ClickSong clickSong){
        this.context = context;
        this.clickSong = clickSong;
    }
    public void setData(List<Song> list){
        this.songList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_song, parent, false);
        return new SongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
        Song song = songList.get(position);
        if(song == null){
            return;
        }

        holder.songTitle.setText(song.getTitle());
        holder.btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSong.clickSong(song);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(songList != null){
            return songList.size();
        }
        return 0;
    }

    public class SongViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgSong;
        private TextView songTitle;
        private Button btnPlay;

        public SongViewHolder(@NonNull View itemView) {
            super(itemView);
            imgSong = itemView.findViewById(R.id.icon_view);
            songTitle = itemView.findViewById(R.id.music_title_text);
            btnPlay = itemView.findViewById(R.id.btn_play);
        }
    }

    public interface ClickSong{
          void clickSong(Song song);
    }
}
