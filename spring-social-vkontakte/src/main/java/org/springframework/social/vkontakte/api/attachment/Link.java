package org.springframework.social.vkontakte.api.attachment;

/**
 * TODO: add description
 *
 * @author vkolodrevskiy
 */
public class Link {
    private String url;
    private String previewUrl;
    private String title;
    private String description;
    private String imageSrc;

    public String getPreviewUrl() {
        return previewUrl;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    @Override
    public String toString() {
        return "LinkAttachment{" +
                "url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", imageSrc='" + imageSrc + '\'' +
                '}';
    }
}
