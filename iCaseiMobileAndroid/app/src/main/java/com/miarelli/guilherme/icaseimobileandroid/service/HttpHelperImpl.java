package com.miarelli.guilherme.icaseimobileandroid.service;


import com.miarelli.guilherme.icaseimobileandroid.utils.Constants;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;

/**
 * Created by guilherme.miarelli on 03/11/2015.
 */
public class HttpHelperImpl extends AbstractHttpHelperImpl {

    public HttpHelperImpl(String constant, String params) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        super(constant, params);
        if(constant!=null && constant.length()>0){
            manipulatingURL();
        }
    }

    /**
     * Method responsible for manipulating the HTTP Request URL
     */
    @Override
    public void manipulatingURL(){

        //Creating a request in the Yahoo Weather API
        if(constant.equals(Constants.MOVIE)){
            getMovie();
        }
    }

    public void getMovie(){

        params = params.replaceAll(" ", "%20");
        try {
            params = URLEncoder.encode(params, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        setHttpRequestURL("http://www.omdbapi.com/?s=%7B" + params + "%7D");
    }

}
