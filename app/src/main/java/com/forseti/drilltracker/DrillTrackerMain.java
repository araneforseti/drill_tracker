package com.forseti.drilltracker;

import android.annotation.TargetApi;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.ReaderBasedJsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.forseti.drilltracker.adapter.ExpandableDrillListAdapter;
import com.forseti.drilltracker.temporary.TemporaryDataUtils;
import com.forseti.drilltracker.views.CreateCategoryFragment;
import com.forseti.drilltracker.views.CreateDrillFragment;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DrillTrackerMain extends AppCompatActivity implements CreateDrillFragment.CreateDrillListener, CreateCategoryFragment.CreateCategoryDialogListener {
    String FILENAME = "drill_list";
    ExpandableDrillListAdapter listAdapter;
    ExpandableListView listView;
    List<Category> categoryList;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        listView = (ExpandableListView) findViewById(R.id.expanded_menu);
        categoryList = new ArrayList<>();

        listAdapter = new ExpandableDrillListAdapter(this, categoryList);
        listView.setAdapter(listAdapter);

        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });

        listView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
            }
        });

        listView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
            }
        });

        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                return false;
            }
        });

        loadData();
    }

    public void addCategory(View view) {
        CreateCategoryFragment createCategoryFragment = new CreateCategoryFragment();
        FragmentManager manager = getFragmentManager();
        createCategoryFragment.show(manager, "CreateCategoryFragment");
    }

    public void addDrill(View view) {
        if (listAdapter.getGroupCount() == 0) {
            Toast toast = Toast.makeText(getApplicationContext(), R.string.no_categories, Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            CreateDrillFragment drillFragment = new CreateDrillFragment();
            drillFragment.setListAdapter(listAdapter);
            FragmentManager manager = getFragmentManager();
            drillFragment.show(manager, "CreateDrillFragment");
        }
    }

    @Override
    public void onDialogPositiveClick(int categoryPosition, Drill newDrill) {
        Category category = (Category) listAdapter.getGroup(categoryPosition);
        category.getDrills().add(newDrill);
        listAdapter.notifyDataSetChanged();
        saveData();
    }

    @Override
    public void onDialogPositiveClick(String userInput) {
        Category newCategory = new Category(userInput);
        listAdapter.addCategory(newCategory);
        saveData();
    }

    public void saveData() {
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
            FileOutputStream fileOutputStream = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fileOutputStream.write(data);
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("Data", "DATA SAVED!");
    }

    public void loadData() {
        ObjectMapper mapper = new ObjectMapper();
        String rawDataString = "";

        try {
            FileInputStream fileInputStream = openFileInput(FILENAME);
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
                listAdapter.addCategory(loadedCategory);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.i("Data", "DATA LOADED");

    }
}
