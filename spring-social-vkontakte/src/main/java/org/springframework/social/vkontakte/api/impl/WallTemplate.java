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

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.social.UncategorizedApiException;
import org.springframework.social.vkontakte.api.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Properties;

/**
 * {@link org.springframework.social.vkontakte.api.WallOperations} implementation.
 * @author vkolodrevskiy
 */
public class WallTemplate extends AbstractVKontakteOperations implements WallOperations {
    private final RestTemplate restTemplate;

    public WallTemplate(RestTemplate restTemplate, String accessToken, ObjectMapper objectMapper, boolean isAuthorizedForUser) {
        super(isAuthorizedForUser, accessToken, objectMapper);
        this.restTemplate = restTemplate;
    }

    public String post(String message, Properties params) {
        requireAuthorization();
        Properties props = new Properties();
        props.putAll(params);
        props.put("message", message);
        URI uri = makeOperationURL("wall.post", props);

        PostStatusResponse status = restTemplate.getForObject(uri, PostStatusResponse.class);
        checkForError(status);

        return status.getStatus().getPostId();
    }

    @Override
    public String post(String message) {
        return this.post(message, (Properties)null);
    }

    @Override
    public String post(String message, String link) {
        Properties props = new Properties();
        props.put("attachments", link);
        return this.post(message, props);
    }

    @Override
    public List<Post> getPosts() {
        return getPosts(-1, -1);
    }

    @Override
    public List<Post> getPosts(int offset, int limit) {
        requireAuthorization();
        Properties props = new Properties();
        if (offset >= 0) {
            props.put("offset", offset);
        }
        if (limit > 0) {
            props.put("count", limit);
        }
        URI uri = makeOperationURL("wall.get", props);
        VKGenericResponse response = restTemplate.getForObject(uri, VKGenericResponse.class);
        checkForError(response);
        return deserializeArray(response, Post.class).getItems();
    }

    @Override
    public Post getPost(String userId, String postId) {
        requireAuthorization();
        Properties props = new Properties();
        props.put("posts", userId + "_" + postId);
        URI uri = makeOperationURL("wall.getById", props);
        VKGenericResponse response = restTemplate.getForObject(uri, VKGenericResponse.class);
        checkForError(response);
        try {
            return objectMapper.readValue(response.getResponse(), Post.class);
        } catch (IOException e) {
            throw new UncategorizedApiException("Error deserializing: " + response.getResponse(), e);
        }
    }
}
