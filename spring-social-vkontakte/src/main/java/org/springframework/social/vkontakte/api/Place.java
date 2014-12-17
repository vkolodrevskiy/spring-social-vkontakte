package org.springframework.social.vkontakte.api;

import java.util.Date;

public class Place {
    private long id;
    private String title;
    private double latitude;
    private double longitude;
    private Date createdDate;
    private String iconUrl;
    private String country;
    private String city;
    private String address;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }
}
