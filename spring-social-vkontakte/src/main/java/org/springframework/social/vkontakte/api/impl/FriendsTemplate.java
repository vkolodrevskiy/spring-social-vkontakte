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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.social.vkontakte.api.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * {@link org.springframework.social.vkontakte.api.IFriendsOperations} implementation.
 * @author vkolodrevskiy
 */
class FriendsTemplate extends AbstractVKontakteOperations implements IFriendsOperations {
    private final RestTemplate restTemplate;

    public FriendsTemplate(RestTemplate restTemplate, String accessToken, ObjectMapper objectMapper, boolean isAuthorizedForUser) {
        super(isAuthorizedForUser, accessToken, objectMapper);
        this.restTemplate = restTemplate;
    }

    public List<VKontakteProfile> get(String fields) {
        return get(null, fields);
    }

    public List<VKontakteProfile> get() {
        return get(null, IFriendsOperations.DEFAULT_FIELDS);
    }

    public List<VKontakteProfile> get(Long userId) {
        return get(userId, IFriendsOperations.DEFAULT_FIELDS);
    }

    public List<VKontakteProfile> get(Long userId, String fields) {
        return get(userId, fields, -1, -1);
    }

    public List<VKontakteProfile> get(Long userId, String fields, int count, int offset) {
        requireAuthorization();
        Properties props = new Properties();

        if (userId != null) {
            props.put("user_id", userId);
        }
        if (count != -1) {
            props.put("count", count);
        }
        if (offset != -1) {
            props.put("offset", offset);
        }
        props.put("fields", fields);
        URI uri = makeOperationURL("friends.get", props, ApiVersion.VERSION_5_27);

        VKGenericResponse response = restTemplate.getForObject(uri, VKGenericResponse.class);
        checkForError(response);

        return deserializeVK50ItemsResponse(response, VKontakteProfile.class).getItems();
    }

    public List<List<String>> getOnline(boolean onlineMobile, int count, int offset) {
        return getOnlineFriends(null, onlineMobile, count, offset);
    }

    public List<List<String>> getOnline(Long userId, boolean onlineMobile, int count, int offset) {
        return getOnlineFriends(userId, onlineMobile, count, offset);
    }

    private List<List<String>> getOnlineFriends(Long userId, boolean onlineMobile, int count, int offset) {
        requireAuthorization();

        Properties props = new Properties();

        // User ID, positive number, current user id is used by default
        if (userId != null) {
            props.put("user_id", userId);
        }

        if (onlineMobile) {
            // to return an additional online_mobile field, get online mobile users
            props.put("online_mobile", "1");
        }

        // number of friends to return
        if (count > 0) {
            props.put("count", String.valueOf(count));
        }

        if (offset > 0) {
            props.put("offset", String.valueOf(offset));
        }

        // http://vk.com/dev/friends.getOnline
        URI uri = makeOperationURL("friends.getOnline", props, ApiVersion.VERSION_5_27);
        VKGenericResponse response = restTemplate.getForObject(uri, VKGenericResponse.class);
        checkForError(response);

        List<List<String>> result = new ArrayList<List<String>>();
        if (onlineMobile) {
            List<String> onlineFriends = new ArrayList<String>();
            List<String> mobileFriends = new ArrayList<String>();

            // get online if exists
            JsonNode onlineNode = response.getResponse().get("online");
            if (onlineNode != null && onlineNode.isArray()) {
                onlineFriends = Arrays.asList(objectMapper.convertValue(onlineNode, String[].class));
            }

            // get mobile online if exists
            JsonNode onlineMobileNode = response.getResponse().get("online_mobile");
            if (onlineMobileNode != null && onlineMobileNode.isArray()) {
                mobileFriends = Arrays.asList(objectMapper.convertValue(onlineMobileNode, String[].class));
            }

            result.add(onlineFriends);
            result.add(mobileFriends);
        } else {
            JsonNode onlineNode = response.getResponse();
            if (onlineNode != null && onlineNode.isArray()) {
                result.add(Arrays.asList(objectMapper.convertValue(onlineNode, String[].class)));
            }
        }

        return result;
    }
}
