/*
 * Copyright 2011 the original author or authors.
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
import org.springframework.social.vkontakte.api.VKontakteErrorException;

import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.social.test.client.RequestMatchers.method;
import static org.springframework.social.test.client.RequestMatchers.requestTo;
import static org.springframework.social.test.client.ResponseCreators.withResponse;

/**
 * {@link WallTemplate} test.
 * @author vkolodrevskiy
 */
public class WallTemplateTest extends AbstractVKontakteApiTest {
    @Test
    public void post() {
        mockServer.expect(requestTo("https://api.vkontakte.ru/method/wall.post?access_token=ACCESS_TOKEN&message=hello"))
                .andExpect(method(GET))
                .andRespond(withResponse(jsonResource("wall-post-response"), responseHeaders));
        String postId = vkontakte.wallOperations().post("hello");
        assertEquals("511", postId);
    }

    @Test(expected = VKontakteErrorException.class)
    public void post_expiredToken() {
		mockServer.expect(requestTo("https://api.vkontakte.ru/method/wall.post?access_token=ACCESS_TOKEN&message=hello"))
			.andExpect(method(GET))
			.andRespond(withResponse(jsonResource("error-code-5"), responseHeaders));

		vkontakte.wallOperations().post("hello");
    }

    @Test(expected = MissingAuthorizationException.class)
    public void post_unauthorized() {
        unauthorizedVKontakte.wallOperations().post("hello");
    }
}
