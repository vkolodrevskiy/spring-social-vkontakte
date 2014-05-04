/*
 * Copyright 2014 the original author or authors.
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
 * Defines operations for working with audios.
 *
 * @author vkolodrevskiy
 */
public interface IAudioOperations {
    /**
     * Returns a list of audio files for current user.
     * You need the following rights to call this method: audio.
     * See http://vk.com/dev/audio.get
     * @return a list of audio files.
     * @throws org.springframework.social.ApiException if there is an error while communicating with VKontakte.
     * @throws org.springframework.social.MissingAuthorizationException if VKontakteTemplate was not created with an access token.
     * @throws org.springframework.social.vkontakte.api.VKontakteErrorException if VKontakte returned error.
     */
    public List<Audio> get();

    /**
     * Returns a list of audio files of a user or community specified by ownerId.
     * You need the following rights to call this method: audio.
     * See http://vk.com/dev/audio.get
     * @param ownerId ID of the user or community that owns the audio file.
     *                Use a negative value to designate a community ID.
     * @return a {@code List} of audio files.
     * @throws org.springframework.social.ApiException if there is an error while communicating with VKontakte.
     * @throws org.springframework.social.MissingAuthorizationException if VKontakteTemplate was not created with an access token.
     * @throws org.springframework.social.vkontakte.api.VKontakteErrorException if VKontakte returned error.
     */
    public List<Audio> get(Integer ownerId);
}
