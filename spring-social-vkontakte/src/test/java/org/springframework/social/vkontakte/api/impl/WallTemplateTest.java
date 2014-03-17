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
import org.springframework.social.vkontakte.api.Post;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * {@link WallTemplate} test.
 * @author vkolodrevskiy
 */
public class WallTemplateTest extends AbstractVKontakteApiTest {
    // TODO: add more tests for posts
    @Test
    public void getPosts() {
        mockServer.expect(requestTo("https://api.vk.com/method/wall.get?access_token=ACCESS_TOKEN&v=3.0"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("wall-getposts-response-3_0"), APPLICATION_JSON));
        List<Post> posts = vkontakte.wallOperations().getPosts();
        assertEquals(20, posts.size());
        assertEquals("1627", posts.get(0).getId());
        assertEquals(3, posts.get(0).getAttachments().size());
    }
}
