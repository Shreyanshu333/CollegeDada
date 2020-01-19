package com.example.collegedada1;

public class AttendanceInformation {

    public String TotalLectures;
    public String Present;
    public String Required;

    public AttendanceInformation(){

    }

    public AttendanceInformation(String totalLectures, String present, String required) {
        TotalLectures = totalLectures;
        Present = present;
        Required = required;
    }
}