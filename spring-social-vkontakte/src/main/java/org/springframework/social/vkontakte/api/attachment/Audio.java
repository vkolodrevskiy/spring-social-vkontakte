package org.springframework.social.vkontakte.api.attachment;

/**
 * TODO: add description
 *
 * @author vkolodrevskiy
 */
public class Audio {
    private String id;
    private String ownerId;
    private String performer;
    private String title;
    private int duration;

    public String getId() {
        return id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getPerformer() {
        return performer;
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
                ", performer='" + performer + '\'' +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                '}';
    }
}
