package com.nibbledebt.nibble.registration;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.TextView;
import com.nibbledebt.nibble.R;
import com.nibbledebt.nibble.common.RestTemplateCreator;
import com.nibbledebt.nibble.integration.model.Bank;
import com.nibbledebt.nibble.integration.model.CustomerData;
import com.nibbledebt.nibble.integration.model.LoginField;
import com.nibbledebt.nibble.security.RegisterObject;
import com.nibbledebt.nibble.security.SecurityContext;
import org.codepond.wizardroid.WizardFlow;
import org.codepond.wizardroid.persistence.ContextVariable;
import org.springframework.http.*;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * Created by ralam on 10/4/15.
 */
public class RegistrationWizard extends RegistrationWizardLayout{
    private RegistrationTask raTask;

    // step 1
    @ContextVariable
    private String email = "test@yopmail.com";
    @ContextVariable
    private String password = "123456";
    @ContextVariable
    private String firstname = "testfname";
    @ContextVariable
    private String lastname = "testlname";

    // step 2
    @ContextVariable
    private String address1 = "123 test drive";
    @ContextVariable
    private String address2;
    @ContextVariable
    private String city = "testcity";
    @ContextVariable
    private String state = "alabama";
    @ContextVariable
    private int stateIdx = 0;
    @ContextVariable
    private String zip = "34444";
    @ContextVariable
    private String phone;

    // step 3
    @ContextVariable
    private Bank bank;
    @ContextVariable
    private Map<String, String> bankCreds = new HashMap<>();


    //You must override this method and create a wizard flow by
    //using WizardFlow.Builder as shown in this example
    @Override
    public WizardFlow onSetup() {
        /* Optionally, you can set different labels for the control buttons
        setNextButtonLabel("Advance");
        setBackButtonLabel("Return");
        setFinishButtonLabel("Finalize"); */



        return new WizardFlow.Builder()
                .addStep(RegisterStep1.class, true)
                .addStep(RegisterStep2.class, true)
                .addStep(RegisterStep3.class, true)
                .create();
    }

    @Override
    public void onWizardComplete() {
        super.onWizardComplete();   //Make sure to first call the super method before anything else

        if(bank!=null && bank.getLoginForm() != null){
            for(LoginField field : bank.getLoginForm().getLoginField()){
                field.setValue(bankCreds.get(field.getName()));
            }
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
            RegisterSummary registerSummary = new RegisterSummary();
            registerSummary.setTargetFragment(getTargetFragment(), 0);
            Bundle bundle = new Bundle();
            if (statusCode >= 200 && statusCode < 300) {
                registerSummary.setHeader("Awesome! Welcome to Nibble");
                registerSummary.setMessage("Please check your email on instructions on how to activate your account. You can click on the link to activate.");
                bundle.putSerializable("header", "Awesome! Welcome to Nibble");
                bundle.putSerializable("message", "Please check your email on instructions on how to activate your account. You can click on the link to activate.");
//                ((TextView) (getActivity().findViewById(R.id.register_summary_header))).setText("Awesome! Welcome to Nibble");
//                ((TextView) (getActivity().findViewById(R.id.register_summary_message))).setText("Please check your email on instructions on how to activate your account. You can click on the link to activate.");
            } else if (statusCode >= 500 && statusCode < 600) {
                registerSummary.setHeader("Oops Oo");
                registerSummary.setMessage("System pooped out trying to register you. Try changing your name (..no, really!)");
                bundle.putSerializable("header", "Oops Oo");
                bundle.putSerializable("message", "System pooped out trying to register you. Try changing your name (..no, really!)");
//                ((TextView) (getActivity().findViewById(R.id.register_summary_header))).setText("Oops Oo");
//                ((TextView) (getActivity().findViewById(R.id.register_summary_message))).setText("System pooped out trying to register you. Try changing your name (..no, really!)");
            }

            registerSummary.setArguments(bundle);
            registerSummary.show(getActivity().getSupportFragmentManager(), "SummaryDialog");
//            FragmentTransaction ft = getFragmentManager().beginTransaction();
//            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//            ft.attach(registerSummary);
//            ft.commit();
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
            } catch (HttpServerErrorException e) {
                return e.getStatusCode().value();
            }
        }
    }
}