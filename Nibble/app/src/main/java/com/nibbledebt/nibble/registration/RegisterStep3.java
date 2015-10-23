package com.nibbledebt.nibble.registration;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.TypedValue;
import android.view.*;
import android.widget.*;

import com.nibbledebt.nibble.R;
import com.nibbledebt.nibble.common.AbstractWizardStep;
import com.nibbledebt.nibble.common.RestTemplateCreator;
import com.nibbledebt.nibble.integration.model.Bank;
import com.nibbledebt.nibble.integration.model.LoginField;
import com.nibbledebt.nibble.security.BanksObject;
import com.nibbledebt.nibble.security.SecurityContext;
import com.nibbledebt.nibble.security.SessionObject;

import org.apache.commons.lang3.StringUtils;
import org.codepond.wizardroid.persistence.ContextVariable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.*;

/**
 * Created by ralam on 10/4/15.
 */
public class RegisterStep3 extends AbstractWizardStep implements RegisterStep3Dialog.RegisterStep3DialogListener{
    private SupportedBanksTask saTask;

    @ContextVariable
    private String bankId;

    @ContextVariable
    private Map<String, String> bankCreds = new HashMap<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the step layout view
        View v = inflater.inflate(R.layout.register_wizard_step3_layout, container, false);

        // set the previous steps as complete
        setStepComplete((ImageView) v.findViewById(R.id.animated_dot_1));
        setStepComplete((ImageView) v.findViewById(R.id.animated_dot_2));

        // start the current step animation
        setCurrentStepAnimation(v.getContext(), R.anim.dot_pulse, (ImageView) v.findViewById(R.id.animated_dot_3));

        // load supported accounts
        saTask = new SupportedBanksTask();
        saTask.execute();

        // return the view
        return v;
    }

    @Override
    public void onDialogPositiveClick(android.support.v4.app.DialogFragment dialog, Map<String, String> formData) {
        dialog.dismiss();

    }

    @Override
    public void onDialogNegativeClick(android.support.v4.app.DialogFragment dialog) {
        deselectAll();
        dialog.dismiss();

    }

    private class SupportedBanksTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... data) {
            try {
                doLoad();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }

        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(Boolean loginSuccessful) {
            final List<Bank> banks = ((BanksObject)SecurityContext.getCurrentContext().getSessionMap().get("banks")).getData("banks");
            for(int i=0; i<banks.size(); i++){
                final ImageView imageView = (ImageView)getActivity().findViewById(getGridImageViewId(i + 1));
//                imageView.setImageBitmap(banks.get(i).getInstitution().getLogo());
                final int idx = i;
                Drawable drawable = new BitmapDrawable(getResources(), banks.get(i).getInstitution().getLogo());
                drawable.setAlpha(150);
                imageView.setBackground(drawable);
                imageView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if(v!=null && v instanceof ImageView) {
                            switch (event.getAction()) {
                                case MotionEvent.ACTION_DOWN:
                                    if(!StringUtils.equalsIgnoreCase(bankId, banks.get(idx).getInstitution().getId())){
                                        v.getBackground().setAlpha(255);
                                        deselectOthers(v.getId());
                                        v.setLayoutParams(new LinearLayout.LayoutParams((int)((double)v.getLayoutParams().width*1.2d), (int)((double)v.getLayoutParams().height*1.2d)));
                                        v.invalidate();
                                    }
                                    bankId = banks.get(idx).getInstitution().getId();
                                    createDialog(banks.get(idx)).show(getActivity().getSupportFragmentManager(), "RegisterStep3Dialog");
                                    break;
                                case MotionEvent.ACTION_UP: {

                                    break;
                                }
                                case MotionEvent.ACTION_CANCEL: {
                                    v.getBackground().setAlpha(255);
                                    v.invalidate();
                                    break;
                                }
                            }
                        }
                        return true;
                    }
                });
            }

        }

        @Override
        protected void onCancelled() {
            saTask = null;
//            hideProgress();
        }

        private void doLoad() throws Exception {
            // The connection URL

            // Create a new RestTemplate instance
            RestTemplate restTemplate = RestTemplateCreator.getTemplateCreator().getNewTemplate();

            // Make the HTTP GET request, marshaling the response to a String
            Bank[] result = restTemplate.getForObject(getString(R.string.banksurl), Bank[].class);

            List<Bank> banks = Arrays.asList(result);
            Iterator<Bank> it = Arrays.asList(result).iterator();

            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.IMAGE_PNG));
            HttpEntity<String> entity = new HttpEntity<String>(headers);


            while(it.hasNext()){
                Bank bank = it.next();
                ResponseEntity<byte[]> response = restTemplate.exchange(getString(R.string.banklogourl) + bank.getInstitution().getLogoCode(), HttpMethod.GET, entity, byte[].class, "1");

                if(response.getStatusCode().equals(HttpStatus.OK))
                    bank.getInstitution().setLogo(BitmapFactory.decodeByteArray(response.getBody(), 0, response.getBody().length));

            }
            SecurityContext.getCurrentContext().getSessionMap().put("banks", new BanksObject(banks));
        }

    }

    private final void deselectOthers(int imageViewId){
        for(int ivid : getAllGridImageViewIds()){
            if(imageViewId!=ivid){
                ImageView imageView = (ImageView)getActivity().findViewById(ivid);
                if(imageView != null){
                    if(imageView.getBackground()!=null) imageView.getBackground().setAlpha(150);
                    imageView.setLayoutParams(new LinearLayout.LayoutParams((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80, getResources().getDisplayMetrics())));
                    imageView.invalidate();
                }
            }
        }
    }


    private final int[] getAllGridImageViewIds(){
        return new int[]{R.id.rform11,R.id.rform12,R.id.rform13,R.id.rform21,R.id.rform22,R.id.rform23,R.id.rform31,R.id.rform32,R.id.rform33};
    }


    private final int getGridImageViewId(int columnCount){
        switch(columnCount){
            case 1 : return R.id.rform11;
            case 2 : return R.id.rform12;
            case 3 : return R.id.rform13;
            case 4 : return R.id.rform21;
            case 5 : return R.id.rform22;
            case 6 : return R.id.rform23;
            case 7 : return R.id.rform31;
            case 8 : return R.id.rform32;
            case 9 : return R.id.rform33;
            default : return R.id.rform41;
        }
    }


    private final void deselectAll(){
        for(int ivid : getAllGridImageViewIds()){
            ImageView imageView = (ImageView)getActivity().findViewById(ivid);
            if(imageView != null){
                if(imageView.getBackground()!=null) imageView.getBackground().setAlpha(150);
                imageView.setLayoutParams(new LinearLayout.LayoutParams((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80, getResources().getDisplayMetrics())));
                imageView.invalidate();
            }
        }
    }

    private RegisterStep3Dialog createDialog(Bank bank){
        RegisterStep3Dialog dialog = new RegisterStep3Dialog();
        dialog.setTargetFragment(this, 0);
        Bundle bundle = new Bundle();
        bundle.putSerializable("bank", bank);
        dialog.setArguments(bundle);
        return dialog;
    }


    protected Boolean hasAllRequiredFields(){
        return true;
    }
}