package com.example.majunia.ui.fragments.channels.service;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.majunia.R;
import com.example.majunia.api.ApiService;
import com.example.majunia.databinding.FragmentTvClaroBinding;
import com.example.majunia.ui.adapters.ItemsAdapter;
import com.example.majunia.ui.model.Channels;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentClaro extends Fragment {

    private FragmentTvClaroBinding binding;
    private ItemsAdapter.itemsListener listener;
    RecyclerView itemsRecycleView;
    ItemsAdapter itemsAdapter;

    public FragmentClaro() {}

    public static FragmentClaro newInstance() {
        return new FragmentClaro();
    }

    private void fetchChannels(String jsonURL, String playlistName) {

//        @SuppressLint("NotifyDataSetChanged") JsonArrayRequest request = new JsonArrayRequest(jsonURL,
//                response -> {
//                    if (response == null) {
//                        return;
//                    }
//
//                    List<Channels> items = new Gson().fromJson(response.toString(), new TypeToken<List<Channels>>() {
//                    }.getType());
//
//                    ArrayList<Channels> channelsArrayList = new ArrayList<Channels>();
//                    channelsArrayList.addAll(items);
//                    itemsAdapter = new ItemsAdapter(requireContext(), channelsArrayList);
//                    itemsRecycleView.setAdapter(itemsAdapter);
//
//                    // refreshing recycler view
//                    itemsAdapter.notifyDataSetChanged();
//                }, error -> {}
//        );
//        Majunia.getInstance().addToRequestQueue(request);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://147.135.114.195/playlist/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<ArrayList<Channels>> call = apiService.getClaroChannels();
        call.enqueue(new Callback<ArrayList<Channels>>() {

            @Override
            public void onResponse(Call<ArrayList<Channels>> call, Response<ArrayList<Channels>> response) {

                if (response.body() == null) {
                    Toast.makeText(requireContext(), "Error fetching data", Toast.LENGTH_SHORT).show();
                    return;
                }

                ArrayList<Channels> channelsArrayList = new ArrayList<>();
                channelsArrayList.addAll(response.body());
                itemsAdapter = new ItemsAdapter(requireContext(), channelsArrayList);
                itemsRecycleView.setAdapter(itemsAdapter);

                // refreshing recycler view
                itemsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<Channels>> call, Throwable t) {
                Toast.makeText(requireContext(), "Error fetching data", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentTvClaroBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        itemsRecycleView = root.findViewById(R.id.itemsRecycleView);

        // Here, we have created new array list and added data to it
        ArrayList<Channels> channelsArrayList = new ArrayList<Channels>();
        // channelsArrayList.add(new Channels("Gatitos"));

        // we are initializing our adapter class and passing our arraylist to it.
        itemsAdapter = new ItemsAdapter(requireContext(), channelsArrayList);
        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        GridLayoutManager linearLayoutManager = new GridLayoutManager(requireContext(), 3);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        itemsRecycleView.setLayoutManager(linearLayoutManager);
        itemsRecycleView.setAdapter(itemsAdapter);

        fetchChannels("http://147.135.114.195/playlist/playlist-claro-hls_kr-channels.json", "hola");

        return root;
    }

}