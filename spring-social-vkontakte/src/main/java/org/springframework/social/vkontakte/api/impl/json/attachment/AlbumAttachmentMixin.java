package org.springframework.social.vkontakte.api.impl.json.attachment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.social.vkontakte.api.attachment.Photo;
import org.springframework.social.vkontakte.api.impl.json.deserializers.UnixTimeDeserializer;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AlbumAttachmentMixin {
    @JsonProperty("id")
    private String albumId;
    @JsonProperty("thumb")
    private Photo thumb;
    @JsonProperty("owner_id")
    private long ownerId;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("created")
    @JsonDeserialize(using = UnixTimeDeserializer.class)
    private Date created;
    @JsonProperty("updated")
    @JsonDeserialize(using = UnixTimeDeserializer.class)
    private Date updated;
    @JsonProperty("size")
    private long size;
}
