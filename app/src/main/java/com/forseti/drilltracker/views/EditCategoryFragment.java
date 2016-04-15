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
import com.forseti.drilltracker.data.Category;

public class EditCategoryFragment extends DialogFragment {
    private Category category;

    public interface EditCategoryDialogListener {
        void onDialogPositiveClick(Category category, String newName);
    }

    EditCategoryDialogListener listener;

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (EditCategoryDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement EditCategoryDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        View categoryView = inflater.inflate(R.layout.dialog_create_category, null);
        final EditText input = (EditText) categoryView.findViewById(R.id.category_name);
        input.setText(category.getName());

        builder.setView(categoryView)
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String userInput = input.getText().toString();
                        listener.onDialogPositiveClick(category, userInput);
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
