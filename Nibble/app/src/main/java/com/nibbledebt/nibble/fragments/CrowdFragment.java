package com.nibbledebt.nibble.fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import android.widget.TextView;
import com.nibbledebt.nibble.R;
import com.nibbledebt.nibble.common.AbstractFragment;
import com.nibbledebt.nibble.common.RestTemplateCreator;
import com.nibbledebt.nibble.integration.model.Contributor;
import org.springframework.web.client.RestTemplate;

import java.util.List;
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

    private CrowdLoadTask crowdLoadTask;

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
//        showProgress();
        swipeContainer = (SwipeRefreshLayout) rootView.findViewById(R.id.trxs_swipe_container);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                crowdLoadTask = new CrowdLoadTask();
                crowdLoadTask.execute();
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        crowdLoadTask = new CrowdLoadTask();
        crowdLoadTask.execute();

        return rootView;
    }


    private class CrowdLoadTask extends AsyncTask<String, Void, Contributor[]> {
        private LinearLayout crowdLayout;

        @Override
        protected Contributor[] doInBackground(String... data) {
            try {
                return doLoadContributors();
//                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }
        @Override
        protected void onPreExecute() {
            crowdLayout = ((LinearLayout)rootView.findViewById(R.id.crowd_contributors));
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(Contributor[] contributors) {

            LayoutInflater li =  (LayoutInflater)rootView.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            crowdLayout.removeAllViews();

            if(contributors ==null || contributors.length == 0){
                TextView subHeader = (TextView)rootView.findViewById(R.id.crowd_subheader);
                subHeader.setText("");
                TextView tv = new TextView(rootView.getContext());
                tv.setText("You do not have any contributors signed up!");
                tv.setGravity(Gravity.CENTER_HORIZONTAL);
                crowdLayout.addView(tv);
            }else{
                TextView subHeader = (TextView)rootView.findViewById(R.id.crowd_subheader);
                subHeader.setText("Contributors list");
                for(Contributor contr : contributors) {
                    View itemview = li.inflate(R.layout.crowd_item, null);
                    TextView name = (TextView) itemview.findViewById(R.id.contributor_name);
                    TextView address = (TextView) itemview.findViewById(R.id.contributor_address);
                    TextView weekContribution = (TextView) itemview.findViewById(R.id.amount_contributed_week);
                    TextView totalContribution = (TextView) itemview.findViewById(R.id.amount_contributed_total);

                    name.setText(contr.getFirstName()+" "+contr.getLastName());
                    address.setText(contr.getCity()+", "+ contr.getState());
                    weekContribution.setText("$ "+ String.format("%.2f", contr.getSummary().getCurrentWeekAmount()));
                    totalContribution.setText("$ "+ String.format("%.2f", contr.getSummary().getTotalAmountPaid()));

                    crowdLayout.addView(itemview);

                }
            }
            crowdLoadTask = null;
            hideProgress();
        }

        @Override
        protected void onCancelled() {
            crowdLoadTask = null;
            hideProgress();
        }

        private Contributor[] doLoadContributors() throws Exception {
            RestTemplate restTemplate = RestTemplateCreator.getTemplateCreator().getNewTemplate();

            return restTemplate.getForObject(
                    getString(R.string.contributorsurl),
                    Contributor[].class);

        }
    }

}
