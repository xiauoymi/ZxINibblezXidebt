package com.nibbledebt.nibble.registration;

import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.nibbledebt.nibble.R;
import com.nibbledebt.nibble.common.RestTemplateCreator;
import com.nibbledebt.nibble.integration.model.Bank;
import com.nibbledebt.nibble.integration.model.CustomerData;
import com.nibbledebt.nibble.integration.model.LoginField;
import com.nibbledebt.nibble.security.BanksObject;
import com.nibbledebt.nibble.security.RegisterObject;
import com.nibbledebt.nibble.security.SecurityContext;
import org.apache.commons.lang3.StringUtils;
import org.codepond.wizardroid.WizardFlow;
import org.codepond.wizardroid.persistence.ContextVariable;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * Created by ralam on 10/4/15.
 */
public class RegistrationWizard extends RegistrationWizardLayout{
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

    private RegistrationTask raTask;

    //You must override this method and create a wizard flow by
    //using WizardFlow.Builder as shown in this example
    @Override
    public WizardFlow onSetup() {
        /* Optionally, you can set different labels for the control buttons
        setNextButtonLabel("Advance");
        setBackButtonLabel("Return");
        setFinishButtonLabel("Finalize"); */



        return new WizardFlow.Builder()       //then set the layout container for the steps.
                .addStep(RegisterStep1.class, true)           //Add your steps in the order you want them
                .addStep(RegisterStep2.class, true)           //Add your steps in the order you want them
                .addStep(RegisterStep3.class)          //to appear and eventually call create()
                .create();                              //to create the wizard flow.
    }

    @Override
    public void onWizardComplete() {
        super.onWizardComplete();   //Make sure to first call the super method before anything else

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

        //... Access context variables here before terminating the wizard
        //...
        //String fullname = firstname + lastname;
        //Store the data in the DB or pass it back to the calling activity
             //Terminate the wizard
    }

    private class RegistrationTask extends AsyncTask<CustomerData, Void, Boolean> {
        @Override
        protected Boolean doInBackground(CustomerData... data) {
            try {
                doRegister(data[0]);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }

        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(Boolean loginSuccessful) {


        }

        @Override
        protected void onCancelled() {
            raTask = null;
//            hideProgress();
        }

        private void doRegister(CustomerData data) throws Exception {
            // The connection URL

            // Create a new RestTemplate instance
            RestTemplate restTemplate = RestTemplateCreator.getTemplateCreator().getNewTemplate();

            // Make the HTTP GET request, marshaling the response to a String
            ResponseEntity<Void> response = restTemplate.postForEntity(getString(R.string.regurl), data, Void.class);

            if(response.getStatusCode().equals(HttpStatus.OK)){
                SecurityContext.getCurrentContext().getSessionMap().put("customerData", new RegisterObject(data));
                getActivity().finish();
            }else if(response.getStatusCode().equals(HttpStatus.valueOf(203))){

            }else{

            }
        }

    }

}
