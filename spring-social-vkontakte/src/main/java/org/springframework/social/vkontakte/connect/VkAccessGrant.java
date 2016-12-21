package org.springframework.social.vkontakte.connect;

import org.springframework.social.oauth2.AccessGrant;

/**
 * Override {@link AccessGrant} to pass user id.
 *
 * @author vkolodrevskiy
 */
public class VkAccessGrant extends AccessGrant {
    private final Integer userId;

    public VkAccessGrant(String accessToken) {
        this(accessToken, null, null, null, null);
    }

    public VkAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn, Integer userId) {
        super(accessToken, scope, refreshToken, expiresIn);
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }
}
