package com.forseti.drilltracker;

import android.annotation.TargetApi;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import com.forseti.drilltracker.views.EditCategoryFragment;
import com.forseti.drilltracker.views.EditDrillFragment;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class DrillTrackerMain extends AppCompatActivity
        implements CreateDrillFragment.CreateDrillListener,
        CreateCategoryFragment.CreateCategoryDialogListener,
        EditCategoryFragment.EditCategoryDialogListener,
        EditDrillFragment.EditDrillListener {

    ExpandableDrillListAdapter listAdapter;
    ExpandableListView listView;
    List<Category> categoryList;
    Toast toast;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        Log.i("Set", "Here");
        listView = (ExpandableListView) findViewById(R.id.category_list);
        categoryList = new ArrayList<>();

        listAdapter = new ExpandableDrillListAdapter(this, categoryList);
        listView.setAdapter(listAdapter);
        Log.i("Set", "Not me");
        registerForContextMenu(listView);

        FloatingActionButton addDrillButton = (FloatingActionButton) findViewById(R.id.add_drill);
        addDrillButton.setSize(FloatingActionButton.SIZE_MINI);
        addDrillButton.setTitle("Add Drill");

        FloatingActionButton addCategoryButton = (FloatingActionButton) findViewById(R.id.add_category);
        addCategoryButton.setSize(FloatingActionButton.SIZE_MINI);

        FloatingActionButton importDrillButton = (FloatingActionButton) findViewById(R.id.import_drills);
        importDrillButton.setSize(FloatingActionButton.SIZE_MINI);

        FloatingActionButton exportDrillButton = (FloatingActionButton) findViewById(R.id.export_drills);
        exportDrillButton.setSize(FloatingActionButton.SIZE_MINI);

        listView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                ExpandableListView.ExpandableListContextMenuInfo info = (ExpandableListView.ExpandableListContextMenuInfo) menuInfo;
                ListContextMenuInfo.createMenu(getApplicationContext(), menu, info, listAdapter, getFragmentManager());
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
            setToast(R.string.no_categories);
        }
        else {
            CreateDrillFragment drillFragment = new CreateDrillFragment();
            drillFragment.setListAdapter(listAdapter);
            FragmentManager manager = getFragmentManager();
            drillFragment.show(manager, "CreateDrillFragment");
        }
    }

    public void importDrills(View view) {
        new MaterialFilePicker()
                .withActivity(this)
                .withRequestCode(1)
                .withFilter(Pattern.compile(".*\\.txt$"))
                .withFilterDirectories(false)
                .withHiddenFiles(false)
                .start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            String filePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            DataUtils.loadFromFile(getApplicationContext(), listAdapter, filePath);
        }
    }

    public void setToast(int toast_message_id) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(getApplicationContext(), toast_message_id, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void deleteAllData() {
        listAdapter.clearData(getApplicationContext());
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

    @Override
    public void onDialogPositiveClick(Category category, String newName) {
        listAdapter.editName(category, newName);
    }

    @Override
    public void onDialogPositiveClick(Drill drill, String name, String summary, String description, String url) {
        listAdapter.editDrill(drill, name, summary, description, url);
    }

    public void exportDrills(View view) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, DataUtils.getData(listAdapter));
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));
    }
}
