package com.example.collegedada1;

public class Course {
    private String CourseName,DownloadLink;

    public Course() {
        //empty constructor needed
    }

    public Course(String Name, String DownloadLink) {
        this.CourseName = Name;
        this.DownloadLink = DownloadLink;
    }

    public String getCourseName() {
        return CourseName;
    }
    public String getDownloadLink() {
        return DownloadLink;
    }

}