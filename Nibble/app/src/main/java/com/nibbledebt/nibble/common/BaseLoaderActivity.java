package com.nibbledebt.nibble.common;

import android.app.Activity;
import android.view.View;
import android.widget.ProgressBar;

/**
 * Created by ralam on 7/15/15.
 */
public class BaseLoaderActivity  extends Activity  {

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
