package com.forseti.drilltracker.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.forseti.drilltracker.Drill;
import com.forseti.drilltracker.R;
import com.forseti.drilltracker.adapter.ExpandableDrillListAdapter;

public class CreateDrillFragment extends DialogFragment {
    private ExpandableDrillListAdapter listAdapter;

    public void setListAdapter(ExpandableDrillListAdapter listAdapter) {
        this.listAdapter = listAdapter;
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
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        int checkedItem = 1;

        builder.setView(inflater.inflate(R.layout.dialog_create_drill, null))
                //.setSingleChoiceItems(new CategoryListAdapter(listAdapter.getCategories()), checkedItem, new DialogInterface.OnClickListener() {
                  //  @Override
                    //public void onClick(DialogInterface dialog, int which) {

                    //}
                //})
                .setPositiveButton(R.string.create, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Drill drill = new Drill("name", "description");
                        int categoryPosition = 1;
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
}
