package com.forseti.drilltracker.clicklistener;

import android.app.FragmentManager;
import android.view.MenuItem;

import com.forseti.drilltracker.adapter.ExpandableDrillListAdapter;
import com.forseti.drilltracker.data.Category;
import com.forseti.drilltracker.views.EditCategoryFragment;

public class CategoryEditMenuListener implements MenuItem.OnMenuItemClickListener {
    private final ExpandableDrillListAdapter listAdapter;
    private final FragmentManager fragmentManager;

    public CategoryEditMenuListener(ExpandableDrillListAdapter listAdapter, FragmentManager fragmentManager) {
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
