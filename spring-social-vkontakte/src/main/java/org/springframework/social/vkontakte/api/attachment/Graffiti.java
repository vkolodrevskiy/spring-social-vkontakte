package org.springframework.social.vkontakte.api.attachment;

/**
 * TODO: add description
 *
 * @author vkolodrevskiy
 */
public class Graffiti {
    private String graffitiId;
    private String ownerId;
    private String src;
    private String srcBig;

    public String getGraffitiId() {
        return graffitiId;
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
        return "GraffitiAttachment{" +
                "graffitiId='" + graffitiId + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", src='" + src + '\'' +
                ", srcBig='" + srcBig + '\'' +
                '}';
    }
}
