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
    private String src75;
    private String src130;
    private String src604;
    private String src807;
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

    public String getSrc75() {
        return src75;
    }

    public String getSrc130() {
        return src130;
    }

    public String getSrc604() {
        return src604;
    }

    public String getSrc807() {
        return src807;
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
