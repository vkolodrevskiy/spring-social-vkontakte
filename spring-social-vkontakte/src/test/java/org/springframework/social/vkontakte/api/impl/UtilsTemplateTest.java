/*
 * Copyright 2011-2014 the original author or authors.
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
import org.springframework.social.vkontakte.api.City;
import org.springframework.social.vkontakte.api.VKObject;
import org.springframework.social.vkontakte.api.VKontakteErrorException;

import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * {@link LocationTemplate} test.
 * @author badbob
 */
public class UtilsTemplateTest extends AbstractVKontakteApiTest {
    @Test
    public void resolveScreenName() {
        mockServer.expect(requestTo("https://api.vk.com/method/utils.resolveScreenName?access_token=&v=5.27&screen_name=durov"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("utils-resolve-screen-name-5.27"), APPLICATION_JSON));
        VKObject vkObject = vkontakte.utilsOperations().resolveScreenName("durov");
        assertEquals("user", vkObject.getType());
        assertEquals(1, vkObject.getId());
    }

    @Test
    public void resolveScreenNameEmpty() {
        mockServer.expect(requestTo("https://api.vk.com/method/utils.resolveScreenName?access_token=&v=5.27&screen_name=durov"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("utils-resolve-screen-name-empty-5.27"), APPLICATION_JSON));
        VKObject vkObject = vkontakte.utilsOperations().resolveScreenName("durov");
        assertEquals(null, vkObject);
    }
}
