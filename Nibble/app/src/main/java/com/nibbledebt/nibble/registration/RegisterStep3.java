package com.nibbledebt.nibble.registration;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.nibbledebt.nibble.R;
import com.nibbledebt.nibble.common.AbstractWizardStep;
import com.nibbledebt.nibble.common.RestTemplateCreator;
import com.nibbledebt.nibble.integration.model.Bank;
import com.nibbledebt.nibble.integration.model.InstitutionDetail;
import com.nibbledebt.nibble.security.BanksObject;
import com.nibbledebt.nibble.security.SecurityContext;
import com.nibbledebt.nibble.security.SessionObject;

import org.codepond.wizardroid.persistence.ContextVariable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ralam on 10/4/15.
 */
public class RegisterStep3 extends AbstractWizardStep {
    private SupportedBanksTask saTask;

    @ContextVariable
    private String companyid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the step layout view
        View v = inflater.inflate(R.layout.register_wizard_step3_layout, container, false);

        // set the previous steps as complete
        setStepComplete((ImageView) v.findViewById(R.id.animated_dot_1));
        setStepComplete((ImageView) v.findViewById(R.id.animated_dot_2));

        // start the current step animation
        setCurrentStepAnimation(v.getContext(), R.anim.dot_pulse, (ImageView) v.findViewById(R.id.animated_dot_3));

        // load supported accounts
        saTask = new SupportedBanksTask();
        saTask.setView(v);
        saTask.execute();

        // return the view
        return v;
    }

    private class SupportedBanksTask extends AsyncTask<String, Void, Boolean> {
        private View view;

        public void setView(View view){
            this.view = view;
        }

        @Override
        protected Boolean doInBackground(String... data) {
            try {
                doLoad();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }

        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(Boolean loginSuccessful) {
            List<Bank> banks = ((BanksObject)SecurityContext.getCurrentContext().getSessionMap().get("banks")).getData("banks");
            for(int i=0; i<banks.size(); i++){
                final ImageView imageView = (ImageView)view.findViewById(getGridImageViewId(i + 1));
//                imageView.setImageBitmap(banks.get(i).getInstitution().getLogo());

                Drawable drawable = new BitmapDrawable(getResources(), banks.get(i).getInstitution().getLogo());
                imageView.setBackground(drawable);
                imageView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if(v!=null && v instanceof ImageView) {
                            switch (event.getAction()) {
                                case MotionEvent.ACTION_DOWN:
                                    v.getBackground().setAlpha(100);
                                    v.invalidate();
                                    break;

                                case MotionEvent.ACTION_UP:
                                case MotionEvent.ACTION_CANCEL: {
                                    v.getBackground().setAlpha(255);
                                    v.invalidate();
                                    break;
                                }
                            }
                        }
                        return false;
                    }
                });
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }


//            if(!loginSuccessful){
//                passwordTextView.setError(getString(R.string.incorrect_password));
//                passwordTextView.requestFocus();
//                loginTask = null;
//
//                hideProgress();
//                //textView.setText("No network connection available.");
//            }else{
//                startActivity(new Intent(getBaseContext(), MainActivity.class));
//                loginTask = null;
//                hideProgress();
//                finish();
//            }
        }

        @Override
        protected void onCancelled() {
            saTask = null;
//            hideProgress();
        }

        private void doLoad() throws Exception {
            // The connection URL

            // Create a new RestTemplate instance
            RestTemplate restTemplate = RestTemplateCreator.getTemplateCreator().getNewTemplate();

            // Make the HTTP GET request, marshaling the response to a String
            Bank[] result = restTemplate.getForObject(getString(R.string.banksurl), Bank[].class);

            List<Bank> banks = Arrays.asList(result);
            Iterator<Bank> it = Arrays.asList(result).iterator();

            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.IMAGE_PNG));
            HttpEntity<String> entity = new HttpEntity<String>(headers);


            while(it.hasNext()){
                Bank bank = it.next();
                ResponseEntity<byte[]> response = restTemplate.exchange(getString(R.string.banklogourl) + bank.getInstitution().getId(), HttpMethod.GET, entity, byte[].class, "1");

                if(response.getStatusCode().equals(HttpStatus.OK))
                    bank.getInstitution().setLogo(BitmapFactory.decodeByteArray(response.getBody(), 0, response.getBody().length));

            }
            SecurityContext.getCurrentContext().getSessionMap().put("banks", new BanksObject(banks));
        }



        public int getGridImageViewId(int columnCount){
            switch(columnCount){
                case 1 : return R.id.rform11;
                case 2 : return R.id.rform12;
                case 3 : return R.id.rform13;
                case 4 : return R.id.rform21;
                case 5 : return R.id.rform22;
                case 6 : return R.id.rform23;
                case 7 : return R.id.rform31;
                case 8 : return R.id.rform32;
                case 9 : return R.id.rform33;
                default : return R.id.rform41;
            }
        }
    }

    protected Boolean hasAllRequiredFields(){
        return true;
    }
}