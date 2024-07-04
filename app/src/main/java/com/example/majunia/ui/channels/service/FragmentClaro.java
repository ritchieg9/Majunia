package com.example.majunia.ui.channels.service;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.example.majunia.R;
import com.example.majunia.databinding.FragmentTvClaroBinding;
import com.example.majunia.ui.Majunia;
import com.example.majunia.ui.adapters.ItemsAdapter;
import com.example.majunia.ui.channelCallback;
import com.example.majunia.ui.model.Channels;

import java.util.ArrayList;
import java.util.List;

public class FragmentClaro extends Fragment implements channelCallback {

    private FragmentTvClaroBinding binding;
    private itemsListener listener;
    RecyclerView courseRV;
    ItemsAdapter itemsAdapter;

    public FragmentClaro() {

    }

    public interface itemsListener {
        void onInputASent(String input, String channelName);
    }
    public static FragmentClaro newInstance() {
        return new FragmentClaro();
    }

    private void fetchChannels(String jsonURL, String playlistName) {

        JsonArrayRequest request = new JsonArrayRequest(jsonURL,
                response -> {
                    if (response == null) {
                        return;
                    }

                    List<Channels> items = new Gson().fromJson(response.toString(), new TypeToken<List<Channels>>() {
                    }.getType());

                    ArrayList<Channels> courseModelArrayList = new ArrayList<Channels>();
                    courseModelArrayList.addAll(items);
                    itemsAdapter = new ItemsAdapter(requireContext(), courseModelArrayList);
                    courseRV.setAdapter(itemsAdapter);

                    // refreshing recycler view
                    itemsAdapter.notifyDataSetChanged();
                }, error -> {}
        );
        Majunia.getInstance().addToRequestQueue(request);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentTvClaroBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        courseRV = root.findViewById(R.id.itemsRecycleView);

        // Here, we have created new array list and added data to it
        ArrayList<Channels> courseModelArrayList = new ArrayList<Channels>();
        courseModelArrayList.add(new Channels("Gatitos"));

        // we are initializing our adapter class and passing our arraylist to it.
        itemsAdapter = new ItemsAdapter(requireContext(), courseModelArrayList);
        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        GridLayoutManager linearLayoutManager = new GridLayoutManager(requireContext(), 3 );

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        courseRV.setLayoutManager(linearLayoutManager);
        courseRV.setAdapter(itemsAdapter);

        fetchChannels("http://147.135.114.195/playlist/playlist-claro-hls_kr-channels.json", "null");

        return root;
    }

    @Override
    public void onItemClick(int pos, TextView name) {
        listener.onInputASent(String.valueOf(pos), name.getText().toString());
    }


}