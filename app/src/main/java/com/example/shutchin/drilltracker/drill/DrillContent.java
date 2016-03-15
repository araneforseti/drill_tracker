package com.example.shutchin.drilltracker.drill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DrillContent {
    public static final List<DrillItem> ITEMS = new ArrayList<DrillItem>();

    public static final Map<String, DrillItem> ITEM_MAP = new HashMap<String, DrillItem>();

    public static void addItem(DrillItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }
}
