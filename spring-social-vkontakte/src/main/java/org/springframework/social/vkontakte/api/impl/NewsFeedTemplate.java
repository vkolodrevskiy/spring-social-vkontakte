package org.springframework.social.vkontakte.api.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.social.vkontakte.api.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * {@link org.springframework.social.vkontakte.api.INewsFeedOperations} implementation.
 * @author dIsoVi
 */
public class NewsFeedTemplate extends AbstractVKontakteOperations implements INewsFeedOperations {
    private final RestTemplate restTemplate;

    public NewsFeedTemplate(RestTemplate restTemplate, String accessToken, ObjectMapper objectMapper, boolean isAuthorizedForUser) {
        super(isAuthorizedForUser, accessToken, objectMapper);
        this.restTemplate = restTemplate;
    }

    public NewsFeedResponse searchNews(String q) {
        return searchNews(q, 0, null, null, null, null, null);
    }

    public NewsFeedResponse searchNews(String q, long count, Double latitude, Double longitude, Date startTime, Date endTime, String startFrom) {
        requireAuthorization();
        Properties props = new Properties();
        props.put("q", q);
        if (count > 0) {
            props.put("count", count);
        }
        if (latitude != null && longitude != null) {
            props.put("latitude", latitude);
            props.put("longitude", longitude);
        }
        if (startTime != null) {
            props.put("start_time", startTime.getTime()/1000);
        }
        if (endTime != null) {
            props.put("end_time", endTime.getTime()/1000);
        }
        if (startFrom != null) {
            props.put("start_from", startFrom);
        }
        // https://vk.com/dev/newsfeed.search
        URI uri = makeOperationURL("newsfeed.search", props, ApiVersion.VERSION_5_27);
        VKGenericResponse response = restTemplate.getForObject(uri, VKGenericResponse.class);
        checkForError(response);
        List<Post> news = deserializeVK50ItemsResponse(response, Post.class).getItems();
        String nextFrom = null;
        if (response.getResponse().has("next_from")) {
            nextFrom = response.getResponse().get("next_from").textValue();
        }
        count = response.getResponse().get("count").asLong();
        long totalCount = response.getResponse().get("total_count").asLong();
        return new NewsFeedResponse(news, nextFrom, count, totalCount);
    }
}
