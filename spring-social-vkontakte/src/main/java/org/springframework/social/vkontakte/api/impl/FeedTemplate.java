/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.vkontakte.api.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.social.UncategorizedApiException;
import org.springframework.social.vkontakte.api.FeedOperations;
import org.springframework.social.vkontakte.api.NewsPost;
import org.springframework.social.vkontakte.api.Post;
import org.springframework.social.vkontakte.api.VKGenericResponse;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Implements VK's {@link FeedOperations}.
 */
public class FeedTemplate extends AbstractVKontakteOperations implements FeedOperations {

    private final RestTemplate restTemplate;

    public FeedTemplate(RestTemplate restTemplate, String accessToken, ObjectMapper objectMapper, boolean isAuthorizedForUser) {
        super(isAuthorizedForUser, accessToken, objectMapper);
        this.restTemplate = restTemplate;
    }

    @Override
    public List<NewsPost> getFeed() {
        return getFeed(null, 0, 25);
    }

    @Override
    public List<NewsPost> getFeed(int offset, int limit) {
        return getFeed(null, offset, limit);
    }

    @Override
    public List<NewsPost> getFeed(String ownerId) {
        return getFeed(ownerId, 0, 25);
    }

    @Override
    public List<NewsPost> getFeed(String uid, int offset, int limit) {
        requireAuthorization();
        Properties props = new Properties();
        if (uid != null) {
            props.put("source_ids", uid);
        }
        props.put("offset", offset);
        props.put("count", limit);
        URI uri = makeOperationURL("newsfeed.get", props);

        VKGenericResponse response = restTemplate.getForObject(uri, VKGenericResponse.class);
        checkForError(response);

        Assert.isTrue(response.getResponse().isObject());
        ArrayNode items = (ArrayNode) response.getResponse().get("items");
        List<NewsPost> posts = new ArrayList<NewsPost>();
        for (int i = 0; i < items.size(); i++) {
            try {
                posts.add(objectMapper.readValue(items.get(i).asText(), NewsPost.class));
            } catch (IOException e) {
                throw new UncategorizedApiException("vkontakte", "Error deserializing: " + items.get(i), e);
            }
        }

        return posts;
    }

    @Override
    public List<Post> searchUserFeed(String query) {
        return searchUserFeed(query, 0, 25);
    }

    @Override
    public List<Post> searchUserFeed(String query, int offset, int limit) {
        Properties props = new Properties();
        props.put("q", query);
        props.put("count", limit);
        props.put("offset", offset);
        props.put("count", limit);
        URI uri = makeOperationURL("newsfeed.search", props);
        VKGenericResponse response = restTemplate.getForObject(uri, VKGenericResponse.class);
        checkForError(response);
        return deserializeArray(response, Post.class).getItems();
    }
}
