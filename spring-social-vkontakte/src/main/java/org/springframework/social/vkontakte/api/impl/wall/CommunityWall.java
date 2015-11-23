package org.springframework.social.vkontakte.api.impl.wall;

/**
 * Model class representing community wall owner
 *
 * @see WallOwner
 * @see CommentsQuery#owner
 */
public final class CommunityWall extends WallOwner {
    public CommunityWall(final long communityId) {
        super(communityId);
    }

    @Override
    public String toString() {
        return "CommunityWall{" +
                "id='" + getId() + '\'' +
                '}';
    }

}
