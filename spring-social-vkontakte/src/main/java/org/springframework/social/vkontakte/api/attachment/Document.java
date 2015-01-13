package org.springframework.social.vkontakte.api.attachment;

/**
 * TODO: add description
 *
 * @author vkolodrevskiy
 */
public class Document {
    private long documentId;
    private long ownerId;
    private String title;
    private String url;
    private String photo100;
    private String photo130;
    private long size;
    private String extension;

    public String getUrl() {
        return url;
    }

    public String getPhoto100() {
        return photo100;
    }

    public String getPhoto130() {
        return photo130;
    }

    public long getDocumentId() {
        return documentId;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public String getTitle() {
        return title;
    }

    public long getSize() {
        return size;
    }

    public String getExtension() {
        return extension;
    }
}
