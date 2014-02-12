package org.springframework.social.vkontakte.api.attachment;

/**
 * TODO: add description
 *
 * @author vkolodrevskiy
 */
public class Photo {
    private String photoId;
    private String ownerId;
    private String src;
    private String srcBig;

    public String getPhotoId() {
        return photoId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getSrc() {
        return src;
    }

    public String getSrcBig() {
        return srcBig;
    }

    @Override
    public String toString() {
        return "PhotoAttachment{" +
                "photoId='" + photoId + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", src='" + src + '\'' +
                ", srcBig='" + srcBig + '\'' +
                '}';
    }
}
