package com.nibbledebt.nibble.common;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.nibbledebt.nibble.R;

import org.codepond.wizardroid.WizardStep;

/**
 * Created by ralam on 10/5/15.
 */
public class AbstractWizardStep extends WizardStep {


    protected void setCurrentStepAnimation(Context context, int animationId, View view){
        Animation pulse = AnimationUtils.loadAnimation(context, R.anim.dot_pulse);
        view.startAnimation(pulse);
    }

    protected void setStepComplete(ImageView view){
        view.setImageResource(R.drawable.register_wizard_status_dot_complete);
    }
}
