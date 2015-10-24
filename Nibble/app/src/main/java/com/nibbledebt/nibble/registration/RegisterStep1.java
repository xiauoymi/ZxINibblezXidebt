package com.nibbledebt.nibble.registration;

import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;

import android.widget.TextView;
import com.nibbledebt.nibble.R;
import com.nibbledebt.nibble.common.AbstractWizardStep;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.codepond.wizardroid.WizardStep;
import org.codepond.wizardroid.persistence.ContextVariable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by ralam on 10/4/15.
 */
public class RegisterStep1 extends AbstractWizardStep{

    @ContextVariable
    private String email;
    @ContextVariable
    private String password;
    @ContextVariable
    private String firstname;
    @ContextVariable
    private String lastname;

    private Map<String, Boolean> validations = new HashMap<String, Boolean>();

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

        emailTextBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                if(StringUtils.isNotBlank(s.toString()) ) validateAndBindEmail();
                if(hasAllRequiredFields()) notifyCompleted();
                else notifyIncomplete();
            }
        });
        passwordTextBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                if(StringUtils.isNotBlank(s.toString()) ) validateAndBindPassword();
                if (hasAllRequiredFields()) notifyCompleted();
                else notifyIncomplete();
            }
        });
        passwordRepeatTextBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                if(StringUtils.isNotBlank(s.toString()) ) validateAndBindPassword();
                if (hasAllRequiredFields()) notifyCompleted();
                else notifyIncomplete();
            }
        });
        firstnameTextBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                if(StringUtils.isNotBlank(s.toString()) ) validateAndBindFirstName();
                if (hasAllRequiredFields()) notifyCompleted();
                else notifyIncomplete();
            }
        });
        lastnameTextBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                if(StringUtils.isNotBlank(s.toString()) ) validateAndBindLastName();
                if (hasAllRequiredFields()) notifyCompleted();
                else notifyIncomplete();
            }
        });


        //and set default values by using Context Variables
        emailTextBox.setText(email);
        passwordTextBox.setText(password);
        firstnameTextBox.setText(firstname);
        lastnameTextBox.setText(lastname);

        // return the view
        return v;
    }

    @Override
    public void onExit(int exitCode) {
        switch (exitCode) {
            case WizardStep.EXIT_NEXT:

                break;
            case WizardStep.EXIT_PREVIOUS:
                break;
        }
    }

    private boolean validateAndBindEmail(){
        if(!isValidEmail(emailTextBox.getText().toString())){
            setErrorBackground(emailTextBox, "Invalid Email.");
            return false;
        }else{
            email = emailTextBox.getText().toString();
            validations.put("email", true);
            return true;
        }
    }

    private boolean validateAndBindPassword(){
        if(StringUtils.isNotBlank(passwordTextBox.getText().toString())){
            if(passwordTextBox.getText().toString().length()<6){
                setErrorBackground(passwordTextBox, "Password must be at least 6 characters long");
                return false;
            }
            password = passwordTextBox.getText().toString();
            if(StringUtils.isNotBlank(passwordRepeatTextBox.getText().toString()) &&
                    StringUtils.equals(passwordTextBox.getText().toString(), passwordRepeatTextBox.getText().toString())){
                password = passwordRepeatTextBox.getText().toString();
                validations.put("password", true);
                return true;
            }else{
                setErrorBackground(passwordRepeatTextBox, "Does not match the password.");
                return false;
            }
        }else{
            setErrorBackground(passwordTextBox, "Password is required.");
            return false;
        }
    }

    private boolean validateAndBindFirstName(){
        if(StringUtils.isNotBlank(firstnameTextBox.getText().toString())){
            firstname = firstnameTextBox.getText().toString();
            validations.put("firstname", true);
            return true;
        }else{
            setErrorBackground(firstnameTextBox, "Last name is required.");
            return false;
        }

    }

    private boolean validateAndBindLastName(){
        if(StringUtils.isNotBlank(lastnameTextBox.getText().toString())){
            lastname = lastnameTextBox.getText().toString();
            validations.put("lastname", true);
            return true;
        }else{
            setErrorBackground(lastnameTextBox, "Last name is required.");
            return false;
        }

    }

    protected Boolean hasAllRequiredFields(){
        if(BooleanUtils.isTrue(validations.get("email")) &&
                BooleanUtils.isTrue(validations.get("password"))  &&
                BooleanUtils.isTrue(validations.get("firstname")) &&
                BooleanUtils.isTrue(validations.get("lastname"))) return true;
        else return false;
    }



}
