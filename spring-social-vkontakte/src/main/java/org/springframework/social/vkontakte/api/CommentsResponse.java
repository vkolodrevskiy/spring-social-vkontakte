package org.springframework.social.vkontakte.api;

import org.springframework.social.vkontakte.api.impl.wall.CommentsQuery;

import java.util.List;

/**
 * Model class representing a response that contains a list of comments on a post on a user wall or community wall.
 *
 * @see IWallOperations#getComments(CommentsQuery)
 */
public class CommentsResponse {
    private final long count;
    private final List<Comment> comments;
    private final List<VKontakteProfile> profiles;
    private final List<Group> groups;
    private final Long realOffset;

    public CommentsResponse(List<Comment> comments, long count, Long realOffset, List<VKontakteProfile> profiles, List<Group> groups) {
        this.comments = comments;
        this.count = count;
        this.realOffset = realOffset;
        this.groups = groups;
        this.profiles = profiles;
    }

    public long getCount() {
        return count;
    }

    public long getRealOffset() {
        return realOffset;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public List<VKontakteProfile> getProfiles() {
        return profiles;
    }
}
