package com.nibbledebt.nibble.fragments;

/**
 * Created by ralam on 7/14/15.
 */

import android.app.Dialog;
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

import com.nibbledebt.nibble.R;
import com.nibbledebt.nibble.common.AbstractFragment;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeFragment extends AbstractFragment {
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

        addMoneyDialog = new Dialog(rootView.getContext());
        addMoneyDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        addMoneyDialog.setContentView(R.layout.fragment_wallet_dialog_load);
        final EditText amountField = ((EditText)addMoneyDialog.findViewById(R.id.load_amount));
        amountField.addTextChangedListener(new TextWatcher() {
            String current = "";

            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {
                if (!s.toString().equals(current)) {
                    amountField.removeTextChangedListener(this);

                    String cleanString = s.toString().replaceAll("[$,.]", "");

                    double parsed = Double.parseDouble(cleanString);
                    String formated = NumberFormat.getCurrencyInstance().format((parsed / 100));

                    current = formated;
                    amountField.setText(formated);
                    amountField.setSelection(formated.length());

                    amountField.addTextChangedListener(this);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });



        return rootView;
    }




}
