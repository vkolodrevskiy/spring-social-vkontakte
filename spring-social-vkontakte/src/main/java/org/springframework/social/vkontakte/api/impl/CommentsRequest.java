package org.springframework.social.vkontakte.api.impl;

import org.springframework.social.vkontakte.api.vkenums.SortOrder;

/**
 * https://vk.com/dev/wall.getComments request
 *
 * @author wiikviz
 */
public class CommentsRequest {
    public String ownerId;
    public Integer postId;
    public boolean needLikes = false;
    public Integer startCommentId;
    public Integer offset;
    public Integer count;
    public SortOrder sort;
    public Integer previewLength;
    public boolean extended = false;

    public CommentsRequest(String ownerId, Integer postId, boolean needLikes, Integer startCommentId, Integer offset, Integer count, SortOrder sort, Integer previewLength, boolean extended) {
        this.ownerId = ownerId;
        this.postId = postId;
        this.needLikes = needLikes;
        this.startCommentId = startCommentId;
        this.offset = offset;
        this.count = count;
        this.sort = sort;
        this.previewLength = previewLength;
        this.extended = extended;
    }
}
