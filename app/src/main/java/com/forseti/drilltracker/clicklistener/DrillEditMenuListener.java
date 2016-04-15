package com.forseti.drilltracker.clicklistener;

import android.app.FragmentManager;
import android.content.Context;
import android.view.MenuItem;

import com.forseti.drilltracker.adapter.ExpandableDrillListAdapter;
import com.forseti.drilltracker.data.Drill;
import com.forseti.drilltracker.views.EditDrillFragment;

public class DrillEditMenuListener implements MenuItem.OnMenuItemClickListener {
    private final Context context;
    private final ExpandableDrillListAdapter listAdapter;
    private final FragmentManager fragmentManager;

    public DrillEditMenuListener(Context context, ExpandableDrillListAdapter listAdapter, FragmentManager fragmentManager) {
        this.context = context;
        this.listAdapter = listAdapter;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        EditDrillFragment drillFragment = new EditDrillFragment();
        drillFragment.setDrill((Drill) listAdapter.getChild(item.getGroupId(), item.getItemId()));
        drillFragment.setCategoryPosition(item.getGroupId());
        drillFragment.setListAdapter(listAdapter);
        drillFragment.show(fragmentManager, "CreateDrillFragment");
        return true;
    }
}
