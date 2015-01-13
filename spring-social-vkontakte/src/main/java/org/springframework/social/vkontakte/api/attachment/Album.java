package org.springframework.social.vkontakte.api.attachment;

import java.util.Date;

public class Album {
    private long albumId;
    private Photo thumb;
    private long ownerId;
    private String title;
    private String description;
    private Date created;
    private Date updated;
    private long size;

    public long getAlbumId() {
        return albumId;
    }

    public Photo getThumb() {
        return thumb;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
    }

    public long getSize() {
        return size;
    }
}
