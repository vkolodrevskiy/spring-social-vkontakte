package org.springframework.social.vkontakte.api.attachment;

import java.util.Date;

/**
 * TODO: add description
 *
 * @author vkolodrevskiy
 */
public class Video {
    private long videoId;
    private long ownerId;
    private String title;
    private String description;
    private String link;
    private String player;
    private String photo130;
    private String photo320;
    private String photo640;
    private int duration;
    private Date date;
    private long views;
    private long comments;

    public long getViews() {
        return views;
    }

    public String getLink() {
        return link;
    }

    public String getPlayer() {
        return player;
    }

    public String getPhoto130() {
        return photo130;
    }

    public String getPhoto320() {
        return photo320;
    }

    public String getPhoto640() {
        return photo640;
    }

    public Date getDate() {
        return date;
    }

    public long getComments() {
        return comments;
    }

    public long getVideoId() {
        return videoId;
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

    public int getDuration() {
        return duration;
    }
}
