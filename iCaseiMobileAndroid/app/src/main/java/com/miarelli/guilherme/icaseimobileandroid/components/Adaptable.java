package com.miarelli.guilherme.icaseimobileandroid.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by #Guilherme on 24/04/2016.
 */
public interface Adaptable {
    View objectToView(Context context, View convertView, LayoutInflater inflater);
}
