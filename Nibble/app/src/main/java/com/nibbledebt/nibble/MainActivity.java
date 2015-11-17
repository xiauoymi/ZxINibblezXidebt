package com.nibbledebt.nibble;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import com.nibbledebt.nibble.common.AbstractFragment;
import com.nibbledebt.nibble.common.BaseLoaderCompatActivity;
import com.nibbledebt.nibble.fragments.RoundupAccountsFragment;
import com.nibbledebt.nibble.fragments.CrowdFragment;
import com.nibbledebt.nibble.fragments.HomeFragment;
import com.nibbledebt.nibble.fragments.LoanAccountsFragment;
import com.nibbledebt.nibble.security.RegisterObject;
import com.nibbledebt.nibble.security.SecurityContext;

public class MainActivity extends BaseLoaderCompatActivity {
      private DrawerLayout mDrawer;
      private Toolbar toolbar;
      private NavigationView nvDrawer;
      private ActionBarDrawerToggle drawerToggle;
      private SignoutTask signoutTask;
      private AbstractFragment currentFragment;

      @Override
      protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            // Set a Toolbar to replace the ActionBar.
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            setProgressBarIndeterminateVisibility(true);


            // Find our drawer view
            mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

            mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawerToggle = new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open,  R.string.drawer_close);


            // Find our drawer view
            nvDrawer = (NavigationView) findViewById(R.id.nvView);
            // Setup drawer view
            setupDrawerContent(nvDrawer);

            // Set Nav Header items
            ((TextView)findViewById(R.id.nav_header_1)).setText(((RegisterObject)SecurityContext.getCurrentContext().getSessionMap().get("memberDetails")).getData("").getFirstName() + " " +
                    ((RegisterObject)SecurityContext.getCurrentContext().getSessionMap().get("memberDetails")).getData("").getLastName() );

            ((TextView)findViewById(R.id.nav_header_2)).setText(((RegisterObject)SecurityContext.getCurrentContext().getSessionMap().get("memberDetails")).getData("").getUsername());

            // Tie DrawerLayout events to the ActionBarToggle
            mDrawer.setDrawerListener(drawerToggle);

            // Set the menu icon instead of the launcher icon.
            final ActionBar ab = getSupportActionBar();
            ab.setHomeAsUpIndicator(R.drawable.ic_action_menu);
            ab.setDisplayHomeAsUpEnabled(true);


            try {
                  currentFragment = new HomeFragment();
                  FragmentManager fragmentManager = this.getSupportFragmentManager();
                  fragmentManager.beginTransaction().replace(R.id.flContent, currentFragment).commit();
            } catch (Exception e) {
                  e.printStackTrace();
                  Log.e(e.getMessage(), "Could not initialize default fragment");
            }
      }

      @Override
      public boolean onOptionsItemSelected(MenuItem item) {
            // The action bar home/up action should open or close the drawer.
            switch (item.getItemId()) {
                  case android.R.id.home:
                        mDrawer.openDrawer(GravityCompat.START);
                        return true;
            }

            if (drawerToggle.onOptionsItemSelected(item)) {
                  return true;
            }
            return super.onOptionsItemSelected(item);

      }

      @Override
      protected void onPostCreate(Bundle savedInstanceState) {
            super.onPostCreate(savedInstanceState);
            // Sync the toggle state after onRestoreInstanceState has occurred.
            drawerToggle.syncState();
      }

      private void setupDrawerContent(NavigationView navigationView) {
            navigationView.setNavigationItemSelectedListener(
                    new NavigationView.OnNavigationItemSelectedListener() {
                          @Override
                          public boolean onNavigationItemSelected(MenuItem menuItem) {
                                selectDrawerItem(menuItem);
                                return true;
                          }
                    });
      }

      public void selectDrawerItem(MenuItem menuItem) {
            Class fragmentClass;
            switch(menuItem.getItemId()) {
                  case R.id.nav_first_fragment:
                        fragmentClass = HomeFragment.class;
                        break;
                  case R.id.nav_second_fragment:
                        fragmentClass = RoundupAccountsFragment.class;
                        break;
                  case R.id.nav_third_fragment:
                        fragmentClass = LoanAccountsFragment.class;
                        break;
                  case R.id.nav_fourth_fragment:
                        fragmentClass = CrowdFragment.class;
                        break;
                  case R.id.nav_help_fragment:
                        fragmentClass = HomeFragment.class;
                        break;
                  case R.id.nav_logout_fragment:
                        fragmentClass = null;
                        try {
                              signoutTask = new SignoutTask();
                              signoutTask.execute();
                        } catch (Exception e) {
                              e.printStackTrace();
                              Log.w(e.getMessage(), "error while loggin out user");
                        }
                        break;
                  default:
                        fragmentClass = null;
            }

            try {
                  if(fragmentClass!=null){

                        mDrawer.closeDrawers();
                        if(currentFragment != null){
                              final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                              ft.detach(currentFragment);
                              ft.commit();
                        }

                        currentFragment = (AbstractFragment) fragmentClass.newInstance();
                        FragmentManager fragmentManager = this.getSupportFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.flContent, currentFragment).commit();

                        // Highlight the selected item, update the title, and close the drawer
                        menuItem.setChecked(true);
                        setTitle(menuItem.getTitle());
                  }
            } catch (Exception e) {
                  e.printStackTrace();
            }
      }


      @Override
      public void onConfigurationChanged(Configuration newConfig) {
            super.onConfigurationChanged(newConfig);
            // Pass any configuration change to the drawer toggles
            drawerToggle.onConfigurationChanged(newConfig);
      }


      @Override
      public void onBackPressed() {
            Log.d("CDA", "onBackPressed Called");
            moveTaskToBack(true);
            Intent setIntent = new Intent(Intent.ACTION_MAIN);
            setIntent.addCategory(Intent.CATEGORY_HOME);
            setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(setIntent);
      }

      private class SignoutTask extends AsyncTask<String, Void,  String> {

            @Override
            protected String doInBackground(String... data) {
                  return "";
            }
            @Override
            protected void onPreExecute() {


            }
            // onPostExecute displays the results of the AsyncTask.
            @Override
            protected void onPostExecute(String result) {
                  SecurityContext.getCurrentContext().getSessionMap().clear();
                  SecurityContext.getCurrentContext().setCookie(null);
                  startActivity(new Intent(getBaseContext(), LoginActivity.class));
                  finish();
                  signoutTask = null;
            }

            @Override
            protected void onCancelled() {
                signoutTask = null;
            }


      }
}