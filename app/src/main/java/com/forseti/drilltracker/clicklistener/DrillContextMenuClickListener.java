package com.forseti.drilltracker.clicklistener;

import android.view.MenuItem;

import com.forseti.drilltracker.adapter.ExpandableDrillListAdapter;

public class DrillContextMenuClickListener  implements MenuItem.OnMenuItemClickListener  {
    private ExpandableDrillListAdapter listAdapter;

    public DrillContextMenuClickListener(ExpandableDrillListAdapter listAdapter) {
        this.listAdapter = listAdapter;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }
}
