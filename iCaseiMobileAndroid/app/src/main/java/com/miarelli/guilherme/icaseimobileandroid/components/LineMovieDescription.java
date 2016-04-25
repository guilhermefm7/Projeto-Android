package com.miarelli.guilherme.icaseimobileandroid.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.miarelli.guilherme.icaseimobileandroid.R;

/**
 * Created by #Guilherme on 24/04/2016.
 */
public class LineMovieDescription implements Adaptable {

    private String title;
    private String description;
    private String poster;
    private String imdbID;

    public LineMovieDescription(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public LineMovieDescription(String title, String description, String poster, String imdbID) {
        this.title = title;
        this.description = description;
        this.poster = poster;
        this.imdbID = imdbID;
    }

    @Override
    public View objectToView(Context context, View convertView, LayoutInflater inflater) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.line_movie_description, null, false);
            viewHolder = new ViewHolder();

            viewHolder.title = (TextView) convertView.findViewById(R.id.lblTitle);
            viewHolder.description = (TextView) convertView.findViewById(R.id.lblDescription);
            convertView.setTag(viewHolder);
        } else {
            if (convertView.getTag() instanceof ViewHolder) {
                viewHolder = (ViewHolder) convertView.getTag();
            } else {
                convertView = inflater.inflate(R.layout.line_movie_description, null, false);
                viewHolder = new ViewHolder();

                viewHolder.title = (TextView) convertView.findViewById(R.id.lblTitle);
                viewHolder.description = (TextView) convertView.findViewById(R.id.lblDescription);
                convertView.setTag(viewHolder);
            }
        }

        viewHolder.title.setText(title);
        viewHolder.description.setText(description);

        return convertView;
    }

    static class ViewHolder {
        TextView title;
        TextView description;
        ImageView poster;
    }
}
