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
package org.springframework.social.vkontakte.api.impl.json.attachment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.social.vkontakte.api.impl.json.deserializers.UnixTimeDeserializer;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NoteAttachmentMixin {

    @JsonProperty("id")
    private String noteId;

    @JsonProperty("user_id")
    private String ownerId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("text")
    private String text;

    @JsonProperty("view_url")
    private String url;

    @JsonProperty("comments")
    private int comments;

    @JsonProperty("date")
    @JsonDeserialize(using = UnixTimeDeserializer.class)
    private Date date;
}
