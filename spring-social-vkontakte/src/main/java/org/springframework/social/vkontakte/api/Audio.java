/*
 * Copyright 2014 the original author or authors.
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
package org.springframework.social.vkontakte.api;

import java.io.Serializable;

/**
 * Model class representing audio.</br>
 * See definition of this object at http://vk.com/dev/audio_object
 *
 * @author vkolodrevskiy
 */
public class Audio implements Serializable {
    private static final long serialVersionUID = -3971749547784051391L;

    // Audio ID.
    // positive number
    private Integer id;

    // Audio owner ID.
    // positive number
    private Integer ownerId;

    // Artist name.
    private String artist;

    // Audio file title.
    private String title;

    // Duration (in seconds).
    // positive number
    private Integer duration;

    // Link to mp3.
    private String url;

    // ID of the lyrics (if available) of the audio file.
    private String lyricsId;

    // ID of the album containing the audio file (if assigned).
    // positive number
    private Integer albumId;

    // Genre ID. See the list of audio genres.
    // positive number
    private Integer genreId;

    public Integer getId() {
        return id;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public String getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }

    public Integer getDuration() {
        return duration;
    }

    public String getUrl() {
        return url;
    }

    public String getLyricsId() {
        return lyricsId;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public Integer getGenreId() {
        return genreId;
    }
}
