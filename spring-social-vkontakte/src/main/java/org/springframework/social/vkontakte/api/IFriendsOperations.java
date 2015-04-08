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
public interface IFriendsOperations {
    public final static String DEFAULT_FIELDS = "first_name,last_name,photo_50,photo_100,photo_200,contacts,bdate,sex,screen_name";
    /**
     * Retrieves a list of user friends for the current authorized user.
     * @return a list of user friends profiles.
	 * @throws org.springframework.social.ApiException if there is an error while communicating with VKontakte.
	 * @throws org.springframework.social.MissingAuthorizationException if VKontakteTemplate was not created with an access token.
     * @throws org.springframework.social.vkontakte.api.VKontakteErrorException if VKontakte returned error.
     */
    public List<VKontakteProfile> get();

    /**
     * Retrieves a list of user friends for the current authorized user.
     * @param fields VKontakte fields to retrieve, comma-delimited.
     * @return a list of user friends profiles.
     * @throws org.springframework.social.ApiException if there is an error while communicating with VKontakte.
     * @throws org.springframework.social.MissingAuthorizationException if VKontakteTemplate was not created with an access token.
     * @throws org.springframework.social.vkontakte.api.VKontakteErrorException if VKontakte returned error.
     */
    public List<VKontakteProfile> get(String fields);

    /**
     * Retrieves a list of user friends for specified user unique identifier.
     * @param userId user unique identifier for which to get friends.
     * @return a list of user friends profiles.
	 * @throws org.springframework.social.ApiException if there is an error while communicating with VKontakte.
	 * @throws org.springframework.social.MissingAuthorizationException if VKontakteTemplate was not created with an access token.
     * @throws org.springframework.social.vkontakte.api.VKontakteErrorException if VKontakte returned error.
     */
    public List<VKontakteProfile> get(Long userId);

    /**
     * Retrieves a list of user friends for specified user unique identifier.
     * @param userId user unique identifier for which to get friends.
     * @param fields VKontakte fields to retrieve, comma-delimited.
     * @return a list of user friends profiles.
     * @throws org.springframework.social.ApiException if there is an error while communicating with VKontakte.
     * @throws org.springframework.social.MissingAuthorizationException if VKontakteTemplate was not created with an access token.
     * @throws org.springframework.social.vkontakte.api.VKontakteErrorException if VKontakte returned error.
     */
    public List<VKontakteProfile> get(Long userId, String fields);

    /**
     * Retrieves a list of user friends for specified user unique identifier.
     * @param userId user unique identifier for which to get friends.
     * @param fields VKontakte fields to retrieve, comma-delimited.
     * @param count Number(positive number) of friends to return. If you want to return all friends pass negative number.
     * @param offset Offset(positive number) needed to return a specific subset of friends.
     * @return a list of user friends profiles.
     * @throws org.springframework.social.ApiException if there is an error while communicating with VKontakte.
     * @throws org.springframework.social.MissingAuthorizationException if VKontakteTemplate was not created with an access token.
     * @throws org.springframework.social.vkontakte.api.VKontakteErrorException if VKontakte returned error.
     */
    public List<VKontakteProfile> get(Long userId, String fields, int count, int offset);

    /**
     * Retrieves a list of user friends id's that are online for the current authorized user.
     * Result <code>List</code> will contain 2 lists if you set <code>onlineMobile</code> parameter to <code>true</code>.
     * First list will contain id's of online friends that use website, second list will contain id's of friends using mobile.
     * @param onlineMobile <code>true</code> to return an additional online_mobile field, <code>false</code> otherwise.
     * @param count Number(positive number) of friends to return. If you want to return all friends pass negative number.
     * @param offset Offset(positive number) needed to return a specific subset of friends.
     * @return a list of user online friends profiles.
     * @throws org.springframework.social.ApiException if there is an error while communicating with VKontakte.
     * @throws org.springframework.social.MissingAuthorizationException if VKontakteTemplate was not created with an access token.
     * @throws org.springframework.social.vkontakte.api.VKontakteErrorException if VKontakte returned error.
     */
    public List<List<String>> getOnline(boolean onlineMobile, int count, int offset);

    /**
     * Retrieves a list of user friends that are online for specified user unique identifier.
     * Result <code>List</code> will contain 2 lists if you set <code>onlineMobile</code> parameter to <code>true</code>.
     * First list will contain id's of online friends that use website, second list will contain id's of friends using mobile.
     * @param userId user unique identifier for which to get friends.
     * @param onlineMobile <code>true</code> to return an additional online_mobile field, <code>false</code> otherwise.
     * @param count Number(positive number) of friends to return. If you want to return all friends pass negative number.
     * @param offset Offset(positive number) needed to return a specific subset of friends.
     * @return a list of user online friends profiles.
     * @throws org.springframework.social.ApiException if there is an error while communicating with VKontakte.
     * @throws org.springframework.social.MissingAuthorizationException if VKontakteTemplate was not created with an access token.
     * @throws org.springframework.social.vkontakte.api.VKontakteErrorException if VKontakte returned error.
     */
    public List<List<String>> getOnline(Long userId, boolean onlineMobile, int count, int offset);
}