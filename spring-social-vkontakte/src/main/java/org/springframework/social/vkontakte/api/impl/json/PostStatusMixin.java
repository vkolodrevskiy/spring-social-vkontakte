package org.springframework.social.vkontakte.api.impl.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Mixin for {@link org.springframework.social.vkontakte.api.PostStatus}
 * @author Ivan Sharamet
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostStatusMixin {

    @JsonCreator
    PostStatusMixin(@JsonProperty("post_id") String postId, @JsonProperty("processing") boolean processing) {}

    @JsonProperty("post_id")
    String postId;

    @JsonProperty("processing")
    boolean processing;
}
