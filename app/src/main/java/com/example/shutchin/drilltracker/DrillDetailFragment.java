package com.example.shutchin.drilltracker;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shutchin.drilltracker.drill.DrillContent;
import com.example.shutchin.drilltracker.drill.DrillItem;

public class DrillDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";

    private DrillItem mItem;


    public DrillDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.x
            mItem = DrillContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.name);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.drill_detail, container, false);

        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.drill_detail)).setText(mItem.description);
        }

        return rootView;
    }
}
