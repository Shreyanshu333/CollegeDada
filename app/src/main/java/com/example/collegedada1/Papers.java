package com.example.collegedada1;

public class Papers {
    private String PaperName,DownloadLink;

    public Papers() {
        //empty constructor needed
    }

    public Papers(String Name, String DownloadLink) {
        this.PaperName = Name;
        this.DownloadLink = DownloadLink;
    }

    public String getPaperName() {
        return PaperName;
    }
    public String getDownloadLink() {
        return DownloadLink;
    }

}