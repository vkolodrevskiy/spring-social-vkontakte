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
    long id;

    @JsonProperty("owner_id")
    long ownerId;

    @JsonProperty("artist")
    String artist;

    @JsonProperty("title")
    String title;

    @JsonProperty("duration")
    long duration;

    @JsonProperty("url")
    String url;

    @JsonProperty("lyrics_id")
    long lyricsId;

    @JsonProperty("album_id")
    long albumId;

    @JsonProperty("genre_id")
    Integer genreId;
}
