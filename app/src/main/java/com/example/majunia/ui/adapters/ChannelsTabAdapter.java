package com.example.majunia.ui.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.majunia.ui.channels.service.FragmentClaro;
import com.example.majunia.ui.channels.service.FragmentFubo;

public class ChannelsTabAdapter extends FragmentStateAdapter {
    public ChannelsTabAdapter(FragmentActivity activity) {
        super(activity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new FragmentClaro();
            case 1:
                return new FragmentFubo();
            case 2:
                return new FragmentFubo();
            default:
                throw new IllegalStateException("Unexpected position " + position);
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
