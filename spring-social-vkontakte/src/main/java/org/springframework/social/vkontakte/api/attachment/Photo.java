package org.springframework.social.vkontakte.api.attachment;

import java.util.Date;

/**
 * TODO: add description
 *
 * @author vkolodrevskiy
 */
public class Photo {
    private long photoId;
    private long ownerId;
    private long albumId;
    private String photo75;
    private String photo130;
    private String photo604;
    private String photo807;
    private String text;
    private long width;
    private long height;
    private Date date;

    public long getPhotoId() {
        return photoId;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public long getAlbumId() {
        return albumId;
    }

    public String getPhoto75() {
        return photo75;
    }

    public String getPhoto130() {
        return photo130;
    }

    public String getPhoto604() {
        return photo604;
    }

    public String getPhoto807() {
        return photo807;
    }

    public String getText() {
        return text;
    }

    public long getWidth() {
        return width;
    }

    public long getHeight() {
        return height;
    }

    public Date getDate() {
        return date;
    }
}
