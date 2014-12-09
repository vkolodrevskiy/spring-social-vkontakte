package org.springframework.social.vkontakte.api.attachment;

import java.util.Date;

/**
 * TODO: add description
 *
 * @author vkolodrevskiy
 */
public class Page {
    private long pageId;
    private long groupId;
    private long creatorId;
    private String title;
    private Date created;
    private Date edited;
    private long views;
    private String url;

    public long getPageId() {
        return pageId;
    }

    public long getGroupId() {
        return groupId;
    }

    public long getCreatorId() {
        return creatorId;
    }

    public String getTitle() {
        return title;
    }

    public Date getCreated() {
        return created;
    }

    public Date getEdited() {
        return edited;
    }

    public long getViews() {
        return views;
    }

    public String getUrl() {
        return url;
    }
}
