package com.nibbledebt.nibble.common;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ProgressBar;

/**
 * Created by ralam on 7/22/15.
 */
public abstract class AbstractFragment extends Fragment {
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
