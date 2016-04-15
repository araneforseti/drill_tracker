package com.forseti.drilltracker.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.forseti.drilltracker.R;
import com.forseti.drilltracker.adapter.ExpandableDrillListAdapter;
import com.forseti.drilltracker.data.Drill;

public class EditDrillFragment extends DialogFragment {
    private ExpandableDrillListAdapter listAdapter;
    private Drill drill;
    private int categoryPosition;

    public void setListAdapter(ExpandableDrillListAdapter listAdapter) {
        this.listAdapter = listAdapter;
    }

    public void setDrill(Drill drill) {
        this.drill = drill;
    }

    public void setCategoryPosition(int categoryPosition) {
        this.categoryPosition = categoryPosition;
    }

    public interface EditDrillListener {
        void onDialogPositiveClick(Drill drill, String name, String summary, String description, String url);
    }

    EditDrillListener listener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (EditDrillListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement EditDrillListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        View createDrillView = inflater.inflate(R.layout.dialog_create_drill, null);

        final EditText drillName = (EditText) createDrillView.findViewById(R.id.create_drill_name);
        final EditText drillDescription = (EditText) createDrillView.findViewById(R.id.create_drill_description);
        final EditText drillInstruction = (EditText) createDrillView.findViewById(R.id.create_drill_instructions);
        final EditText drillURL = (EditText) createDrillView.findViewById(R.id.create_drill_video_url);
        final Spinner categoryRadio = (Spinner) createDrillView.findViewById(R.id.categories_spinner);

        ArrayAdapter categoryAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, listAdapter.getCategories());
        categoryAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        categoryRadio.setAdapter(categoryAdapter);

        drillName.setText(drill.getName());
        drillDescription.setText(drill.getDescription());
        drillInstruction.setText(drill.getInstructions());
        drillURL.setText(drill.getVideoURL());
        categoryRadio.setSelection(categoryPosition);
        categoryRadio.setEnabled(false);

        builder.setView(createDrillView)
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = drillName.getText().toString();
                        String description = drillDescription.getText().toString();
                        String instructions = drillInstruction.getText().toString();
                        String url = drillURL.getText().toString();

                        listener.onDialogPositiveClick(drill, name, description, instructions, url);
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
}
