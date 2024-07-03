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

import com.example.majunia.R;
import com.example.majunia.databinding.FragmentTvClaroBinding;
import com.example.majunia.ui.adapters.ItemsAdapter;
import com.example.majunia.ui.model.Channels;

import java.util.ArrayList;

public class FragmentClaro extends Fragment {

    private FragmentTvClaroBinding binding;

    public FragmentClaro() {}

    public static FragmentClaro newInstance() {
        return new FragmentClaro();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentTvClaroBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView courseRV = root.findViewById(R.id.itemsRecycleView);

        // Here, we have created new array list and added data to it
        ArrayList<Channels> courseModelArrayList = new ArrayList<Channels>();
        courseModelArrayList.add(new Channels("DSA in Java"));

        // we are initializing our adapter class and passing our arraylist to it.
        ItemsAdapter itemsAdapter = new ItemsAdapter(requireContext(), courseModelArrayList);
        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        GridLayoutManager linearLayoutManager = new GridLayoutManager(requireContext(), 3 );

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        courseRV.setLayoutManager(linearLayoutManager);
        courseRV.setAdapter(itemsAdapter);

        return root;
    }
}