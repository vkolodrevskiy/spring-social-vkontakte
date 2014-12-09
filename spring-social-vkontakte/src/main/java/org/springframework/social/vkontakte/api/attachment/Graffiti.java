package org.springframework.social.vkontakte.api.attachment;

/**
 * TODO: add description
 *
 * @author vkolodrevskiy
 */
public class Graffiti {
    private long graffitiId;
    private long ownerId;
    private String photo200;
    private String photo586;

    public long getGraffitiId() {
        return graffitiId;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public String getPhoto200() {
        return photo200;
    }

    public String getPhoto586() {
        return photo586;
    }

    @Override
    public String toString() {
        return "GraffitiAttachment{" +
                "graffitiId='" + graffitiId + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", photo200='" + photo200 + '\'' +
                ", photo586='" + photo586 + '\'' +
                '}';
    }
}
