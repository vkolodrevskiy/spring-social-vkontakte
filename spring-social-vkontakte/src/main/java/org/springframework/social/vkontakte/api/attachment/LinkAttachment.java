package org.springframework.social.vkontakte.api.attachment;

/**
 * Attachment associated with a website link.
 */
public class LinkAttachment extends Attachment {

    private String url;
    private String title;
    private String description;
    private String image;

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "LinkAttachment{" +
                "url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
