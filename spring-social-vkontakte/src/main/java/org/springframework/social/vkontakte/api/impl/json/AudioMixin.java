package org.springframework.social.vkontakte.api.impl.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * {@link org.springframework.social.vkontakte.api.Audio} mixin.
 *
 * @author vkolodrevskiy
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AudioMixin {
    @JsonProperty("id")
    Integer id;

    @JsonProperty("owner_id")
    Integer ownerId;

    @JsonProperty("artis")
    String artis;

    @JsonProperty("title")
    String title;

    @JsonProperty("duration")
    Integer duration;

    @JsonProperty("url")
    String url;

    @JsonProperty("lyrics_id")
    String lyricsId;

    @JsonProperty("album_id")
    Integer albumId;

    @JsonProperty("genre_id")
    Integer genreId;
}
