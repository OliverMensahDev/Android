package com.example.olivermensah.synccases.custom;

/**
 * Created by olivermensah on 12/4/17.
 */

public class Report  extends User{
    private String type;
    private String longitude;
    private String latitude;
    private String city;
    private String time;
    private String title;
    private String description;
    private String urlToUploadedFile;

    public Report(String type, String longitude, String latitude, String city, String time, String title, String description, String urlToUploadedFile) {
        this.type = type;
        this.longitude = longitude;
        this.latitude = latitude;
        this.city = city;
        this.time = time;
        this.title = title;
        this.description = description;
        this.urlToUploadedFile = urlToUploadedFile;
    }
    public Report(){}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlToUploadedFile() {
        return urlToUploadedFile;
    }

    public void setUrlToUploadedFile(String urlToUploadedFile) {
        this.urlToUploadedFile = urlToUploadedFile;
    }

    @Override
    public String toString() {
        return "Report{" +
                "type='" + type + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", city='" + city + '\'' +
                ", time='" + time + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", urlToUploadedFile='" + urlToUploadedFile + '\'' +
                '}';
    }
}
