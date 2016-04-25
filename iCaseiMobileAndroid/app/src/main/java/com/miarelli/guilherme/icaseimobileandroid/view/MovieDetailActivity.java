package com.miarelli.guilherme.icaseimobileandroid.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.miarelli.guilherme.icaseimobileandroid.R;
import com.miarelli.guilherme.icaseimobileandroid.business.Movie;
import com.miarelli.guilherme.icaseimobileandroid.control.MovieController;
import com.miarelli.guilherme.icaseimobileandroid.utils.Constants;

import butterknife.ButterKnife;

public class MovieDetailActivity extends AppCompatActivity {

    private MovieController movieController;
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if(this.getIntent().getSerializableExtra(Constants.MOVIE)!=null){
            movie = (Movie) this.getIntent().getSerializableExtra(Constants.MOVIE);
        }
    }
}
