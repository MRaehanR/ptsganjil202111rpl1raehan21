package com.example.movieapps.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.movieapps.R;
import com.google.android.material.textfield.TextInputLayout;

public class FavoriteEditActivity extends AppCompatActivity {

    TextInputLayout originalTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_edit);

        originalTitle = findViewById(R.id.tf_originalTitle);
        originalTitle.setError("Harus diisi bang");
        originalTitle.setError(null);
    }
}