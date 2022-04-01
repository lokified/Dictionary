package com.loki.dictionary.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.loki.dictionary.R;

public class MeaningViewHolder extends RecyclerView.ViewHolder {

    TextView mPartOfSpeech;
    RecyclerView mRecyclerDefinition;

    public MeaningViewHolder(@NonNull View itemView) {
        super(itemView);

        mPartOfSpeech = itemView.findViewById(R.id.text_partOfSpeech);
        mRecyclerDefinition = itemView.findViewById(R.id.recycler_definitions);
    }
}
