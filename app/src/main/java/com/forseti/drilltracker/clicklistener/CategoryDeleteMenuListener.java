package com.forseti.drilltracker.clicklistener;

import android.content.Context;
import android.view.MenuItem;
import android.widget.Toast;

import com.forseti.drilltracker.adapter.ExpandableDrillListAdapter;

public class CategoryDeleteMenuListener implements MenuItem.OnMenuItemClickListener {
    private final Context context;
    private final ExpandableDrillListAdapter listAdapter;

    public CategoryDeleteMenuListener(Context context, ExpandableDrillListAdapter listAdapter) {
        this.context = context;
        this.listAdapter = listAdapter;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (listAdapter.getChildrenCount(item.getGroupId()) != 0) {
            Toast toast = Toast.makeText(context, "Not an empty category!", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            listAdapter.removeCategory(context, item.getGroupId());
        }
        return true;
    }
}
