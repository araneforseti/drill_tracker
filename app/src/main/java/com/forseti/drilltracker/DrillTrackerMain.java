package com.forseti.drilltracker;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;

import com.forseti.drilltracker.adapter.ExpandableDrillListAdapter;
import com.forseti.drilltracker.temporary.TemporaryDataUtils;
import com.forseti.drilltracker.views.SingleInputDialogFragment;

import java.util.List;

public class DrillTrackerMain extends AppCompatActivity implements SingleInputDialogFragment.SingleInputDialogListener {
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

        ImageView fab = (ImageView) findViewById(R.id.fab);
        fab.setImageDrawable(getDrawable(android.R.drawable.btn_plus));

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

    public void addDance(View view) {
        Toast.makeText(getApplicationContext(), "Add button clicked", Toast.LENGTH_SHORT).show();
        categoryList.add(new Category("Category"));
        listAdapter.notifyDataSetChanged();
    }

    public void showDanceDialog() {
//        NewDanceDialogFragment dialogFragment = new NewDanceDialogFragment();
//        FragmentManager fragmentManager = (FragmentManager) getSupportFragmentManager();
//        dialogFragment.show(fragmentManager, "DanceDialogFragment");
    }

    @Override
    public void onDialogPositiveClick(String userInput) {

    }
}
