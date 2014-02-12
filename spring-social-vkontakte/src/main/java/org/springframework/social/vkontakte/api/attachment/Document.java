package org.springframework.social.vkontakte.api.attachment;

/**
 * TODO: add description
 *
 * @author vkolodrevskiy
 */
public class Document {
    private String documentId;
    private String ownerId;
    private String title;
    private long size;
    private String extension;

    public String getDocumentId() {
        return documentId;
    }

    public String getOwnerId() {
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
