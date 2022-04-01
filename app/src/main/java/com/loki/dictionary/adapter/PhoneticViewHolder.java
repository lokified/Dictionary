package com.loki.dictionary.adapter;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.loki.dictionary.R;

public class PhoneticViewHolder extends RecyclerView.ViewHolder {

    public TextView mTextPhonetic;
    public ImageButton mImageBtn;

    public PhoneticViewHolder(@NonNull View itemView) {
        super(itemView);

        mTextPhonetic = itemView.findViewById(R.id.text_phonetic);
        mImageBtn = itemView.findViewById(R.id.img_btn_audio);
    }
}
