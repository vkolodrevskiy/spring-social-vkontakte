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
package org.springframework.social.vkontakte.api.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.social.vkontakte.api.ApiVersion;
import org.springframework.social.vkontakte.api.FriendsOperations;
import org.springframework.social.vkontakte.api.VKGenericResponse;
import org.springframework.social.vkontakte.api.VKontakteProfile;
import org.springframework.social.vkontakte.api.VKontakteProfiles;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * {@link org.springframework.social.vkontakte.api.FriendsOperations} implementation.
 * @author vkolodrevskiy
 */
class FriendsTemplate extends AbstractVKontakteOperations implements FriendsOperations {
    private final RestTemplate restTemplate;

    public FriendsTemplate(RestTemplate restTemplate, String accessToken, ObjectMapper objectMapper, boolean isAuthorizedForUser) {
        super(isAuthorizedForUser, accessToken, objectMapper);
        this.restTemplate = restTemplate;
    }

    @Override
    public List<VKontakteProfile> get() {
        requireAuthorization();
        Properties props = new Properties();

        props.put("fields", "uid,first_name,last_name,photo,photo_medium,photo_big,contacts,bdate,sex,screen_name");

        // http://vk.com/dev/friends.get
        URI uri = makeOperationURL("friends.get", props, ApiVersion.VERSION_3_0);

        VKontakteProfiles profiles = restTemplate.getForObject(uri, VKontakteProfiles.class);
        checkForError(profiles);

        return profiles.getProfiles();
    }

    @Override
    public List<VKontakteProfile> get(String userId) {
        requireAuthorization();
        Properties props = new Properties();

        props.put("uid", userId.trim());
        props.put("fields", "uid,first_name,last_name,photo,photo_medium,photo_big,contacts,bdate,sex,screen_name");
        URI uri = makeOperationURL("friends.get", props, ApiVersion.VERSION_3_0);

        VKontakteProfiles profiles = restTemplate.getForObject(uri, VKontakteProfiles.class);
        checkForError(profiles);

        return profiles.getProfiles();
    }

    @Override
    public List<List<String>> getOnline(boolean onlineMobile, int count, int offset) {
        return getOnlineFriends(null, onlineMobile, count, offset);
    }

    @Override
    public List<List<String>> getOnline(String userId, boolean onlineMobile, int count, int offset) {
        return getOnlineFriends(userId, onlineMobile, count, offset);
    }

    private List<List<String>> getOnlineFriends(String userId, boolean onlineMobile, int count, int offset) {
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
            props.put("count", String.valueOf(count));
        }

        // http://vk.com/dev/friends.getOnlineю.
        URI uri = makeOperationURL("friends.getOnline", props, ApiVersion.VERSION_5_8);
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
