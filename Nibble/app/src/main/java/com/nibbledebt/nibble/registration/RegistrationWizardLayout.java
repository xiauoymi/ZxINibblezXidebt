package com.nibbledebt.nibble.registration;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.ProgressBar;
import com.nibbledebt.nibble.R;

import org.codepond.wizardroid.WizardFragment;
import org.codepond.wizardroid.persistence.ContextManager;

/**
 * Created by ralam on 10/5/15.
 */
public abstract class RegistrationWizardLayout extends WizardFragment implements View.OnClickListener {
    private Button nextButton;
    private Button previousButton;
    private String nextButtonText;
    private String finishButtonText;
    private String backButtonText;
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

    public RegistrationWizardLayout() {
    }

    public RegistrationWizardLayout(ContextManager contextManager) {
        super(contextManager);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View wizardLayout = inflater.inflate(R.layout.register_wizard_layout, container, false);
        this.nextButton = (Button) wizardLayout.findViewById(R.id.wizard_next_button);
        this.nextButton.setOnClickListener(this);
        this.nextButton.setText(this.getNextButtonLabel());
        this.previousButton = (Button) wizardLayout.findViewById(R.id.wizard_previous_button);
        this.previousButton.setOnClickListener(this);
        this.previousButton.setText(this.getBackButtonLabel());
        progressBar = (ProgressBar)wizardLayout.findViewById(R.id.register_progress);
        progressContainer = wizardLayout.findViewById(R.id.register_progress_container);
        return wizardLayout;
    }

    public void onResume() {
        super.onResume();
        this.updateWizardControls();
    }

    public void onWizardComplete() {
        super.onWizardComplete();
    }

    public void onClick(View v) {
        if (v.getId() == org.codepond.wizardroid.R.id.wizard_next_button) {
            this.wizard.goNext();
        } else if (v.getId() == org.codepond.wizardroid.R.id.wizard_previous_button) {
            this.wizard.goBack();
        }

    }

    public void onStepChanged() {
        super.onStepChanged();
        this.updateWizardControls();
    }

    private void updateWizardControls() {
        this.previousButton.setEnabled(!this.wizard.isFirstStep());
        this.previousButton.setText(this.getBackButtonLabel());
        this.nextButton.setEnabled(this.wizard.canGoNext());
        this.nextButton.setText(this.wizard.isLastStep() ? this.getFinishButtonText() : this.getNextButtonLabel());
    }

    public String getNextButtonLabel() {
        return TextUtils.isEmpty(this.nextButtonText) ? this.getResources().getString(org.codepond.wizardroid.R.string.action_next) : this.nextButtonText;
    }

    public void setNextButtonText(String nextButtonText) {
        this.nextButtonText = nextButtonText;
    }

    public String getFinishButtonText() {
        return TextUtils.isEmpty(this.finishButtonText) ? this.getResources().getString(org.codepond.wizardroid.R.string.action_finish) : this.finishButtonText;
    }

    public void setFinishButtonText(String finishButtonText) {
        this.finishButtonText = finishButtonText;
    }

    public String getBackButtonLabel() {
        return TextUtils.isEmpty(this.backButtonText) ? this.getResources().getString(org.codepond.wizardroid.R.string.action_previous) : this.backButtonText;
    }

    public void setBackButtonText(String backButtonText) {
        this.backButtonText = backButtonText;
    }
}