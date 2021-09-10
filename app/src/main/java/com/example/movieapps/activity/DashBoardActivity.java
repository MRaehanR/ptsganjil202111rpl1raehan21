package com.example.movieapps.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.movieapps.R;

public class DashBoardActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_movieList, btn_favorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        btn_movieList = findViewById(R.id.btn_movieList);
        btn_favorite = findViewById(R.id.btn_favorite);

        btn_movieList.setOnClickListener(this);
        btn_favorite.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btn_movieList){
            Toast.makeText(getApplicationContext(), "HALO BANG", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), MovieActivity.class));
        }else if(v == btn_favorite){
            startActivity(new Intent(getApplicationContext(), FavoriteMovieActivity.class));
        }
    }
}