/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.vkontakte.api.impl;

import org.junit.Test;
import org.springframework.social.MissingAuthorizationException;

/**
 * {@link FeedTemplate} test.
 *
 * @author vkolodrevskiy
 */
public class FeedTemplateTest extends AbstractVKontakteApiTest {
    @Test(expected = MissingAuthorizationException.class)
    public void getFeed1__unauthorized() {
        unauthorizedVKontakte.feedOperations().getFeed();
    }

    @Test(expected = MissingAuthorizationException.class)
    public void getFeed2__unauthorized() {
        unauthorizedVKontakte.feedOperations().getFeed(1, 2);
    }

    @Test(expected = MissingAuthorizationException.class)
    public void getFeed3__unauthorized() {
        unauthorizedVKontakte.feedOperations().getFeed("123");
    }

    @Test(expected = MissingAuthorizationException.class)
    public void getFeed4__unauthorized() {
        unauthorizedVKontakte.feedOperations().getFeed("123", 1, 2);
    }

    @Test(expected = MissingAuthorizationException.class)
    public void searchUserFeed1__unauthorized() {
        unauthorizedVKontakte.feedOperations().searchUserFeed("123");
    }

    @Test(expected = MissingAuthorizationException.class)
    public void searchUserFeed2__unauthorized() {
        unauthorizedVKontakte.feedOperations().searchUserFeed("123", 1, 2);
    }
}
