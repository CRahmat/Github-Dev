package com.github.githubdev.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;

import com.github.githubdev.home.MyRepository;
import com.github.githubdev.favorite.Favorite;
import com.github.githubdev.profile.Profile;
import com.github.githubdev.R;
import com.github.githubdev.search.Search;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    BottomNavigationView bottomNavigation;
    Fragment selectedFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        if (savedInstanceState == null) {
            selectedFragment = new MyRepository();
            bottomNavigation.setOnNavigationItemSelectedListener(this);
            loadFragment(selectedFragment);
        }
    }
    private boolean loadFragment(Fragment selectedFragment) {
        if(selectedFragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_content, selectedFragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do you want to Exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                selectedFragment = new MyRepository();
                loadFragment(selectedFragment);
                break;
            case R.id.nav_favorite:
                selectedFragment = new Favorite();
                loadFragment(selectedFragment);
                break;
            case R.id.nav_search:
                selectedFragment = new Search();
                loadFragment(selectedFragment);
                break;
            case R.id.nav_profile:
                selectedFragment = new Profile();
                loadFragment(selectedFragment);
                break;
        }
        return loadFragment(selectedFragment);
    }
}
