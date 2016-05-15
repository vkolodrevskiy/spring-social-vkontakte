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
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.social.vkontakte.api.attachment.*;

import java.io.IOException;

/**
 * We use non-standard TypeDeserializer to deal with VK "duplicated" type info, eg:
 * [ type: link, link:  Link Object ]
 */
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
@JsonSubTypes({
    @JsonSubTypes.Type(name="link", value=LinkAttachment.class),
    @JsonSubTypes.Type(name="photo", value=PhotoAttachment.class),
    @JsonSubTypes.Type(name="posted_photo", value=PostedPhotoAttachment.class),
    @JsonSubTypes.Type(name="photos_list", value=PhotosListAttachment.class),
    @JsonSubTypes.Type(name="video", value=VideoAttachment.class),
    @JsonSubTypes.Type(name="audio", value=AudioAttachment.class),
    @JsonSubTypes.Type(name="graffiti", value=GraffitiAttachment.class),
    @JsonSubTypes.Type(name="sticker", value=StickerAttachment.class),
    @JsonSubTypes.Type(name="doc", value=DocumentAttachment.class),
    @JsonSubTypes.Type(name="note", value=NoteAttachment.class),
    @JsonSubTypes.Type(name="app", value=ApplicationAttachment.class),
    @JsonSubTypes.Type(name="poll", value=PollAttachment.class),
    @JsonSubTypes.Type(name="page", value=PageAttachment.class),
    @JsonSubTypes.Type(name="album", value=AlbumAttachment.class)
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class AttachmentMixin {

    @JsonProperty("type")
   	@JsonDeserialize(using = AttachmentTypeDeserializer.class)
    AttachmentType type;

    private static class AttachmentTypeDeserializer extends JsonDeserializer<AttachmentType> {
   		@Override
   		public AttachmentType deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
   			return AttachmentType.valueOf(jp.getText().toUpperCase());
   		}
   	}
}
