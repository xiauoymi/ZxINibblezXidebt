package com.nibbledebt.nibble.common;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;

import android.widget.ProgressBar;
import android.widget.TextView;
import com.nibbledebt.nibble.R;

import org.codepond.wizardroid.WizardStep;

/**
 * Created by ralam on 10/5/15.
 */
public abstract class AbstractWizardStep extends WizardStep {


    protected void setCurrentStepAnimation(Context context, int animationId, View view, View[] hideViews){
        Animation pulse = AnimationUtils.loadAnimation(context, R.anim.dot_pulse);
        view.startAnimation(pulse);
        for(View v : hideViews){
            v.setVisibility(View.INVISIBLE);
        }
    }

    protected void setStepComplete(ImageView view){
        view.setImageResource(R.drawable.register_wizard_status_dot_complete);
    }


    protected final boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }


    protected void setErrorBackground(EditText textField, String message){
//        ((StateListDrawable)textField.getBackground()).addState(StateListDrawable.ConstantState);


        textField.setError(Html.fromHtml("<font color='red'>" + message + "</font>"), getResources().getDrawable(R.drawable.nibble_main_textbox_error));
    }

    protected abstract Boolean hasAllRequiredFields();

    protected ProgressBar progressBar;
    protected View progressContainer;

    public void showProgress(){
        progressBar.setVisibility(View.VISIBLE);
        progressContainer.setVisibility(View.VISIBLE);
    }

    public void hideProgress(){
        progressBar.setVisibility(View.GONE);
        progressContainer.setVisibility(View.GONE);
    }

}
