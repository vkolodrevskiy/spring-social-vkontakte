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
package org.springframework.social.vkontakte.api.impl.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.social.vkontakte.api.Post;
import org.springframework.social.vkontakte.api.attachment.Attachment;
import org.springframework.social.vkontakte.api.impl.json.deserializers.UnixTimeDeserializer;

import java.util.Date;
import java.util.List;

/**
 * Mixin for {@link org.springframework.social.vkontakte.api.Post}
 *
 * @author vkolodrevskiy
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostMixin {
    @JsonProperty("id")
    long id;

    @JsonProperty("from_id")
    long fromId;

    @JsonProperty("owner_id")
    long ownerId;

    @JsonProperty("date")
    @JsonDeserialize(using = UnixTimeDeserializer.class)
    Date date;

    @JsonProperty("text")
    String text;

    @JsonProperty("likes")
    Post.Likes likes;

    @JsonProperty("reposts")
    Post.Reposts reposts;

    @JsonProperty("comments")
    Post.Comments comments;

    @JsonProperty("attachments")
    List<? extends Attachment> attachments;

    @JsonProperty("copy_history")
    List<Post> copyHistory;
}
