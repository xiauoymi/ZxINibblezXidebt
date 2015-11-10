package com.nibbledebt.nibble.fragments;

/**
 * Created by ralam on 7/14/15.
 */

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ProgressBar;

import at.grabner.circleprogress.CircleProgressView;
import at.grabner.circleprogress.TextMode;
import com.nibbledebt.nibble.R;
import com.nibbledebt.nibble.common.AbstractFragment;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeFragment extends AbstractFragment implements CircleProgressView.OnProgressChangedListener{
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    public static final String SECTION_NUMBER = "0";
    public static final String IMAGE_RESOURCE_NAME = "ic_action_home";
    public static final String IMAGE_RESOURCE_NAME_CHECKED = "ic_action_home_white";

    private View rootView;
    private Dialog addMoneyDialog;
    private SwipeRefreshLayout swipeContainer;
    private CircleProgressView mCircleView;
    Boolean mShowUnit = true;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        String title = getResources().getString(R.string.home_section);

        int imageId = getResources().getIdentifier(title.toLowerCase(Locale.getDefault()),
                "drawable", getActivity().getPackageName());
        getActivity().setTitle("");

        progressBar = (ProgressBar)rootView.findViewById(R.id.main_wallet_progress);
        progressContainer = rootView.findViewById(R.id.main_wallet_progress_container);
//        mainContainer = rootView.findViewById(R.id.main_wallet_content_container);

//        showProgress();

        swipeContainer = (SwipeRefreshLayout) rootView.findViewById(R.id.wallet_swipe_container);
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

        setupVisualization();


        return rootView;
    }


    private void setupVisualization(){
        mCircleView = (CircleProgressView) rootView.findViewById(R.id.circleView);
        mCircleView.setOnProgressChangedListener(this);

        //value setting
        mCircleView.setMaxValue(100);
        mCircleView.setValue(0);


        //show unit
        mCircleView.setUnit("%");
        mCircleView.setShowUnit(mShowUnit);

        //text sizes
        mCircleView.setTextSize(50); // text size set, auto text size off
        mCircleView.setUnitSize(40); // if i set the text size i also have to set the unit size
        mCircleView.setAutoTextSize(true); // enable auto text size, previous values are overwritten
        //if you want the calculated text sizes to be bigger/smaller you can do so via
        mCircleView.setUnitScale(0.9f);
        mCircleView.setTextScale(0.9f);

//        //custom typeface
//        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/ANDROID_ROBOT.ttf");
//        mCircleView.setTextTypeface(font);
//        mCircleView.setUnitTextTypeface(font);


        //color
        //you can use a gradient
        mCircleView.setBarColor(getResources().getColor(R.color.nibble_main_green), getResources().getColor(R.color.nibble_main_green2));

        //colors of text and unit can be set via
//        mCircleView.setTextColor(Color.WHITE);
        //or to use the same color as in the gradient
//        mCircleView.setAutoTextColor(true); //previous set values are ignored

        //text mode
//        mCircleView.setText("Target"); //shows the given text in the circle view
//        mCircleView.setTextMode(TextMode.TEXT); // Set text mode to text to show text

        //in the following text modes, the text is ignored
//        mCircleView.setTextMode(TextMode.VALUE); // Shows the current value
//        mCircleView.setTextMode(TextMode.PERCENT); // Shows current percent of the current value from the max value

        //spinning
//        mCircleView.spin(); // start spinning
//        mCircleView.stopSpinning(); // stops spinning. Spinner gets shorter until it disappears.
//        mCircleView.setValueAnimated(24); // stops spinning. Spinner spins until on top. Then fills to set value.
//        mCircleView.setShowTextWhileSpinning(true); // Show/hide text in spinning mode
        //animation callbacks
    }

    @Override
    public void onStart() {
        super.onStart();
        mCircleView.setValue(0);
        mCircleView.setValueAnimated(42);
    }

    @Override
    public void onProgressChanged(float value) {
    }

}
