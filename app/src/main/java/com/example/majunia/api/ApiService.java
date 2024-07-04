package com.example.majunia.api;

import com.example.majunia.ui.model.Channels;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("playlist-claro-hls_kr-channels.json")
    Call<ArrayList<Channels>> getClaroChannels();
}