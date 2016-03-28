package com.forseti.drilltracker.clicklistener;

import android.content.Context;
import android.util.Log;
import android.view.MenuItem;

import com.forseti.drilltracker.adapter.ExpandableDrillListAdapter;
import com.forseti.drilltracker.menuinfo.ListContextMenuInfo;

public class DrillContextMenuClickListener  implements MenuItem.OnMenuItemClickListener  {
    private ExpandableDrillListAdapter listAdapter;
    private Context context;

    public DrillContextMenuClickListener(Context context, ExpandableDrillListAdapter listAdapter) {
        this.listAdapter = listAdapter;
        this.context = context;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int itemId = item.getItemId();

        switch (itemId) {
            case ListContextMenuInfo.DRILL_DELETE:
                Log.i("Data Removal", "Removing a drill!");
                deleteDrill(item);
                return true;
            case ListContextMenuInfo.DRILL_EDIT:
                Log.i("Data Editing", "Editing a drill!");
                editDrill(item);
                return true;
            default:
                return true;
        }
    }

    private void editDrill(MenuItem item) {

    }

    private void deleteDrill(MenuItem item) {
        Log.i("Data Removal", "Category: " + item.getGroupId() + " | Drill: " + item.getItemId());
        listAdapter.removeDrill(context, item.getGroupId(), item.getItemId());
    }
}
