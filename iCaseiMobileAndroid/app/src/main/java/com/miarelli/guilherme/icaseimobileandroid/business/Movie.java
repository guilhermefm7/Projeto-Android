package com.miarelli.guilherme.icaseimobileandroid.business;

import com.miarelli.guilherme.icaseimobileandroid.service.AbstractHttpHelperImpl;
import com.miarelli.guilherme.icaseimobileandroid.service.HttpHelperImpl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by guilherme.miarelli on 23/04/2016.
 * Class responsible for receiving movie information
 */
public class Movie implements Serializable{

    private String imdbID = "";
    private String Title = "";
    private String Poster = "";
    private String description = "";

    public Movie() {
    }

    public void movieRequest(String constant, String movieName, AbstractHttpHelperImpl.Callback<String> callback){
        HttpHelperImpl httpHelperImpl = null;

        try {
            httpHelperImpl = new HttpHelperImpl(constant, movieName);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        //Making a HTTP request from Yahoo Weather API
        httpHelperImpl.get(callback);
    }

    public String getImdbID() {
        return imdbID;
    }

    public String getTitle() {
        return Title;
    }

    public String getPoster() {
        return Poster;
    }

    public String getDescription() {
        return description;
    }
}
