/*
 * Copyright 2011-2014 the original author or authors.
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
import org.springframework.social.vkontakte.api.ISecureOperations;
import org.springframework.social.vkontakte.api.VKGenericResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collection;
import java.util.Properties;
/**
 * {@link org.springframework.social.vkontakte.api.ISecureOperations} implementation.
 * @author bsideup
 */
public class SecureTemplate extends AbstractVKontakteSecureOperations implements ISecureOperations {

    protected RestTemplate restTemplate;

    public SecureTemplate(RestTemplate restTemplate, String accessToken, ObjectMapper objectMapper, boolean isAuthorizedForUser, String clientSecret) {
        super(isAuthorizedForUser, accessToken, objectMapper, clientSecret);
        this.restTemplate = restTemplate;
    }

    public void sendNotification(Collection<String> userIds, String message) {
        requireAuthorization();
        Properties props = new Properties();
        props.put("user_ids", StringUtils.collectionToCommaDelimitedString(userIds));
        props.put("message", message);

        // see documentation under http://vk.com/dev/secure.sendNotification
        URI uri = makeOperationURL("secure.sendNotification", props, ApiVersion.VERSION_5_8);

        VKGenericResponse response = restTemplate.getForObject(uri, VKGenericResponse.class);
        checkForError(response);
    }
}
