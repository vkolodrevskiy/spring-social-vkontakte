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
import org.springframework.social.UncategorizedApiException;
import org.springframework.social.vkontakte.api.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Properties;

/**
 * {@link org.springframework.social.vkontakte.api.IGroupsOperations} implementation.
 * @author dIsoVi
 */
public class GroupsTemplate extends AbstractVKontakteOperations implements IGroupsOperations {
    private final RestTemplate restTemplate;

    public GroupsTemplate(RestTemplate restTemplate, String accessToken, ObjectMapper objectMapper, boolean isAuthorizedForUser) {
        super(isAuthorizedForUser, accessToken, objectMapper);
        this.restTemplate = restTemplate;
    }

    public List<Group> getGroups() {
        return getGroups(0, null, -1, 0, true);
    }

    public List<Group> getGroups(long offset, long count) {
        return getGroups(0, null, offset, count, true);
    }

    public List<Group> getGroups(long userId) {
        return getGroups(userId, null, -1, 0, true);
    }

    public List<Group> getGroups(long userId, long offset, long count) {
        return getGroups(userId, null, offset, count, true);
    }

    public List<Group> getGroups(long userId, String filter, long offset, long count, boolean extended) {
        requireAuthorization();
        Properties props = new Properties();
        if (offset >= 0) {
            props.put("offset", offset);
        }
        if (count > 0) {
            props.put("count", count);
        }
        if (userId > 0) {
            props.put("user_id", userId);
        }
        if (filter != null) {
            props.put("filter", filter);
        }
        if (extended) {
            props.put("extended", 1);
        }
        // https://vk.com/dev/groups.get
        URI uri = makeOperationURL("groups.get", props, ApiVersion.VERSION_5_27);
        VKGenericResponse response = restTemplate.getForObject(uri, VKGenericResponse.class);
        checkForError(response);
        return deserializeVK50ItemsResponse(response, Group.class).getItems();
    }
}
