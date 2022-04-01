package com.loki.dictionary.network;

import com.loki.dictionary.models.APIResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DictionaryAPI {

    @GET("entries/en/{word}")
    Call<List<APIResponse>> getMeaning(
            @Path("word") String yourWord
    );

}
