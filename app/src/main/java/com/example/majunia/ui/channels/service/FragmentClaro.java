package com.example.majunia.ui.channels.service;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.majunia.R;

public class FragmentClaro extends Fragment {

    public FragmentClaro() {
    }
    public static FragmentClaro newInstance() {
        return new FragmentClaro();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tv_claro, container, false);
    }
}