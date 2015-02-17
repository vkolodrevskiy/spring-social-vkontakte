package org.springframework.social.vkontakte.api.impl;

import java.util.Date;

/**
 * https://vk.com/dev/newsfeed.search request
 * @author dIsoVi
 */
public class NewsFeedSearchRequest {
    public String q;
    public Long count;
    public Double latitude;
    public Double longitude;
    public String startFrom;
    public Date startTime;
    public Date endTime;
    public boolean extended = false;
    public String fields;

    public NewsFeedSearchRequest(String q, long count, Double latitude, Double longitude, String startFrom, Date startTime, Date endTime, boolean extended, String fields) {
        this.q = q;
        this.count = count;
        this.latitude = latitude;
        this.longitude = longitude;
        this.startFrom = startFrom;
        this.startTime = startTime;
        this.endTime = endTime;
        this.extended = extended;
        this.fields = fields;
    }

    public NewsFeedSearchRequest(String q, boolean extended, String fields) {
        this.q = q;
        this.fields = fields;
        this.extended = extended;
    }

    public NewsFeedSearchRequest(String q) {
        this.q = q;
    }

    private NewsFeedSearchRequest() {
        // for jackson
    }
}
