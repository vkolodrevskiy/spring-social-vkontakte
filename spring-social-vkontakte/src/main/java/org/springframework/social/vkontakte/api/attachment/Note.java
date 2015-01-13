package org.springframework.social.vkontakte.api.attachment;

import java.util.Date;

/**
 * TODO: add description
 *
 * @author vkolodrevskiy
 */
public class Note {
    private long noteId;
    private long ownerId;
    private String title;
    private String text;
    private String url;
    private Date date;
    private int comments;

    public Date getDate() {
        return date;
    }

    public String getUrl() {
        return url;
    }

    public String getText() {
        return text;
    }

    public long getNoteId() {
        return noteId;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public String getTitle() {
        return title;
    }

    public int getComments() {
        return comments;
    }
}
