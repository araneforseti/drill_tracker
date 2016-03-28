package com.forseti.drilltracker.clicklistener;

import android.content.Context;
import android.util.Log;
import android.view.MenuItem;

import com.forseti.drilltracker.adapter.ExpandableDrillListAdapter;
import com.forseti.drilltracker.menuinfo.ListContextMenuInfo;

public class CategoryContextMenuClickListener implements MenuItem.OnMenuItemClickListener {

    ExpandableDrillListAdapter expandableDrillListAdapter;
    Context context;

    public CategoryContextMenuClickListener(Context context, ExpandableDrillListAdapter listAdapter) {
        this.expandableDrillListAdapter = listAdapter;
        this.context = context;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int itemId = item.getItemId();

        switch (itemId) {
            case ListContextMenuInfo.CATEGORY_DELETE:
                Log.i("Category Removal", "Removing a category!");
                deleteCategory(item);
                return true;
            case ListContextMenuInfo.CATEGORY_EDIT:
                Log.i("Category Editing", "Editing a category!");
                editCategory(item);
                return true;
            default:
                return true;
        }
    }

    private void editCategory(MenuItem item) {

    }

    private void deleteCategory(MenuItem item) {

    }
}
