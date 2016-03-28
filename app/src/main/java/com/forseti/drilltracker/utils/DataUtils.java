package com.forseti.drilltracker.utils;

import android.content.Context;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forseti.drilltracker.adapter.ExpandableDrillListAdapter;
import com.forseti.drilltracker.data.Category;

import org.json.JSONArray;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DataUtils {
    final static String FILENAME = "drill_list";

    public static void saveData(Context context, ExpandableDrillListAdapter listAdapter) {
        List<Category> categories = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        for (int index = 0; index < listAdapter.getGroupCount(); index++) {
            Category category = (Category) listAdapter.getGroup(index);
            Log.i("Data Category", category.toString());
            categories.add(category);
        }
        try {
            String dataString = mapper.writeValueAsString(categories);
            byte[] data = dataString.getBytes();
            Log.i("Data Byte", dataString);
            FileOutputStream fileOutputStream = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fileOutputStream.write(data);
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("Data", "DATA SAVED!");
    }

    public static void loadData(Context context, ExpandableDrillListAdapter listAdapter) {
        ObjectMapper mapper = new ObjectMapper();
        String rawDataString = "";

        try {
            FileInputStream fileInputStream = context.openFileInput(FILENAME);
            int cha;
            while ((cha = fileInputStream.read()) != -1) {
                rawDataString = rawDataString + (char) cha;
            }
            fileInputStream.close();

            Log.i("RawData", rawDataString);

            JSONArray jsonArray = new JSONArray(rawDataString);
            for (int index = 0; index < jsonArray.length(); index++) {
                String rawCategory = jsonArray.getString(index);
                Category loadedCategory = mapper.readValue(rawCategory, Category.class);
                Log.i("ParsedData", loadedCategory.toString());
                listAdapter.addCategory(context, loadedCategory);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.i("Data", "DATA LOADED");
    }
}
