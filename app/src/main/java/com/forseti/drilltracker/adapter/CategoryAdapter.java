package com.forseti.drilltracker.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import com.forseti.drilltracker.Category;

import java.util.List;

public class CategoryAdapter extends ArrayAdapter {

    public CategoryAdapter(Context context, int resource) {
        super(context, resource);
    }

}
