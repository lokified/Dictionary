package com.loki.dictionary.network;

import android.content.Context;
import android.widget.Toast;

import com.loki.dictionary.Constants;
import com.loki.dictionary.models.APIResponse;
import com.loki.dictionary.utils.OnFetchDataListener;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DictionaryClient {

    private static  Retrofit retrofit;
    public Context context;

    public DictionaryClient(Context context) {
        this.context = context;
    }

    public static DictionaryAPI getClient() {

        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }

        return retrofit.create(DictionaryAPI.class);
    }


    public void getWordMeaning(OnFetchDataListener listener, String word) {
        DictionaryAPI api = DictionaryClient.getClient();
        Call<List<APIResponse>> call = api.getMeaning(word);

        try {
            call.enqueue(new Callback<List<APIResponse>>() {
                @Override
                public void onResponse(Call<List<APIResponse>> call, Response<List<APIResponse>> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    listener.onFetchData(response.body().get(0), response.message());
                }

                @Override
                public void onFailure(Call<List<APIResponse>> call, Throwable t) {

                    listener.onError("Request failed");
                }
            });
        }
        catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "An error occurred!!", Toast.LENGTH_SHORT).show();
        }
    }
}
