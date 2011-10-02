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
 * User operations.
 * @author vkolodrevskiy
 */
public interface UsersOperations {
	/**
	 * Retrieves the profile for the authenticated user.
	 * @return the user's profile information.
	 * @throws org.springframework.social.ApiException if there is an error while communicating with VKontakte.
	 * @throws org.springframework.social.MissingAuthorizationException if VKontakteTemplate was not created with an access token.
     * @throws org.springframework.social.vkontakte.api.VKontakteErrorException if VKontakte returned error.
	 */
	VKontakteProfile getProfile();

	/**
	 * Retrieves profiles for specified user unique identifiers.
	 * @param userIds VKontakte user profile unique identifiers, for which to gt data.
     * If <code>null<code/> is passed user profile or the current user will be returned.
	 * @return the user's profile information.
	 * @throws org.springframework.social.ApiException if there is an error while communicating with VKontakte.
	 * @throws org.springframework.social.MissingAuthorizationException if VKontakteTemplate was not created with an access token.
     * @throws org.springframework.social.vkontakte.api.VKontakteErrorException if VKontakte returned error.
	 */
	List<VKontakteProfile> getProfiles(List<String> userIds);
}