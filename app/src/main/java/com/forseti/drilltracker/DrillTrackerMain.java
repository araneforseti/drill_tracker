package com.forseti.drilltracker;

import android.annotation.TargetApi;
import android.app.FragmentManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.forseti.drilltracker.adapter.ExpandableDrillListAdapter;
import com.forseti.drilltracker.data.Category;
import com.forseti.drilltracker.data.Drill;
import com.forseti.drilltracker.menuinfo.ListContextMenuInfo;
import com.forseti.drilltracker.utils.DataUtils;
import com.forseti.drilltracker.views.CreateCategoryFragment;
import com.forseti.drilltracker.views.CreateDrillFragment;
import com.forseti.drilltracker.views.DetailedDrillFragment;

import java.util.ArrayList;
import java.util.List;

public class DrillTrackerMain extends AppCompatActivity implements CreateDrillFragment.CreateDrillListener, CreateCategoryFragment.CreateCategoryDialogListener {
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

        registerForContextMenu(listView);

        listView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                ExpandableListView.ExpandableListContextMenuInfo info = (ExpandableListView.ExpandableListContextMenuInfo) menuInfo;
                ListContextMenuInfo.createMenu(getApplicationContext(), menu, info, listAdapter);
            }
        });

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
                Drill drill = (Drill) listAdapter.getChild(groupPosition, childPosition);
                showDetailedDrill(drill);
                return false;
            }
        });

        DataUtils.loadData(getApplicationContext(), listAdapter);
    }

    private void showDetailedDrill(Drill drill) {
        DetailedDrillFragment drillFragment = new DetailedDrillFragment();
        drillFragment.setDrill(drill);
        FragmentManager manager = getFragmentManager();
        drillFragment.show(manager, "DetailedDrillFragment");
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
        listAdapter.addDrill(getApplicationContext(), categoryPosition, newDrill);
    }

    @Override
    public void onDialogPositiveClick(String userInput) {
        Category newCategory = new Category(userInput);
        listAdapter.addCategory(getApplicationContext(), newCategory);
    }
}
