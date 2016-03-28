package com.forseti.drilltracker.menuinfo;

import android.content.Context;
import android.view.ContextMenu;
import android.widget.ExpandableListView;

import com.forseti.drilltracker.adapter.ExpandableDrillListAdapter;
import com.forseti.drilltracker.clicklistener.CategoryContextMenuClickListener;
import com.forseti.drilltracker.clicklistener.DrillContextMenuClickListener;
import com.forseti.drilltracker.clicklistener.DrillDeleteMenuListener;

public class ListContextMenuInfo {
    public final static int CATEGORY_EDIT = 0;
    public final static int CATEGORY_DELETE = 1;
    public final static int DRILL_EDIT = 2;
    public final static int DRILL_DELETE = 3;

    public static void createMenu(Context context, ContextMenu menu, ExpandableListView.ExpandableListContextMenuInfo info, ExpandableDrillListAdapter listAdapter) {
        int category = ExpandableListView.getPackedPositionGroup(info.packedPosition);
        int type = ExpandableListView.getPackedPositionType(info.packedPosition);
        int drillPosition = ExpandableListView.getPackedPositionChild(info.packedPosition);

        if (type == ExpandableListView.PACKED_POSITION_TYPE_GROUP) {
            CategoryContextMenuClickListener categoryContextMenuClickListener = new CategoryContextMenuClickListener(context, listAdapter);
            menu.add(category, drillPosition, 1, "Edit Category Name").setOnMenuItemClickListener(categoryContextMenuClickListener);
            menu.add(category, drillPosition, 2, "Delete Category").setOnMenuItemClickListener(categoryContextMenuClickListener);
        }
        else {
            DrillContextMenuClickListener drillContextMenuClickListener = new DrillContextMenuClickListener(context, listAdapter);
            menu.add(category, drillPosition, 1, "Edit Drill").setOnMenuItemClickListener(drillContextMenuClickListener);
            menu.add(category, drillPosition, 2, "Delete Drill").setOnMenuItemClickListener(new DrillDeleteMenuListener(context, listAdapter));;
        }
    }
}
