package com.nibbledebt.nibble.registration;

import org.codepond.wizardroid.WizardFlow;

/**
 * Created by ralam on 10/4/15.
 */
public class RegistrationWizard extends RegistrationWizardLayout{

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
        //... Access context variables here before terminating the wizard
        //...
        //String fullname = firstname + lastname;
        //Store the data in the DB or pass it back to the calling activity
        getActivity().finish();     //Terminate the wizard
    }



}
