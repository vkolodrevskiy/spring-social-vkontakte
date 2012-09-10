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

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.springframework.social.UncategorizedApiException;
import org.springframework.social.vkontakte.api.*;
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

    /**
     * Retrieves recent posts for the authenticated user.
     * Requires "read_stream" permission to read non-public posts.
     * Returns up to the most recent 25 posts.
     *
     * @return a list of {@link org.springframework.social.vkontakte.api.Post}s for the authenticated user.
     * @throws org.springframework.social.ApiException
     *          if there is an error while communicating with Facebook.
     * @throws org.springframework.social.MissingAuthorizationException
     *          if FacebookTemplate was not created with an access token.
     */
    @Override
    public List<NewsPost> getFeed() {
        return getFeed(null, 0, 25);
    }

    /**
     * Retrieves recent posts for the authenticated user.
     * Requires "read_stream" permission to read non-public posts.
     *
     *
     * @param offset the offset into the feed to start retrieving posts.
     * @param limit  the maximum number of posts to return.
     * @return a list of {@link org.springframework.social.vkontakte.api.Post}s for the authenticated user.
     * @throws org.springframework.social.ApiException
     *          if there is an error while communicating with Facebook.
     * @throws org.springframework.social.MissingAuthorizationException
     *          if FacebookTemplate was not created with an access token.
     */
    @Override
    public List<NewsPost> getFeed(int offset, int limit) {
        return getFeed(null, offset, limit);
    }

    /**
     * Retrieves recent feed entries for a given user.
     * Returns up to the most recent 25 posts.
     * Requires "read_stream" permission to read non-public posts.
     *
     *
     * @param ownerId VKontakte ID or alias for the owner (user, group, event, page, etc) of the feed.
     * @return a list of {@link org.springframework.social.vkontakte.api.Post}s for the specified user.
     * @throws org.springframework.social.ApiException
     *          if there is an error while communicating with Facebook.
     * @throws org.springframework.social.MissingAuthorizationException
     *          if FacebookTemplate was not created with an access token.
     */
    @Override
    public List<NewsPost> getFeed(String ownerId) {
        return getFeed(ownerId, 0, 25);
    }

    /**
     * Retrieves recent feed entries for a given user.
     * Requires "read_stream" permission to read non-public posts.
     * Returns up to the most recent 25 posts.
     *
     *
     * @param uid user id
     * @param offset  the offset into the feed to start retrieving posts.
     * @param limit   the maximum number of posts to return.
     * @return a list of {@link org.springframework.social.vkontakte.api.Post}s for the specified user.
     * @throws org.springframework.social.ApiException
     *          if there is an error while communicating with Facebook.
     * @throws org.springframework.social.MissingAuthorizationException
     *          if FacebookTemplate was not created with an access token.
     */
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
                posts.add(objectMapper.readValue(items.get(i), NewsPost.class));
            } catch (IOException e) {
                throw new UncategorizedApiException("Error deserializing: " + items.get(i), e);
            }
        }

        return posts;
    }

    /**
     * Searches the authenticated user's feed.
     * Returns up to 25 posts that match the query.
     *
     *
     * @param query the search query (e.g., "football")
     * @return a list of {@link org.springframework.social.vkontakte.api.Post}s that match the search query
     * @throws org.springframework.social.ApiException
     *          if there is an error while communicating with Facebook.
     * @throws org.springframework.social.MissingAuthorizationException
     *          if FacebookTemplate was not created with an access token.
     */
    @Override
    public List<Post> searchUserFeed(String query) {
        return searchUserFeed(query, 0, 25);
    }

    /**
     * Searches the authenticated user's feed.
     *
     *
     * @param query  the search query (e.g., "football")
     * @param offset the offset into the feed to start retrieving posts.
     * @param limit  the maximum number of posts to return.
     * @return a list of {@link org.springframework.social.vkontakte.api.Post}s that match the search query
     * @throws org.springframework.social.ApiException
     *          if there is an error while communicating with Facebook.
     * @throws org.springframework.social.MissingAuthorizationException
     *          if FacebookTemplate was not created with an access token.
     */
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
