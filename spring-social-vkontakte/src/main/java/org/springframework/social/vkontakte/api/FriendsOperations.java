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
 * Defines operations for interacting with a user's friends.
 * @author vkolodrevskiy
 */
public interface FriendsOperations {
    /**
     * Retrieves a list of user friends for the current authorized user.
     * @return a list of user friends profiles.
	 * @throws org.springframework.social.ApiException if there is an error while communicating with VKontakte.
	 * @throws org.springframework.social.MissingAuthorizationException if VKontakteTemplate was not created with an access token.
     * @throws org.springframework.social.vkontakte.api.VKontakteErrorException if VKontakte returned error.
     */
    public List<VKontakteProfile> get();

    /**
     * Retrieves a list of user friends for specified user unique identifier.
     * @param userId user unique identifier for which to get friends.
     * @return a list of user friends profiles.
	 * @throws org.springframework.social.ApiException if there is an error while communicating with VKontakte.
	 * @throws org.springframework.social.MissingAuthorizationException if VKontakteTemplate was not created with an access token.
     * @throws org.springframework.social.vkontakte.api.VKontakteErrorException if VKontakte returned error.
     */
    public List<VKontakteProfile> get(String userId);
}