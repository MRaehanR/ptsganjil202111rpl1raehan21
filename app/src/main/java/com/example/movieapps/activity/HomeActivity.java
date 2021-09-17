package com.example.movieapps.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.movieapps.R;
import com.example.movieapps.fragment.FavoriteFragment;
import com.example.movieapps.fragment.MovieFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView bottomNavigationView;
    Boolean isFromFavorite = false;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        bundle = getIntent().getExtras();
        if(bundle != null){
            isFromFavorite = bundle.getBoolean("isFromFavorite");
        }
        if (isFromFavorite) {
            bottomNavigationView.setSelectedItemId(R.id.menu_favorite);
        } else {
            bottomNavigationView.setSelectedItemId(R.id.menu_movieList);
        }
    }

    MovieFragment movieFragment = new MovieFragment();
    FavoriteFragment favoriteFragment = new FavoriteFragment();

    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_movieList:
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.fl_container, movieFragment).commit();
                return true;
            case R.id.menu_favorite:
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.fl_container, favoriteFragment).commit();
                return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}