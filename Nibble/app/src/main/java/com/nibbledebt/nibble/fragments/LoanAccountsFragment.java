package com.nibbledebt.nibble.fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.*;
import android.widget.*;
import com.nibbledebt.nibble.R;
import com.nibbledebt.nibble.common.AbstractFragment;
import com.nibbledebt.nibble.common.RestTemplateCreator;
import com.nibbledebt.nibble.integration.model.Account;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * Created by ralam on 7/14/15.
 */
public class LoanAccountsFragment extends AbstractFragment {
    private View rootView;
    private LoanAccountsLoadTask loanAcctsLoadTask;
    private LoanAccountsUpdateTask loanAcctsUpdateTask;
    private Dialog addCardDialog;
    private SwipeRefreshLayout swipeContainer;
    private Map<Long, Boolean> loanAccountMap = new HashMap<>();


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
//                // custom dialog
//                Button dialogButton = (Button) addCardDialog.findViewById(R.id.add_card_button);
//                // if button is clicked, close the custom dialog
//                dialogButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        showProgress();
//                        addCardDialog.dismiss();
//                    }
//                });
//
//                addCardDialog.show();
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
                loanAcctsLoadTask = new LoanAccountsLoadTask();
                loanAcctsLoadTask.execute();
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);


        loanAcctsLoadTask = new LoanAccountsLoadTask();
        loanAcctsLoadTask.execute();

        return rootView;
    }

    private class LoanAccountsLoadTask extends AsyncTask<String, Void,  Account[]> {
        private LinearLayout loanAcctsLayout;

        @Override
        protected Account[] doInBackground(String... data) {

            try {
                return doLoadLoanAccounts();
//                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        @Override
        protected void onPreExecute() {
            loanAcctsLayout = ((LinearLayout)rootView.findViewById(R.id.loan_accts_container));
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute( Account[] accounts) {
            LayoutInflater li =  (LayoutInflater)rootView.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            loanAcctsLayout.removeAllViews();
            loanAccountMap.clear();

            if(accounts ==null || accounts.length == 0){
                TextView tv = new TextView(rootView.getContext());
                tv.setText("You do not have any loan accounts! Add one to start paying it off.");
                tv.setGravity(Gravity.CENTER_HORIZONTAL);
                loanAcctsLayout.addView(tv);
            }else{
                for(final Account acct : accounts) {
                    View itemview = li.inflate(R.layout.loan_acct_item, null);
                    loanAccountMap.put(acct.getAccountId(), acct.getUseForPayoff());
                    TextView instName = (TextView) itemview.findViewById(R.id.inst_name);
                    TextView acctName = (TextView) itemview.findViewById(R.id.acct_name);
                    TextView acctInterest = (TextView) itemview.findViewById(R.id.acct_interest);
                    CheckBox uesForPayoffCB = (CheckBox) itemview.findViewById(R.id.use_for_payoff);

                    uesForPayoffCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            loanAccountMap.put(acct.getAccountId(), isChecked);
                            if(isChecked){
                                loanAcctsUpdateTask = new LoanAccountsUpdateTask();
                                loanAcctsUpdateTask.execute(acct.getAccountId());
                            }else{
                                buttonView.setChecked(true);
                            }
                        }
                    });

                    instName.setText(acct.getInstitutionName());
                    acctName.setText(acct.getAccountName());
                    uesForPayoffCB.setChecked(acct.getUseForPayoff());
                    if(acct.getDetail()!=null) acctInterest.setText(acct.getDetail().getInterestRate()!=null ? acct.getDetail().getInterestRate() : "n/a");
                    else acctInterest.setText("n/a");
                    loanAcctsLayout.addView(itemview);

                }
            }
            loanAcctsLoadTask = null;
            swipeContainer.setRefreshing(false);
            hideProgress();
        }

        @Override
        protected void onCancelled() {
            loanAcctsLoadTask = null;
            hideProgress();
            swipeContainer.setRefreshing(false);
        }

        private Account[] doLoadLoanAccounts() throws Exception {
            RestTemplate restTemplate = RestTemplateCreator.getTemplateCreator().getNewTemplate();

            return restTemplate.getForObject(
                    getString(R.string.loanacctsurl),
                    Account[].class);

        }


    }

    private class LoanAccountsUpdateTask extends AsyncTask<Long, Void,  Boolean> {

        @Override
        protected Boolean doInBackground(Long... accountId) {

            try {
                doUpdateLoanAccounts(accountId.length>0 ? accountId[0] : null);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute( Boolean result) {
            loanAcctsUpdateTask = null;
            hideProgress();
        }

        @Override
        protected void onCancelled() {
            loanAcctsUpdateTask = null;
            hideProgress();
        }

        private void doUpdateLoanAccounts(Long  accountId) throws Exception {
            RestTemplate restTemplate = RestTemplateCreator.getTemplateCreator().getNewTemplate();
            restTemplate.postForEntity(getString(R.string.loanacctsurl), accountId, Void.class);
        }
    }


}