package com.forseti.drilltracker.menuinfo;

import android.content.Context;
import android.view.ContextMenu;
import android.widget.ExpandableListView;

import com.forseti.drilltracker.R;
import com.forseti.drilltracker.adapter.ExpandableDrillListAdapter;
import com.forseti.drilltracker.clicklistener.CategoryDeleteMenuListener;
import com.forseti.drilltracker.clicklistener.DrillDeleteMenuListener;

public class ListContextMenuInfo {

    public static void createMenu(Context context, ContextMenu menu, ExpandableListView.ExpandableListContextMenuInfo info, ExpandableDrillListAdapter listAdapter) {
        int category = ExpandableListView.getPackedPositionGroup(info.packedPosition);
        int type = ExpandableListView.getPackedPositionType(info.packedPosition);
        int drillPosition = ExpandableListView.getPackedPositionChild(info.packedPosition);

        if (type == ExpandableListView.PACKED_POSITION_TYPE_GROUP) {
            menu.add(category, drillPosition, 2, R.string.delete_categoy).setOnMenuItemClickListener(new CategoryDeleteMenuListener(context, listAdapter));
        }
        else {
            menu.add(category, drillPosition, 2, R.string.delete_drill).setOnMenuItemClickListener(new DrillDeleteMenuListener(context, listAdapter));;
        }
    }
}
