package org.springframework.social.vkontakte.api.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.social.vkontakte.api.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
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

    public NewsFeedResponse searchNews(NewsFeedSearchRequest request) {
        requireAuthorization();
        Properties props = new Properties();
        props.put("q", request.q);
        if (request.count != null && request.count > 0) {
            props.put("count", request.count);
        }
        if (request.latitude != null && request.longitude != null) {
            props.put("latitude", request.latitude);
            props.put("longitude", request.longitude);
        }
        if (request.startTime != null) {
            props.put("start_time", request.startTime.getTime()/1000);
        }
        if (request.endTime != null) {
            props.put("end_time", request.endTime.getTime()/1000);
        }
        if (request.startFrom != null) {
            props.put("start_from", request.startFrom);
        }
        if (request.extended) {
            props.put("extended", 1);
            if (request.fields != null) {
                props.put("fields", request.fields);
            }
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
        List<VKontakteProfile> profiles = null;
        List<Group> groups = null;
        if (request.extended) {
            profiles = deserializeItems((ArrayNode) response.getResponse().get("profiles"), VKontakteProfile.class);
            groups = deserializeItems((ArrayNode) response.getResponse().get("groups"), Group.class);
        }
        long count = response.getResponse().get("count").asLong();
        long totalCount = response.getResponse().get("total_count").asLong();
        return new NewsFeedResponse(news, nextFrom, count, totalCount, groups, profiles);
    }
}
