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
    private String header;
    private String message;

    private View view;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.register_message_dialog_layout, null);
        ((TextView)view.findViewById(R.id.register_message_dialog_header)).setText(header);
        ((TextView)view.findViewById(R.id.register_message_dialog_message)).setText(message);
        (view.findViewById(R.id.register_message_dialog_ok_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                getActivity().finish();
            }
        });
        builder.setView(view);
        return builder.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }


    public void setHeader(String header) {
        this.header = header;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}