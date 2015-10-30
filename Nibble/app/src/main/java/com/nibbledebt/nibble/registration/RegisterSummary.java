package com.nibbledebt.nibble.registration;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.nibbledebt.nibble.R;

/**
 * Created by ralam on 10/4/15.
 */
public class RegisterSummary extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the step layout view
        return inflater.inflate(R.layout.register_summary_layout, container, false);
    }

    @Override
    public void onAttach (Activity activity){
        super.onAttach(activity);
    }

}