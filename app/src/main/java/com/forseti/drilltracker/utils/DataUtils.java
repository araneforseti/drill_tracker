package com.forseti.drilltracker.utils;

import android.content.Context;
import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.forseti.drilltracker.adapter.ExpandableDrillListAdapter;
import com.forseti.drilltracker.data.Category;

import org.json.JSONArray;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DataUtils {
    final static String FILENAME = "drill_list";

    public static void saveData(Context context, ExpandableDrillListAdapter listAdapter) {
        List<Category> categories = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        Log.i("Data saving", "Saving " + listAdapter.getGroupCount());

        for (int index = 0; index < listAdapter.getGroupCount(); index++) {
            Category category = (Category) listAdapter.getGroup(index);
            Log.i("Data Category", category.toString());
            categories.add(category);
        }
        try {
            String dataString = mapper.writeValueAsString(categories);
            byte[] data = dataString.getBytes();
            FileOutputStream fileOutputStream = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fileOutputStream.write(data);
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    }

    public static void loadFromFile(Context context, ExpandableDrillListAdapter listAdapter, String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        String rawDataString = "";

        try {
            FileInputStream fileInputStream = new FileInputStream(new File(filePath));
            int cha;
            while ((cha = fileInputStream.read()) != -1) {
                rawDataString = rawDataString + (char) cha;
            }
            fileInputStream.close();

            JSONArray jsonArray = new JSONArray(rawDataString);
            for (int index = 0; index < jsonArray.length(); index++) {
                String rawCategory = jsonArray.getString(index);
                Category loadedCategory = mapper.readValue(rawCategory, Category.class);
                Log.i("ParsedData", loadedCategory.toString());
                listAdapter.addCategoryAndDrills(context, loadedCategory);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getData(ExpandableDrillListAdapter listAdapter) {
        List<Category> categories = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        String dataString = "";

        Log.i("Data saving", "Saving " + listAdapter.getGroupCount());

        for (int index = 0; index < listAdapter.getGroupCount(); index++) {
            Category category = (Category) listAdapter.getGroup(index);
            Log.i("Data Category", category.toString());
            categories.add(category);
        }

        try {
            dataString = mapper.writeValueAsString(categories);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return dataString;
    }
}
