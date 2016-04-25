package com.miarelli.guilherme.icaseimobileandroid.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.miarelli.guilherme.icaseimobileandroid.R;
import com.miarelli.guilherme.icaseimobileandroid.control.MovieController;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.imgMenu)
    ImageView imgMenu;

    @Bind(R.id.imgMenuSimbolic)
    ImageView imgMenuSimbolic;

    @Bind(R.id.imgClose)
    ImageView imgClose;

    @Bind(R.id.imgCloseSimbolic)
    ImageView imgCloseSimbolic;

    @Bind(R.id.imgSearch)
    ImageView imgSearch;

    @Bind(R.id.imgSearchSimbolic)
    ImageView imgSearchSimbolic;

    @Bind(R.id.imgBack)
    ImageView imgBack;

    @Bind(R.id.imgBackSimbolic)
    ImageView imgBackSimbolic;

    @Bind(R.id.rlSearchInfo)
    LinearLayout rlSearchInfo;

    @Bind(R.id.edtSearch)
    EditText edtSearch;

    @Bind(R.id.rlMovieList)
    RelativeLayout rlMovieList;

    @Bind(R.id.listMovie)
    ListView listMovie;

    private MovieController movieController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setUpControllers();
        setListeners();
    }

    @OnClick(R.id.imgSearch)
    void onItemClickSearch(View view) {
        setComponentsVisibiltyForSearch(true);
        setStateForSearch(true);
    }

    @OnClick(R.id.imgClose)
    void onItemClickClose(View view) {
        setComponentsVisibiltyForSearch(false);
        setStateForSearch(false);
    }

    @OnClick(R.id.imgBack)
    void onItemClickBack(View view) {
        setComponentsVisibiltyForSearch(false);
        setStateForSearch(false);
    }

    @OnClick(R.id.edtSearch)
    void onItemClickEdtSearch(View view) {

    }

    private void setListeners(){
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edtSearch.getText().length() > 0) {
                    rlSearchInfo.setVisibility(View.GONE);
                    rlMovieList.setVisibility(View.VISIBLE);
                    movieController.searchMovie(edtSearch.getText().toString());
                } else {
                    rlSearchInfo.setVisibility(View.VISIBLE);
                    rlMovieList.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        listMovie.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                movieController.callMovieDetailActivity();
            }
        });
    }

    private void setUpControllers(){
        movieController = new MovieController(this, listMovie);
    }

    /**
     * Method used for setting components visibility
     */
    private void setComponentsVisibiltyForSearch(boolean hasBeenSearching){
        if(hasBeenSearching){
            imgMenu.setVisibility(View.GONE);
            imgMenuSimbolic.setVisibility(View.GONE);

            imgSearch.setVisibility(View.GONE);
            imgSearchSimbolic.setVisibility(View.GONE);

            imgBack.setVisibility(View.VISIBLE);
            imgBackSimbolic.setVisibility(View.VISIBLE);

            imgClose.setVisibility(View.VISIBLE);
            imgCloseSimbolic.setVisibility(View.VISIBLE);
        }
        else{
            imgMenu.setVisibility(View.VISIBLE);
            imgMenuSimbolic.setVisibility(View.VISIBLE);

            imgSearch.setVisibility(View.VISIBLE);
            imgSearchSimbolic.setVisibility(View.VISIBLE);

            imgBack.setVisibility(View.GONE);
            imgBackSimbolic.setVisibility(View.GONE);

            imgClose.setVisibility(View.GONE);
            imgCloseSimbolic.setVisibility(View.GONE);
        }
    }

    /**
     * Method used for setting the search edit text state
     */
    private void setStateForSearch(boolean hasBeenSearching){
        setEditTextFocus(edtSearch, hasBeenSearching);
    }

    private void setEditTextFocus(EditText edt, boolean setFocus){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if(setFocus){
            edt.setVisibility(View.VISIBLE);
            edt.setFocusableInTouchMode(true);
            edt.setFocusable(true);
            edt.requestFocus();
            imm.showSoftInput(edt, InputMethodManager.SHOW_IMPLICIT);
        }
        else{
            edtSearch.setText("");
            edt.clearFocus();
            edt.setVisibility(View.GONE);
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
        }
    }

}
