package com.nibbledebt.nibble.registration;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nibbledebt.nibble.MainActivity;
import com.nibbledebt.nibble.R;
import com.nibbledebt.nibble.common.AbstractWizardStep;
import com.nibbledebt.nibble.common.RestTemplateCreator;
import com.nibbledebt.nibble.integration.model.InstitutionDetail;
import com.nibbledebt.nibble.integration.model.Items;
import com.nibbledebt.nibble.security.SecurityContext;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;

/**
 * Created by ralam on 10/4/15.
 */
public class RegisterStep3 extends AbstractWizardStep {
    private SupportedAccountsTask saTask;

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
        saTask = new SupportedAccountsTask();
        saTask.execute();

        // return the view
        return v;
    }

    private class SupportedAccountsTask extends AsyncTask<String, Void, Boolean> {
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
            String banksurl = "http://nibble-web.herokuapp.com/services/rest/banks";
            String banklogourl = "http://nibble-web.herokuapp.com/services/rest/logo";

            // Create a new RestTemplate instance
            RestTemplate restTemplate = RestTemplateCreator.getTemplateCreator().getNewTemplate();

            // Make the HTTP GET request, marshaling the response to a String
            Items result = restTemplate.getForObject(banksurl, Items.class);

            for(InstitutionDetail detail : result.getInstitutionDetailList()){
                detail.getInstitution().setLogo(BitmapFactory.decodeStream(restTemplate.getForObject(banklogourl, InputStream.class)));
            }


        }
    }
}
