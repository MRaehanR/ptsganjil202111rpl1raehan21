package com.example.movieapps.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.movieapps.R;
import com.example.movieapps.adapter.FavoriteMovieAdapter;
import com.example.movieapps.adapter.MovieAdapter;
import com.example.movieapps.helper.RealmHelper;
import com.example.movieapps.model.MovieModel;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class FavoriteMovieActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FavoriteMovieAdapter adapter;
    List<MovieModel> movielist;
    Bundle bundle;
    Realm realm;
    RealmHelper realmHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_movie);

        getSupportActionBar().setTitle("Favorite Movies");

        recyclerView = findViewById(R.id.rv_movies);

        recyclerView.setLayoutManager(new LinearLayoutManager(FavoriteMovieActivity.this));

        movielist = new ArrayList<>();

        RealmConfiguration configuration = new RealmConfiguration.Builder().allowWritesOnUiThread(true).build();
        realm = Realm.getInstance(configuration);

        realmHelper = new RealmHelper(realm);
        movielist = new ArrayList<>();

        movielist = realmHelper.getAllMovies();

        adapter = new FavoriteMovieAdapter(movielist, this);
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(FavoriteMovieActivity.this, DashBoardActivity.class));
        finish();
    }
}