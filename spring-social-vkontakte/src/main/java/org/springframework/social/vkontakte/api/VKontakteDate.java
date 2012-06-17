package org.springframework.social.vkontakte.api;

import java.io.Serializable;

public class VKontakteDate implements Serializable {

    private static final long serialVersionUID = 1L;

    private final int year;

    private final int month;

    private final int day;

    public VKontakteDate(int day, int month, int year) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

}
