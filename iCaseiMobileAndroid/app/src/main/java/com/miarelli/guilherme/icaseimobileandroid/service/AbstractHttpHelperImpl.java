package com.miarelli.guilherme.icaseimobileandroid.service;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper class that retrieves HTML or text from an HTTP address and lets you specify two event handlers, a method
 * that is called on a separate thread to parse the HTML/text and a method that is called on the UI thread to
 * update the user interface
 */
public abstract class AbstractHttpHelperImpl implements AbstractHttpHelper {
    /**
     * Specify an implementation for this callback in order to hande code in the background to parse the text
     * in the execute method and then code to handle updating the UI on the UI thread in the finish method.
     *
     * @param <String> The type of data that execute returns and finish uses to update the UI
     */
    public interface Callback<String> {
        void finish(String result);
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    //Variable constant responsible for getting the HTTP Request
    protected String constant;


    //Variable responsible for getting the URL params
    protected String params;

    //Variable responsible for getting the full HTTP Request URL
    protected String httpRequestURL;

    public AbstractHttpHelperImpl(String constant,String params) {
        super();
        this.constant = constant;
        setParams(params);
    }

    /**
     * Read all HTML or text from the input stream using the specified text encoding
     *
     * @param input    The stream to read text from
     * @param encoding The encoding of the stream
     * @return All text read from the stream
     */
    private static String readAll(InputStream input, String encoding) {
        try {
            InputStreamReader reader = new InputStreamReader(input, encoding);
            StringBuilder result = new StringBuilder();
            char[] buffer = new char[4096];
            int len;
            while ((len = reader.read(buffer, 0, buffer.length)) > 0) {
                result.append(buffer, 0, len);
            }
            reader.close();
            return result.toString();
        } catch (IOException ignored) {
        }
        return null;
    }

    /**
     * Find out and return what type of text encoding is specified by the server
     *
     * @param conn The opened HTTP connection to fetch the encoding for
     * @return The string name of the encoding. utf-8 is the default.
     */
    private static String getEncoding(HttpURLConnection conn) {
        String encoding = "utf-8";
        String contentType = conn.getHeaderField("Content-Type").toLowerCase();
        if (contentType.contains("charset=")) {
            int found = contentType.indexOf("charset=");
            encoding = contentType.substring(found + 8, contentType.length()).trim();
        } else if (conn.getContentEncoding() != null) {
            encoding = conn.getContentEncoding();
        }
        return encoding;
    }

    /**
     * Perform an HTTP GET network request to retrieve text from a remote web site or API. Specify a callback
     * to handle the text parsing and UI updating.
     *
     * @param callback The event handlers that will be called by the helper method
     */
    public void get(final Callback<String> callback) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL src = new URL(httpRequestURL);
                    HttpURLConnection conn = (HttpURLConnection) src.openConnection();
                    conn.setRequestMethod("GET");
                    int responseCode = conn.getResponseCode();

                    String html;

                    if (responseCode == 200) {
                        html = readAll(conn.getInputStream(), getEncoding(conn));
                        conn.disconnect();
                    } else {
                        html = readAll(conn.getErrorStream(), getEncoding(conn));
                    }

                    return html;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String result) {
                callback.finish(result);
            }
        }.execute();
    }

    /**
     * Method responsible for manipulating the HTTP Request URL
     */
    public void manipulatingURL() {
    }

    public String getConstant() {
        return constant;
    }

    public void setConstant(String constant) {
        this.constant = constant;
    }

    public String getHttpRequestURL() {
        return httpRequestURL;
    }

    public void setHttpRequestURL(String httpRequestURL) {
        this.httpRequestURL = httpRequestURL;
    }

}