package org.springframework.social.vkontakte.api.impl.wall;

/**
 * Model class representing an abstract wall's owner
 *
 * @see UserWall
 * @see CommunityWall
 * @see CommentsQuery#owner
 */
public abstract class WallOwner {
    private final long id;

    protected WallOwner(final long ownerId) {
        this.id = ownerId;
    }

    public long getId() {
        return id;
    }
}
