/*
 * Copyright 2011 the original author or authors.
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
package org.springframework.social.vkontakte.api;

import java.util.List;

/**
 * News Feed functions, modelled after Facebook's API.
 * @author nekoval@yandex.ru
 */
public interface FeedOperations {

    /**
     * Retrieves recent posts for the authenticated user.
     * Requires "read_stream" permission to read non-public posts.
     * Returns up to the most recent 25 posts.
     *
     * @return a list of {@link Post}s for the authenticated user.
     * @throws org.springframework.social.ApiException if there is an error while communicating with Facebook.
     * @throws org.springframework.social.MissingAuthorizationException if VKontakteTemplate was not created with an access token.
     */
    List<NewsPost> getFeed();

    /**
     * Retrieves recent posts for the authenticated user.
     * Requires "read_stream" permission to read non-public posts.
     *
     * @param offset the offset into the feed to start retrieving posts.
     * @param limit the maximum number of posts to return.
     * @return a list of {@link Post}s for the authenticated user.
     * @throws org.springframework.social.ApiException if there is an error while communicating with Facebook.
     * @throws org.springframework.social.MissingAuthorizationException if VKontakteTemplate was not created with an access token.
     */
    List<NewsPost> getFeed(int offset, int limit);

    /**
     * Retrieves recent feed entries for a given user.
     * Returns up to the most recent 25 posts.
     * Requires "read_stream" permission to read non-public posts.
     *
     * @param ownerId the Facebook ID or alias for the owner (user, group, event, page, etc) of the feed.
     * @return a list of {@link Post}s for the specified user.
     * @throws org.springframework.social.ApiException if there is an error while communicating with Facebook.
     * @throws org.springframework.social.MissingAuthorizationException if VKontakteTemplate was not created with an access token.
     */
    List<NewsPost> getFeed(String ownerId);

    /**
     * Retrieves recent feed entries for a given user.
     * Requires "read_stream" permission to read non-public posts.
     * Returns up to the most recent 25 posts.
     *
     * @param ownerId the Facebook ID or alias for the owner (user, group, event, page, etc) of the feed.
     * @param offset the offset into the feed to start retrieving posts.
     * @param limit the maximum number of posts to return.
     * @return a list of {@link Post}s for the specified user.
     * @throws org.springframework.social.ApiException if there is an error while communicating with Facebook.
     * @throws org.springframework.social.MissingAuthorizationException if VKontakteTemplate was not created with an access token.
     */
    List<NewsPost> getFeed(String ownerId, int offset, int limit);

    /**
     * Searches the authenticated user's feed.
     * Returns up to 25 posts that match the query.
     *
     * @param query the search query (e.g., "football")
     * @return a list of {@link Post}s that match the search query
     * @throws org.springframework.social.ApiException if there is an error while communicating with Facebook.
     * @throws org.springframework.social.MissingAuthorizationException if VKontakteTemplate was not created with an access token.
     */
    List<Post> searchUserFeed(String query);

    /**
     * Searches the authenticated user's feed.
     *
     * @param query the search query (e.g., "football")
     * @param offset the offset into the feed to start retrieving posts.
     * @param limit the maximum number of posts to return.
     * @return a list of {@link Post}s that match the search query
     * @throws org.springframework.social.ApiException if there is an error while communicating with Facebook.
     * @throws org.springframework.social.MissingAuthorizationException if VKontakteTemplate was not created with an access token.
     */
    List<Post> searchUserFeed(String query, int offset, int limit);
}
