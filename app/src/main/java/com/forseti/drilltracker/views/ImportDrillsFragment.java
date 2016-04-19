package com.forseti.drilltracker.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.forseti.drilltracker.R;
import com.nbsp.materialfilepicker.MaterialFilePicker;

import java.util.regex.Pattern;

public class ImportDrillsFragment extends DialogFragment {


    public interface ImportDrillsDialogListener {
        void importDrills(String importFileName);
    }

    ImportDrillsDialogListener listener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (ImportDrillsDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement ImportDrillsDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        View importView = inflater.inflate(R.layout.import_drill, null);
        final EditText importFile = (EditText) importView.findViewById(R.id.import_file_name);

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setView(importView)
                .setPositiveButton(R.string.import_positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String filename = importFile.getText().toString();
                        listener.importDrills(filename);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        return builder.create();
    }
}
