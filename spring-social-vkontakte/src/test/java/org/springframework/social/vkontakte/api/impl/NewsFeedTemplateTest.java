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
import org.springframework.social.vkontakte.api.Group;
import org.springframework.social.vkontakte.api.NewsFeedResponse;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * {@link GroupsTemplate} test.
 * @author dIsoVi
 */
public class NewsFeedTemplateTest extends AbstractVKontakteApiTest {
    @Test
    public void searchNews() {
        mockServer.expect(requestTo("https://api.vk.com/method/newsfeed.search?access_token=ACCESS_TOKEN&v=5.27&extended=1&q=VK+API"))
            .andExpect(method(GET))
            .andRespond(withSuccess(jsonResource("newsfeed-5_27"), APPLICATION_JSON));
        NewsFeedSearchRequest request = new NewsFeedSearchRequest("VK API", true, null);
        NewsFeedResponse newsFeedResponse = vkontakte.newsFeedOperations().searchNews(request);
        assertEquals(3, newsFeedResponse.getNews().size());
        assertEquals(69171335, newsFeedResponse.getGroups().get(0).getGroupId());
        assertEquals("id271875268", newsFeedResponse.getProfiles().get(1).getScreenName());
    }
}
