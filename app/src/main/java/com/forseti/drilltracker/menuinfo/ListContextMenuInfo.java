package com.forseti.drilltracker.menuinfo;

import android.app.FragmentManager;
import android.content.Context;
import android.view.ContextMenu;
import android.widget.ExpandableListView;

import com.forseti.drilltracker.R;
import com.forseti.drilltracker.adapter.ExpandableDrillListAdapter;
import com.forseti.drilltracker.clicklistener.CategoryDeleteMenuListener;
import com.forseti.drilltracker.clicklistener.CategoryEditMenuListener;
import com.forseti.drilltracker.clicklistener.DrillDeleteMenuListener;
import com.forseti.drilltracker.clicklistener.DrillEditMenuListener;

public class ListContextMenuInfo {

    public static void createMenu(Context context, ContextMenu menu, ExpandableListView.ExpandableListContextMenuInfo info, ExpandableDrillListAdapter listAdapter, FragmentManager fragmentManager) {
        int category = ExpandableListView.getPackedPositionGroup(info.packedPosition);
        int type = ExpandableListView.getPackedPositionType(info.packedPosition);
        int drillPosition = ExpandableListView.getPackedPositionChild(info.packedPosition);

        if (type == ExpandableListView.PACKED_POSITION_TYPE_GROUP) {
            menu.add(category, drillPosition, 2, R.string.delete_categoy).setOnMenuItemClickListener(new CategoryDeleteMenuListener(context, listAdapter));
            menu.add(category, drillPosition, 2, R.string.edit_categoy).setOnMenuItemClickListener(new CategoryEditMenuListener(listAdapter, fragmentManager));
        }
        else {
            menu.add(category, drillPosition, 2, R.string.delete_drill).setOnMenuItemClickListener(new DrillDeleteMenuListener(context, listAdapter));
            menu.add(category, drillPosition, 2, R.string.edit_drill).setOnMenuItemClickListener(new DrillEditMenuListener(listAdapter, fragmentManager));
        }
    }
}
