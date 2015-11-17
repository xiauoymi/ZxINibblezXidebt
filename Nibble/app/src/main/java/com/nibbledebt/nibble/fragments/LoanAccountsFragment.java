package com.nibbledebt.nibble.fragments;

import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import com.nibbledebt.nibble.R;
import com.nibbledebt.nibble.common.AbstractFragment;

import java.util.Locale;

/**
 * Created by ralam on 7/14/15.
 */
public class LoanAccountsFragment extends AbstractFragment {
    private View rootView;
    private AccountsTask acctsTask;
    private Dialog addCardDialog;
    private SwipeRefreshLayout swipeContainer;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_loan_accounts, container, false);
        getActivity().setTitle("");

        progressBar = (ProgressBar)rootView.findViewById(R.id.loan_accts_progress);
        progressContainer = rootView.findViewById(R.id.loan_accts_progress_container);


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


//        showProgress();

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