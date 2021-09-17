package com.example.movieapps.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.movieapps.R;
import com.example.movieapps.helper.RealmHelper;
import com.example.movieapps.model.MovieModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class FavoriteDetailActivity extends AppCompatActivity {

    ImageView ivPoster;
    TextView tvOriginalTitle, tvOverview, tvReleaseDate;
    int id;
    String posterPath, originalTitle, overview, releaseDate;
    FloatingActionButton fab_edit;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_detail);

        tvOriginalTitle = findViewById(R.id.tv_original_title);
        tvOverview = findViewById(R.id.tv_overview);
        tvReleaseDate = findViewById(R.id.tv_realese_date);
        ivPoster = findViewById(R.id.iv_poster);
        fab_edit = findViewById(R.id.fab_edit);

        bundle = getIntent().getExtras();

        if(bundle != null){
            originalTitle = bundle.getString("original_title");
            overview = bundle.getString("overview");
            releaseDate = bundle.getString("release_date");
            posterPath = bundle.getString("poster_path");
            id = bundle.getInt("id");
            getSupportActionBar().setTitle(originalTitle);
        }

        tvOriginalTitle.setText(originalTitle);
        tvReleaseDate.setText(releaseDate);
        tvOverview.setText(overview);
        Glide.with(getApplicationContext())
                .load("https://image.tmdb.org/t/p/w500/".concat(posterPath))
                .into(ivPoster);

        fab_edit.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), FavoriteEditActivity.class);
            intent.putExtra("original_title", originalTitle);
            intent.putExtra("overview", overview);
            intent.putExtra("release_date", releaseDate);
            intent.putExtra("poster_path", posterPath);
            intent.putExtra("id", id);
            startActivity(intent);
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(FavoriteDetailActivity.this, FavoriteMovieActivity.class));
    }
}