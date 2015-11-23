package org.springframework.social.vkontakte.api.impl.wall;

/**
 * Model class representing user wall owner
 *
 * @see WallOwner
 * @see CommentsQuery#owner
 */
public final class UserWall extends WallOwner {
    public UserWall(final long userId) {
        super(userId);
    }

    @Override
    public String toString() {
        return "UserWall{" +
                "id='" + getId() + '\'' +
                '}';
    }
}

