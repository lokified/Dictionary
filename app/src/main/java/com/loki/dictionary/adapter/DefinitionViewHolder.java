package com.loki.dictionary.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.loki.dictionary.R;

public class DefinitionViewHolder extends RecyclerView.ViewHolder {

    public TextView mTxtDefintion, mTxtExample, mTxtSynonym, mTxtAntonym;

    public DefinitionViewHolder(@NonNull View itemView) {
        super(itemView);

        mTxtAntonym = itemView.findViewById(R.id.text_antonym);
        mTxtDefintion = itemView.findViewById(R.id.text_definition);
        mTxtExample = itemView.findViewById(R.id.text_example);
        mTxtSynonym = itemView.findViewById(R.id.text_synonym);

    }
}
