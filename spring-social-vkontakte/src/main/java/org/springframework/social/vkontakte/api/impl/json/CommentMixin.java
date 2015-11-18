package org.springframework.social.vkontakte.api.impl.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.social.vkontakte.api.impl.json.deserializers.UnixTimeDeserializer;

import java.util.Date;

/**
 * Mixin for {@link org.springframework.social.vkontakte.api.Comment}
 *
 * @author wiikviz
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentMixin {
    @JsonProperty("from_id")
    long fromId;

    @JsonProperty("date")
    @JsonDeserialize(using = UnixTimeDeserializer.class)
    Date date;

    @JsonProperty("reply_to_user")
    long replyToUser;

    @JsonProperty("reply_to_comment")
    long replyToComment;
}
