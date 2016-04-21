package com.forseti.drilltracker.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.forseti.drilltracker.data.Drill;
import com.forseti.drilltracker.R;
import com.forseti.drilltracker.adapter.ExpandableDrillListAdapter;

import java.util.ArrayList;
import java.util.List;

public class CreateDrillFragment extends DialogFragment {
    public static final String CATEGORIES_KEY = "Categories";
    List<String> categories;

    public void setList(List<String> categories) {
        this.categories = categories;
    }

    public interface CreateDrillListener {
        void onDialogPositiveClick(int categoryPosition, Drill newDrill);
    }

    CreateDrillListener listener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (CreateDrillListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement CreateDrillListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            categories = savedInstanceState.getStringArrayList(CATEGORIES_KEY);
        }

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        View createDrillView = inflater.inflate(R.layout.dialog_create_drill, null);

        final EditText drillName = (EditText) createDrillView.findViewById(R.id.create_drill_name);
        final EditText drillDescription = (EditText) createDrillView.findViewById(R.id.create_drill_description);
        final EditText drillInstruction = (EditText) createDrillView.findViewById(R.id.create_drill_instructions);
        final EditText drillURL = (EditText) createDrillView.findViewById(R.id.create_drill_video_url);

        final Spinner categoryRadio = (Spinner) createDrillView.findViewById(R.id.categories_spinner);
        ArrayAdapter categoryAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, categories);
        categoryAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        categoryRadio.setAdapter(categoryAdapter);

        builder.setView(createDrillView)
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Drill drill = new Drill(drillName.getText().toString(), drillDescription.getText().toString());
                        drill.setInstructions(drillInstruction.getText().toString());
                        drill.setVideoURL(drillURL.getText().toString());
                        int categoryPosition = categoryRadio.getSelectedItemPosition();
                        listener.onDialogPositiveClick(categoryPosition, drill);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }
        );

        return builder.create();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putStringArrayList(CATEGORIES_KEY, (ArrayList<String>) categories);

        super.onSaveInstanceState(savedInstanceState);
    }


}
