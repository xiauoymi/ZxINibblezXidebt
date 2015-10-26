package com.nibbledebt.nibble.registration;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.method.PasswordTransformationMethod;
import android.util.TypedValue;
import android.view.View;
import android.widget.*;
import com.nibbledebt.nibble.R;
import com.nibbledebt.nibble.integration.model.Bank;
import com.nibbledebt.nibble.integration.model.LoginField;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ralam on 10/22/15.
 */
public class RegisterStep3Dialog extends DialogFragment {

    private Bank bank;
    private View view;
    private Map<String, EditText> formEditTextList;
    private HashMap<String, String> bankCreds;

    public interface RegisterStep3DialogListener {
        void onDialogPositiveClick(DialogFragment dialog, Map<String, String> formData, Bank selectedBank);
        void onDialogNegativeClick(DialogFragment dialog);
    }

    // Use this instance of the interface to deliver action events
    RegisterStep3DialogListener mListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mListener = (RegisterStep3DialogListener)this.getTargetFragment();
        bank = (Bank)getArguments().getSerializable("bank");
        bankCreds = (HashMap<String, String>)getArguments().getSerializable("bankCreds");
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        view = getActivity().getLayoutInflater().inflate(R.layout.register_wizard_step3_dialog_layout, null);
        (view.findViewById(R.id.register_step3_dialog_add_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> formData = new HashMap<>();
                boolean valid = true;
                for(LoginField field : bank.getLoginForm().getLoginField())  {
                    if(!StringUtils.isNotBlank(formEditTextList.get(field.getName()).getText().toString()) ){
                        formEditTextList.get(field.getName()).setError(field.getName()+" is required.");
                        valid = false;
                    }else if(formEditTextList.get(field.getName()).getText().toString().length()<=field.getValueLengthMin() ||
                            (field.getValueLengthMax()>0 && formEditTextList.get(field.getName()).getText().toString().length()>field.getValueLengthMax())){
                        formEditTextList.get(field.getName()).setError("Must be between "+field.getValueLengthMin() + " and " + field.getValueLengthMax() + " characters.");
                        valid = false;
                    }else{
                        formData.put(field.getName(), formEditTextList.get(field.getName()).getText().toString());
                    }
                }
                if(valid) mListener.onDialogPositiveClick(RegisterStep3Dialog.this, formData, bank);
            }
        });
        (view.findViewById(R.id.register_step3_dialog_cancel_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onDialogNegativeClick(RegisterStep3Dialog.this);
            }
        });

        builder.setView(view);


        ((TextView)view.findViewById(R.id.step3_dialog_header)).setText(bank.getInstitution().getName() + " Authentication");
        LinearLayout fieldLayout = (LinearLayout)view.findViewById(R.id.step3_dialog_layout);
        formEditTextList = new HashMap<>();
        for(LoginField field : bank.getLoginForm().getLoginField()){
            EditText loginFieldEditText = new EditText(getActivity().getBaseContext());
            loginFieldEditText.setHint(field.getDescription());
            loginFieldEditText.setHintTextColor(getResources().getColor(R.color.hint_text));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(0, 0, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics()));

            loginFieldEditText.setPadding((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15, getResources().getDisplayMetrics()),
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics()), 0,
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics()));

            loginFieldEditText.setLayoutParams(params);
            loginFieldEditText.setBackground(getResources().getDrawable(R.drawable.nibble_main_textbox, null));
            loginFieldEditText.setTextColor(getResources().getColor(R.color.white));
            loginFieldEditText.setCompoundDrawablePadding((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15, getResources().getDisplayMetrics()));
            if(field.getMask()) loginFieldEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            fieldLayout.addView(loginFieldEditText, params);
            if(bankCreds.get(field.getName()) != null) loginFieldEditText.setText(bankCreds.get(field.getName()));
            formEditTextList.put(field.getName(),loginFieldEditText );

        }

        // Create the AlertDialog object and return it
        return builder.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }
}