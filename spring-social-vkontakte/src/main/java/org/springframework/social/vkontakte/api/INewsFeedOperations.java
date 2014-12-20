package org.springframework.social.vkontakte.api;

import java.util.Date;

/**
 * Defines operations for interacting with a user's newsfeed.
 * @author dIsoVi
 */
public interface INewsFeedOperations {
    NewsFeedResponse searchNews(String q);
    NewsFeedResponse searchNews(String q, long count, Double latitude, Double longitude, Date startTime, Date endTime, String startFrom);
}
