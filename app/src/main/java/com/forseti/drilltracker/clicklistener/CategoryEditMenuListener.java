package com.forseti.drilltracker.clicklistener;

import android.app.Activity;
import android.app.Application;
import android.app.FragmentManager;
import android.content.Context;
import android.view.MenuItem;

import com.forseti.drilltracker.adapter.ExpandableDrillListAdapter;
import com.forseti.drilltracker.data.Category;
import com.forseti.drilltracker.views.EditCategoryFragment;

public class CategoryEditMenuListener implements MenuItem.OnMenuItemClickListener {
    private final Context context;
    private final ExpandableDrillListAdapter listAdapter;
    private final FragmentManager fragmentManager;

    public CategoryEditMenuListener(Context context, ExpandableDrillListAdapter listAdapter, FragmentManager fragmentManager) {
        this.context = context;
        this.listAdapter = listAdapter;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Category category = (Category) listAdapter.getGroup(item.getGroupId());
        EditCategoryFragment editCategoryFragment = new EditCategoryFragment();
        editCategoryFragment.setCategory(category);
        editCategoryFragment.show(fragmentManager, "CreateCategoryFragment");
        return true;
    }
}
