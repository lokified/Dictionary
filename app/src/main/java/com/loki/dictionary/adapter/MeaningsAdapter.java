package com.loki.dictionary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.loki.dictionary.R;
import com.loki.dictionary.models.Meanings;

import java.util.List;

public class MeaningsAdapter extends RecyclerView.Adapter<MeaningViewHolder> {

    private Context mContext;
    private List<Meanings> mMeanings;

    public MeaningsAdapter(Context mContext, List<Meanings> mMeanings) {
        this.mContext = mContext;
        this.mMeanings = mMeanings;
    }

    @NonNull
    @Override
    public MeaningViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MeaningViewHolder(LayoutInflater.from(mContext).inflate(R.layout.meaning_list_item
        , parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MeaningViewHolder holder, int position) {

        holder.mPartOfSpeech.setText("Parts of Speech: " + mMeanings.get(position).getPartOfSpeech());
        holder.mRecyclerDefinition.setHasFixedSize(true);
        holder.mRecyclerDefinition.setLayoutManager( new GridLayoutManager(mContext, 1));

        DefinitionAdapter definitionAdapter = new DefinitionAdapter(mContext, mMeanings.get(position).getDefinitions());
        holder.mRecyclerDefinition.setAdapter(definitionAdapter);
    }

    @Override
    public int getItemCount() {
        return mMeanings.size();
    }
}
