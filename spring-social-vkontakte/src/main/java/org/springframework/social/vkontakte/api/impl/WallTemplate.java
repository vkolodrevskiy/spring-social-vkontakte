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
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.social.UncategorizedApiException;
import org.springframework.social.vkontakte.api.*;
import org.springframework.social.vkontakte.api.impl.wall.CommentsQuery;
import org.springframework.social.vkontakte.api.impl.wall.CommunityWall;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Properties;

/**
 * {@link org.springframework.social.vkontakte.api.IWallOperations} implementation.
 *
 * @author vkolodrevskiy
 */
public class WallTemplate extends AbstractVKontakteOperations implements IWallOperations {
    private final RestTemplate restTemplate;

    public WallTemplate(RestTemplate restTemplate, String accessToken, ObjectMapper objectMapper, boolean isAuthorizedForUser) {
        super(isAuthorizedForUser, accessToken, objectMapper);
        this.restTemplate = restTemplate;
    }

    public List<Post> getPosts() {
        return getPosts(-1, -1);
    }

    public List<Post> getPostsForUser(Long userId, int offset, int limit) {
        requireAuthorization();
        Properties props = new Properties();
        if (offset >= 0) {
            props.put("offset", offset);
        }
        if (limit > 0) {
            props.put("count", limit);
        }
        if (userId != null) {
            props.put("owner_id", userId);
        }
        // http://vk.com/dev/wall.get
        URI uri = makeOperationURL("wall.get", props, ApiVersion.VERSION_5_27);
        VKGenericResponse response = restTemplate.getForObject(uri, VKGenericResponse.class);
        checkForError(response);
        return deserializeVK50ItemsResponse(response, Post.class).getItems();
    }

    public List<Post> getPosts(int offset, int limit) {
        return getPostsForUser(null, offset, limit);
    }

    public Post getPost(Long userId, String postId) {
        requireAuthorization();
        Properties props = new Properties();
        props.put("posts", userId + "_" + postId);

        // http://vk.com/dev/wall.getById
        URI uri = makeOperationURL("wall.getById", props, ApiVersion.VERSION_5_27);
        VKGenericResponse response = restTemplate.getForObject(uri, VKGenericResponse.class);
        checkForError(response);
        try {
            return objectMapper.readValue(response.getResponse().get(0).toString(), Post.class);
        } catch (IOException e) {
            throw new UncategorizedApiException("vkontakte", "Error deserializing: " + response.getResponse(), e);
        }
    }

    public PostStatus post(PostData postData) {
        requireAuthorization();

        // http://vk.com/dev/wall.post
        URI uri = makeOperationURL("wall.post", postData.toProperties(), ApiVersion.VERSION_3_0);
        PostStatusResponse response = restTemplate.getForObject(uri, PostStatusResponse.class);
        checkForError(response);

        return response.getStatus();
    }

    @Override
    public CommentsResponse getComments(CommentsQuery query) {
        MultiValueMap<String, Object> data = new LinkedMultiValueMap<String, Object>();

        if (query.owner instanceof CommunityWall) {
            data.set("owner_id", "-" + String.valueOf(query.owner.getId()));
        } else {
            data.set("owner_id", String.valueOf(query.owner.getId()));
        }

        data.set("post_id", String.valueOf(query.postId));

        if (query.needLikes) {
            data.set("need_likes", "1");
        }

        if (query.startCommentId != null && query.startCommentId > 0) {
            data.set("start_comment_id", query.startCommentId.toString());
        }

        if (query.offset != null && query.offset > 0) {
            data.set("offset", query.offset.toString());
        }

        if (query.count != null && query.count > 0) {
            data.set("count", query.count.toString());
        }

        if (query.sort != null) {
            data.set("sort", query.sort.toString());
        }

        if (query.previewLength != null && query.previewLength > 0) {
            data.set("preview_length", query.previewLength.toString());
        } else {
            //Specify 0 as it does not want to truncate comments.
            data.set("preview_length", "0");
        }

        if (query.extended) {
            data.set("extended", "1");
        }

        URI uri = makeOperationPOST("wall.getComments", data, ApiVersion.VERSION_5_33);
        VKGenericResponse response = restTemplate.postForObject(uri, data, VKGenericResponse.class);
        checkForError(response);

        List<Comment> comments = deserializeVK50ItemsResponse(response, Comment.class).getItems();
        List<VKontakteProfile> profiles = null;
        List<Group> groups = null;
        if (query.extended) {
            profiles = deserializeItems((ArrayNode) response.getResponse().get("profiles"), VKontakteProfile.class);
            groups = deserializeItems((ArrayNode) response.getResponse().get("groups"), Group.class);
        }
        long count = response.getResponse().get("count").asLong();
        Long realOffset = null;
        if (query.startCommentId != null) {
            realOffset = response.getResponse().get("real_offset").asLong();
        }
        return new CommentsResponse(comments, count, realOffset, profiles, groups);
    }
}
