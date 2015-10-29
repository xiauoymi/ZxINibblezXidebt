package com.nibbledebt.nibble.registration;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.TextView;
import com.nibbledebt.nibble.R;

/**
 * Created by ralam on 10/22/15.
 */
public class RegisterMessageDialog extends DialogFragment {

    private View view;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        view = getActivity().getLayoutInflater().inflate(R.layout.register_message_dialog_layout, null);

        int statusCode = getArguments().getInt("statusCode");

        if(statusCode >= 200 && statusCode < 300){
            ((TextView)(view.findViewById(R.id.register_message_dialog_header))).setText("Awesome! Welcome to Nibble");
            ((TextView)(view.findViewById(R.id.register_message_dialog_message))).setText("Please check your email on instructions on how to activate your account. You can click on the link to activate.");
            (view.findViewById(R.id.register_message_dialog_add_btn)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                    getActivity().finish();
                }
            });
        }else if(statusCode >= 500 && statusCode < 600){
            ((TextView)(view.findViewById(R.id.register_message_dialog_header))).setText("Oops Oo");
            ((TextView)(view.findViewById(R.id.register_message_dialog_message))).setText("System pooped out trying to register you. Try changing your name (..no, really!)");
            (view.findViewById(R.id.register_message_dialog_add_btn)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                }
            });
        }

        // Create the AlertDialog object and return it
        return builder.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }
}