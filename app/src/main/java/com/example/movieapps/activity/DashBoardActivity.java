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
            startActivity(new Intent(getApplicationContext(), MovieActivity.class));
            finish();
        }else if(v == btn_favorite){
            startActivity(new Intent(getApplicationContext(), FavoriteMovieActivity.class));
            finish();
        }
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