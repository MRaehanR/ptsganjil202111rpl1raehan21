package com.example.movieapps.helper;

import android.util.Log;

import com.example.movieapps.model.MovieModel;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {
    Realm realm;

    public RealmHelper(Realm realm) {
        this.realm = realm;
    }

    public void save(final MovieModel movieModel) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (realm != null) {
                    Log.e("Created", "Database was created");
                    Number currentIdNum = realm.where(MovieModel.class).max("id");
                    int nextId;
                    if (currentIdNum == null) {
                        nextId = 1;
                    } else {
                        nextId = currentIdNum.intValue() + 1;
                    }
                    movieModel.setId(nextId);
                    MovieModel model = realm.copyToRealm(movieModel);
                } else {
                    Log.e("Failed", "execute: Database not Exist");
                }
            }
        });
    }

    public List<MovieModel> getAllMovies() {
        RealmResults<MovieModel> results = realm.where(MovieModel.class).findAll();
        return results;
    }

    public void update(final Integer id, final String original_title, final String overview, final String release_date, final String poster_path){
        realm.executeTransactionAsync(realm1 -> {
            MovieModel movieModel = realm.where(MovieModel.class)
                    .equalTo("id", id)
                    .findFirst();
            movieModel.setOriginal_title(original_title);
            movieModel.setOverview(overview);
            movieModel.setRelease_date(release_date);
        }, () -> {
            Log.e("Success", "onSuccess: Update Successfully");
        }, error -> {
            error.printStackTrace();
        });
    }

    // Error tidak bisa delete 
    public void delete(Integer id){
//        final RealmResults<MovieModel> model = realm.where(MovieModel.class)
//                .equalTo("id", id)
//                .findAllAsync();
//        MovieModel model = realm.where(MovieModel.class).equalTo("id", id).findFirst();
//        realm.executeTransactionAsync(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                final RealmResults<MovieModel> model = realm.where(MovieModel.class)
//                        .equalTo("id", id)
//                        .findAllAsync();
//                model.deleteAllFromRealm();
//            }
//        }, new Realm.Transaction.OnSuccess() {
//            @Override
//            public void onSuccess() {
//                Log.d("Success", "Sucsess");
//            }
//        }, new Realm.Transaction.OnError() {
//            @Override
//            public void onError(Throwable error) {
//                error.printStackTrace();
//                Log.d("error", "error ngab");
//                Log.d("idnya", String.valueOf(id));
////                Log.d("Modelnya", String.valueOf(model));
//            }
//        });

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                final RealmResults<MovieModel> model = realm.where(MovieModel.class).equalTo("id", id).findAll();
                model.deleteFirstFromRealm();
            }
        });
    }
}