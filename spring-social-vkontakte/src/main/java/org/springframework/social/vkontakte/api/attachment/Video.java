package org.springframework.social.vkontakte.api.attachment;

/**
 * TODO: add description
 *
 * @author vkolodrevskiy
 */
public class Video {
    private String videoId;
    private String ownerId;
    private String title;
    private int duration;

    public String getVideoId() {
        return videoId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }
}
