package org.springframework.social.vkontakte.api.attachment;

/**
 * Attachment associated with a website link.
 */
public class LinkAttachment extends Attachment {
    private Link link;

    public Link getLink() {
        return link;
    }
}
