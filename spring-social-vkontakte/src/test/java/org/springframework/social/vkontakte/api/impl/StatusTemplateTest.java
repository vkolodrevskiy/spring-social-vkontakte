package org.springframework.social.vkontakte.api.impl;

import org.junit.Test;
import org.springframework.social.MissingAuthorizationException;

/**
 * {@link StatusTemplate} test.
 *
 * @author vkolodrevskiy
 */
public class StatusTemplateTest extends AbstractVKontakteApiTest {
    @Test(expected = MissingAuthorizationException.class)
    public void get_currentUser_unauthorized() {
        unauthorizedVKontakte.friendsOperations().get();
    }
}
