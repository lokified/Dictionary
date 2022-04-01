package com.loki.dictionary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.loki.dictionary.R;
import com.loki.dictionary.models.Definitions;

import java.util.List;

public class DefinitionAdapter extends RecyclerView.Adapter<DefinitionViewHolder> {

    private Context mContext;
    private List<Definitions> mDefinitions;

    public DefinitionAdapter(Context mContext, List<Definitions> mDefinitions) {
        this.mContext = mContext;
        this.mDefinitions = mDefinitions;
    }

    @NonNull
    @Override
    public DefinitionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DefinitionViewHolder(LayoutInflater.from(mContext).inflate(R.layout.definitions_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DefinitionViewHolder holder, int position) {
        holder.mTxtDefintion.setText("Definition: " + mDefinitions.get(position).getDefinition());
        holder.mTxtExample.setText("Example: " + mDefinitions.get(position).getExample());

        StringBuilder synonyms = new StringBuilder();
        StringBuilder antonyms = new StringBuilder();

        synonyms.append(mDefinitions.get(position).getSynonym());
        antonyms.append(mDefinitions.get(position).getAntonyms());

        holder.mTxtSynonym.setText(synonyms);
        holder.mTxtAntonym.setText(antonyms);

        holder.mTxtSynonym.setSelected(true);
        holder.mTxtAntonym.setSelected(true);

    }

    @Override
    public int getItemCount() {
        return mDefinitions.size();
    }
}
