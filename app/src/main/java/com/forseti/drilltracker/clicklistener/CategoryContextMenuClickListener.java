package com.forseti.drilltracker.clicklistener;

import android.view.MenuItem;
import android.widget.Toast;

import com.forseti.drilltracker.adapter.ExpandableDrillListAdapter;
import com.forseti.drilltracker.menuinfo.CategoryContextMenuInfo;

public class CategoryContextMenuClickListener implements MenuItem.OnMenuItemClickListener {

    ExpandableDrillListAdapter expandableDrillListAdapter;

    public CategoryContextMenuClickListener(ExpandableDrillListAdapter listAdapter) {
        this.expandableDrillListAdapter = listAdapter;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int itemId = item.getItemId();

        switch (itemId) {
            case CategoryContextMenuInfo.CATEGORY_DELETE:
                deleteCategory(item);
                return true;
            case CategoryContextMenuInfo.CATEGORY_EDIT:
                editCategory(item);
                return true;
            case CategoryContextMenuInfo.DRILL_DELETE:
                deleteDrill(item);
                return true;
            case CategoryContextMenuInfo.DRILL_EDIT:
                editDrill(item);
                return true;
            default:
                return true;
        }
    }

    private void editDrill(MenuItem item) {

    }

    private void deleteDrill(MenuItem item) {

    }

    private void editCategory(MenuItem item) {

    }

    private void deleteCategory(MenuItem item) {

    }
}
