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
package org.springframework.social.vkontakte.api.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.social.vkontakte.api.ApiVersion;
import org.springframework.social.vkontakte.api.Audio;
import org.springframework.social.vkontakte.api.IAudioOperations;
import org.springframework.social.vkontakte.api.VKGenericResponse;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.Properties;

/**
 * {@link org.springframework.social.vkontakte.api.IAudioOperations} implementation.
 * @author vkolodrevskiy
 */
public class AudioTemplate extends AbstractVKontakteOperations implements IAudioOperations {
    private final RestTemplate restTemplate;

    public AudioTemplate(RestTemplate restTemplate, String accessToken, ObjectMapper objectMapper,
                         boolean isAuthorizedForUser) {
        super(isAuthorizedForUser, accessToken, objectMapper);
        this.restTemplate = restTemplate;
    }

    public List<Audio> get() {
        requireAuthorization();
        Properties props = new Properties();

        // right now decided for simplicity
        // not to get user information(need_user=0)
        // coz having ownerId in response you can get this information
        // also when you specify ownerId as negative(for communities)
        // response does not contain user information
        props.put("need_user", "0");

        // http://vk.com/dev/wall.get
        URI uri = makeOperationURL("audio.get", props, ApiVersion.VERSION_5_21);
        VKGenericResponse response = restTemplate.getForObject(uri, VKGenericResponse.class);
        checkForError(response);
        return deserializeArray(response, Audio.class).getItems();
    }

    public List<Audio> get(Integer ownerId) {
        requireAuthorization();
        Properties props = new Properties();
        props.put("need_user", "0");
        props.put("owner_id", String.valueOf(ownerId));

        // http://vk.com/dev/wall.get
        URI uri = makeOperationURL("audio.get", props, ApiVersion.VERSION_5_21);
        VKGenericResponse response = restTemplate.getForObject(uri, VKGenericResponse.class);
        checkForError(response);
        return deserializeArray(response, Audio.class).getItems();
    }
}
