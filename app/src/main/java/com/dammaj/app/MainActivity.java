package com.dammaj.app;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.dammaj.app.databinding.ActivityMainBinding;
import com.dammaj.app.ui.AudioFragment;
import com.dammaj.app.ui.HomeFragment;
import com.dammaj.app.ui.LibraryFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private ActivityMainBinding binding;
    private HomeFragment homeFragment;
    private LibraryFragment libraryFragment;
    private AudioFragment audioFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Set RTL configuration for Arabic
        setRTLConfiguration();
        
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize fragments
        initFragments();

        // Set up bottom navigation
        binding.bottomNavigation.setOnNavigationItemSelectedListener(this);

        // Load default fragment (Home)
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                .beginTransaction()
                .replace(binding.fragmentContainer.getId(), homeFragment)
                .commit();
        }
    }

    private void setRTLConfiguration() {
        // Force RTL layout for Arabic content
        Configuration config = getResources().getConfiguration();
        config.setLayoutDirection(new Locale("ar"));
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
    }

    private void initFragments() {
        homeFragment = new HomeFragment();
        libraryFragment = new LibraryFragment();
        audioFragment = new AudioFragment();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        
        if (itemId == R.id.nav_home) {
            getSupportFragmentManager()
                .beginTransaction()
                .replace(binding.fragmentContainer.getId(), homeFragment)
                .commit();
            return true;
        } else if (itemId == R.id.nav_library) {
            getSupportFragmentManager()
                .beginTransaction()
                .replace(binding.fragmentContainer.getId(), libraryFragment)
                .commit();
            return true;
        } else if (itemId == R.id.nav_audio) {
            getSupportFragmentManager()
                .beginTransaction()
                .replace(binding.fragmentContainer.getId(), audioFragment)
                .commit();
            return true;
        }
        
        return false;
    }

    @Override
    public void onBackPressed() {
        // Handle fragment back stack
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}