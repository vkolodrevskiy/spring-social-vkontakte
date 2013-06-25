/*
 * Copyright 2013 the original author or authors.
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

/**
 * Status operations.
 *
 * @author vkolodrevskiy
 * @see <a href="http://vk.com/dev/status.get">status.get</a>
 * @see <a href="http://vk.com/dev/status.set">status.set</a>
 */
public interface StatusOperations {
    /**
     * Get status.
     * @param uid ID of the user or community, status information of which shall be received. Current user id is used by default
     * @return status as String.
     * @throws org.springframework.social.ApiException if there is an error while communicating with VKontakte.
     * @throws org.springframework.social.MissingAuthorizationException if VKontakteTemplate was not created with an access token.
     * @throws org.springframework.social.vkontakte.api.VKontakteErrorException if VKontakte returned error.
     */
    String get(String uid);

    /**
     * Sets a new status for the current user.
     * @param status string to be set as status.
     * @throws org.springframework.social.ApiException if there is an error while communicating with VKontakte.
     * @throws org.springframework.social.MissingAuthorizationException if VKontakteTemplate was not created with an access token.
     * @throws org.springframework.social.vkontakte.api.VKontakteErrorException if VKontakte returned error.
     */
    void set(String status);
}
