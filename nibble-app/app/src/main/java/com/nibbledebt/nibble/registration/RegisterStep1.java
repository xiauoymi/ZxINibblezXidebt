package com.nibbledebt.nibble.registration;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nibbledebt.nibble.R;
import com.nibbledebt.nibble.common.AbstractWizardStep;

/**
 * Created by ralam on 10/4/15.
 */
public class RegisterStep1 extends AbstractWizardStep {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the step layout view
        View v = inflater.inflate(R.layout.register_wizard_step1_layout, container, false);

        // start the current step animation
        setCurrentStepAnimation(v.getContext(), R.anim.dot_pulse, (ImageView) v.findViewById(R.id.animated_dot_1));

        // return the view
        return v;
    }
}
