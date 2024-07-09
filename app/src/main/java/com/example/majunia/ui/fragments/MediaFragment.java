package com.example.majunia.ui.fragments;

import android.os.Bundle;

import androidx.annotation.OptIn;
import androidx.fragment.app.Fragment;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.datasource.DefaultHttpDataSource;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.hls.HlsMediaSource;
import androidx.media3.ui.PlayerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.majunia.R;
import com.example.majunia.databinding.FragmentMediaBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MediaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MediaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private FragmentMediaBinding binding;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ExoPlayer player;
    private PlayerView playerView;

    public MediaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MediaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MediaFragment newInstance(String param1, String param2) {
        MediaFragment fragment = new MediaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @OptIn(markerClass = UnstableApi.class)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        binding = FragmentMediaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        playerView = root.findViewById(R.id.playerView);

        // Initialize ExoPlayer
        player = new ExoPlayer.Builder(requireContext()).build();playerView.setPlayer(player);

        // Prepare the HLS media source
        HlsMediaSource hlsMediaSource = new HlsMediaSource.Factory(new DefaultHttpDataSource.Factory())
                .createMediaSource(MediaItem.fromUri("https://test-streams.mux.dev/x36xhzz/x36xhzz.m3u8"));

        // Set the media source and prepare the player
        player.setMediaSource(hlsMediaSource);
        player.prepare();

        // Start playback
        player.play();

        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Release the player when the activity is destroyed
        if (player != null) {
            player.release();
        }
    }
}