package com.forseti.drilltracker.clicklistener;

import android.content.Context;
import android.util.Log;
import android.view.MenuItem;

import com.forseti.drilltracker.adapter.ExpandableDrillListAdapter;

public class DrillDeleteMenuListener implements MenuItem.OnMenuItemClickListener {


    private final ExpandableDrillListAdapter listAdapter;
    private final Context context;

    public DrillDeleteMenuListener(Context context, ExpandableDrillListAdapter listAdapter) {
        this.context = context;
        this.listAdapter = listAdapter;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Log.i("Data Removal", "Category: " + item.getGroupId() + " | Drill: " + item.getItemId());
        listAdapter.removeDrill(context, item.getGroupId(), item.getItemId());
        return true;
    }
}
