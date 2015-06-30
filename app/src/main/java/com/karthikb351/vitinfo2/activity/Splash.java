/*
 * VITacademics
 * Copyright (C) 2015  Gaurav Agerwala <gauravagerwala@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.karthikb351.vitinfo2.activity;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;

import com.karthikb351.vitinfo2.R;

public class Splash extends Activity {

    SoundPool sp;
    int sound=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        sp=new SoundPool(5, AudioManager.STREAM_MUSIC,00);
        //sound==sp.load(this,R.raw.something);
        Thread timer= new Thread(){
            public void run(){
                try{
                    sleep(5000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent openDa = new Intent("com.karthikb351.vitinfo2.activity.LoginActivity");
                    startActivity(openDa);
                }
            }
        };
        timer.start();
    }

    protected void onPause(){
        super.onPause();
        sp.release();
        finish();
    }
}