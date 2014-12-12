package org.springframework.social.vkontakte.api.impl.json.attachment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.social.vkontakte.api.attachment.PhotosListItem;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PhotosListAttachmentMixin {
    @JsonProperty("photos_list")
    private List<PhotosListItem> photosList;
}
