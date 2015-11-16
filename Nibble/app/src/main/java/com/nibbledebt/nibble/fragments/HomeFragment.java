package com.nibbledebt.nibble.fragments;

/**
 * Created by ralam on 7/14/15.
 */

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import android.widget.TextView;
import at.grabner.circleprogress.CircleProgressView;
import at.grabner.circleprogress.TextMode;
import com.nibbledebt.nibble.MainActivity;
import com.nibbledebt.nibble.R;
import com.nibbledebt.nibble.common.AbstractFragment;
import com.nibbledebt.nibble.common.RestTemplateCreator;
import com.nibbledebt.nibble.integration.model.CustomerData;
import com.nibbledebt.nibble.integration.model.MemberDetails;
import com.nibbledebt.nibble.integration.model.Transaction;
import com.nibbledebt.nibble.integration.model.TransactionSummary;
import com.nibbledebt.nibble.security.RegisterObject;
import com.nibbledebt.nibble.security.SecurityContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeFragment extends AbstractFragment implements CircleProgressView.OnProgressChangedListener{

    private View rootView;
    private SwipeRefreshLayout swipeContainer;
    private CircleProgressView mCircleView;
    private SummaryLoadTask summaryLoadTask;
    private float currentValue;

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

        summaryLoadTask = new SummaryLoadTask();
        summaryLoadTask.execute();


        return rootView;
    }

    private void startCountAnimation(final TextView textView, float value) {
        ValueAnimator animator = new ValueAnimator();
        animator.setFloatValues(0.00f, value);
        animator.setDuration(1500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                textView.setText("$ "+ String.format("%.2f", animation.getAnimatedValue()));
            }
        });
        animator.start();
    }


    private void setupVisualization(){
        mCircleView = (CircleProgressView) rootView.findViewById(R.id.circleView);
        mCircleView.setOnProgressChangedListener(this);

        //value setting
        mCircleView.setMaxValue(100);
        mCircleView.setValue(0);


        //show unit
        mCircleView.setUnit("%");
        mCircleView.setShowUnit(true);

        //text sizes
        mCircleView.setTextSize(50); // text size set, auto text size off
        mCircleView.setUnitSize(40); // if i set the text size i also have to set the unit size
        mCircleView.setAutoTextSize(true); // enable auto text size, previous values are overwritten
        mCircleView.setUnitScale(0.9f);
        mCircleView.setTextScale(0.9f);
        mCircleView.setBarColor(getResources().getColor(R.color.nibble_main_green), getResources().getColor(R.color.nibble_main_green2));

    }

    @Override
    public void onStart() {
        super.onStart();
        mCircleView.setValueAnimated(currentValue);
    }

    @Override
    public void onResume() {
        super.onResume();
        mCircleView.setValueAnimated(currentValue);
    }

    @Override
    public void onPause() {
        super.onPause();
        mCircleView.setValue(0);
    }

    @Override
    public void onProgressChanged(float value) {
    }

    private class SummaryLoadTask extends AsyncTask<String, Void, TransactionSummary> {
        private LinearLayout trxLayout;

        @Override
        protected TransactionSummary doInBackground(String... data) {
            try {
                return doLoadSummary();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }
        @Override
        protected void onPreExecute() {
            trxLayout = ((LinearLayout)rootView.findViewById(R.id.trxs_layout));
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(TransactionSummary summary) {
            LayoutInflater li =  (LayoutInflater)rootView.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if(summary != null){
                startCountAnimation((TextView)rootView.findViewById(R.id.fragment_accumulated_amount), summary.getCurrentWeekAmount().floatValue());
                startCountAnimation((TextView)rootView.findViewById(R.id.fragment_target_amount), summary.getWeeklyTarget().floatValue());
                BigDecimal totalContr = summary.getTotalAmountPaid();
                if(summary.getContributorSummaries()!=null && !summary.getContributorSummaries().isEmpty()){
                    for(TransactionSummary contrSummary : summary.getContributorSummaries()){
                        totalContr = totalContr.add(contrSummary.getTotalAmountPaid());
                    }
                }
                if(summary.getTrxs()!=null && !summary.getTrxs().isEmpty()){
                    ((TextView)rootView.findViewById(R.id.home_subheader)).setText("Transactions & Roundups");
                    for(Transaction trx : summary.getTrxs()){
                        View trxView = li.inflate(R.layout.trx_item, null);
                        ((TextView)trxView.findViewById(R.id.trx_amount)).setText("$ "+new DecimalFormat("0.00").format(trx.getTrxAmount()));
                        ((TextView)trxView.findViewById(R.id.trx_name)).setText(trx.getDescription());
                        ((TextView)trxView.findViewById(R.id.trx_date)).setText(trx.getTrxDate());
                        ((TextView)trxView.findViewById(R.id.roundup_amount)).setText("$ "+new DecimalFormat("0.00").format(trx.getRoundupAmount()));
                        ((TextView)trxView.findViewById(R.id.trx_id)).setText(trx.getTrxId());
                        trxLayout.addView(trxView);
                    }
                }
                startCountAnimation((TextView)rootView.findViewById(R.id.fragment_saved_amount), 0.00f);
                startCountAnimation((TextView)rootView.findViewById(R.id.fragment_contributed_amount), totalContr.floatValue());
                currentValue = summary.getCurrentTargetPercent();
                mCircleView.setValueAnimated(currentValue);
            }
            summaryLoadTask = null;
            hideProgress();
        }

        @Override
        protected void onCancelled() {
            summaryLoadTask = null;
            hideProgress();
        }

        private TransactionSummary doLoadSummary() throws Exception {
            RestTemplate restTemplate = RestTemplateCreator.getTemplateCreator().getNewTemplate();

            return restTemplate.getForObject(
                    getString(R.string.summaryurl),
                    TransactionSummary.class);

        }
    }

}
