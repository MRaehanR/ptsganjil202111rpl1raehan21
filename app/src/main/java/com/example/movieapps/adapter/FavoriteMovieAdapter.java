package com.example.movieapps.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movieapps.R;
import com.example.movieapps.activity.FavoriteDetailActivity;
import com.example.movieapps.activity.FavoriteMovieActivity;
import com.example.movieapps.activity.MovieDetailActivity;
import com.example.movieapps.helper.RealmHelper;
import com.example.movieapps.model.MovieModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class FavoriteMovieAdapter extends RecyclerView.Adapter<FavoriteMovieAdapter.FavoriteMovieViewHolder>{


    RealmHelper realmHelper;
    Realm realm;
    public List<MovieModel> movieModelList;
    Context context;

    public FavoriteMovieAdapter(List<MovieModel> movieModelList, Context context) {
        this.movieModelList = movieModelList;
        this.context = context;
        setHasStableIds(true);
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public FavoriteMovieViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_list, parent, false);
        return new FavoriteMovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull FavoriteMovieAdapter.FavoriteMovieViewHolder holder, int position) {
        holder.tvOriginalTitle.setText(movieModelList.get(position).getOriginal_title());
        holder.tvRealeseDate.setText(movieModelList.get(position).getRelease_date());
        String url = movieModelList.get(position).getPoster_path();
        Glide.with(context)
                .load(url)
                .into(holder.ivPoster);
        holder.cvMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FavoriteDetailActivity.class);
                intent.putExtra("id", movieModelList.get(position).getId());
                intent.putExtra("original_title", movieModelList.get(position).getOriginal_title());
                intent.putExtra("overview", movieModelList.get(position).getOverview());
                intent.putExtra("release_date", movieModelList.get(position).getRelease_date());
                intent.putExtra("poster_path", movieModelList.get(position).getPoster_path());
                context.startActivity(intent);
            }
        });
        holder.positionItem = movieModelList.get(position).getId();
    }

    @Override
    public int getItemCount() {
        return (movieModelList != null) ? movieModelList.size() : 0;
    }

    public class FavoriteMovieViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener{

        int positionItem;
        TextView tvOriginalTitle, tvRealeseDate;
        CardView cvMovie;
        ImageView ivPoster;

        
        public FavoriteMovieViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            tvOriginalTitle = itemView.findViewById(R.id.tv_original_title);
            tvRealeseDate = itemView.findViewById(R.id.tv_realese_date);
            cvMovie = itemView.findViewById(R.id.cv_movie);
            ivPoster = itemView.findViewById(R.id.iv_poster);

            cvMovie.setOnCreateContextMenuListener(this);

            RealmConfiguration configuration = new RealmConfiguration.Builder().build();
            realm = Realm.getInstance(configuration);
            realmHelper = new RealmHelper(realm);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()){
                case R.id.option1:
//                    Toast.makeText(context, positionItem, Toast.LENGTH_SHORT).show();
                    System.out.println("positionItem"+ positionItem);
                    Log.d("ini position", String.valueOf(positionItem));
                    break;
                case R.id.option2:
                    Toast.makeText(context, "Delete", Toast.LENGTH_SHORT).show();
                    realmHelper.delete(positionItem);
                    break;
            }

            notifyDataSetChanged();
            return false;
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(R.menu.context_menu, R.id.option1, 1, "Edit").setOnMenuItemClickListener(this);
            menu.add(R.menu.context_menu, R.id.option2, 2, "Delete").setOnMenuItemClickListener(this);
        }
    }
}
