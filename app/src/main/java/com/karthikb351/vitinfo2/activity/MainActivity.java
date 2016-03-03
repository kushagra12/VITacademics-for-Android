/*
 * VITacademics
 * Copyright (C) 2015  Aneesh Neelam <neelam.aneesh@gmail.com>
 * Copyright (C) 2015  Saurabh Joshi <saurabhjoshi94@outlook.com>
 * Copyright (C) 2015  Gaurav Agerwala <gauravagerwala@gmail.com>
 * Copyright (C) 2015  Karthik Balakrishnan <karthikb351@gmail.com>
 * Copyright (C) 2015  Pulkit Juneja <pulkit.16296@gmail.com>
 * Copyright (C) 2015  Hemant Jain <hemanham@gmail.com>
 * Copyright (C) 2015  Darshan Mehta <darshanmehta17@gmail.com>
 *
 * This file is part of VITacademics.
 *
 * VITacademics is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * VITacademics is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with VITacademics.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.karthikb351.vitinfo2.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.karthikb351.vitinfo2.MainApplication;
import com.karthikb351.vitinfo2.R;
import com.karthikb351.vitinfo2.api.NetworkController;
import com.karthikb351.vitinfo2.api.RequestConfig;
import com.karthikb351.vitinfo2.event.RefreshFragmentEvent;
import com.karthikb351.vitinfo2.fragment.AboutFragment;
import com.karthikb351.vitinfo2.fragment.grades.GradesFragment;
import com.karthikb351.vitinfo2.fragment.messages.MessagesFragment;
import com.karthikb351.vitinfo2.fragment.schedule.ScheduleFragment;
import com.karthikb351.vitinfo2.fragment.settings.SettingsFragment;
import com.karthikb351.vitinfo2.model.Status;
import com.karthikb351.vitinfo2.utility.Constants;
import com.karthikb351.vitinfo2.utility.StringUtils;
import com.karthikb351.vitinfo2.utility.ResultListener;

import java.util.Arrays;
import java.util.List;

import de.greenrobot.event.EventBus;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

    private String name;
    private String registerNumber;

    private List<String> navigationTabs;

    private LinearLayout mainContent;

    private DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeData();
        initializeView();
    }

    private void initializeData() {
        name = ((MainApplication) getApplication()).getDataHolderInstanceInitialized().getName();
        registerNumber = ((MainApplication) getApplication()).getDataHolderInstanceInitialized().getRegisterNumber();
        }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void initializeView() {

        TextView headerUsername;
        TextView headerRegistrationNumber;

        navigationTabs = Arrays.asList(getResources().getStringArray(R.array.navigation_tab));

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mainContent = (LinearLayout) findViewById(R.id.llMainContent);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(
                    this, drawerLayout, toolbar,
                    R.string.navigation_drawer_open, R.string.navigation_drawer_close
            );
            drawerLayout.setDrawerListener(mDrawerToggle);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            mDrawerToggle.syncState();
        }


        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(final MenuItem menuItem) {
                menuItem.setChecked(true);
                drawerLayout.closeDrawers();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        switchFragment(menuItem.getItemId());
                    }
                }, Constants.DRAWER_CLOSE_TIME_OUT);
                return true;
            }
        });

        View headerLayout = navigationView.getHeaderView(0);

        headerRegistrationNumber = (TextView) headerLayout.findViewById(R.id.header_registration_number);
        headerUsername = (TextView) headerLayout.findViewById(R.id.header_username);

        if(StringUtils.checkString(registerNumber)){
            headerRegistrationNumber.setText(registerNumber);
        }

        if(StringUtils.checkString(name)){
            headerUsername.setText(StringUtils.toTitleCase(name.toLowerCase()));
        }

        getSupportFragmentManager().beginTransaction().add(R.id.flContent, new ScheduleFragment(), ScheduleFragment.class.getSimpleName()).commitAllowingStateLoss();
    }

    private void switchFragment(int id) {
        Fragment fragment = null;
        int position = 0;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (id) {

            case R.id.drawer_timetable:
                fragment = ScheduleFragment.newInstance();
                position = 0;
                break;

            /*case R.id.drawer_courses:
                fragment = CoursesFragment.newInstance();
                position = 1;
                break;*/
//            case R.id.drawer_home:
//                fragment = TodayFragment.newInstance();
//                position = 2;
//                break;
            case R.id.drawer_settings:
                fragment = SettingsFragment.newInstance();
                position = 3;
                break;
            case R.id.drawer_messages:
                fragment = MessagesFragment.newInstance();
                position = 4;
                break;
            case R.id.drawer_about:
                fragment = AboutFragment.newInstance();
                position = 5;
                break;
            case R.id.drawer_grades:
                fragment = GradesFragment.newInstance();
                position = 6;
                break ;
        }
        fragmentTransaction.replace(R.id.flContent, fragment, navigationTabs.get(position)).commit();
    }

    public void pullToRefresh(final ResultListener callback) {

        NetworkController networkController = NetworkController.getInstance(getApplicationContext());
        final ResultListener resultListener = new ResultListener() {
            @Override
            public void onSuccess() {
                initializeData();
                if (callback != null) {
                    callback.onSuccess();
                }
                try {
                    Snackbar.make(findViewById(R.id.llMainContent), "Success", Snackbar.LENGTH_LONG).show();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
                EventBus.getDefault().post(new RefreshFragmentEvent());
            }

            @Override
            public void onFailure(Status status) {
                if (callback != null) {
                    callback.onFailure(status);
                }
                try {
                    Snackbar.make(findViewById(R.id.llMainContent), status.getMessage(), Snackbar.LENGTH_LONG).show();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        };

        RequestConfig requestConfig = new RequestConfig(new ResultListener() {
            @Override
            public void onSuccess() {
                ((MainApplication) getApplication()).getDataHolderInstance().refreshData(MainActivity.this, resultListener);
            }

            @Override
            public void onFailure(Status status) {
                resultListener.onFailure(status);
            }
        });
        requestConfig.addRequest(RequestConfig.REQUEST_SYSTEM);
        requestConfig.addRequest(RequestConfig.REQUEST_REFRESH);
        requestConfig.addRequest(RequestConfig.REQUEST_GRADES);
        requestConfig.addRequest(RequestConfig.REQUEST_TOKEN);

        networkController.dispatch(requestConfig);
    }

    public LinearLayout getMainContent() {
        return mainContent;
    }
}
