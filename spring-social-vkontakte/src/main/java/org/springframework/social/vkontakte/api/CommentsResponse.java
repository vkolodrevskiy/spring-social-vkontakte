package org.springframework.social.vkontakte.api;

import java.util.List;

/**
 * https://vk.com/dev/wall.getComments response
 *
 * @author wiikviz
 */
public class CommentsResponse {
    private long count;
    private List<Comment> comments;
    private List<VKontakteProfile> profiles;
    private List<Group> groups;
    private Long realOffset;

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
