package com.example.movieapps.activity;

import androidx.appcompat.app.AppCompatActivity;

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

public class MovieDetailActivity extends AppCompatActivity {

    ImageView ivPoster;
    TextView tvOriginalTitle, tvOverview, tvReleaseDate;
    int id;
    String poster_path, originalTitle, overview, releaseDate;
    FloatingActionButton fab_favorite;
    Bundle bundle;
    Realm realm;
    RealmHelper realmHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        tvOriginalTitle = findViewById(R.id.tv_original_title);
        tvOverview = findViewById(R.id.tv_overview);
        tvReleaseDate = findViewById(R.id.tv_realese_date);
        ivPoster = findViewById(R.id.iv_poster);
        fab_favorite = findViewById(R.id.fab_favorite);

        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().allowWritesOnUiThread(true).build();
        realm = Realm.getInstance(configuration);

        realmHelper = new RealmHelper(realm);

        bundle = getIntent().getExtras();

        if(bundle != null){
            originalTitle = bundle.getString("original_title");
            overview = bundle.getString("overview");
            releaseDate = bundle.getString("release_date");
            poster_path = bundle.getString("poster_path");
            id = bundle.getInt("id");
            getSupportActionBar().setTitle(originalTitle);
        }

        tvOriginalTitle.setText(originalTitle);
        tvReleaseDate.setText(releaseDate);
        tvOverview.setText(overview);
        Glide.with(getApplicationContext())
                .load("https://image.tmdb.org/t/p/w500/".concat(poster_path))
                .into(ivPoster);

        fab_favorite.setOnClickListener(v -> {
            MovieModel movieModel = new MovieModel(id, originalTitle, overview, releaseDate, poster_path);
            realmHelper.save(movieModel);
            Log.d("Model", movieModel.getOriginal_title());
            Toast.makeText(getApplicationContext(), "add to your favorite", Toast.LENGTH_SHORT).show();
        });
    }
}