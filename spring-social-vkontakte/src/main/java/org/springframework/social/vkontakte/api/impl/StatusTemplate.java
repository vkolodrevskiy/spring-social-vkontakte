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
package org.springframework.social.vkontakte.api.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.social.vkontakte.api.StatusOperations;
import org.springframework.social.vkontakte.api.VKGenericResponse;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Properties;

/**
 * {@link StatusOperations} implementation.
 *
 * @author vkolodrevskiy
 */
public class StatusTemplate extends AbstractVKontakteOperations implements StatusOperations {
    private final RestTemplate restTemplate;

    public StatusTemplate(RestTemplate restTemplate, String accessToken, ObjectMapper objectMapper, boolean isAuthorizedForUser) {
        super(isAuthorizedForUser, accessToken, objectMapper);
        this.restTemplate = restTemplate;
    }

    @Override
    public String get(String uid) {
        return null;
    }

    @Override
    public void set(String status) {
        requireAuthorization();
        Properties props = new Properties();

        props.put("text", status);
        URI uri = makeOperationURL("status.set", props);

        VKGenericResponse response = restTemplate.getForObject(uri, VKGenericResponse.class);
        checkForError(response);
    }
}
