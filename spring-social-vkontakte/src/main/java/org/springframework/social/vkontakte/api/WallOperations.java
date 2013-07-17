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
import java.util.Properties;

/**
 * Interface defining operations that can be performed on a VKontakte wall.
 * @author vkolodrevskiy
 */
public interface WallOperations {

    /**
   	 * Posts a status update to the authenticated user's wall.
   	 * Requires "wall" permission.
   	 * @param message the message to post.
     * @param params other parameters to post ("owner_id", "friends_only", "from_group", etc)
   	 * @return the ID of the new wall entry.
   	 * @throws org.springframework.social.ApiException if there is an error while communicating with VKontakte.
   	 * @throws org.springframework.social.MissingAuthorizationException if VKontakteTemplate was not created with an access token.
     * @throws org.springframework.social.vkontakte.api.VKontakteErrorException if VKontakte returned error.
   	 */
    public String post(String message, Properties params);

	/**
	 * Posts a status update to the authenticated user's wall.
	 * Requires "wall" permission.
	 * @param message the message to post.
	 * @return the ID of the new wall entry.
	 * @throws org.springframework.social.ApiException if there is an error while communicating with VKontakte.
	 * @throws org.springframework.social.MissingAuthorizationException if VKontakteTemplate was not created with an access token.
     * @throws org.springframework.social.vkontakte.api.VKontakteErrorException if VKontakte returned error.
	 */
	String post(String message);


    /**
   	 * Posts a status update with an external link to the authenticated user's wall.
   	 * Requires "wall" permission.
   	 * @param message the message to post.
     * @param link external link to be shared on user's wall
   	 * @return the ID of the new wall entry.
   	 * @throws org.springframework.social.ApiException if there is an error while communicating with VKontakte.
   	 * @throws org.springframework.social.MissingAuthorizationException if VKontakteTemplate was not created with an access token.
     * @throws org.springframework.social.vkontakte.api.VKontakteErrorException if VKontakte returned error.
   	 */
   	String post(String message, String link);


    /**
     * Retrieve wall posts for the authenticated user.
     * @return list of wall posts, up to 100 (VK default)
     */
    List<Post> getPosts();

    /**
     * Retrieve wall posts for the authenticated user.
     * @param offset the offset to start retrieving posts.
   	 * @param limit the maximum number of posts to return.
     * @return list of wall posts
     */
    List<Post> getPosts(int offset, int limit);

    /**
     * Get current user's post by post_id.
     * @param userId vkontakte user id
     * @param postId post_id
     * @return existing user's wall post
     * @throws org.springframework.social.vkontakte.api.VKontakteErrorException if VKontakte returned error.
     */
    Post getPost(String userId, String postId);
}