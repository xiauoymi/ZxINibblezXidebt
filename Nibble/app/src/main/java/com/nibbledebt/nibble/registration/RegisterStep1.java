package com.nibbledebt.nibble.registration;

import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;

import com.nibbledebt.nibble.R;
import com.nibbledebt.nibble.common.AbstractWizardStep;

import org.apache.commons.lang3.StringUtils;
import org.codepond.wizardroid.WizardStep;
import org.codepond.wizardroid.persistence.ContextVariable;

/**
 * Created by ralam on 10/4/15.
 */
public class RegisterStep1 extends AbstractWizardStep {

    @ContextVariable
    private String email;
    @ContextVariable
    private String password;
    @ContextVariable
    private String passwordRepeat;
    @ContextVariable
    private String firstname;
    @ContextVariable
    private String lastname;


    private EditText emailTextBox;
    private EditText passwordTextBox;
    private EditText passwordRepeatTextBox;
    private EditText firstnameTextBox;
    private EditText lastnameTextBox;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the step layout view
        View v = inflater.inflate(R.layout.register_wizard_step1_layout, container, false);

        // start the current step animation
        setCurrentStepAnimation(v.getContext(), R.anim.dot_pulse, (ImageView) v.findViewById(R.id.animated_dot_1));

        //Get reference to the textboxes
        emailTextBox = (EditText) v.findViewById(R.id.register_email);
        passwordTextBox = (EditText) v.findViewById(R.id.register_password);
        passwordRepeatTextBox = (EditText) v.findViewById(R.id.register_password_repeat);
        firstnameTextBox = (EditText) v.findViewById(R.id.register_firstname);
        lastnameTextBox = (EditText) v.findViewById(R.id.register_lastname);

        //and set default values by using Context Variables
        emailTextBox.setText(email);
        passwordTextBox.setText(password);
        passwordRepeatTextBox.setText(passwordRepeat);
        firstnameTextBox.setText(firstname);
        lastnameTextBox.setText(lastname);

        // return the view
        return v;
    }

    @Override
    public void onExit(int exitCode) {
        switch (exitCode) {
            case WizardStep.EXIT_NEXT:
                validateAndBindDataFields();
                break;
            case WizardStep.EXIT_PREVIOUS:
                break;
        }
    }

    private void validateAndBindDataFields(){
        if(!isValidEmail(emailTextBox.getText().toString())){
            setErrorBackground(emailTextBox, "Invalid Email.");
        }else{
            email = emailTextBox.getText().toString();
        }

        if(StringUtils.isNotBlank(passwordTextBox.getText().toString()) &&
                passwordTextBox.getText().toString().length()>6){
            passwordTextBox.setBackground(getResources().getDrawable(R.drawable.nibble_main_textbox_error));
        }else{
            password = passwordTextBox.getText().toString();
        }
    }


    private void bindDataFields() {
        password = passwordTextBox.getText().toString();
        passwordRepeat = passwordRepeatTextBox.getText().toString();
        firstname = firstnameTextBox.getText().toString();
        lastname = lastnameTextBox.getText().toString();
    }

    private void setErrorBackground(EditText textField, String message){
        int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            emailTextBox.setBackgroundDrawable(getResources().getDrawable(R.drawable.nibble_main_textbox_error));
        } else {
            emailTextBox.setBackground(getResources().getDrawable(R.drawable.nibble_main_textbox_error));
        }


        textField.setError(Html.fromHtml("<font color='red'>"+message+"</font>"), getResources().getDrawable(R.drawable.nibble_main_textbox_error));
    }
}
