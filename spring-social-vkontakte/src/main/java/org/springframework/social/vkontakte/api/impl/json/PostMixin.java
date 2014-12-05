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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.social.vkontakte.api.Post;
import org.springframework.social.vkontakte.api.attachment.Attachment;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Mixin for {@link org.springframework.social.vkontakte.api.Post}
 * @author vkolodrevskiy
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostMixin {
	@JsonCreator
	PostMixin(@JsonProperty("id") String postId, @JsonProperty("date") @JsonDeserialize(using = UnixTimeDeserializer.class) Date createdDate, @JsonProperty("text") String text) {}

    @JsonProperty("date")
    @JsonDeserialize(using = UnixTimeDeserializer.class)
    Date createdDate;

    @JsonProperty("id")
    String id;

    @JsonProperty("text")
    String text;

    @JsonProperty("likes")
    Post.Likes likes;

    @JsonProperty("reposts")
    Post.Reposts reposts;

    @JsonProperty("attachments")
    List<? extends Attachment> attachments;

    static class UnixTimeDeserializer  extends JsonDeserializer<Date> {
        @Override
        public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            Date result = null;
            long unixTime = jp.getLongValue();
            result = new Date(unixTime * 1000);
            return result;
        }
    }
}
