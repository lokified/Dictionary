package com.loki.dictionary.adapter;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.loki.dictionary.R;
import com.loki.dictionary.models.Phonetics;

import java.util.List;

public class PhoneticsAdapter extends RecyclerView.Adapter<PhoneticViewHolder> {

    private Context mContext;
    private List<Phonetics> mPhonetics;

    public PhoneticsAdapter(Context mContext, List<Phonetics> mPhonetics) {
        this.mContext = mContext;
        this.mPhonetics = mPhonetics;
    }

    @NonNull
    @Override
    public PhoneticViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PhoneticViewHolder(LayoutInflater.from(mContext).inflate(R.layout.phonetics_list_item,
                parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneticViewHolder holder, int position) {

        holder.mTextPhonetic.setText(mPhonetics.get(position).getText());
        holder.mImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer player = new MediaPlayer();

                try {
                    player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    player.setDataSource("https://" + mPhonetics.get(position).getAudio());
                    player.prepare();
                    player.start();
                }
                catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(mContext, "Error playing audio", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPhonetics.size();
    }
}
