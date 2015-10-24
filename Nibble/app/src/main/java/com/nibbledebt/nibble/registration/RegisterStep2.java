package com.nibbledebt.nibble.registration;

import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.nibbledebt.nibble.R;
import com.nibbledebt.nibble.common.AbstractWizardStep;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.codepond.wizardroid.persistence.ContextVariable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ralam on 10/4/15.
 */
public class RegisterStep2 extends AbstractWizardStep {

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


    private Map<String, Boolean> validations = new HashMap<String, Boolean>();

    private EditText address1TextBox;
    private EditText address2TextBox;
    private EditText cityTextBox;
    private Spinner stateDropDown;
    private EditText zipTextBox;
    private EditText phoneTextBox;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the step layout view
        View v = inflater.inflate(R.layout.register_wizard_step2_layout, container, false);

        // set the previous step as complete
        setStepComplete((ImageView) v.findViewById(R.id.animated_dot_1));

        // start the current step animation
        setCurrentStepAnimation(v.getContext(), R.anim.dot_pulse, (ImageView) v.findViewById(R.id.animated_dot_2));


        //Get reference to the textboxes
        address1TextBox = (EditText) v.findViewById(R.id.register_address1);
        address2TextBox = (EditText) v.findViewById(R.id.register_address2);
        cityTextBox = (EditText) v.findViewById(R.id.register_city);
        stateDropDown = (Spinner) v.findViewById(R.id.register_state);
        zipTextBox = (EditText) v.findViewById(R.id.register_zip);
        phoneTextBox = (EditText) v.findViewById(R.id.register_phone);

        address1TextBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                if(StringUtils.isNotBlank(s.toString()) ) validateAndBindAddress1();
                if(hasAllRequiredFields()) notifyCompleted();
                else notifyIncomplete();
            }
        });
        address2TextBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                if(StringUtils.isNotBlank(s.toString()) ) bindAddress2();
            }
        });
        cityTextBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                if(StringUtils.isNotBlank(s.toString()) ) validateAndBindCity();
                if (hasAllRequiredFields()) notifyCompleted();
                else notifyIncomplete();
            }
        });


        stateDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(StringUtils.isNotBlank((String)stateDropDown.getSelectedItem()) ) validateAndBindState();
                if (hasAllRequiredFields()) notifyCompleted();
                else notifyIncomplete();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        zipTextBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                if(StringUtils.isNotBlank(s.toString()) ) validateAndBindZip();
                if (hasAllRequiredFields()) notifyCompleted();
                else notifyIncomplete();
            }
        });

        phoneTextBox.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        phoneTextBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                if(StringUtils.isNotBlank(s.toString()) ) bindPhone();
            }
        });


        //and set default values by using Context Variables
        address1TextBox.setText(address1);
        address2TextBox.setText(address2);
        cityTextBox.setText(city);
        stateDropDown.setSelection(stateIdx);
        zipTextBox.setText(zip);
        phoneTextBox.setText(phone);

        if (hasAllRequiredFields()) notifyCompleted();

        // return the view
        return v;
    }

    @Override
    protected Boolean hasAllRequiredFields(){
        if(BooleanUtils.isTrue(validations.get("address1")) &&
                BooleanUtils.isTrue(validations.get("city")) &&
                BooleanUtils.isTrue(validations.get("state")) &&
                BooleanUtils.isTrue(validations.get("zip")) )return true;
        else return false;
    }

    private boolean validateAndBindAddress1(){
        if(StringUtils.isNotBlank(address1TextBox.getText().toString())){
            address1 = address1TextBox.getText().toString();
            validations.put("address1", true);
            return true;
        }else{
            setErrorBackground(address1TextBox, "Address Line 1 is required.");
            return false;
        }
    }
    private boolean validateAndBindCity(){
        if(StringUtils.isNotBlank(cityTextBox.getText().toString())){
            city = cityTextBox.getText().toString();
            validations.put("city", true);
            return true;
        }else{
            setErrorBackground(cityTextBox, "City is required.");
            return false;
        }
    }
    private boolean validateAndBindZip(){
        if(StringUtils.isNotBlank(zipTextBox.getText().toString())){
            zip = zipTextBox.getText().toString();
            validations.put("zip", true);
            return true;
        }else{
            setErrorBackground(zipTextBox, "Zip is required.");
            return false;
        }
    }
    private boolean bindAddress2(){
        if(StringUtils.isNotBlank(address2TextBox.getText().toString())){
            address2 = address2TextBox.getText().toString();
            validations.put("address2", true);
        }
        return true;
    }
    private boolean bindPhone(){
        if(StringUtils.isNotBlank(phoneTextBox.getText().toString())){
            phone = phoneTextBox.getText().toString();
            validations.put("phone", true);
        }
        return true;
    }
    private boolean validateAndBindState(){
        if(!StringUtils.equalsIgnoreCase((String )stateDropDown.getSelectedItem(), "Pick a State")){
            stateIdx = stateDropDown.getSelectedItemPosition();
            state = (String)stateDropDown.getSelectedItem();
            validations.put("state", true);
            return true;
        }else{
            return false;
        }
    }
}
