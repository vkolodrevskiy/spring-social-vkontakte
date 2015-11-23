package org.springframework.social.vkontakte.api.impl.wall;

import org.springframework.social.vkontakte.api.IWallOperations;
import org.springframework.social.vkontakte.api.vkenums.SortOrder;

/**
 * Model class representing query for the list of comments on a post on a user wall or community wall.
 *
 * @author wiikviz
 * @see IWallOperations#getComments(CommentsQuery)
 * @see <a href="https://vk.com/dev/wall.getComments">wall.getComments | Developers | VK</a>
 */
public final class CommentsQuery {
    public final WallOwner owner;
    public final int postId;
    public final boolean needLikes;
    public final Integer startCommentId;
    public final Integer offset;
    public final Integer count;
    public final SortOrder sort;
    public final Integer previewLength;
    public final boolean extended;

    public CommentsQuery(WallOwner owner, int postId, boolean needLikes, Integer startCommentId, Integer offset, Integer count, SortOrder sort, Integer previewLength, boolean extended) {
        this.owner = owner;
        this.postId = postId;
        this.needLikes = needLikes;
        this.startCommentId = startCommentId;
        this.offset = offset;
        this.count = count;
        this.sort = sort;
        this.previewLength = previewLength;
        this.extended = extended;
    }

    @Override
    public String toString() {
        return "CommentsQuery{" +
                "owner='" + owner + '\'' +
                ", postId=" + postId +
                '}';
    }

    public static class Builder {
        private WallOwner owner;
        private Integer postId;
        private boolean needLikes;
        private Integer startCommentId;
        private Integer offset;
        private Integer count;
        private SortOrder sort;
        private Integer previewLength;
        private boolean extended;

        /**
         * @param owner  {@link UserWall} or {@link UserWall}
         * @param postId post ID.
         */
        public Builder(WallOwner owner, int postId) {
            this.owner = owner;
            this.postId = postId;
        }

        public Builder needLikes(boolean needLikes) {
            this.needLikes = needLikes;
            return this;
        }

        public Builder startCommentId(int startCommentId) {
            this.startCommentId = startCommentId;
            return this;
        }

        public Builder offset(int offset) {
            this.offset = offset;
            return this;
        }

        public Builder count(int count) {
            this.count = count;
            return this;
        }

        public Builder sort(SortOrder sort) {
            this.sort = sort;
            return this;
        }

        /**
         * @param previewLength number of characters at which to truncate comments when previewed.
         *                      If {@code null} or {@code 0} comments are not truncated.
         * @return {@link org.springframework.social.vkontakte.api.impl.wall.CommentsQuery.Builder}
         */
        public Builder previewLength(int previewLength) {
            this.previewLength = previewLength;
            return this;
        }

        public Builder extended(boolean extended) {
            this.extended = extended;
            return this;
        }

        public CommentsQuery build() {
            return new CommentsQuery(owner, postId, needLikes, startCommentId, offset, count, sort, previewLength, extended);
        }
    }
}
