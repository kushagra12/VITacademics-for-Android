/*
 * VITacademics
 * Copyright (C) 2015  Aneesh Neelam <neelam.aneesh@gmail.com>
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

package com.karthikb351.vitinfo2.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.karthikb351.vitinfo2.utility.Constants;

import co.uk.rushorm.core.RushCore;

public class ResetTask extends AsyncTask<Void, Void, Void> {

    private Context context;

    public ResetTask(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... params) {
        RushCore.getInstance().clearDatabase();
        return null;


    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.FILENAME_SHAREDPREFERENCES, Context.MODE_PRIVATE);
        sharedPreferences.edit()
                .clear()
                .apply();

    }
}