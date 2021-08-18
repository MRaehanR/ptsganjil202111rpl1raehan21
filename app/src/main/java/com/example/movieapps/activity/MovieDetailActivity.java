package com.example.movieapps.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.movieapps.R;

public class MovieDetailActivity extends AppCompatActivity {

    ImageView ivPoster;
    TextView tvOriginalTitle, tvOverview, tvReleaseDate;
    String poster_path, originalTitle, overview, releaseDate;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        tvOriginalTitle = findViewById(R.id.tv_original_title);
        tvOverview = findViewById(R.id.tv_overview);
        tvReleaseDate = findViewById(R.id.tv_realese_date);
        ivPoster = findViewById(R.id.iv_poster);

        bundle = getIntent().getExtras();

        if(bundle != null){
            originalTitle = bundle.getString("original_title");
            overview = bundle.getString("overview");
            releaseDate = bundle.getString("release_date");
            poster_path = bundle.getString("poster_path");
            getSupportActionBar().setTitle(originalTitle);
        }

        tvOriginalTitle.setText(originalTitle);
        tvReleaseDate.setText(releaseDate);
        tvOverview.setText(overview);
        Glide.with(getApplicationContext())
                .load("https://image.tmdb.org/t/p/w500/".concat(poster_path))
                .into(ivPoster);
    }
}