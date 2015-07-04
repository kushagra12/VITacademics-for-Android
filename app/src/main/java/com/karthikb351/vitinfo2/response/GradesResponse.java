/*
 * VITacademics
 * Copyright (C) 2015  Aneesh Neelam <neelam.aneesh@gmail.com>
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

package com.karthikb351.vitinfo2.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.karthikb351.vitinfo2.contract.Grade;
import com.karthikb351.vitinfo2.contract.GradeCount;
import com.karthikb351.vitinfo2.contract.SemesterWiseGrade;
import com.karthikb351.vitinfo2.model.Status;

public class GradesResponse {

    @Expose
    @SerializedName("reg_no")
    private String registerNumber;

    @Expose
    @SerializedName("dob")
    private String dateOfBirth;

    @Expose
    @SerializedName("mobile")
    private String mobileNumber;

    @Expose
    @SerializedName("campus")
    private String campus;

    @Expose
    @SerializedName("status")
    private Status status;

    @Expose
    @SerializedName("grades_refreshed")
    private String refreshed;

    @Expose
    @SerializedName("cached")
    private boolean cached;

    @Expose
    @SerializedName("grades")
    private Grade[] grades;

    @Expose
    @SerializedName("semester_wise")
    private SemesterWiseGrade[] semesterWiseGrades;

    @Expose
    @SerializedName("cgpa")
    private double cgpa;

    @Expose
    @SerializedName("grade_count")
    private GradeCount[] gradeCount;

    @Expose
    @SerializedName("credits_registered")
    private int creditsRegistered;

    @Expose
    @SerializedName("credits_earned")
    private int creditsEarned;

    public GradesResponse() {
    }

    public GradesResponse(String registerNumber, String dateOfBirth, String mobileNumber, String campus, Status status, String refreshed, boolean cached, Grade[] grades, SemesterWiseGrade[] semesterWiseGrades, double cgpa, GradeCount[] gradeCount, int creditsRegistered, int creditsEarned) {
        this.registerNumber = registerNumber;
        this.dateOfBirth = dateOfBirth;
        this.mobileNumber = mobileNumber;
        this.campus = campus;
        this.status = status;
        this.refreshed = refreshed;
        this.cached = cached;
        this.grades = grades;
        this.semesterWiseGrades = semesterWiseGrades;
        this.cgpa = cgpa;
        this.gradeCount = gradeCount;
        this.creditsRegistered = creditsRegistered;
        this.creditsEarned = creditsEarned;
    }

    public String getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getRefreshed() {
        return refreshed;
    }

    public void setRefreshed(String refreshed) {
        this.refreshed = refreshed;
    }

    public boolean isCached() {
        return cached;
    }

    public void setCached(boolean cached) {
        this.cached = cached;
    }

    public Grade[] getGrades() {
        return grades;
    }

    public void setGrades(Grade[] grades) {
        this.grades = grades;
    }

    public SemesterWiseGrade[] getSemesterWiseGrades() {
        return semesterWiseGrades;
    }

    public void setSemesterWiseGrades(SemesterWiseGrade[] semesterWiseGrades) {
        this.semesterWiseGrades = semesterWiseGrades;
    }

    public double getCgpa() {
        return cgpa;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }

    public GradeCount[] getGradeCount() {
        return gradeCount;
    }

    public void setGradeCount(GradeCount[] gradeCount) {
        this.gradeCount = gradeCount;
    }

    public int getCreditsRegistered() {
        return creditsRegistered;
    }

    public void setCreditsRegistered(int creditsRegistered) {
        this.creditsRegistered = creditsRegistered;
    }

    public int getCreditsEarned() {
        return creditsEarned;
    }

    public void setCreditsEarned(int creditsEarned) {
        this.creditsEarned = creditsEarned;
    }
}