package com.nibbledebt.nibble.fragments;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.nibbledebt.nibble.R;
import com.nibbledebt.nibble.common.AbstractFragment;

import java.util.Locale;

/**
 * Created by ralam on 7/14/15.
 */
public class CrowdFragment extends AbstractFragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    public static final String SECTION_NUMBER = "2";
    public static final String IMAGE_RESOURCE_NAME = "ic_action_list_2";
    public static final String IMAGE_RESOURCE_NAME_CHECKED = "ic_action_list_2_white";

    private View rootView;
    private SwipeRefreshLayout swipeContainer;

    public CrowdFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_crowd, container, false);
        String title = getResources().getString(R.string.crowd_section);

        int imageId = getResources().getIdentifier(title.toLowerCase(Locale.getDefault()),
                "drawable", getActivity().getPackageName());
        getActivity().setTitle("");


        progressBar = (ProgressBar)rootView.findViewById(R.id.main_trxs_progress);
        progressContainer = rootView.findViewById(R.id.main_trxs_progress_container);

//        mainContainer = rootView.findViewById(R.id.main_wallet_content_container);
        showProgress();
        swipeContainer = (SwipeRefreshLayout) rootView.findViewById(R.id.trxs_swipe_container);
        // Setup refresh listener which triggers new data loading
//        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                trxTask = new TrxTask();
//                trxTask.execute();
//            }
//        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

//        trxTask = new TrxTask();
//        trxTask.execute();

        return rootView;
    }


}
