package com.loki.dictionary.utils;

import com.loki.dictionary.models.APIResponse;

public interface OnFetchDataListener {

    void onFetchData(APIResponse apiResponse, String message);
    void onError(String message);
}
