package com.nibbledebt.nibble.fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.*;
import android.widget.*;
import com.nibbledebt.nibble.common.AbstractFragment;

import com.nibbledebt.nibble.R;
import com.nibbledebt.nibble.common.RestTemplateCreator;
import com.nibbledebt.nibble.integration.model.Account;
import com.nibbledebt.nibble.integration.model.Contributor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * Created by ralam on 7/14/15.
 */
public class RoundupAccountsFragment extends AbstractFragment {

    private View rootView;
    private RoundupAccountsLoadTask roundupAcctsLoadTask;
    private RoundupAccountsUpdateTask roundupAcctsUpdateTask;
    private Dialog addCardDialog;
    private SwipeRefreshLayout swipeContainer;
    private Map<Long, Boolean> roundingAccountMap = new HashMap<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_roundup_accounts, container, false);
        getActivity().setTitle("");

        progressBar = (ProgressBar)rootView.findViewById(R.id.roundup_accts_progress);
        progressContainer = rootView.findViewById(R.id.roundup_accts_progress_container);


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
                roundupAcctsLoadTask = new RoundupAccountsLoadTask();
                roundupAcctsLoadTask.execute();
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);


        roundupAcctsLoadTask = new RoundupAccountsLoadTask();
        roundupAcctsLoadTask.execute();

        return rootView;
    }

    private class RoundupAccountsLoadTask extends AsyncTask<String, Void,  Account[]> {
        private LinearLayout roundupAcctsLayout;

        @Override
        protected Account[] doInBackground(String... data) {

            try {
                return doLoadRoundupAccounts();
//                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        @Override
        protected void onPreExecute() {
            roundupAcctsLayout = ((LinearLayout)rootView.findViewById(R.id.roundup_accts_container));
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute( Account[] accounts) {
            LayoutInflater li =  (LayoutInflater)rootView.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            roundupAcctsLayout.removeAllViews();
            roundingAccountMap.clear();

            if(accounts ==null || accounts.length == 0){
                TextView tv = new TextView(rootView.getContext());
                tv.setText("You do not have any roundup accounts! Add one to get started.");
                tv.setGravity(Gravity.CENTER_HORIZONTAL);
                roundupAcctsLayout.addView(tv);
            }else{
                for(final Account acct : accounts) {
                    View itemview = li.inflate(R.layout.roundup_acct_item, null);
                    roundingAccountMap.put(acct.getAccountId(), acct.getUseForRounding());
                    TextView instName = (TextView) itemview.findViewById(R.id.inst_name);
                    TextView acctName = (TextView) itemview.findViewById(R.id.acct_name);
                    CheckBox uesForRoundingCB = (CheckBox) itemview.findViewById(R.id.use_for_rounding);

                    uesForRoundingCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            roundingAccountMap.put(acct.getAccountId(), isChecked);
                            List<Long> accountIds = new ArrayList<>();
                            for(Long accountId : roundingAccountMap.keySet()){
                                if(roundingAccountMap.get(accountId)){
                                    accountIds.add(accountId);
                                }
                            }
                            Long[] accountIdsArray = new Long[accountIds.size()];
                            accountIds.toArray(accountIdsArray);
                            roundupAcctsUpdateTask = new RoundupAccountsUpdateTask();
                            roundupAcctsUpdateTask.execute(accountIdsArray);
                        }
                    });

                    instName.setText(acct.getInstitutionName());
                    acctName.setText(acct.getAccountName());
                    uesForRoundingCB.setChecked(acct.getUseForRounding());
                    roundupAcctsLayout.addView(itemview);


                }
            }
            roundupAcctsLoadTask = null;
            swipeContainer.setRefreshing(false);
            hideProgress();
        }

        @Override
        protected void onCancelled() {
            roundupAcctsLoadTask = null;
            hideProgress();
            swipeContainer.setRefreshing(false);
        }

        private Account[] doLoadRoundupAccounts() throws Exception {
            RestTemplate restTemplate = RestTemplateCreator.getTemplateCreator().getNewTemplate();

            return restTemplate.getForObject(
                    getString(R.string.roundupacctsurl),
                    Account[].class);

        }


    }

    private class RoundupAccountsUpdateTask extends AsyncTask<Long[], Void,  Boolean> {

        @Override
        protected Boolean doInBackground(Long[]... accountIds) {

            try {
                doUpdateRoundupAccounts(accountIds[0]);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute( Boolean result) {
            roundupAcctsUpdateTask = null;
            hideProgress();
        }

        @Override
        protected void onCancelled() {
            roundupAcctsUpdateTask = null;
            hideProgress();
        }

        private void doUpdateRoundupAccounts(Long[]  accountIds) throws Exception {
            RestTemplate restTemplate = RestTemplateCreator.getTemplateCreator().getNewTemplate();
            restTemplate.postForEntity(getString(R.string.roundupacctsurl), accountIds, Void.class);
        }


    }



}