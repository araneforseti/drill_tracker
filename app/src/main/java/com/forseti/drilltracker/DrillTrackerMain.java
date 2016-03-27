package com.forseti.drilltracker;

import android.annotation.TargetApi;
import android.app.FragmentManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.forseti.drilltracker.adapter.ExpandableDrillListAdapter;
import com.forseti.drilltracker.temporary.TemporaryDataUtils;
import com.forseti.drilltracker.views.CreateCategoryFragment;
import com.forseti.drilltracker.views.CreateDrillFragment;

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
        categoryList = TemporaryDataUtils.createDanceList();

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
    }

    public void addCategory(View view) {
        CreateCategoryFragment createCategoryFragment = new CreateCategoryFragment();
        FragmentManager manager = getFragmentManager();
        createCategoryFragment.show(manager, "CreateCategoryFragment");
    }

    public void addDrill(View view) {
        CreateDrillFragment drillFragment = new CreateDrillFragment();
        drillFragment.setListAdapter(listAdapter);
        FragmentManager manager = getFragmentManager();
        drillFragment.show(manager, "CreateDrillFragment");
    }

    @Override
    public void onDialogPositiveClick(int categoryPosition, Drill newDrill) {
        Category category = (Category) listAdapter.getGroup(categoryPosition);
        category.getDrills().add(newDrill);
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDialogPositiveClick(String userInput) {
        Category newCategory = new Category(userInput);
        listAdapter.addCategory(newCategory);
    }
}
