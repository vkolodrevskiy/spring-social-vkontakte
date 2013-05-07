package org.springframework.social.vkontakte.api.impl.json;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

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
