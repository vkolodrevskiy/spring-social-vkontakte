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

import org.springframework.social.vkontakte.api.impl.CommentsRequest;

import java.util.List;

/**
 * Interface defining operations that can be performed on a VKontakte wall.
 *
 * @author vkolodrevskiy
 */
public interface IWallOperations {
    /**
     * Retrieve wall posts for the authenticated user.
     *
     * @return list of wall posts, up to 100 (VK default)
     */
    List<Post> getPosts();

    /**
     * Retrieve wall posts for the specified user.
     *
     * @param userId vkontakte user id
     * @param offset the offset to start retrieving posts.
     * @param limit  the maximum number of posts to return.
     * @return list of wall posts
     */
    List<Post> getPostsForUser(Long userId, int offset, int limit);

    /**
     * Retrieve wall posts for the authenticated user.
     *
     * @param offset the offset to start retrieving posts.
     * @param limit  the maximum number of posts to return.
     * @return list of wall posts
     */
    List<Post> getPosts(int offset, int limit);

    /**
     * Get current user's post by post_id.
     *
     * @param userId vkontakte user id
     * @param postId post_id
     * @return existing user's wall post
     * @throws org.springframework.social.vkontakte.api.VKontakteErrorException if VKontakte returned error.
     */
    Post getPost(Long userId, String postId);


    PostStatus post(PostData postData);

    /**
     * Returns a response that contains a list of comments on a post on a user wall or community wall.
     *
     * @param request {@link CommentsRequest}
     * @return a {@link CommentsResponse}, object representing the response on request of comments on a post on a user's or community's wall
     * @throws org.springframework.social.vkontakte.api.VKontakteErrorException if VKontakte returned error.
     */
    CommentsResponse getComments(CommentsRequest request);
}