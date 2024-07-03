package com.example.majunia.ui.channels;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.majunia.R;
import com.example.majunia.databinding.FragmentChannelsBinding;
import com.example.majunia.ui.adapters.LiveChannelsViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChannelsFragment extends Fragment {

    private FragmentChannelsBinding binding;
    ViewPager2 viewPager2;
    TabLayout tabLayout;
    List<String> places = Arrays.asList("Claro", "DGo", "Fubo" );

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        binding = FragmentChannelsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        viewPager2 = root.findViewById(R.id.viewPager2);
        tabLayout = root.findViewById(R.id.tab_layout);
        viewPager2.setAdapter(new LiveChannelsViewPagerAdapter(getActivity()));

        new TabLayoutMediator(tabLayout, viewPager2,
                (tab, position) -> tab.setText((places.get(position)))
        ).attach();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}