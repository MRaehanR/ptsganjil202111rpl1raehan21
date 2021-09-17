package com.example.movieapps.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.movieapps.R;
import com.example.movieapps.helper.RealmHelper;
import com.example.movieapps.model.MovieModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class FavoriteEditActivity extends AppCompatActivity {

    int id;
    TextInputEditText tf_originalTitle, tf_releaseDate, tf_overview;
    String originalTitle, releaseDate, overview, posterPath;
    ImageView iv_poster;
    Button btn_save;
    Bundle bundle;
    Realm realm;
    RealmHelper realmHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_edit);

        tf_originalTitle = findViewById(R.id.tf_originalTitle);
        tf_releaseDate = findViewById(R.id.tf_releaseDate);
        tf_overview = findViewById(R.id.tf_overview);
        iv_poster = findViewById(R.id.iv_poster);
        btn_save = findViewById(R.id.btn_save);

        RealmConfiguration configuration = new RealmConfiguration.Builder().allowWritesOnUiThread(true).build();
        realm = Realm.getInstance(configuration);

        realmHelper = new RealmHelper(realm);

        bundle = getIntent().getExtras();
        if(bundle != null){
            id = bundle.getInt("id");
            originalTitle = bundle.getString("original_title");
            releaseDate = bundle.getString("release_date");
            overview = bundle.getString("overview");
            posterPath = bundle.getString("poster_path");
            getSupportActionBar().setTitle(originalTitle);
        }

        tf_originalTitle.setText(originalTitle);
        tf_releaseDate.setText(releaseDate);
        tf_overview.setText(overview);
        Glide.with(getApplicationContext())
                .load("https://image.tmdb.org/t/p/w500/".concat(posterPath))
                .into(iv_poster);

        btn_save.setOnClickListener(v -> {
            originalTitle = tf_originalTitle.getText().toString();
            releaseDate = tf_releaseDate.getText().toString();
            overview = tf_overview.getText().toString();
            realmHelper.update(id, originalTitle, overview, releaseDate);
            Toast.makeText(getApplicationContext(), "Update Successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            intent.putExtra("isFromFavorite", true);
            startActivity(intent);
            finish();
        });
    }
}