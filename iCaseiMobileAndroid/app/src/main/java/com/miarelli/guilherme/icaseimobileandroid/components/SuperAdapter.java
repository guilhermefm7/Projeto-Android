package com.miarelli.guilherme.icaseimobileandroid.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by #Guilherme on 24/04/2016.
 */
public class SuperAdapter extends BaseAdapter {

    List<? extends Adaptable> listObjAdapter;
    Context context;

    public SuperAdapter(Context context, List<? extends Adaptable> listObjAdapter) {
        this.context = context;
        this.listObjAdapter = listObjAdapter;
    }

    @Override
    public int getCount() {
        return listObjAdapter.size();
    }

    @Override
    public Adaptable getItem(int index) {
        return listObjAdapter.get(index);
    }

    @Override
    public long getItemId(int index) {
        return index;
    }

    public void clearData() {
        listObjAdapter.clear();
    }

    @Override
    public View getView(int index, View view, ViewGroup parent) {
        return getItem(index).objectToView(context, view, LayoutInflater.from(parent.getContext()));
    }
}
