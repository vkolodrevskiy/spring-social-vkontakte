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

import org.springframework.social.support.URIBuilder;
import org.springframework.social.vkontakte.api.Posts;
import org.springframework.social.vkontakte.api.WallOperations;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Properties;

/**
 * {@link org.springframework.social.vkontakte.api.WallOperations} implementation.
 * @author vkolodrevskiy
 */
public class WallTemplate extends AbstractVKontakteOperations implements WallOperations {
    private final RestTemplate restTemplate;

    public WallTemplate(RestTemplate restTemplate, String accessToken, boolean isAuthorizedForUser) {
        super(isAuthorizedForUser, accessToken);
        this.restTemplate = restTemplate;
    }

    @Override
    public String post(String message) {
        requireAuthorization();
        Properties props = new Properties();
        props.put("message", message);
        URI uri = URIBuilder.fromUri(makeOperationURL("wall.post", props)).build();

        Posts posts = restTemplate.getForObject(uri, Posts.class);
        checkForError(posts);

        return posts.getPost().getPostId();
    }
}
