package com.nibbledebt.nibble.fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.nibbledebt.nibble.common.AbstractFragment;

import com.nibbledebt.nibble.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by ralam on 7/14/15.
 */
public class AccountsFragment extends AbstractFragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    public static final String SECTION_NUMBER = "1";
    public static final String IMAGE_RESOURCE_NAME = "ic_action_creditcard";
    public static final String IMAGE_RESOURCE_NAME_CHECKED = "ic_action_creditcard_white";
    private View rootView;
    private AccountsTask acctsTask;
    private Dialog addCardDialog;
    private SwipeRefreshLayout swipeContainer;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_accounts, container, false);
        String title = getResources().getString(R.string.accts_section);

        int imageId = getResources().getIdentifier(title.toLowerCase(Locale.getDefault()),
                "drawable", getActivity().getPackageName());
        getActivity().setTitle("");

        progressBar = (ProgressBar)rootView.findViewById(R.id.main_accts_progress);
        progressContainer = rootView.findViewById(R.id.main_accts_progress_container);


        addCardDialog = new Dialog(rootView.getContext());
        addCardDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        addCardDialog.setContentView(R.layout.fragment_accounts_dialog_na);


        ((Button)rootView.findViewById(R.id.add_acct_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // custom dialog
                Button dialogButton = (Button) addCardDialog.findViewById(R.id.add_card_button);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showProgress();
                        addCardDialog.dismiss();
                    }
                });

                addCardDialog.show();
            }
        });


        showProgress();

        swipeContainer = (SwipeRefreshLayout) rootView.findViewById(R.id.accts_swipe_container);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                acctsTask = new AccountsTask();
                acctsTask.execute();
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);


        acctsTask = new AccountsTask();
        acctsTask.execute();

        return rootView;
    }

    private class AccountsTask extends AsyncTask<String, Void,  String> {
        private LinearLayout walleAccts;

        @Override
        protected String doInBackground(String... data) {
            return "";
        }
        @Override
        protected void onPreExecute() {
            walleAccts = ((LinearLayout)rootView.findViewById(R.id.wallet_accts_full));
            acctsTask = null;
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute( String result) {

        }

        @Override
        protected void onCancelled() {
            acctsTask = null;
            hideProgress();
            swipeContainer.setRefreshing(false);
        }




    }



}