package com.forseti.drilltracker.menuinfo;

import android.view.ContextMenu;
import android.widget.ExpandableListView;

import com.forseti.drilltracker.adapter.ExpandableDrillListAdapter;
import com.forseti.drilltracker.clicklistener.CategoryContextMenuClickListener;
import com.forseti.drilltracker.clicklistener.DrillContextMenuClickListener;

public class CategoryContextMenuInfo {
    public final static int CATEGORY_EDIT = 0;
    public final static int CATEGORY_DELETE = 1;
    public final static int DRILL_EDIT = 2;
    public final static int DRILL_DELETE = 3;

    public static void createMenu(ContextMenu menu, ExpandableListView.ExpandableListContextMenuInfo info, ExpandableDrillListAdapter listAdapter) {
        int type = ExpandableListView.getPackedPositionType(info.packedPosition);
        if (type == ExpandableListView.PACKED_POSITION_TYPE_GROUP) {
            CategoryContextMenuClickListener categoryContextMenuClickListener = new CategoryContextMenuClickListener(listAdapter);
            menu.add(0, CATEGORY_EDIT, 1, "Edit Category Name").setOnMenuItemClickListener(categoryContextMenuClickListener);
            menu.add(0, CATEGORY_DELETE, 2, "Delete Category").setOnMenuItemClickListener(categoryContextMenuClickListener);
        }
        else {
            DrillContextMenuClickListener drillContextMenuClickListener = new DrillContextMenuClickListener(listAdapter);
            menu.add(0, DRILL_EDIT, 1, "Edit Drill").setOnMenuItemClickListener(drillContextMenuClickListener);
            menu.add(0, DRILL_DELETE, 2, "Delete Drill").setOnMenuItemClickListener(drillContextMenuClickListener);;
        }
    }
}
