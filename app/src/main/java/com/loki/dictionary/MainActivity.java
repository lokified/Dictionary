package com.loki.dictionary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;
import android.widget.Toast;

import com.loki.dictionary.adapter.MeaningsAdapter;
import com.loki.dictionary.adapter.PhoneticsAdapter;
import com.loki.dictionary.models.APIResponse;
import com.loki.dictionary.network.DictionaryClient;
import com.loki.dictionary.utils.OnFetchDataListener;

public class MainActivity extends AppCompatActivity {

    SearchView mSearchView;
    TextView mTextViewWord;
    RecyclerView mRecyclerPhonetics, mRecyclerMeanings;
    ProgressDialog progressDialog;
    PhoneticsAdapter mPhoneticsAdapter;
    MeaningsAdapter mMeaningsAdapter;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearchView = findViewById(R.id.search_View);
        mTextViewWord = findViewById(R.id.text_word);
        mRecyclerMeanings = findViewById(R.id.recycler_meanings);
        mRecyclerPhonetics = findViewById(R.id.recycler_phonetics);

        progressDialog = new ProgressDialog(this);

        progressDialog.setTitle("loading...");
        progressDialog.show();

        DictionaryClient client = new DictionaryClient(MainActivity.this);
        client.getWordMeaning(listener, "hello");

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();


        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                progressDialog.setTitle("searching " + query);
                progressDialog.show();

                DictionaryClient client = new DictionaryClient(MainActivity.this);
                client.getWordMeaning(listener, query);

                addToSharedPreferences(query);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private final OnFetchDataListener listener = new OnFetchDataListener() {
        @Override
        public void onFetchData(APIResponse apiResponse, String message) {
            progressDialog.dismiss();
            if (apiResponse == null) {
                Toast.makeText(MainActivity.this, "No data found", Toast.LENGTH_SHORT).show();
                return;
            }

            showData(apiResponse);
        }

        @Override
        public void onError(String message) {
            progressDialog.dismiss();
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };


    private void showData(APIResponse apiResponse) {
        mTextViewWord.setText(apiResponse.getWord());
        mRecyclerPhonetics.setHasFixedSize(true);
        mRecyclerPhonetics.setLayoutManager(new GridLayoutManager(this, 1));
        mPhoneticsAdapter = new PhoneticsAdapter(this, apiResponse.getPhonetics());
        mRecyclerPhonetics.setAdapter(mPhoneticsAdapter);

        mRecyclerMeanings.setHasFixedSize(true);
        mRecyclerMeanings.setLayoutManager(new GridLayoutManager(this, 1));
        mMeaningsAdapter = new MeaningsAdapter(this, apiResponse.getMeanings());
        mRecyclerMeanings.setAdapter(mMeaningsAdapter);
    }


    private void addToSharedPreferences(String word){
        mEditor.putString(Constants.KEY_WORD, word).apply();
    }
}