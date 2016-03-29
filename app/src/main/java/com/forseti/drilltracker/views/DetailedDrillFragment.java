package com.forseti.drilltracker.views;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.forseti.drilltracker.R;
import com.forseti.drilltracker.data.Drill;

public class DetailedDrillFragment extends DialogFragment {
    Drill drill;

    public void setDrill(Drill drill) {
        this.drill = drill;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstance) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final LayoutInflater inflater = getActivity().getLayoutInflater();

        View detailedDrillFragment = inflater.inflate(R.layout.detailed_drill, null);

        final TextView drillName = (TextView) detailedDrillFragment.findViewById(R.id.drill_name);
        final TextView drillSummary = (TextView) detailedDrillFragment.findViewById(R.id.drill_summary);
        final TextView drillInstructions = (TextView) detailedDrillFragment.findViewById(R.id.detailed_drill_instructions);
        final TextView drillVideo = (TextView) detailedDrillFragment.findViewById(R.id.drill_video_url);

        drillName.setText(drill.getName());
        drillSummary.setText(drill.getDescription());
        drillInstructions.setText(drill.getInstructions());

        if (drill.getVideoURL() != null && !drill.getVideoURL().isEmpty()) {
            drillVideo.setText(drill.getVideoURL());
            drillVideo.setMovementMethod(LinkMovementMethod.getInstance());
            drillVideo.setVisibility(View.VISIBLE);
            drillVideo.requestFocus();
        } else {
            drillVideo.setMinimumHeight(0);
            drillVideo.setWillNotDraw(true);
        }

        builder.setView(detailedDrillFragment);

        return builder.create();
    }
}
