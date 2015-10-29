package com.nibbledebt.nibble.registration;

import android.os.AsyncTask;
import android.os.Bundle;
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
                .addStep(RegisterStep3.class)
                .addStep(RegisterStep4.class)
                .create();
    }

    @Override
    public void onWizardComplete() {
        super.onWizardComplete();   //Make sure to first call the super method before anything else

        //... Access context variables here before terminating the wizard
        //...
        //String fullname = firstname + lastname;
        //Store the data in the DB or pass it back to the calling activity
             //Terminate the wizard
    }




}
