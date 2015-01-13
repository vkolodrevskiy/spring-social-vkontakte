package org.springframework.social.vkontakte.api.attachment;

/**
 * TODO: add description
 *
 * @author vkolodrevskiy
 */
public class Audio {
    private long id;
    private long ownerId;
    private String artist;
    private String title;
    private int duration;
    private String url;
    private long lyricsId;
    private long albumId;
    private long genreId;

    public String getArtist() {
        return artist;
    }

    public String getUrl() {
        return url;
    }

    public long getLyricsId() {
        return lyricsId;
    }

    public long getAlbumId() {
        return albumId;
    }

    public long getGenreId() {
        return genreId;
    }

    public long getId() {
        return id;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "AudioAttachment{" +
                "id='" + id + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", artist='" + artist + '\'' +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                '}';
    }
}
