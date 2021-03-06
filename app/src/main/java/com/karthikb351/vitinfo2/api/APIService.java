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

package com.karthikb351.vitinfo2.api;

import com.karthikb351.vitinfo2.contract.Friend;
import com.karthikb351.vitinfo2.contract.SpotlightMessage;
import com.karthikb351.vitinfo2.response.GradesResponse;
import com.karthikb351.vitinfo2.response.LoginResponse;
import com.karthikb351.vitinfo2.response.RefreshResponse;
import com.karthikb351.vitinfo2.response.SpotlightResponse;
import com.karthikb351.vitinfo2.response.SystemResponse;
import com.karthikb351.vitinfo2.response.TokenResponse;

import retrofit.Call;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface APIService {

    @GET("/api/v2/system")
    Call<SystemResponse> system();

    @GET("/api/v2/{campus}/spotlight")
    Call<SpotlightResponse> spotlight(@Path("campus") String campus);

    @FormUrlEncoded
    @POST("/api/v2/{campus}/login")
    Call<LoginResponse> login(@Path("campus") String campus, @Field("regno") String regno, @Field("dob") String dob, @Field("mobile") String mobile);

    @FormUrlEncoded
    @POST("/api/v2/{campus}/refresh")
    Call<RefreshResponse> refresh(@Path("campus") String campus, @Field("regno") String regno, @Field("dob") String dob, @Field("mobile") String mobile);

    @FormUrlEncoded
    @POST("/api/v2/{campus}/token")
    Call<TokenResponse> token(@Path("campus") String campus, @Field("regno") String regno, @Field("dob") String dob, @Field("mobile") String mobile);

    @FormUrlEncoded
    @POST("/api/v2/{campus}/grades")
    Call<GradesResponse> grades(@Path("campus") String campus, @Field("regno") String regno, @Field("dob") String dob, @Field("mobile") String mobile);

    @FormUrlEncoded
    @POST("/api/v2/{campus}/share")
    Call<Friend> share(@Path("campus") String campus, @Field("token") String token, @Field("receiver") String receiver);

    @FormUrlEncoded
    @POST("/api/v2/{campus}/share")
    Call<Friend> share(@Path("campus") String campus, @Field("regno") String regno, @Field("dob") String dob, @Field("mobile") String mobile, @Field("receiver") String receiver);
}
