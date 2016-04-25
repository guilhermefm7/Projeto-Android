package com.miarelli.guilherme.icaseimobileandroid.control;

import android.content.Context;
import android.content.Intent;
import android.widget.ListView;

import com.google.gson.Gson;
import com.miarelli.guilherme.icaseimobileandroid.business.Movie;
import com.miarelli.guilherme.icaseimobileandroid.components.Adaptable;
import com.miarelli.guilherme.icaseimobileandroid.components.LineMovieDescription;
import com.miarelli.guilherme.icaseimobileandroid.components.SuperAdapter;
import com.miarelli.guilherme.icaseimobileandroid.service.AbstractHttpHelperImpl;
import com.miarelli.guilherme.icaseimobileandroid.utils.Constants;
import com.miarelli.guilherme.icaseimobileandroid.view.MovieDetailActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guilherme.miarelli on 23/04/2016.
 * Controller responsible for controlling Movie manipulation
 */
public class MovieController extends AbstractController<Movie, Movie>{

    private ArrayList<Adaptable> listMovieDetail = new ArrayList<>();
    private SuperAdapter superAdapter;
    private Context context;
    private LineMovieDescription lineExplorerDetailStoreImage;
    private AbstractHttpHelperImpl.Callback<String> callback;
    private Gson gson = new Gson();
    private List<Movie> listMovie;
    private ListView listViewMovie;

    public MovieController(Context context, ListView listViewMovie) {
        super();
        this.context = context;
        this.listViewMovie = listViewMovie;
    }

    public void searchMovie(String movieName){
        listMovie = new ArrayList<>();
        getEntity().movieRequest(Constants.MOVIE, movieName, callback = new AbstractHttpHelperImpl.Callback<String>() {
            @Override
            public void finish(String result) {

                if (result != null) {
                    JSONObject data = null;
                    JSONArray array = null;
                    try {
                        data = new JSONObject(result);
                        if (data != null && data.has("Search")) {
                            array = (JSONArray) data.get("Search");

                            if (array.length() > 0) {
                                for (int i = 0; i < array.length(); i++) {
                                    listMovie.add(gson.fromJson(array.get(i).toString(), Movie.class));
                                }
                            }

                            setAdapter();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void setAdapter(){
        listMovieDetail = new ArrayList<>();
        for(Movie m : listMovie){
            lineExplorerDetailStoreImage = new LineMovieDescription(m.getTitle(), m.getDescription());
            listMovieDetail.add(lineExplorerDetailStoreImage);
        }

        superAdapter = new SuperAdapter(context, listMovieDetail);
        listViewMovie.setAdapter(superAdapter);
    }

    public void callMovieDetailActivity(){
        Intent myIntent = new Intent(context, MovieDetailActivity.class);
        myIntent.putExtra(Constants.MOVIE,getEntity());
        context.startService(myIntent);
    }

}

