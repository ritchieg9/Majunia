package com.example.majunia;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.example.majunia.ui.adapters.ItemsAdapter;
import com.example.majunia.ui.fragments.MediaFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.OptIn;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.datasource.DefaultHttpDataSource;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.hls.HlsMediaSource;
import androidx.media3.ui.PlayerView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.majunia.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements ItemsAdapter.itemsListener {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    private ExoPlayer player;
    private PlayerView playerView;

    MediaFragment mediaFragment;

    @OptIn(markerClass = UnstableApi.class)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .setAnchorView(R.id.fab).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_channels, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if(destination.getId() == R.id.nav_slideshow){
                findViewById(R.id.video_area).setVisibility(View.GONE);
            } else {
                findViewById(R.id.video_area).setVisibility(View.VISIBLE);
            }
        });

        playerView = findViewById(R.id.playerView);

        // Initialize ExoPlayer
        player = new ExoPlayer.Builder(this).build();playerView.setPlayer(player);

        // Prepare the HLS media source
        HlsMediaSource hlsMediaSource = new HlsMediaSource.Factory(new DefaultHttpDataSource.Factory())
                .createMediaSource(MediaItem.fromUri("https://test-streams.mux.dev/x36xhzz/x36xhzz.m3u8"));

        // Set the media source and prepare the player
        player.setMediaSource(hlsMediaSource);
        player.prepare();

        // Start playback
        player.play();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Release the player when the activity is destroyed
        if (player != null) {
            player.release();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onInputASent(String input, String channelName) {
        Toast.makeText(getBaseContext(), channelName, Toast.LENGTH_SHORT).show();
    }
}