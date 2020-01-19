package com.example.collegedada1;

public class UserInformation {

    public String Name;
    public String RollNo;
    public String PhoneNo;
    public String CPI;
    public String SPI;
    public String Semester;
    public String LinkedIn;


    public UserInformation(){

    }

    public UserInformation(String name, String rollNo, String phoneNo, String cpi, String spi,String semester,String linkedIn) {
        Name = name;
        RollNo = rollNo;
        PhoneNo = phoneNo;
        CPI = cpi;
        SPI =spi;
        Semester =semester;
        LinkedIn = linkedIn;
    }
}