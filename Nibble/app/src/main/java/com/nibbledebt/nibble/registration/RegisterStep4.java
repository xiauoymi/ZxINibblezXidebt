package com.nibbledebt.nibble.registration;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.nibbledebt.nibble.R;
import com.nibbledebt.nibble.common.AbstractWizardStep;
import com.nibbledebt.nibble.common.RestTemplateCreator;
import com.nibbledebt.nibble.integration.model.Bank;
import com.nibbledebt.nibble.integration.model.CustomerData;
import com.nibbledebt.nibble.integration.model.LoginField;
import com.nibbledebt.nibble.security.BanksObject;
import com.nibbledebt.nibble.security.RegisterObject;
import com.nibbledebt.nibble.security.SecurityContext;
import org.codepond.wizardroid.WizardStep;
import org.codepond.wizardroid.persistence.ContextVariable;
import org.springframework.http.*;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * Created by ralam on 10/4/15.
 */
public class RegisterStep4 extends AbstractWizardStep{

    private RegistrationTask raTask;
    // step 1
    @ContextVariable
    private String email;
    @ContextVariable
    private String password;
    @ContextVariable
    private String firstname;
    @ContextVariable
    private String lastname;

    // step 2
    @ContextVariable
    private String address1;
    @ContextVariable
    private String address2;
    @ContextVariable
    private String city;
    @ContextVariable
    private String state;
    @ContextVariable
    private int stateIdx;
    @ContextVariable
    private String zip;
    @ContextVariable
    private String phone;

    // step 3
    @ContextVariable
    private Bank bank;
    @ContextVariable
    private HashMap<String, String> bankCreds = new HashMap<>();

    private View step4View;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the step layout view
        step4View = inflater.inflate(R.layout.register_wizard_step4_layout, container, false);

        // set the previous steps as complete
        setStepComplete((ImageView) step4View.findViewById(R.id.animated_dot_1));
        setStepComplete((ImageView) step4View.findViewById(R.id.animated_dot_2));
        setStepComplete((ImageView) step4View.findViewById(R.id.animated_dot_3));

        // start the current step animation
        setCurrentStepAnimation(step4View.getContext(), R.anim.dot_pulse, (ImageView) step4View.findViewById(R.id.animated_dot_3), new View[]{step4View.findViewById(R.id.subtext_step_1), step4View.findViewById(R.id.subtext_step_2), step4View.findViewById(R.id.subtext_step_3)});

        // load supported accounts
        for(LoginField field : bank.getLoginForm().getLoginField()){
            field.setValue(bankCreds.get(field.getName()));
        }
        CustomerData customerData = new CustomerData();
        customerData.setEmail(email);
        customerData.setUsername(email);
        customerData.setPassword(password);
        customerData.setFirstName(firstname);
        customerData.setLastName(lastname);
        customerData.setAddress1(address1);
        customerData.setAddress2(address2);
        customerData.setCity(city);
        customerData.setState(state);
        customerData.setZip(Integer.valueOf(zip));
        customerData.setBank(bank);

        raTask = new RegistrationTask();
        raTask.execute(new CustomerData[]{customerData});

        // return the view
        return step4View;
    }

    @Override
    public void onAttach (Activity activity){
        super.onAttach(activity);
    }

    private class RegistrationTask extends AsyncTask<CustomerData, Void, Integer> {
        @Override
        protected Integer doInBackground(CustomerData... data) {
            try {
                return doRegister(data[0]);
            } catch (Exception e) {
                e.printStackTrace();
                return 500;
            }

        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(Integer statusCode) {
            if(statusCode >= 200 && statusCode < 300){
                ((TextView)(step4View.findViewById(R.id.register_step4_header))).setText("Awesome! Welcome to Nibble");
                ((TextView)(step4View.findViewById(R.id.register_step4_message))).setText("Please check your email on instructions on how to activate your account. You can click on the link to activate.");
            }else if(statusCode >= 500 && statusCode < 600){
                ((TextView)(step4View.findViewById(R.id.register_step4_header))).setText("Oops Oo");
                ((TextView)(step4View.findViewById(R.id.register_step4_message))).setText("System pooped out trying to register you. Try changing your name (..no, really!)");
            }
        }

        @Override
        protected void onCancelled() {
            raTask = null;
//            hideProgress();
        }

        private Integer doRegister(CustomerData data) throws Exception {
            // Create a new RestTemplate instance
            RestTemplate restTemplate = RestTemplateCreator.getTemplateCreator().getNewTemplate();

            // Make the HTTP GET request, marshaling the response to a String
            try {
                ResponseEntity<Void> response = restTemplate.postForEntity(getString(R.string.regurl), data, Void.class);
                SecurityContext.getCurrentContext().getSessionMap().put("customerData", new RegisterObject(data));
                return response.getStatusCode().value();
            }catch(HttpServerErrorException e){
                return e.getStatusCode().value();
            }
        }
    }

   protected Boolean hasAllRequiredFields(){
        return true;
    }
}