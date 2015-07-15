/*
 * VITacademics
 * Copyright (C) 2015  Aneesh Neelam <neelam.aneesh@gmail.com>
 * Copyright (C) 2015  Saurabh Joshi <saurabhjoshi94@outlook.com>
 * Copyright (C) 2015  Gaurav Agerwala <gauravagerwala@gmail.com>
 * Copyright (C) 2015  Karthik Balakrishnan <karthikb351@gmail.com>
 * Copyright (C) 2015  Pulkit Juneja <pulkit.16296@gmail.com>
 * Copyright (C) 2015  Hemant Jain <hemanham@gmail.com>
 *
 * This file is part of VITacademics.
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

package com.karthikb351.vitinfo2.utility;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.karthikb351.vitinfo2.activity.MainActivity;

public abstract class HidingScrollListener extends RecyclerView.OnScrollListener {

    private static final float HIDE_THRESHOLD = 10;
    private static final float SHOW_THRESHOLD = 70;

    private int mToolbarOffset = 0;
    private Context context ;
    private int mToolbarHeight;
    boolean toolbarVisible = true ;
    private LinearLayout mainContent;

    public HidingScrollListener(Context context)
    {
        this.context = context ;
        mainContent = ((MainActivity)context).getMainContent();
        mToolbarHeight = ((MainActivity)context).getSupportActionBar().getHeight();
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        clipToolbarOffset();
        Move(mToolbarOffset);

        if((mToolbarOffset <mToolbarHeight && dy>0) || (mToolbarOffset >0 && dy<0)) {
            mToolbarOffset += dy;
        }
    }

    private void clipToolbarOffset() {
        if(mToolbarOffset > mToolbarHeight) {
            mToolbarOffset = mToolbarHeight;
        } else if(mToolbarOffset < 0) {
            mToolbarOffset = 0;
        }
    }

    public void Move(int distance)
    {
     // TODO, rethink this. Must not change Minimum API Level
     mainContent.setTranslationY(-distance);
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        if(newState == RecyclerView.SCROLL_STATE_IDLE) {
            if (toolbarVisible) {
                if (mToolbarOffset > HIDE_THRESHOLD)
                {
                    setInvisible();
                }
                else
                {
                    setVisible();
                }
            }
            else
            {
                if ((mToolbarHeight - mToolbarOffset) > SHOW_THRESHOLD)
                {
                    setVisible();
                }
                else
                {
                    setInvisible();
                }
            }
        }

    }
    private void setVisible() {
        if(mToolbarOffset > 0)
        {
            mToolbarOffset = 0;
        }
        toolbarVisible = true;
    }

    private void setInvisible() {
        if(mToolbarOffset < mToolbarHeight)
        {
            mToolbarOffset = mToolbarHeight;
        }
        toolbarVisible = false;
    }

}
